package com.example.beexinchao.redis;

import com.example.beexinchao.request.SearchContactRequest;
import java.util.stream.Collectors;

public class ContactSearchCacheKey {
	private ContactSearchCacheKey() {}

	public static String buildCacheKey(SearchContactRequest req) {

		String contactNm = normalize(req.getContactNm());
		String contactNmKn = normalize(req.getContactNmKn());

		String regions = "";
		if (req.getRegionCds() != null && !req.getRegionCds().isEmpty()) {
			regions =
					req.getRegionCds().stream()
							.sorted()
							.map(String::valueOf)
							.collect(Collectors.joining(","));
		}

		return String.format("CONTACT_SEARCH:%s:%s:%s", contactNm, contactNmKn, regions);
	}

	private static String normalize(String value) {
		if (value == null) {
			return "_";
		}
		return value.trim().isEmpty() ? "_" : value.trim().toLowerCase();
	}
}
