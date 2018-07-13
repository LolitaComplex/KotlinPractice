package com.doing.kotlin.usercenter.data.api

import com.doing.kotlin.usercenter.data.protocal.RegisterReq
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("userCenter/register")
    fun register(@Body body: RegisterReq): Flowable<String>
}