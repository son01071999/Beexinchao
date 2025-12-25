package com.example.beexinchao.repo;

import com.example.beexinchao.entity.ContactMEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMRepo
		extends JpaRepository<ContactMEntity, Long>, JpaSpecificationExecutor<ContactMEntity> {}
