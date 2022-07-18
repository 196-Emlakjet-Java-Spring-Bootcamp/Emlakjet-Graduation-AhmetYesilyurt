package com.ysl.commonservice.dao;

import com.ysl.commonservice.model.EmlakUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmlakUserRepository extends JpaRepository<EmlakUser, Long> {

    boolean existsByEmail(String email);

}
