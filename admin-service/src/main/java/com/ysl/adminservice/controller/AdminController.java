package com.ysl.adminservice.controller;

import com.ysl.adminservice.service.AdminService;
import com.ysl.commonservice.model.Advert;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;


    @GetMapping(value = "/admin/{userId}")
    public ResponseEntity<List<Advert>> getAllAdvertByUserId(@PathVariable Long userId) {

        return new ResponseEntity<>(adminService.getAdvertByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/admin/all-advert")
    public ResponseEntity<List<Advert>> getAllAdvert() {
        return new ResponseEntity<>(adminService.getAllAdverts(), HttpStatus.OK);
    }

    @GetMapping(value = "/admin/review")
    public ResponseEntity<List<Advert>> inReviewAdverts() {
        return new ResponseEntity<>(adminService.inReviewAdverts(), HttpStatus.OK);
    }

    @GetMapping(value = "/admin/passive")
    public ResponseEntity<List<Advert>> passiveAdverts() {
        return new ResponseEntity<>(adminService.passiveAdverts(), HttpStatus.OK);
    }

    @GetMapping(value = "/admin/active")
    public ResponseEntity<List<Advert>> activeAdverts() {
        return new ResponseEntity<>(adminService.activeAdverts(), HttpStatus.OK);
    }

    @PutMapping(value = "/admin/make-active/{id}")
    public ResponseEntity<Advert> makeActive(@PathVariable Long id) {
        return new ResponseEntity<>(adminService.makeActiveStatus(id), HttpStatus.OK);
    }

    @PutMapping(value = "/admin/make-passive/{id}")
    public ResponseEntity<Advert> makePassive(@PathVariable Long id) {
        return new ResponseEntity<>(adminService.makePassiveStatus(id), HttpStatus.OK);
    }

    @GetMapping(value = "/admin/last-10-advert")
    public ResponseEntity<List<Advert>> get10Adverts() {
        return new ResponseEntity<>(adminService.get10Advert(), HttpStatus.OK);
    }
}
