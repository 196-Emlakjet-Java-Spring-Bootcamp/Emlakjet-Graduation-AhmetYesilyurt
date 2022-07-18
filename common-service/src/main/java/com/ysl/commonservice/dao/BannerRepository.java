package com.ysl.commonservice.dao;

import com.ysl.commonservice.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {

}
