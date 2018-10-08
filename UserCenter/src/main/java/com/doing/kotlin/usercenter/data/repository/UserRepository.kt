package com.doing.kotlin.usercenter.data.repository

import com.doing.kotlin.baselib.common.AppConfig
import com.doing.kotlin.usercenter.data.api.UploadApi
import com.doing.kotlin.usercenter.data.api.UserApi

object UserRepository{

    fun getUserApi(): UserApi {
        return AppConfig.sRetrofitFactory.create(UserApi::class.java)
    }

    fun getUploadApi(): UploadApi{
        return AppConfig.sRetrofitFactory.create(UploadApi::class.java)
    }
}