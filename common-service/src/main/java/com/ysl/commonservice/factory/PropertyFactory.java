package com.ysl.commonservice.factory;

import com.ysl.commonservice.dao.AddressRepository;
import com.ysl.commonservice.dto.request.PropertyRequestDTO;
import com.ysl.commonservice.enums.PropertyType;
import com.ysl.commonservice.exception.EmlakjetAppException;
import com.ysl.commonservice.model.property.Commercial;
import com.ysl.commonservice.model.property.Land;
import com.ysl.commonservice.model.property.Property;
import com.ysl.commonservice.model.property.Residential;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ysl.commonservice.constant.ErrorCode.INVALID_PROPERTY_TYPE;

@Component
@RequiredArgsConstructor
public class PropertyFactory {

    private final AddressRepository addressRepository;

    public Property getProperty(PropertyRequestDTO requestDTO) {

        PropertyType propertyType = requestDTO.getPropertyType();

        switch (propertyType) {
            case LAND :
                Land land = new Land();
                updateCommonPropertyDetails(requestDTO, land);
                land.setZoning(requestDTO.getLandPropertyDetails().getZoning());
                land.setParcelNo(requestDTO.getLandPropertyDetails().getParcelNo());
                return land;
            case COMMERCIAL:
                Commercial commercial = new Commercial();
                updateCommonPropertyDetails(requestDTO, commercial);

                commercial.setCategory(requestDTO.getCommercialPropertyDetails().getCategory());
                commercial.setDues(requestDTO.getCommercialPropertyDetails().getDues());

                return commercial;

            case RESIDENTIAL:
                Residential residential =  new Residential();
                updateCommonPropertyDetails(requestDTO, residential);

                residential.setBuildingAge(requestDTO.getResidentialPropertyDetails().getBuildingAge());
                residential.setFloor(requestDTO.getResidentialPropertyDetails().getFloor());
                residential.setNoOfBath(requestDTO.getResidentialPropertyDetails().getNoOfBath());
                residential.setNoOfRoom(requestDTO.getResidentialPropertyDetails().getNoOfRoom());

                return residential;
        }
        throw EmlakjetAppException.builder()
                .errorCode(INVALID_PROPERTY_TYPE)
                .httpStatusCode(400)
                .build();


    }
    private void updateCommonPropertyDetails(PropertyRequestDTO requestDTO, Property property) {

        property.setIsForSale(requestDTO.getIsForSale());
        property.setGrossSquareMeter(requestDTO.getGrossSquareMeter());
        property.setDescription(requestDTO.getDescription());
        property.setAddress(addressRepository.getById(requestDTO.getAddressId()));

    }






}
