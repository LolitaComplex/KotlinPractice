package com.doing.kotlin.usercenter.data.api

import com.doing.kotlin.baselib.data.protocal.BaseResponse
import com.doing.kotlin.usercenter.data.protocal.RegisterReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface UserApi {

//    @POST("userCenter/register")
//    fun register(@Field("mobile") mobile: String, @Field("pwd") pwd: String,
//                 @Field("verifyCode") verifyCode: String): Observable<BaseResponse<String>>

    @POST("userCenter/register")
    fun register(@Body body: RegisterReq): Observable<BaseResponse<String>>
}