package com.example.beexinchao.repo;

import com.example.beexinchao.entity.RgonMEntity;
import com.example.beexinchao.response.RgonInfoDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RgonMRepo extends JpaRepository<RgonMEntity, Long> {
	@Query(
			nativeQuery = true,
			value =
					"SELECT rgon_cd   AS rgonCd, rgon_nm   AS rgonNm FROM rgon_m rm WHERE rm.delete_flg  = '0' AND rm.rgon_use_typ  = '1'")
	List<RgonInfoDto> findActiveCoreRegions();
}
