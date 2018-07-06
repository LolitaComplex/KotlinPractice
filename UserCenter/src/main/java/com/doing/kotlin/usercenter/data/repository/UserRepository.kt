package com.doing.kotlin.usercenter.data.repository

import com.doing.kotlin.usercenter.data.api.UserApi
import com.kotlin.provider.data.net.AppRetrofitFactory

class UserRepository private constructor(){

    companion object {
        fun getUserApi(): UserApi {
            return AppRetrofitFactory.sInstance.create(UserApi::class.java)
        }
    }
}