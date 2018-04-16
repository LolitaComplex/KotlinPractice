package com.doing.kotlin.usercenter.service.impl

import rx.Observable

interface UserService {

    fun register(mobil: String, verifyCode: String): Observable<Boolean>

}