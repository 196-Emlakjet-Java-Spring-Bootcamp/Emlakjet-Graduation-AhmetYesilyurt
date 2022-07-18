package com.ysl.emlakjet.service;

import com.ysl.commonservice.constant.ErrorCode;
import com.ysl.commonservice.dao.EmlakUserRepository;
import com.ysl.commonservice.dto.request.AdvertRequestDTO;
import com.ysl.commonservice.exception.EmlakjetAppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AdvertValidatorService {

    private final EmlakUserRepository emlakUserRepository;

    @Transactional
    public void validateCreateAdvertAdvertRequest(AdvertRequestDTO requestDTO) {
        emlakUserRepository
                .findById(requestDTO.getEmlakUserId())
                .orElseThrow(() -> EmlakjetAppException.builder()
                        .errorCode(ErrorCode.USER_NOT_FOUND.formatted(requestDTO.getEmlakUserId()))
                        .httpStatusCode(400)
                        .build());



    }
}
