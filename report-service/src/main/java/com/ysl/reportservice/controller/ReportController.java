package com.ysl.reportservice.controller;

import com.ysl.commonservice.model.Report;
import com.ysl.reportservice.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequiredArgsConstructor
@Transactional
public class ReportController {

    private final ReportService service;

    @GetMapping(value = "/report/{id}")
    public ResponseEntity<Report> getReport(@PathVariable Long id) {
        return new ResponseEntity<>(service.getReportByAdvertId(id), HttpStatus.OK);
        //ilan id
    }

    @PutMapping(value = "/report/{advertId}")
    public ResponseEntity<Report> updateReport(@PathVariable Long advertId) {
        return new ResponseEntity<>(service.updateReportByAdvertId(advertId), HttpStatus.OK);
    }
}
