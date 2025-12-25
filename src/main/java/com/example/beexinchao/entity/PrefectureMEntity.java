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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
		name = "prefecture_m",
		indexes = {@Index(name = "index_prefecture_m_1", columnList = "rgon_cd")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrefectureMEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prefecture_cd")
	private Long prefectureCd;

	@Column(name = "prefecture_nm", nullable = false)
	private String prefectureNm;

	// Many Prefectures → One Region
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rgon_cd", nullable = false)
	private RgonMEntity region;

	//  One Prefecture → Many Contacts
	@OneToMany(mappedBy = "prefecture", fetch = FetchType.LAZY)
	private List<ContactMEntity> contacts;
}
