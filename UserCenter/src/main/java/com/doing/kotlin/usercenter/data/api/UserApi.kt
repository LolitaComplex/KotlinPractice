package com.doing.kotlin.usercenter.data.api

import com.doing.kotlin.usercenter.data.protocal.*
import io.reactivex.Flowable
import retrofit2.http.*

interface UserApi {

    @POST("userCenter/register")
    fun register(@Body body: RegisterReq): Flowable<String>

    @POST("userCenter/login")
    fun login(@Body body: LoginReq): Flowable<UserInfo>

    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body body: ForgetPasswordReq): Flowable<String>

    @POST("userCenter/resetPwd")
    fun resetPwd(@Body body: ResetPasswordReq): Flowable<String>

}