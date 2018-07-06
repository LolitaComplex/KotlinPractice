package com.doing.kotlin.usercenter.service.impl

import com.doing.kotlin.baselib.data.rx.BaseException
import com.doing.kotlin.usercenter.data.protocal.RegisterReq
import com.doing.kotlin.usercenter.data.repository.UserRepository
import io.reactivex.Flowable
import javax.inject.Inject

class OtherServiceImpl @Inject constructor(): UserService {

    override fun register(mobile: String, pwd: String, verifyCode: String): Flowable<Boolean> {

        return UserRepository.getUserApi().register(RegisterReq(mobile, pwd, verifyCode))
                .flatMap {
                    if (it.status == 0) {
                        Flowable.just(false)
                    } else {
                        Flowable.error(BaseException(it.status, it.message))
                    }
                }
    }
}