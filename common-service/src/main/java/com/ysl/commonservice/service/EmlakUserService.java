package com.ysl.commonservice.service;

import com.ysl.commonservice.constant.ErrorCode;
import com.ysl.commonservice.dao.EmlakUserAuthenticationRepository;
import com.ysl.commonservice.dao.EmlakUserRepository;
import com.ysl.commonservice.dto.request.EmlakUserSignupDTO;
import com.ysl.commonservice.exception.EmlakjetAppException;
import com.ysl.commonservice.model.EmlakUser;
import com.ysl.commonservice.model.EmlakUserAuthentication;
import com.ysl.commonservice.transformers.EmlakUserTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmlakUserService {

    private final EmlakUserRepository emlakUserRepository;
    private final EmlakUserTransformer emlakUserTransformer;
    private final EmlakUserAuthenticationRepository emlakUserAuthenticationRepository;

    public List<EmlakUser> getAllEmlakUsers() {
        return emlakUserRepository.findAll();
    }

    public EmlakUser getUserById(Long id) {
        return emlakUserRepository.findById(id).orElseThrow(() -> EmlakjetAppException.builder()
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .httpStatusCode(400)
                .build());

    }

    @Transactional
    public EmlakUser signUpEmlakUser(EmlakUserSignupDTO emlakUserSignupDTO) {
        /* save authentication */
        if (ObjectUtils.isEmpty(emlakUserSignupDTO.getPasswordHash())) {
            throw EmlakjetAppException.builder()
                    .errorCode(ErrorCode.PASSWORD_EMPTY)
                    .httpStatusCode(401)
                    .build();
        }

        EmlakUserAuthentication userAuthentication = new EmlakUserAuthentication();
        EmlakUser emlakUser = emlakUserTransformer.transform(emlakUserSignupDTO);
        userAuthentication.setEmlakUser(emlakUser);
        userAuthentication.setPasswordHash(emlakUserSignupDTO.getPasswordHash());
        emlakUserAuthenticationRepository.save(userAuthentication);
        /* save user */
        return emlakUserRepository.save(emlakUser);

    }



}
