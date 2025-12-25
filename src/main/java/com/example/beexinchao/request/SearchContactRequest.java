package com.example.beexinchao.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class SearchContactRequest {
	@JsonProperty("region_cds")
	private List<Long> regionCds;

	@JsonProperty("contact_nm")
	private String contactNm;

	@JsonProperty("contact_nm_kn")
	private String contactNmKn;
}
