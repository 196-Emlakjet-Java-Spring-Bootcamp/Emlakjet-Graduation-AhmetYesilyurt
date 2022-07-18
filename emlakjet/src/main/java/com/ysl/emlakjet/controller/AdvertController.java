package com.ysl.emlakjet.controller;

import com.ysl.commonservice.dto.request.AdvertRequestDTO;
import com.ysl.commonservice.dto.request.AdvertUpdateDTO;
import com.ysl.commonservice.model.Advert;
import com.ysl.emlakjet.service.AdvertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@Transactional
public class AdvertController {
    private final AdvertService advertService;


    @PostMapping(value = "/advert")
    public ResponseEntity<Advert> createAdvert(
            @RequestBody AdvertRequestDTO request, @RequestHeader(value = "emlak_user_id") String emlakUserId) {
        log.info("createAdvert --> User id : {}", emlakUserId);
        request.setEmlakUserId(Long.valueOf(emlakUserId));
        return new ResponseEntity<>(advertService.createAdvert(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "/advert/{advertNo}")
    public ResponseEntity<Advert> getAdvertByAdvertId(@PathVariable long advertNo) {
        return new ResponseEntity<>(advertService.getAdvertByAdvertId(advertNo), HttpStatus.OK);
    }

    @PutMapping(value = "/advert/{advertId}")
    public ResponseEntity<Advert> updateAdvert(@RequestBody AdvertUpdateDTO request, @PathVariable Long advertId) {
        return new ResponseEntity<>(advertService.updateAdvert(request, advertId), HttpStatus.OK);
    }

    @GetMapping(value = "/advert/get10")
    public ResponseEntity <List<Advert>> get10ActiveAdvert() {
        return new ResponseEntity<>(advertService.get10ActiveAdvert(), HttpStatus.OK);
    }

}
