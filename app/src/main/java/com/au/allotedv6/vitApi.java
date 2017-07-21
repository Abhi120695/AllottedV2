package com.au.allotedv6;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Abhishek-Pc on 11-03-2016.
 */
public interface vitApi {
    @FormUrlEncoded
    @POST("/api/v2/{campus}/login")
    void login(@Path("campus") String str, @Field("regno") String str2, @Field("dob") String str3, @Field("mobile") String str4, Callback<Login> callback);

}
