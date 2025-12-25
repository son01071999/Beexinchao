package com.example.beexinchao.service;

import com.example.beexinchao.entity.ContactMEntity;
import com.example.beexinchao.entity.PrefectureMEntity;
import com.example.beexinchao.entity.RgonMEntity;
import com.example.beexinchao.exception.runtime.ContactNotFoundException;
import com.example.beexinchao.exception.runtime.RegionNotFoundException;
import com.example.beexinchao.redis.ContactSearchCacheKey;
import com.example.beexinchao.repo.ContactMRepo;
import com.example.beexinchao.repo.RgonMRepo;
import com.example.beexinchao.repo.UrlMRepo;
import com.example.beexinchao.request.RefLoadFormDto;
import com.example.beexinchao.request.SearchContactRequest;
import com.example.beexinchao.response.LoadOutDto;
import com.example.beexinchao.response.RgonInfoDto;
import com.example.beexinchao.response.SearchContactResponse;
import com.example.beexinchao.response.UrlInfoDto;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContactSelectionService {
	private final RgonMRepo rgonMRepo;
	private final UrlMRepo urlMRepo;
	private final ContactMRepo contactMRepo;
	private final RedisTemplate<String, Object> redisTemplate;
	private final int SCREEN_CD = 1001; // mock data as 1001 in DB
	private static final Duration CACHE_TTL = Duration.ofMinutes(5);

	@Transactional(readOnly = true)
	public LoadOutDto initDataForContactSelectionScreen(RefLoadFormDto request) {
		// get all region with delete flag = 0 and region use type = 1
		List<RgonInfoDto> rgonInfoDtos = rgonMRepo.findActiveCoreRegions();
		if (rgonInfoDtos.isEmpty()) {
			throw new RegionNotFoundException();
		}
		// get url (html template) by screen Id
		UrlInfoDto url = urlMRepo.findByScreenCd(this.SCREEN_CD);

		return LoadOutDto.builder()
				.rgonInfoDtos(rgonInfoDtos)
				.refSelectHTML(url.getUrl())
				.previousScreen(request.getKindRef())
				.build();
	}

	@Transactional(readOnly = true)
	public List<SearchContactResponse> searchContact(SearchContactRequest request) {
		if ((request.getContactNm() == null || request.getContactNm().isEmpty())
				&& (request.getContactNmKn() == null || request.getContactNmKn().isEmpty())
				&& (request.getRegionCds() == null || request.getRegionCds().isEmpty())) {
			// No condition -> return empty list
			return List.of();
		}

		String cacheKey = ContactSearchCacheKey.buildCacheKey(request);
		Object cached = redisTemplate.opsForValue().get(cacheKey);
		if (cached != null) {
			return (List<SearchContactResponse>) cached;
		}

		List<ContactMEntity> contacts = contactMRepo.findAll(this.buildSearchCondition(request));
		if (contacts.isEmpty()) {
			throw new ContactNotFoundException();
		}
		List<SearchContactResponse> result =
				contacts.stream()
						.map(
								c ->
										SearchContactResponse.builder()
												.contactCd(c.getRefCd())
												.contactNm(c.getRefNm())
												.contactNmKn(c.getRefKn())
												.prefectureNm(c.getPrefecture().getPrefectureNm())
												.regionNm(c.getPrefecture().getRegion().getRgonNm())
												.build())
						.toList();
		// save data to cache
		redisTemplate.opsForValue().set(cacheKey, result, CACHE_TTL);

		return result;
	}

	private Specification<ContactMEntity> buildSearchCondition(SearchContactRequest request) {
		return (root, query, criteriaBuilder) -> {
			// Join
			Join<ContactMEntity, PrefectureMEntity> prefecture = root.join("prefecture", JoinType.INNER);

			Join<PrefectureMEntity, RgonMEntity> region = prefecture.join("region", JoinType.INNER);

			List<Predicate> predicates = new ArrayList<>();

			// regionCd : checkbox => search IN
			if (request.getRegionCds() != null && !request.getRegionCds().isEmpty()) {
				predicates.add(region.get("rgonCd").in(request.getRegionCds()));
			}

			// ContactNm and ContactNmKn => LIKE prefix
			if (request.getContactNm() != null && !request.getContactNm().isBlank()) {
				predicates.add(criteriaBuilder.like(root.get("refNm"), request.getContactNm() + "%"));
			}
			if (request.getContactNmKn() != null && !request.getContactNmKn().isBlank()) {
				predicates.add(criteriaBuilder.like(root.get("refKn"), request.getContactNmKn() + "%"));
			}

			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
