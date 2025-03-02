package com.APM_web_service.APM_web;

import com.APM_web_service.APM_web.repository.AirJpaRepository;
import com.APM_web_service.APM_web.repository.AirRepository;
import com.APM_web_service.APM_web.service.AirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

@Configuration
public class AppConfig {
    private final AirJpaRepository airJpaRepository;

    @Autowired
    public AppConfig(AirJpaRepository airJpaRepository) {this.airJpaRepository = airJpaRepository;}

    @Bean
    public AirService airService() {return new AirService(airJpaRepository);}
}
