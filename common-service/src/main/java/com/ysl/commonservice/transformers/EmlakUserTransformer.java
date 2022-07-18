package com.ysl.commonservice.transformers;

import com.ysl.commonservice.dto.request.EmlakUserSignupDTO;
import com.ysl.commonservice.enums.Role;
import com.ysl.commonservice.model.EmlakUser;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class EmlakUserTransformer {
    /*CONVERTER*/
    public EmlakUser transform(EmlakUserSignupDTO emlakUserSignupDTO) {
        EmlakUser emlakUser = new EmlakUser();
        emlakUser.setFirstName(emlakUserSignupDTO.getFirstName());
        emlakUser.setLastName(emlakUserSignupDTO.getLastName());
        emlakUser.setEmail(emlakUserSignupDTO.getEmail());
        emlakUser.setMobileNo(emlakUserSignupDTO.getMobileNo());
        emlakUser.setRole(Dialog.ModalityType.valueOf(Role.class, emlakUserSignupDTO.getRoleType()));

        return emlakUser;
    }


}
