package com.ysl.bannerservice.service;

import com.ysl.commonservice.dao.BannerRepository;
import com.ysl.commonservice.dto.request.BannerRequestDTO;
import com.ysl.commonservice.dto.response.BannerResponseDTO;
import com.ysl.commonservice.model.Banner;
import com.ysl.commonservice.transformers.BannerTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BannerService {

    private final BannerRepository bannerRepository;
    private final BannerTransformer bannerTransformer;

    public List<BannerResponseDTO> getAllBanners() {
        return bannerRepository.findAll().stream()
                .map(BannerTransformer.Response::transform)
                .collect(Collectors.toList());
    }

    public Banner saveBanner(BannerRequestDTO requestDTO) {
        return bannerRepository.save(bannerTransformer.transform((requestDTO)));
    }


}
