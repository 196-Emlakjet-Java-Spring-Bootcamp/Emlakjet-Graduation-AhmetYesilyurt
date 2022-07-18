package com.ysl.emlakjet.controller;

import com.ysl.commonservice.dto.request.PropertyRequestDTO;
import com.ysl.commonservice.model.property.Property;
import com.ysl.emlakjet.service.PropertyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping(value = "/property")
    public ResponseEntity<Property> saveProperty(
            @RequestBody PropertyRequestDTO propertyRequestDTO,
            @RequestHeader(value="emlak_user_id") String emlakUserId) {
        log.info("saveProperty --> User id : {}", emlakUserId);

        propertyRequestDTO.setEmlakUserId(Long.valueOf(emlakUserId));
        return new ResponseEntity<>(propertyService.saveProperty(propertyRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/property/{id}")
    public ResponseEntity<Property> getProperty(@PathVariable("id") Long id) {
        return new ResponseEntity<>(propertyService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/properties")
    public ResponseEntity<List<Property>> getAllProperties() {
        return ResponseEntity.ok(propertyService.getAllProperties());
    }
}
