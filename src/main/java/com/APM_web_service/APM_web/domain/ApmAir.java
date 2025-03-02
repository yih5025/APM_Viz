package com.APM_web_service.APM_web.domain;

import jakarta.persistence.*;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "APM_air_data_table")
public class ApmAir {
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getNpm() {
        return npm;
    }

    public void setNpm(Integer npm) {
        this.npm = npm;
    }

    public Integer getBottomAngle() {
        return bottomAngle;
    }

    public void setBottomAngle(Integer bottomAngle) {
        this.bottomAngle = bottomAngle;
    }

    public Integer getPm1Position() {
        return pm1Position;
    }

    public void setPm1Position(Integer pm1Position) {
        this.pm1Position = pm1Position;
    }

    public Integer getPm2Position() {
        return pm2Position;
    }

    public void setPm2Position(Integer pm2Position) {
        this.pm2Position = pm2Position;
    }

    public Integer getPm3Position() {
        return pm3Position;
    }

    public void setPm3Position(Integer pm3Position) {
        this.pm3Position = pm3Position;
    }

    public Integer getPm1Pm25() {
        return pm1Pm25;
    }

    public void setPm1Pm25(Integer pm1Pm25) {
        this.pm1Pm25 = pm1Pm25;
    }

    public Integer getPm2Pm25() {
        return pm2Pm25;
    }

    public void setPm2Pm25(Integer pm2Pm25) {
        this.pm2Pm25 = pm2Pm25;
    }

    public Integer getPm3Pm25() {
        return pm3Pm25;
    }

    public void setPm3Pm25(Integer pm3Pm25) {
        this.pm3Pm25 = pm3Pm25;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    public BigDecimal getOzone() {
        return ozone;
    }

    public void setOzone(BigDecimal ozone) {
        this.ozone = ozone;
    }

    public BigDecimal getCo() {
        return co;
    }

    public void setCo(BigDecimal co) {
        this.co = co;
    }

    public BigDecimal getNo2() {
        return no2;
    }

    public void setNo2(BigDecimal no2) {
        this.no2 = no2;
    }

    public BigDecimal getSo2() {
        return so2;
    }

    public void setSo2(BigDecimal so2) {
        this.so2 = so2;
    }

    public BigDecimal getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(BigDecimal windDirection) {
        this.windDirection = windDirection;
    }

    public BigDecimal getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(BigDecimal windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "npm")
    private Integer npm;

    @Column(name = "bottom_angle")
    private Integer bottomAngle;

    @Column(name = "pm1_position")
    private Integer pm1Position;

    @Column(name = "pm2_position")
    private Integer pm2Position;

    @Column(name = "pm3_position")
    private Integer pm3Position;

    @Column(name = "pm1_pm25")
    private Integer pm1Pm25;

    @Column(name = "pm2_pm25")
    private Integer pm2Pm25;

    @Column(name = "pm3_pm25")
    private Integer pm3Pm25;

    @Column(name = "temperature")
    private BigDecimal temperature;

    @Column(name = "humidity")
    private BigDecimal humidity;

    @Column(name = "ozone")
    private BigDecimal ozone;

    @Column(name = "co")
    private BigDecimal co;

    @Column(name = "no2")
    private BigDecimal no2;

    @Column(name = "so2")
    private BigDecimal so2;

    @Column(name = "wind_direction")
    private BigDecimal windDirection;

    @Column(name = "wind_speed")
    private BigDecimal windSpeed;
}
