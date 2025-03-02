package com.APM_web_service.APM_web.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.*;
import java.io.IOException;

public class onem2m_get {
    public static String MobiusGetMethod() {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("http://114.71.220.59:2021/Mobius/Ksensor_ubicomp/data?fu=2&ty=4&rcn=4&cra=20240426T010101crb=20240503T094003")

                .method("GET", null)
                .addHeader("Accept", "application/json")
                .addHeader("X-M2M-RI", "12345")
                .addHeader("X-M2M-Origin", "SOrigin")
                .build();

        StringBuilder responseBuilder = new StringBuilder();

        try (Response response = client.newCall(request).execute()) {
            if (response.code() != 200) {
                System.out.println("There was a problem. Status Code: " + response.code());
            }

            JSONObject obj = new JSONObject(response.body().string());

            JSONObject o1 = obj.getJSONObject("m2m:rsp");

            JSONArray a1 = o1.getJSONArray("m2m:cin");

            for (int i = 0; i < a1.length(); i++) {
                responseBuilder.append(a1.getJSONObject(i).getString("con"));
                responseBuilder.append("\n");
            }

        } catch (IOException io) {
            io.printStackTrace(System.out);
        }

        return responseBuilder.toString();
    }
}