package com.ysl.commonservice.transformers;

import com.ysl.commonservice.constant.ErrorCode;
import com.ysl.commonservice.dao.AdvertRepository;
import com.ysl.commonservice.dto.request.BannerRequestDTO;
import com.ysl.commonservice.dto.response.BannerResponseDTO;
import com.ysl.commonservice.exception.EmlakjetAppException;
import com.ysl.commonservice.model.Advert;
import com.ysl.commonservice.model.Banner;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BannerTransformer {

    /*CONVERTER*/
    private final AdvertRepository advertRepository;

    public static class Response {
        public static BannerResponseDTO transform(Banner banner) {
            BannerResponseDTO response = new BannerResponseDTO();
            BeanUtils.copyProperties(banner, response);
            response.setPostedDate(banner.getCreatedAt());
            return response;
        }
    }
    public static class Request {
        public static BannerRequestDTO transform(Advert advert) {
            BannerRequestDTO response = new BannerRequestDTO();
            response.setAdvertUUID(advert.getAdvertUUID());
            return response;
        }
    }

    public Banner transform(BannerRequestDTO request) {
        Optional<Advert> optionalAdvert = advertRepository.findByAdvertUUID(request.getAdvertUUID());
        optionalAdvert.orElseThrow(() -> EmlakjetAppException.builder()
                .errorCode(ErrorCode.ADVERT_NOT_FOUND.formatted(request.getAdvertUUID()))
                .httpStatusCode(400)
                .build());

        Advert ad = optionalAdvert.orElse(null);
        Banner banner = new Banner();

        banner.setId(ad.getId());
        banner.setAdvertId(ad.getAdvertUUID());
        banner.setTitle(ad.getTitle());
        banner.setPhoneNumber(ad.getPhoneNumber());

        banner.setCity(ad.getProperty().getAddress().getCity());
        banner.setDistrict(ad.getProperty().getAddress().getDistrict());

        banner.setGrossSquareMeter(ad.getProperty().getGrossSquareMeter());
        banner.setPropertyType(ad.getProperty().getPropertyType());
        banner.setCost(ad.getCost());

        banner.setCreatedAt(ad.getCreatedAt());
        banner.setCreatedBy(ad.getCreatedBy());

        return banner;

    }

}
