package com.au.allotedv6;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Abhishek-Pc on 12-03-2016.
 */
public interface vitapi2 {

    @FormUrlEncoded
    @POST("/api/v2/{campus}/share")
    void share(@Path("campus") String str, @Field("regno") String str2, @Field("dob") String str3, @Field("mobile") String str4, @Field("receiver") String str5, Callback<Donloadeddata> callback);

}
