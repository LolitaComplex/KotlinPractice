package com.doing.kotlin.usercenter.service.impl

import io.reactivex.Flowable

interface UserService {

    fun register(mobile: String, pwd: String, verifyCode: String): Flowable<Boolean>

}