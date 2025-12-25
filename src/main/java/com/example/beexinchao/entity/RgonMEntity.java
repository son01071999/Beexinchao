package com.example.beexinchao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
		name = "rgon_m",
		indexes = {@Index(name = "index_rgon_m_1", columnList = "rgon_nm")})
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RgonMEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rgon_cd")
	private Long rgonCd;

	@Column(name = "rgon_nm")
	private String rgonNm;

	@Column(name = "delete_flg")
	private String deleteFlg;

	@Column(name = "rgon_use_typ")
	private Integer rgonUseTyp;

	@Column(name = "disp_order")
	private String dispOrder;

	@OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
	private List<PrefectureMEntity> prefectures;
}
