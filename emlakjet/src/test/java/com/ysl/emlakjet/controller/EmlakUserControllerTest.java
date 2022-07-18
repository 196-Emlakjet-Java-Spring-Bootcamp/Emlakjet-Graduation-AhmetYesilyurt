package com.ysl.emlakjet.controller;

import com.ysl.commonservice.dto.request.EmlakUserSignupDTO;
import com.ysl.commonservice.enums.Role;
import com.ysl.commonservice.model.EmlakUser;
import com.ysl.commonservice.service.EmlakUserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EmlakUserControllerTest {
    @InjectMocks
    private EmlakUserController userController;

    @Mock
    private EmlakUserService userService;

    private EmlakUserSignupDTO prepareMockEmlakUserDTO() {
        EmlakUserSignupDTO userDTO = new EmlakUserSignupDTO();
        userDTO.setRoleType(Role.INDIVIDUAL.toString());
        userDTO.setPasswordHash("123");
        userDTO.setFirstName("Ahmet");
        userDTO.setLastName("Yeşilyurt");
        userDTO.setMobileNo("05555555555");

        return userDTO;
    }

    @Test
    void getAllList() {
        ResponseEntity<List<EmlakUser>> res = userController.getAllEmlakUsers();
        assertEquals(200, res.getStatusCodeValue());
    }


    @Test
    void create() {
        ResponseEntity<?> res = userController.signUpEmlakUser(prepareMockEmlakUserDTO());
        assertEquals(200, res.getStatusCodeValue());
    }

    @Test
    void getUserById() {
        ResponseEntity<EmlakUser> res = userController.getEmlakUserById(1);
        assertEquals(200, res.getStatusCodeValue());
    }

}