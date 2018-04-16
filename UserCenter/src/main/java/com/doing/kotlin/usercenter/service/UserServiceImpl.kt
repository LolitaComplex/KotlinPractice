package com.doing.kotlin.usercenter.service

import com.doing.kotlin.usercenter.data.repository.UserRepository
import com.doing.kotlin.usercenter.service.impl.UserService
import rx.Observable

class UserServiceImpl: UserService {

    override fun register(mobil: String, verifyCode: String): Observable<Boolean> {
        return UserRepository.getUserApi().register()
    }
}