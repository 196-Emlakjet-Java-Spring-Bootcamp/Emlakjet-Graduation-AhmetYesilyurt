package com.ysl.emlakjet.controller;

import com.ysl.commonservice.dto.request.AddressRequestDTO;
import com.ysl.commonservice.model.Address;
import com.ysl.commonservice.transformers.AddressTransformer;
import com.ysl.emlakjet.service.AddressService;
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
public class AddressController {
    private final AddressService addressService;
    private final AddressTransformer address;
    @GetMapping(value = "/address/{id}")
    public ResponseEntity<Address> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(addressService.getById(id), HttpStatus.OK);
    }
    @PostMapping(value = "/address")
    public ResponseEntity<Address> saveAddress(@RequestBody AddressRequestDTO addressRequestDTO,
            @RequestHeader(value="emlak_user_id") String emlakUserId) {
        log.info("saveAddress --> User id : {}", emlakUserId);
        addressRequestDTO.setEmlakUserId(Long.valueOf(emlakUserId));
        return new ResponseEntity<>(addressService.saveAddress(address.transform(addressRequestDTO)), HttpStatus.CREATED);
    }

    @GetMapping(value = "/addresses")
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

}
