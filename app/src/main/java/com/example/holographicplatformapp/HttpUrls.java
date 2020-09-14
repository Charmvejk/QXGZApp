package com.example.holographicplatformapp;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/*网络请求
 * */
public class HttpUrls {
    public static String postXml(String url, String xmlStr) {
        RequestBody body = RequestBody.create(MediaType.parse("application/utf-8"), xmlStr);
        Request requestOk = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response;
        try {
            response = new OkHttpClient().newCall(requestOk).execute();
            String jsonString = response.body().string();
            if (response.isSuccessful()) {
                return jsonString;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "";
    }
}
