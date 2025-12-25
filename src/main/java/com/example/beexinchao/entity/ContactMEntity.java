package com.example.beexinchao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
		name = "contact_m",
		indexes = {
			@Index(name = "index_contact_m_1", columnList = "ref_nm"),
			@Index(name = "index_contact_m_2", columnList = "ref_kn"),
			@Index(name = "index_contact_m_3", columnList = "prefecture_cd")
		})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactMEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ref_cd")
	private Long refCd;

	@Column(name = "ref_nm", nullable = false)
	private String refNm;

	@Column(name = "ref_kn", nullable = false)
	private String refKn;

	@Column(name = "tel_no", nullable = false, length = 45)
	private String telNo;

	//  Many Contacts → One Prefecture
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prefecture_cd", nullable = false)
	private PrefectureMEntity prefecture;
}
