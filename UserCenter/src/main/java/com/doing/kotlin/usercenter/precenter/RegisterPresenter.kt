package com.doing.kotlin.usercenter.precenter

import com.doing.kotlin.baselib.common.ext.excute
import com.doing.kotlin.baselib.common.data.rx.BaseSubscriber
import com.doing.kotlin.baselib.common.presenter.BasePresenter
import com.doing.kotlin.usercenter.precenter.view.RegisterView
import com.doing.kotlin.usercenter.service.impl.UserService
import javax.inject.Inject

class RegisterPresenter @Inject constructor(): BasePresenter<RegisterView>() {

    @Inject
    lateinit var mUserService: UserService

    fun register(mobile: String, pwd: String, verifyCode: String) {
        mUserService.register(mobile, pwd, verifyCode)
                .excute(object : BaseSubscriber<Boolean>() {
                    override fun onNext(t: Boolean) {
                        mView.onRegisterResult(t)
                    }
                })
    }
}