package com.ysl.commonservice.transformers;


import com.ysl.commonservice.dao.AddressRepository;
import com.ysl.commonservice.dao.EmlakUserRepository;
import com.ysl.commonservice.dto.request.PropertyRequestDTO;
import com.ysl.commonservice.factory.PropertyFactory;
import com.ysl.commonservice.exception.EmlakjetAppException;
import com.ysl.commonservice.model.Address;
import com.ysl.commonservice.model.EmlakUser;
import com.ysl.commonservice.model.property.Property;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.ysl.commonservice.constant.ErrorCode.ADDRESS_NOT_FOUND;
import static com.ysl.commonservice.constant.ErrorCode.USER_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class PropertyTransformer {
    private final PropertyFactory propertyFactory;
    private final EmlakUserRepository emlakUserRepository;
    private final AddressRepository addressRepository;

    public Property transform(PropertyRequestDTO propertyRequestDTO) {

        Property property = propertyFactory.getProperty(propertyRequestDTO);
        Optional<EmlakUser> owner = emlakUserRepository.findById(propertyRequestDTO.getEmlakUserId());
        owner.orElseThrow(()->
                EmlakjetAppException.builder()
                        .errorCode(USER_NOT_FOUND.formatted(propertyRequestDTO.getEmlakUserId()))
                        .httpStatusCode(400)
                        .build()
        );
        Address address = addressRepository.findById(propertyRequestDTO.getAddressId())
                .orElseThrow(()->
                        EmlakjetAppException.builder()
                                .errorCode(ADDRESS_NOT_FOUND.formatted(propertyRequestDTO.getAddressId()))
                                .httpStatusCode(400)
                                .build());

        owner.ifPresent(nonEmptyEmlakUser-> {
            property.setOwner(nonEmptyEmlakUser);
            property.setCreatedBy(nonEmptyEmlakUser.getId());
            property.setModifiedBy(nonEmptyEmlakUser.getId());
            property.setAddress(address);
        });
        return property;

    }

}
