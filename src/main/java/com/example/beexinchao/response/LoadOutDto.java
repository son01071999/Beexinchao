package com.example.beexinchao.response;

import com.example.beexinchao.base.CommonResultDto;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoadOutDto extends CommonResultDto {
	private List<RgonInfoDto> rgonInfoDtos;
	private String refSelectHTML;
	private int previousScreen;
}
