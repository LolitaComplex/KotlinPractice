package com.doing.kotlin.usercenter.precenter

import com.doing.kotlin.baselib.data.rx.BaseSubscriber
import com.doing.kotlin.baselib.ext.execute
import com.doing.kotlin.baselib.ext.executeAndShowProgress
import com.doing.kotlin.baselib.presenter.BasePresenter
import com.doing.kotlin.usercenter.precenter.view.RegisterView
import com.doing.kotlin.usercenter.service.impl.UserService
import javax.inject.Inject

class RegisterPresenter @Inject constructor(): BasePresenter<RegisterView>() {

    companion object {
        private const val TAG = "RegisterPresenter"
    }

    @Inject
    lateinit var mUserService: UserService

    fun register(mobile: String, pwd: String, verifyCode: String) {
        mUserService.register(mobile, pwd, verifyCode)
                .executeAndShowProgress(object : BaseSubscriber<Boolean>() {
                    override fun onNext(t: Boolean) {
                        mView.onRegisterResult(t)
                    }
                }, mProvider, mView)

    }
}