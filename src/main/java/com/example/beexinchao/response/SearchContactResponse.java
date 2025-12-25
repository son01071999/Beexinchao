package com.example.beexinchao.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchContactResponse {
	private Long contactCd; // ref_cd in DB
	private String contactNm; // ref_nm in DB
	private String contactNmKn; // ref_kn in DB
	private String prefectureNm; // prefecture_nm in DB
	private String regionNm; // rgon_nm in DB
}
