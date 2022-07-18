package com.ysl.emlakjet.service;

import com.ysl.commonservice.dao.PropertyRepository;
import com.ysl.commonservice.dto.request.PropertyRequestDTO;
import com.ysl.commonservice.model.property.Property;
import com.ysl.commonservice.transformers.PropertyTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyTransformer propertyTransformer;

    public Property saveProperty(PropertyRequestDTO propertyRequestDTO) {
        return propertyRepository.save(propertyTransformer.transform(propertyRequestDTO));
    }

    public Property getById(Long id) {
        return propertyRepository.findById(id).orElse(null);
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }
}
