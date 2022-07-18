package com.ysl.commonservice.dao;

import com.ysl.commonservice.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    Report findByAdvertReport_Id(Long id);
}