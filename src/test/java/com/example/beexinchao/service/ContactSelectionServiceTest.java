package com.example.beexinchao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.beexinchao.redis.ContactSearchCacheKey;
import com.example.beexinchao.request.RefLoadFormDto;
import com.example.beexinchao.request.SearchContactRequest;
import com.example.beexinchao.response.LoadOutDto;
import com.example.beexinchao.response.SearchContactResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class ContactSelectionServiceTest {
	@Autowired private ContactSelectionService contactSelectionService;

	@Autowired private RedisTemplate<String, Object> redisTemplate;

	@Test
	void initDataForContactSelectionScreen_happyCase() {

		// given
		RefLoadFormDto request = RefLoadFormDto.builder().kindRef(1).build();

		// when
		LoadOutDto result = contactSelectionService.initDataForContactSelectionScreen(request);

		// then
		assertNotNull(result);
		assertNotNull(result.getRefSelectHTML());
		assertFalse(result.getRgonInfoDtos().isEmpty());
		assertEquals(1, result.getPreviousScreen());
	}

	@Test
	void searchContact_noParams_returnEmpty() {

		// given
		SearchContactRequest request = new SearchContactRequest();

		// when
		List<SearchContactResponse> result = contactSelectionService.searchContact(request);

		// then
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test
	void searchContact_allParams_cacheMiss() {

		// given
		SearchContactRequest request = new SearchContactRequest();
		request.setContactNm("Ur");
		request.setContactNmKn("Ur");
		request.setRegionCds(List.of(3L));

		String cacheKey = ContactSearchCacheKey.buildCacheKey(request);
		// clear cache
		redisTemplate.delete(cacheKey);

		// when
		List<SearchContactResponse> result = contactSelectionService.searchContact(request);

		// then
		assertNotNull(result);
		assertFalse(result.isEmpty());

		// verify cache created
		Object cached = redisTemplate.opsForValue().get(cacheKey);
		assertNotNull(cached);
	}

	@Test
	void searchContact_atLeastOneParam_cacheHit() {

		// given
		SearchContactRequest request = new SearchContactRequest();
		request.setContactNm("Ur");

		String cacheKey = ContactSearchCacheKey.buildCacheKey(request);
		// clear cache
		redisTemplate.delete(cacheKey);

		// warm up cache
		List<SearchContactResponse> first = contactSelectionService.searchContact(request);

		assertFalse(first.isEmpty());

		// when (2nd call)
		List<SearchContactResponse> second = contactSelectionService.searchContact(request);

		// then
		assertEquals(first.size(), second.size());
	}
}
