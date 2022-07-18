package com.ysl.bannerservice.controller;

import com.ysl.bannerservice.service.BannerService;
import com.ysl.commonservice.dto.request.BannerRequestDTO;
import com.ysl.commonservice.dto.response.BannerResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BannerController {
    private final BannerService service;

    @GetMapping(value = "/banners")
    public ResponseEntity<List<BannerResponseDTO>> getAllBanners() {
        return new ResponseEntity<>(service.getAllBanners(), HttpStatus.OK);
    }

    @RequestMapping(value = "/banner", method = RequestMethod.POST)
    public ResponseEntity<?> saveBanner(@RequestBody BannerRequestDTO request) {
        log.info("Received banner", request);
        return new ResponseEntity<>(service.saveBanner(request), HttpStatus.OK);

    }

}
