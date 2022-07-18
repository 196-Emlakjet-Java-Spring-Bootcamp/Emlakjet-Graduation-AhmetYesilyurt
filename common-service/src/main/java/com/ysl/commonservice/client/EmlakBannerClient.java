package com.ysl.commonservice.client;

import com.ysl.commonservice.dto.request.BannerRequestDTO;
import com.ysl.commonservice.dto.response.BannerResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "banner-service", url = "${banner-client-url:http://localhost:8082}")
public interface EmlakBannerClient {
    @PostMapping(value = "/banner")
    BannerResponseDTO saveBanner(@RequestBody BannerRequestDTO request);
}

