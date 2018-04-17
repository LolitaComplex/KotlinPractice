package com.doing.kotlin.usercenter.service

import com.doing.kotlin.baselib.common.data.rx.BaseException
import com.doing.kotlin.usercenter.data.protocal.RegisterReq
import com.doing.kotlin.usercenter.data.repository.UserRepository
import com.doing.kotlin.usercenter.service.impl.UserService
import rx.Observable
import javax.inject.Inject

class UserServiceImpl @Inject constructor(): UserService {

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {

        return UserRepository.getUserApi().register(RegisterReq(mobile, pwd, verifyCode))
                .flatMap {
                    if (it.status == 0) {
                        Observable.just(true)
                    } else {
                        Observable.error(BaseException(it.status, it.message))
                    }
                }
    }
}