package com.APM_web_service.APM_web.service;

import com.APM_web_service.APM_web.domain.ApmAir;
import com.APM_web_service.APM_web.repository.AirJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AirService {
    private final AirJpaRepository airJpaRepository;

    public AirService(AirJpaRepository airJpaRepository) {
        this.airJpaRepository = airJpaRepository;
    }

    public void saveAirData(String parsedData) {
        List<ApmAir> airList = new ArrayList<>();

        String[] lines = parsedData.split("\n");
        for (String line : lines) {
            ApmAir airApm = convertToAir(line);
            airList.add(airApm);
        }

        airJpaRepository.saveAll(airList);
    }

    public ApmAir convertToAir(String line) {
        try {
            String[] fields = line.split(",");
            String dataTime = fields[0];
            Integer npm = Integer.parseInt(fields[1]);
            Integer bottomAngle = Integer.parseInt(fields[2]);
            Integer pm1Position = Integer.parseInt(fields[3]);
            Integer pm2Position = Integer.parseInt(fields[4]);
            Integer pm3Position = Integer.parseInt(fields[5]);
            Integer pm1Pm25 = Integer.parseInt(fields[6]);
            Integer pm2Pm25 = Integer.parseInt(fields[7]);
            Integer pm3Pm25 = Integer.parseInt(fields[8]);
            BigDecimal temperature = new BigDecimal(fields[9]);
            BigDecimal humidity = new BigDecimal(fields[10]);
            BigDecimal ozone = new BigDecimal(fields[11]);
            BigDecimal co = new BigDecimal(fields[12]);
            BigDecimal no2 = new BigDecimal(fields[13]);
            BigDecimal so2 = new BigDecimal(fields[14]);
            BigDecimal windDirection = new BigDecimal(fields[15]);
            BigDecimal windSpeed = new BigDecimal(fields[16]);

            ApmAir air = new ApmAir();
            air.setDate(LocalDateTime.parse(dataTime, DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
            air.setNpm(npm);
            air.setBottomAngle(bottomAngle);
            air.setPm1Position(pm1Position);
            air.setPm2Position(pm2Position);
            air.setPm3Position(pm3Position);
            air.setPm1Pm25(pm1Pm25);
            air.setPm2Pm25(pm2Pm25);
            air.setPm3Pm25(pm3Pm25);
            air.setTemperature(temperature);
            air.setHumidity(humidity);
            air.setOzone(ozone);
            air.setCo(co);
            air.setNo2(no2);
            air.setSo2(so2);
            air.setWindDirection(windDirection);
            air.setWindSpeed(windSpeed);

            return air;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<ApmAir> findAirList() {
        return airJpaRepository.findAll();
    }
}
