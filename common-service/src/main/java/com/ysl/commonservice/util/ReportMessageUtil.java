package com.ysl.commonservice.util;

import com.ysl.commonservice.constant.CommonConstants;
import com.ysl.commonservice.dto.request.ReportRequestDTO;
import com.ysl.commonservice.model.Advert;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@UtilityClass
public class ReportMessageUtil {


    @SneakyThrows
    public ReportRequestDTO getAdvertCreatedReportMessage(Advert advert)  {

        LocalDateTime t1 = advert.getCreatedAt();
        LocalDateTime t2 = LocalDateTime.now();
        Period period = Period.between(t1.toLocalDate(), t2.toLocalDate());
        Duration duration = Duration.between(t1, t2);

        return ReportRequestDTO
                .builder()
                .advertId(advert.getId())
                .reportMessage(advert.getTitle()+" adlı ilanı "+ advert.getPostedByEmlakUser().getFirstName() + " kullanıcısı tarafından "
                        + duration.toMinutes() +" dakika önce oluşturulmuştur.İlan " + advert.getView()+ " kere görüntülenmiştir.")
                .build();

    }
}
