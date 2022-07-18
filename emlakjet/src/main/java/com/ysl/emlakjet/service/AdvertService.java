package com.ysl.emlakjet.service;

import com.ysl.commonservice.client.EmlakBannerClient;
import com.ysl.commonservice.constant.ErrorCode;
import com.ysl.commonservice.dao.AdvertRepository;
import com.ysl.commonservice.dto.request.AdvertRequestDTO;
import com.ysl.commonservice.dto.request.AdvertUpdateDTO;
import com.ysl.commonservice.dto.request.BannerRequestDTO;
import com.ysl.commonservice.dto.response.BannerResponseDTO;
import com.ysl.commonservice.enums.AdvertStatus;
import com.ysl.commonservice.exception.EmlakjetAppException;
import com.ysl.commonservice.model.Advert;
import com.ysl.commonservice.transformers.AdvertTransformer;
import com.ysl.commonservice.transformers.BannerTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AdvertService {


    private final AdvertRepository advertRepository;
    private final AdvertTransformer advertTransformer;
    private final EmlakBannerClient emlakBannerClient;
    private final AdvertValidatorService advertValidatorService;
    private final ExecutorService executorService; //Thread


    public Advert createAdvert(AdvertRequestDTO requestDTO) {
        /* validate the advert create request */
        advertValidatorService.validateCreateAdvertAdvertRequest(requestDTO);
        /* execute database operations for creating advert */
        Advert savedAdvert = saveAdvert(requestDTO);

        /* create banner using feign client */
        log.info("going to call banner service with advert uuid: {}", savedAdvert.getAdvertUUID());

        // it won't block my request thread
        executorService.execute(() -> {
            BannerRequestDTO bannerRequestDTO = BannerTransformer.Request.transform(savedAdvert);
            BannerResponseDTO bannerResponseDTO = emlakBannerClient.saveBanner(bannerRequestDTO);
            log.info("created banner with id {}", bannerResponseDTO.getAdvertUUID());
        });
        return savedAdvert;
    }

    public Advert saveAdvert(AdvertRequestDTO requestDTO) {
        Advert savedAdvert = advertRepository.save(advertTransformer.transform(requestDTO));
        advertRepository.save(savedAdvert);
        return savedAdvert;
    }

    @Transactional
    public Advert getAdvertByAdvertId(long advertId) {

        advertRepository.increase(advertId);

        return advertRepository.findById(advertId)
                .orElse(null);
    }

    public Advert updateAdvert(AdvertUpdateDTO request, Long advertId) {

        Advert advert = advertRepository.findById(advertId).orElseThrow(() -> EmlakjetAppException.builder()
                .errorCode(ErrorCode.ADVERT_NOT_FOUND)
                .httpStatusCode(400)
                .build());


        advert.setPhoneNumber(request.getPhoneNumber());
        advert.setTitle(request.getTitle());
        advert.setCost(request.getCost());
        advert.setDuration(request.getDuration());
        advert.setDescription(request.getDescription());

        log.info("The advert has been successfully updated");
        return advertRepository.save(advert);
    }


    public List<Advert> get10ActiveAdvert() {
        // Son 10 aktif ilan...

        List<Advert> adverts = advertRepository.findFirst10ByAdvertStatusOrderByCreatedAtDesc(AdvertStatus.ACTIVE);

        if (!adverts.isEmpty()) {
            return adverts;
        } else {
            throw EmlakjetAppException.builder()
                    .errorCode(ErrorCode.ADVERT_NOT_FOUND)
                    .httpStatusCode(400)
                    .build();
        }

    }


}
