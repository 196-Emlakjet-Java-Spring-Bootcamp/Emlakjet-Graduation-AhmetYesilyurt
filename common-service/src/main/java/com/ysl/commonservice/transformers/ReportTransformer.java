package com.ysl.commonservice.transformers;

import com.ysl.commonservice.dao.AdvertRepository;
import com.ysl.commonservice.dto.request.ReportRequestDTO;
import com.ysl.commonservice.model.Advert;
import com.ysl.commonservice.model.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportTransformer {
    private final AdvertRepository advertRepository;

    public Report transform(ReportRequestDTO reportRequestDTO) {
        Advert advert = advertRepository.getById(reportRequestDTO.getAdvertId());
        Report report = new Report();
        report.setMessage(reportRequestDTO.getReportMessage());
        report.setAdvertReport(advert);
        return report;
    }


}
