package com.ysl.reportservice.service;

import com.ysl.commonservice.dao.AdvertRepository;
import com.ysl.commonservice.dao.ReportRepository;
import com.ysl.commonservice.transformers.ReportTransformer;
import org.junit.Before;
import org.mockito.Mockito;

public class ReportServiceTest {

    private ReportService reportService;

    private ReportRepository repository;
    private ReportTransformer reportTransformer;
    private AdvertRepository advertRepository;

    @Before
    public void setUp() throws Exception {

        repository = Mockito.mock(ReportRepository.class);
        reportTransformer = Mockito.mock(ReportTransformer.class);
        advertRepository = Mockito.mock(AdvertRepository.class);
        reportService = new ReportService(repository, reportTransformer, advertRepository);

    }
}