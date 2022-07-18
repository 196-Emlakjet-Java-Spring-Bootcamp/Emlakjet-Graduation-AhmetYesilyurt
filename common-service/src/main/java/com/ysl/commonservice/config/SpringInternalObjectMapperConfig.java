package com.ysl.commonservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class SpringInternalObjectMapperConfig {

    /*
  Java 8 date/time type `java.time.LocalDateTime` not supported by default:
   add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling

  https://stackoverflow.com/questions/38344534/cant-deserialize-object-containing-localdate-received-from-amqp-messaging/71544892#71544892

   */

    @Bean
    public Jackson2JsonMessageConverter messageConverter(ObjectMapper springInternalObjectMapper) {
        log.info("Updating spring's object mapper.");
        springInternalObjectMapper.findAndRegisterModules();
        return new Jackson2JsonMessageConverter(springInternalObjectMapper);
    }
}
