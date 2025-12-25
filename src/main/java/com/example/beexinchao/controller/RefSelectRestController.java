package com.example.beexinchao.controller;

import com.example.beexinchao.request.RefLoadFormDto;
import com.example.beexinchao.request.SearchContactRequest;
import com.example.beexinchao.response.LoadOutDto;
import com.example.beexinchao.response.SearchContactResponse;
import com.example.beexinchao.service.ContactSelectionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase/event_entry/event_info/ajax")
public class RefSelectRestController {
	private final ContactSelectionService service;

	@PostMapping("/ref_select_load")
	public ResponseEntity<LoadOutDto> initDataForContactSelectionScreen(
			@RequestBody RefLoadFormDto request) {
		return ResponseEntity.ok().body(service.initDataForContactSelectionScreen(request));
	}

	@PostMapping("/search_contact")
	public ResponseEntity<List<SearchContactResponse>> searchContact(
			@RequestBody SearchContactRequest request) {
		return ResponseEntity.ok().body(service.searchContact(request));
	}
}
