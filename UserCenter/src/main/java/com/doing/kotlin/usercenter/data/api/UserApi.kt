package com.doing.kotlin.usercenter.data.api

import com.doing.kotlin.usercenter.data.protocal.RegisterReq
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {

    @POST("userCenter/register")
    fun register(@Body body: RegisterReq): Flowable<String>

    @GET("userCenter/login")
    fun login(@Query("mobile") mobile: String, @Query("pwd") pwd: String): Flowable<String>
}