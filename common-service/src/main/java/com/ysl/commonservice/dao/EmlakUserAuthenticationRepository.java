package com.ysl.commonservice.dao;

import com.ysl.commonservice.model.EmlakUserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmlakUserAuthenticationRepository extends JpaRepository<EmlakUserAuthentication, Long> {
    Optional<EmlakUserAuthentication> findByEmlakUser_EmailAndPasswordHash(String email, String passwordHash);
}