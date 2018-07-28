package com.doing.kotlin.usercenter.service.impl

import com.doing.kotlin.usercenter.data.protocal.UserInfo
import io.reactivex.Flowable

interface UserService {

    fun register(mobile: String, pwd: String, verifyCode: String): Flowable<Boolean>

    fun login(mobile: String, pwd: String, pushId: String): Flowable<UserInfo>
}