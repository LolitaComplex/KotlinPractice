package com.doing.kotlin.usercenter.precenter

import com.doing.kotlin.baselib.common.ext.excute
import com.doing.kotlin.baselib.common.data.net.BaseSubscriber
import com.doing.kotlin.baselib.common.presenter.BasePresenter
import com.doing.kotlin.usercenter.precenter.view.RegisterView
import com.doing.kotlin.usercenter.service.UserServiceImpl

class RegisterPresenter: BasePresenter<RegisterView>() {

    fun register(mobile: String, verifyCode: String) {
        val userService = UserServiceImpl()
        userService.register(mobile, verifyCode)
                .excute(object : BaseSubscriber<Boolean>() {
                    override fun onNext(t: Boolean) {

                    }
                })
    }
}