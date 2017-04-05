package com.example.kimhao.first_project.API;

/**
 * Created by KimHao on 05/04/2017.
 */

public class ApiUtils {
    public static final String BASE_URL = "https://api.stackexchange.com/2.2/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
