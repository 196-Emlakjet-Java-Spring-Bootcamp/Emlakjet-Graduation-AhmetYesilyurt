package com.ysl.commonservice.service;

import com.ysl.commonservice.config.RabbitMqConfig;
import com.ysl.commonservice.dto.request.ReportRequestDTO;
import com.ysl.commonservice.model.Advert;
import com.ysl.commonservice.util.ReportMessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ReportQueueService {

    private final RabbitMqConfig rabbitMqConfig;

    private final RabbitTemplate rabbitTemplate;


    @SneakyThrows
    @Transactional
    public void sendAdvertCreatedReport (Advert savedAdvert)  {

        ReportRequestDTO message = ReportMessageUtil.getAdvertCreatedReportMessage(savedAdvert);
        rabbitTemplate.convertAndSend(rabbitMqConfig.getExchange(), rabbitMqConfig.getRoutingkey(), message);
    }







}


