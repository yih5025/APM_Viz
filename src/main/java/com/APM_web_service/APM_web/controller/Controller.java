package com.APM_web_service.APM_web.controller;

import com.APM_web_service.APM_web.service.AirKoreaAPI;
import com.APM_web_service.APM_web.service.AirService;
import com.APM_web_service.APM_web.service.InfluxDB;
import com.APM_web_service.APM_web.service.onem2m_get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@org.springframework.stereotype.Controller
public class Controller {
    private AirService airService;

    @Autowired
    public Controller(AirService airService) {
        this.airService = airService;
    }

    @GetMapping("/")
    public String Home(Model model) throws IOException {
        System.out.println("get:index");

        String AirKoreaParsedData = AirKoreaAPI.fetchDataAndParse();
        InfluxDB.saveAirKoreaDataToInfluxDB(AirKoreaParsedData);

        String MobiusParsedData = onem2m_get.MobiusGetMethod();
        InfluxDB.saveMobiusDataToInfluxDB(MobiusParsedData);
        airService.saveAirData(MobiusParsedData);

        model.addAttribute("firstPm25Value", InfluxDB.getFirstPm25Value());
        model.addAttribute("firstSo2Value", InfluxDB.getFirstSo2Value());
        model.addAttribute("firstNo2Value", InfluxDB.getFirstNo2Value());
        model.addAttribute("firstO3Value", InfluxDB.getFirstO3Value());
        model.addAttribute("firstCoValue", InfluxDB.getFirstCoValue());
        model.addAttribute("firstTemperatureValue", InfluxDB.getFirstTemperatureValue());
        model.addAttribute("firstHumidityValue", InfluxDB.getFirstHumidityValue());
        model.addAttribute("firstWindSpeedValue", InfluxDB.getFirstWindSpeedValue());

        System.out.println("First PM25 Value: " + InfluxDB.getFirstPm25Value());
        System.out.println("First SO2 Value: " + InfluxDB.getFirstSo2Value());
        System.out.println("First NO2 Value: " + InfluxDB.getFirstNo2Value());
        System.out.println("First O3 Value: " + InfluxDB.getFirstO3Value());
        System.out.println("First CO Value: " + InfluxDB.getFirstCoValue());
        System.out.println("First Temperature Value: " + InfluxDB.getFirstTemperatureValue());
        System.out.println("First Humidity Value: " + InfluxDB.getFirstHumidityValue());
        System.out.println("First Wind Speed Value: " + InfluxDB.getFirstWindSpeedValue());

        InfluxDB.resetFirstPm25Values();
        InfluxDB.resetFirstSo2Value();
        InfluxDB.resetFirstNo2Value();
        InfluxDB.resetFirstO3Value();
        InfluxDB.resetFirstCoValue();
        InfluxDB.resetFirstTemperatureValue();
        InfluxDB.resetFirstHumidityValue();
        InfluxDB.resetFirstWindSpeedValue();

        return "index";
    }
}
