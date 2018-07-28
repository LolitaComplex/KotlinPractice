package com.doing.kotlin.usercenter.service

import com.doing.kotlin.usercenter.data.protocal.LoginReq
import com.doing.kotlin.usercenter.data.protocal.RegisterReq
import com.doing.kotlin.usercenter.data.protocal.UserInfo
import com.doing.kotlin.usercenter.data.repository.UserRepository
import com.doing.kotlin.usercenter.service.impl.UserService
import io.reactivex.Flowable
import javax.inject.Inject

class UserServiceImpl @Inject constructor(): UserService {

    override fun register(mobile: String, pwd: String, verifyCode: String): Flowable<Boolean> {
        return UserRepository.getUserApi().register(RegisterReq(mobile, pwd, verifyCode))
                .flatMap { Flowable.just(true) }
    }

    override fun login(mobile: String, pwd: String, pushId: String): Flowable<UserInfo> {
        return UserRepository.getUserApi().login(LoginReq(mobile, pwd, pushId))
    }
}