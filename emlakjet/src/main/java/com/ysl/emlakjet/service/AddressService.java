package com.ysl.emlakjet.service;

import com.ysl.commonservice.dao.AddressRepository;
import com.ysl.commonservice.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressService {
    private final AddressRepository addressRepository;

    public Address getById(Long id) {
        return addressRepository.findById(id).orElse(null);

    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

}
