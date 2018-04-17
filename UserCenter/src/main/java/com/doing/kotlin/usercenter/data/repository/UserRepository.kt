package com.doing.kotlin.usercenter.data.repository

import com.doing.kotlin.baselib.common.data.net.RetrofitFactory
import com.doing.kotlin.usercenter.data.api.UserApi

class UserRepository private constructor(){

    companion object {
        fun getUserApi(): UserApi {
            return RetrofitFactory.sInstance.create(UserApi::class.java)
        }
    }
}