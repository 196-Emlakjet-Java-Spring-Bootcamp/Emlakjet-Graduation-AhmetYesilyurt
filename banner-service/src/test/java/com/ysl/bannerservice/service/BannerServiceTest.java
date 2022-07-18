package com.ysl.bannerservice.service;

import com.ysl.commonservice.dao.AdvertRepository;
import com.ysl.commonservice.dao.BannerRepository;
import com.ysl.commonservice.dto.request.BannerRequestDTO;
import com.ysl.commonservice.dto.response.BannerResponseDTO;
import com.ysl.commonservice.enums.AdvertStatus;
import com.ysl.commonservice.enums.PropertyType;
import com.ysl.commonservice.model.Address;
import com.ysl.commonservice.model.Advert;
import com.ysl.commonservice.model.Banner;
import com.ysl.commonservice.model.property.Residential;
import com.ysl.commonservice.transformers.BannerTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.ysl.bannerservice.service.BannerTestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BannerServiceTest {

    @Mock
    private BannerRepository bannerRepository;
    @Mock
    private AdvertRepository advertRepository;
    @InjectMocks
    private BannerTransformer bannerTransformer;
    @Captor
    ArgumentCaptor<Banner> argumentCaptor;

    private BannerService bannerService;

    @BeforeEach
    public void setup() {
        bannerService = new BannerService(bannerRepository, bannerTransformer);
    }

    @Test
    void getAllBanners() {
        Mockito.when(bannerRepository.findAll())
                .thenReturn(Collections.singletonList(prepareBanner()));
        List<BannerResponseDTO> banners = bannerService.getAllBanners();
        verify(bannerRepository).findAll();
        assertThat(banners.size()).isNotZero();
    }

    @Test
    void saveBanner() {

        Banner banner = prepareBanner();
        when(advertRepository.findByAdvertUUID(any())).thenReturn(Optional.ofNullable(prepareAdvert()));
        bannerService.saveBanner(convertToBannerRequest(banner));
        verify(bannerRepository).save(argumentCaptor.capture());
        Banner value = argumentCaptor.getValue();
        assertEquals(banner.getAdvertId(), value.getAdvertId());
        assertEquals(banner.getPhoneNumber(), value.getPhoneNumber());
        assertEquals(banner.getCost(), value.getCost());
        assertEquals(banner.getCity(), value.getCity());
        assertEquals(banner.getDistrict(), value.getDistrict());
        assertEquals(banner.getTitle(), value.getTitle());
        assertEquals(banner.getPropertyType(), value.getPropertyType());
    }

    public Banner prepareBanner() {
        Banner banner = new Banner();
        banner.setAdvertId(TEST_AD_ID);
        banner.setCity(TEST_CITY);
        banner.setId(TEST_ID);
        banner.setGrossSquareMeter(TEST_GROSS_SQUARE_METER);
        banner.setPhoneNumber(TEST_PHONE_NUMBER);
        banner.setCreatedAt(LocalDateTime.now());
        banner.setTitle(TEST_TITLE);
        banner.setPropertyType(PropertyType.RESIDENTIAL);
        banner.setDistrict(TEST_DISTRICT);
        banner.setCost(COST);
        return banner;
    }


    public Advert prepareAdvert() {
        Advert advert = new Advert();
        advert.setAdvertUUID(TEST_AD_ID);
        advert.setTitle(TEST_TITLE);
        advert.setDescription(TEST_DESCRIPTION);
        advert.setCost(COST);
        advert.setDuration(TEST_DURATION);
        advert.setPhoneNumber(TEST_PHONE_NUMBER);
        advert.setAdvertStatus(AdvertStatus.ACTIVE);

        advert.setProperty(prepareResidential());

        return advert;
    }

    public Residential prepareResidential() {
        Residential residential = new Residential();

        residential.setId(TEST_ID);
        residential.setFloor(2);
        residential.setCreatedAt(LocalDateTime.now());
        residential.setDescription(TEST_DESCRIPTION);
        residential.setGrossSquareMeter(TEST_GROSS_SQUARE_METER);
        residential.setBuildingAge(8);
        residential.setNoOfBath(2);
        residential.setNoOfRoom(3);
        residential.setIsForSale(Boolean.TRUE);

        residential.setAddress(prepareAddress());

        return residential;
    }

    public Address prepareAddress() {
        Address address = new Address();
        address.setId(TEST_ID);
        address.setAddressDesc(TEST_ADDRESS_DESCRIPTION);
        address.setDistrict(TEST_DISTRICT);
        address.setCity(TEST_CITY);
        return address;
    }


    public BannerRequestDTO convertToBannerRequest(Banner banner) {
        BannerRequestDTO bannerRequestDTO = new BannerRequestDTO();
        bannerRequestDTO.setAdvertUUID(banner.getAdvertId());
        return bannerRequestDTO;
    }

    public BannerRequestDTO prepareBannerRequest() {
        BannerRequestDTO banner = new BannerRequestDTO();
        banner.setAdvertUUID(TEST_AD_ID);
        return banner;
    }


}