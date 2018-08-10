package com.doing.kotlin.usercenter.data.api

import io.reactivex.Flowable
import retrofit2.http.POST

interface UploadApi {

    @POST("common/getUploadToken")
    fun getUploadToken() : Flowable<String>
}