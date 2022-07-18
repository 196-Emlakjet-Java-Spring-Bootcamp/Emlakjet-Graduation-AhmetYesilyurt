package com.ysl.commonservice.transformers;

import com.ysl.commonservice.dto.request.AddressRequestDTO;
import com.ysl.commonservice.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressTransformer {
    /*CONVERTER*/
    public Address transform(AddressRequestDTO addressRequestDTO) {
        Address address = new Address();
        address.setAddressDesc(addressRequestDTO.getAddressDesc());
        address.setCity(addressRequestDTO.getCity());
        address.setDistrict(addressRequestDTO.getDistrict());
        address.setCreatedBy(addressRequestDTO.getEmlakUserId());
        address.setModifiedBy(addressRequestDTO.getEmlakUserId());
        return address;
    }

}
