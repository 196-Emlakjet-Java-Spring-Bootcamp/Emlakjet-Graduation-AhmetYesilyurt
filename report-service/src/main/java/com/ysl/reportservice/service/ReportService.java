package com.ysl.reportservice.service;

import com.ysl.commonservice.constant.ErrorCode;
import com.ysl.commonservice.dao.AdvertRepository;
import com.ysl.commonservice.dao.ReportRepository;
import com.ysl.commonservice.dto.request.ReportRequestDTO;
import com.ysl.commonservice.exception.EmlakjetAppException;
import com.ysl.commonservice.model.Advert;
import com.ysl.commonservice.model.Report;
import com.ysl.commonservice.transformers.ReportTransformer;
import com.ysl.commonservice.util.ReportMessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ReportService {

    private final ReportRepository repository;
    private final ReportTransformer reportTransformer;
    private final AdvertRepository advertRepository;



    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receivedMessage(ReportRequestDTO message) {
        log.info("Received message from queue : {}",message);
        repository.save(reportTransformer.transform(message));
    }

    public Report getReportByAdvertId(Long advertId) {
        return repository.findByAdvertReport_Id(advertId);
    }

    public Report updateReportByAdvertId(Long advertId) {

        Advert advert = advertRepository.findById(advertId).orElseThrow(() -> EmlakjetAppException.builder()
                .errorCode(ErrorCode.ADVERT_NOT_FOUND)
                .httpStatusCode(400)
                .build());

        Report report = repository.findByAdvertReport_Id(advertId);
        report.setMessage(ReportMessageUtil.getAdvertCreatedReportMessage(advert).getReportMessage());

        return repository.save(report);

    }





}
