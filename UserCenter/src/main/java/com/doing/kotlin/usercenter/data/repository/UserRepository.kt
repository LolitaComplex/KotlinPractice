package com.doing.kotlin.usercenter.data.repository

import com.doing.kotlin.usercenter.data.api.UploadApi
import com.doing.kotlin.usercenter.data.api.UserApi
import com.kotlin.provider.data.net.AppRetrofitFactory

object UserRepository{

    fun getUserApi(): UserApi {
        return AppRetrofitFactory.sInstance.create(UserApi::class.java)
    }

    fun getUploadApi(): UploadApi{
        return AppRetrofitFactory.sInstance.create(UploadApi::class.java)
    }
}