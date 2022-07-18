package com.ysl.emlakjet.controller;

import com.ysl.commonservice.dto.request.EmlakUserSignupDTO;
import com.ysl.commonservice.model.EmlakUser;
import com.ysl.commonservice.service.EmlakUserService;
import com.ysl.emlakjet.service.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Transactional
public class EmlakUserController {
    private final EmlakUserService emlakUserService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<EmlakUser>> getAllEmlakUsers() {
        return new ResponseEntity<>(emlakUserService.getAllEmlakUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUpEmlakUser(@RequestBody EmlakUserSignupDTO emlakUserRequestDTO) {
        return new ResponseEntity<>(emlakUserService.signUpEmlakUser(emlakUserRequestDTO),HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<EmlakUser> getEmlakUserById(@PathVariable long userId) {
        return new ResponseEntity<>(emlakUserService.getUserById(userId), HttpStatus.OK);
    }



}
