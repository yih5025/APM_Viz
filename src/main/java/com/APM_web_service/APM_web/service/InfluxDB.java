package com.APM_web_service.APM_web.service;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class InfluxDB {
    private static char[] token = "DcKEp5NVq6hrMfrvjbEBkwW3TdqKtukNrnLoSwijEUxhN90454sSnHGubrOlm8ZJdiJDmb3tPSj5ZylOdIZ1cw==".toCharArray();
    private static String org = "Ubicomp";
    private static String bucket = "Ubicomp-Bucket";

    private static double firstPm25Value = -1;
    private static double firstCoValue = -1;
    private static double firstNo2Value = -1;
    private static double firstO3Value = -1;
    private static double firstSo2Value = -1;
    private static double firstTemperatureValue = -1;
    private static double firstHumidityValue = -1;
    private static double firstWindSpeedValue = -1;


    public static void saveMobiusDataToInfluxDB(String parsedData) {
        String[] dataLines = parsedData.split("\n");

        InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://localhost:8086", token, org, bucket);
        WriteApi writeApi = influxDBClient.getWriteApi();

        for (String dataLine : dataLines) {
            String[] fields = dataLine.split(",");
            String dataTimeString = fields[0];

            LocalDateTime dateTime = LocalDateTime.parse(dataTimeString, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            Instant instant = dateTime.toInstant(ZoneOffset.UTC);
            long timeMillis = instant.toEpochMilli();

            String stationName = "Ubicomp"; // Assuming station name is Ubicomp
            int npm = Integer.parseInt(fields[1]);
            int bottomAngle = Integer.parseInt(fields[2]);
            int pm1Angle = Integer.parseInt(fields[3]);
            int pm2Angle = Integer.parseInt(fields[4]);
            int pm3Angle = Integer.parseInt(fields[5]);
            int pm1Dust = Integer.parseInt(fields[6]);
            int pm2Dust = Integer.parseInt(fields[7]);
            int pm3Dust = Integer.parseInt(fields[8]);
            double temperature = Double.parseDouble(fields[9]);
            double humidity = Double.parseDouble(fields[10]);
            double ozone = Double.parseDouble(fields[11]);
            double co = Double.parseDouble(fields[12]);
            double no2 = Double.parseDouble(fields[13]);
            double so2 = Double.parseDouble(fields[14]);
            double windDirection = Double.parseDouble(fields[15]);
            double windSpeed = Double.parseDouble(fields[16]);

            if (firstPm25Value == -1) {
                firstPm25Value = pm1Dust;
                System.out.println(firstPm25Value);
            }
            if (firstCoValue == -1) {
                firstCoValue = co;
                System.out.println(firstCoValue);
            }
            if (firstNo2Value == -1) {
                firstNo2Value = no2;
                System.out.println(firstNo2Value);
            }
            if (firstO3Value == -1) {
                firstO3Value = ozone;
                System.out.println(firstO3Value);
            }
            if (firstSo2Value == -1) {
                firstSo2Value = so2;
                System.out.println(firstSo2Value);
            }
            if (firstTemperatureValue == -1) {
                firstTemperatureValue = temperature;
                System.out.println(firstTemperatureValue);
            }
            if (firstHumidityValue == -1) {
                firstHumidityValue = humidity;
                System.out.println(firstHumidityValue);
            }
            if (firstWindSpeedValue == -1) {
                firstWindSpeedValue = windSpeed;
                System.out.println(firstWindSpeedValue);
            }

            Point point = Point.measurement("air_quality")
                    .addTag("station_name", stationName)
                    .addField("npm", npm)
                    .addField("bottom_angle", bottomAngle)
                    .addField("pm1_angle", pm1Angle)
                    .addField("pm2_angle", pm2Angle)
                    .addField("pm3_angle", pm3Angle)
                    .addField("pm1_dust", pm1Dust)
                    .addField("pm2_dust", pm2Dust)
                    .addField("pm3_dust", pm3Dust)
                    .addField("temperature", temperature)
                    .addField("humidity", humidity)
                    .addField("ozone", ozone)
                    .addField("co", co)
                    .addField("no2", no2)
                    .addField("so2", so2)
                    .addField("wind_direction", windDirection)
                    .addField("wind_speed", windSpeed)
                    .time(timeMillis, WritePrecision.MS);

            writeApi.writePoint(point);
            System.out.println("Data point written successfully:" + point.toLineProtocol());
        }

        influxDBClient.close();
    }

    public static void saveAirKoreaDataToInfluxDB(String parsedData) {

        String[] dataLines = parsedData.split("\n");

        InfluxDBClient influxDBClient = InfluxDBClientFactory.create("http://localhost:8086", token, org, bucket);

        WriteApi writeApi = influxDBClient.getWriteApi();

        for (String dataLine : dataLines) {
            String[] fields = dataLine.split(",");
            String dataTimeString = fields[0].split("=")[1];

            LocalDateTime dateTime = LocalDateTime.parse(dataTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            Instant instant = dateTime.toInstant(ZoneOffset.UTC);
            long timeMillis = instant.toEpochMilli();

            String stationName = fields[1].split("=")[1];
            double so2Value = Double.parseDouble(fields[2].split("=")[1]);
            double coValue = Double.parseDouble(fields[3].split("=")[1]);
            double o3Value = Double.parseDouble(fields[4].split("=")[1]);
            double no2Value = Double.parseDouble(fields[5].split("=")[1]);
            double pm10Value = Double.parseDouble(fields[6].split("=")[1]);
            double pm25Value = Double.parseDouble(fields[7].split("=")[1]);

            Point point = Point.measurement("air_quality")
                    .addTag("station_name", stationName)
                    .addField("so2_value", so2Value)
                    .addField("co_value", coValue)
                    .addField("o3_value", o3Value)
                    .addField("no2_value", no2Value)
                    .addField("pm10_value", pm10Value)
                    .addField("pm25_value", pm25Value)
                    .time(timeMillis, WritePrecision.MS);

            writeApi.writePoint(point);
//            System.out.println("Data point written successfully:" + point.toLineProtocol());
        }
        influxDBClient.close();
    }

    public static double getFirstPm25Value() {
        return firstPm25Value;
    }
    public static double getFirstNo2Value() {
        return firstNo2Value;
    }
    public static double getFirstCoValue() {
        return firstCoValue;
    }
    public static double getFirstO3Value() {
        return firstO3Value;
    }
    public static double getFirstSo2Value() {
        return firstSo2Value;
    }
    public static double getFirstTemperatureValue() {
        return firstTemperatureValue;
    }
    public static double getFirstHumidityValue() {
        return firstHumidityValue;
    }
    public static double getFirstWindSpeedValue() {
        return firstWindSpeedValue;
    }

    public static void resetFirstPm25Values() {
        firstPm25Value = -1;
    }
    public static void resetFirstNo2Value() {
        firstNo2Value = -1;
    }
    public static void resetFirstCoValue() {
        firstCoValue = -1;
    }
    public static void resetFirstO3Value() {
        firstO3Value = -1;
    }
    public static void resetFirstSo2Value() {
        firstSo2Value = -1;
    }
    public static void resetFirstTemperatureValue() {
        firstTemperatureValue = -1;
    }
    public static void resetFirstHumidityValue() {
        firstHumidityValue = -1;
    }
    public static void resetFirstWindSpeedValue() {
        firstWindSpeedValue = -1;
    }

}
