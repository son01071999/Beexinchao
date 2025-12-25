package com.example.beexinchao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "url_m")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UrlMEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "url", columnDefinition = "LONGTEXT")
	private String url;

	@Column(name = "screen_cd")
	private Integer screenCd;

	@Column(name = "screen_nm")
	private String screeNm;
}
