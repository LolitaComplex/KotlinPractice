package com.doing.kotlin.usercenter.data.api

import com.doing.kotlin.usercenter.data.protocal.LoginReq
import com.doing.kotlin.usercenter.data.protocal.RegisterReq
import com.doing.kotlin.usercenter.data.protocal.UserInfo
import io.reactivex.Flowable
import retrofit2.http.*

interface UserApi {

    @POST("userCenter/register")
    fun register(@Body body: RegisterReq): Flowable<String>

    @POST("userCenter/login")
    fun login(@Body body: LoginReq): Flowable<UserInfo>

}