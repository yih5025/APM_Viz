package com.APM_web_service.APM_web.service;

// Created by J. Yun, SCH Univ., yun@sch.ac.kr

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.MediaType;
import okhttp3.Response;
import org.json.*;
import java.io.IOException;

public class onem2m_post {
    public static String MobiusPostMethod(String postion) {

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        MediaType mediaType = MediaType.parse("application/vnd.onem2m-res+json; ty=4");
        RequestBody body = RequestBody.create(mediaType, "{\"m2m:cin\": {\"con\": \"" + postion + "\"}}");
        Request request = new Request.Builder()
                .url("http://203.253.128.161:7579/Mobius/sch19999999/ledm")
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .addHeader("X-M2M-RI", "12345")
                .addHeader("X-M2M-Origin", "XXXXXXXXXXX") // aei = change to YOUR aei
                .addHeader("Content-Type", "application/vnd.onem2m-res+json; ty=4")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.code() != 201) {
                System.out.println("There was a problem. Status Code: " + response.code());
                return null;
            }

            JSONObject obj = new JSONObject(response.body().string());

            JSONObject o1 = obj.getJSONObject("m2m:cin");
            System.out.println(o1);

            String con = o1.getString("con");
            System.out.println(con);
        } catch (IOException io) {
            io.printStackTrace(System.out);
        }
        return null;
    }
}
