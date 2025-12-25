package com.example.beexinchao.request;

import com.example.beexinchao.base.CommonScreenInDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RefLoadFormDto extends CommonScreenInDto {
	@JsonProperty("kind_ref")
	private int kindRef;
}
