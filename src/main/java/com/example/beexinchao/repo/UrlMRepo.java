package com.example.beexinchao.repo;

import com.example.beexinchao.entity.UrlMEntity;
import com.example.beexinchao.response.UrlInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UrlMRepo extends JpaRepository<UrlMEntity, Long> {
	@Query(nativeQuery = true, value = "SELECT url FROM url_m WHERE screen_cd = :screenId ")
	UrlInfoDto findByScreenCd(@Param("screenId") int screenId);
}
