package com.doing.kotlin.usercenter.data.api

import com.doing.kotlin.baselib.common.data.protocal.BaseResponse
import com.doing.kotlin.usercenter.data.protocal.RegisterReq
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.Query
import rx.Observable

interface UserApi {

    @POST("userCenter/register")
    fun register(@Field): Observable<BaseResponse<String>>
}