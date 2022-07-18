package com.ysl.commonservice.dao;

import com.ysl.commonservice.enums.AdvertStatus;
import com.ysl.commonservice.model.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {

    Optional<Advert> findById(Long id);

    List<Advert> findByCreatedBy(Long userId);

    Optional<Advert> findByAdvertUUID(String advertUUID);

    List<Advert> findAllByAdvertStatus(AdvertStatus status);

    List<Advert> findFirst10ByAdvertStatusOrderByCreatedAtDesc(AdvertStatus status);

    List<Advert> findFirst10ByOrderByCreatedAtDesc();


    @Modifying(clearAutomatically = true)
    @Query("UPDATE Advert u SET u.view = u.view + 1 where u.id = :id")
    void increase(@Param("id") long id);


}
