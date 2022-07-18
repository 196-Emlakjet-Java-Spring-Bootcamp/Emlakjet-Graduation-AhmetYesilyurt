package com.ysl.adminservice.service;

import com.ysl.commonservice.constant.ErrorCode;
import com.ysl.commonservice.dao.AdvertRepository;
import com.ysl.commonservice.enums.AdvertStatus;
import com.ysl.commonservice.exception.EmlakjetAppException;
import com.ysl.commonservice.model.Advert;
import com.ysl.commonservice.service.EmlakUserService;
import com.ysl.commonservice.service.ReportQueueService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {
    private final AdvertRepository advertRepository;
    private final EmlakUserService emlakUserService;
    private final ReportQueueService reportQueueService;


    public List<Advert> getAllAdverts() {
        List<Advert> allAdverts = advertRepository.findAll();
        return new ArrayList<>(allAdverts);
    }

    public List<Advert> getAdvertByUserId(Long userId) {

        if (emlakUserService.getUserById(userId) != null) {

            return new ArrayList<>(advertRepository.findByCreatedBy(userId));

        } else {
            throw EmlakjetAppException.builder()
                    .errorCode(ErrorCode.USER_NOT_FOUND)
                    .httpStatusCode(400)
                    .build();
        }

    }


    public List<Advert> inReviewAdverts() {

        return advertRepository.findAllByAdvertStatus(AdvertStatus.IN_REVIEW);
    }

    public List<Advert> passiveAdverts() {

        return advertRepository.findAllByAdvertStatus(AdvertStatus.PASSIVE);
    }


    public List<Advert> activeAdverts() {

        return advertRepository.findAllByAdvertStatus(AdvertStatus.ACTIVE);
    }


    @SneakyThrows
    public Advert makeActiveStatus(Long id) {

        Advert advert = advertRepository.findById(id).orElseThrow(() -> EmlakjetAppException.builder()
                .errorCode(ErrorCode.ADVERT_NOT_FOUND)
                .httpStatusCode(400)
                .build());

        advert.setAdvertStatus(AdvertStatus.ACTIVE);

        /*  The approved advert is taken to the message queue and the report is created.*/
        reportQueueService.sendAdvertCreatedReport(advert);

        return advertRepository.save(advert);
    }


    public Advert makePassiveStatus(Long id) {

        Advert advert = advertRepository.findById(id).orElseThrow(() -> EmlakjetAppException.builder()
                .errorCode(ErrorCode.ADVERT_NOT_FOUND)
                .httpStatusCode(400)
                .build());

        advert.setAdvertStatus(AdvertStatus.PASSIVE);

        return advertRepository.save(advert);
    }

    public List<Advert> get10Advert() {
        return advertRepository.findFirst10ByOrderByCreatedAtDesc();
    }


}
