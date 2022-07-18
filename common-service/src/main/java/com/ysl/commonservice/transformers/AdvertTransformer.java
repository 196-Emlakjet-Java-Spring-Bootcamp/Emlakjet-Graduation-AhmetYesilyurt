package com.ysl.commonservice.transformers;

import com.ysl.commonservice.constant.ErrorCode;
import com.ysl.commonservice.dao.EmlakUserRepository;
import com.ysl.commonservice.dao.PropertyRepository;
import com.ysl.commonservice.dto.request.AdvertRequestDTO;
import com.ysl.commonservice.exception.EmlakjetAppException;
import com.ysl.commonservice.model.Advert;
import com.ysl.commonservice.model.EmlakUser;
import com.ysl.commonservice.model.property.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.ysl.commonservice.constant.ErrorCode.PROPERTY_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class AdvertTransformer {
    /*CONVERTER*/
    private final EmlakUserRepository emlakUserRepository;
    private final PropertyRepository propertyRepository;

    public Advert transform(AdvertRequestDTO request) {
        Optional<Property> property = propertyRepository.findById(request.getPropertyId());
        Optional<EmlakUser> emlakUser = emlakUserRepository.findById(request.getEmlakUserId());

        property.orElseThrow(()->EmlakjetAppException.builder()
                .errorCode(PROPERTY_NOT_FOUND.formatted(request.getPropertyId()))
                .httpStatusCode(400)
                .build());
        emlakUser.orElseThrow(() -> EmlakjetAppException.builder()
                .errorCode(ErrorCode.USER_NOT_FOUND.formatted(request.getEmlakUserId()))
                .httpStatusCode(400)
                .build());

        Advert advert = new Advert();
        advert.setTitle(request.getTitle());
        advert.setDescription(request.getDescription());
        advert.setProperty(property.orElse(null));
        advert.setPostedByEmlakUser(emlakUser.orElse(null));


        advert.setCost(request.getCost());
        advert.setDuration(request.getDuration());
        advert.setPhoneNumber(request.getPhoneNumber());

        emlakUser.ifPresent(nonEmptyEmlakUser -> {
            advert.setCreatedBy(nonEmptyEmlakUser.getId());
            advert.setModifiedBy(nonEmptyEmlakUser.getId());
        });
        return advert;
    }

}
