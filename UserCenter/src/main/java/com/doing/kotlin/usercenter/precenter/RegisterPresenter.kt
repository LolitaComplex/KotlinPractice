package com.doing.kotlin.usercenter.precenter

import com.doing.kotlin.baselib.data.rx.BaseSubscriber
import com.doing.kotlin.baselib.ext.execute
import com.doing.kotlin.baselib.ext.executeAndShowProgress
import com.doing.kotlin.baselib.presenter.BasePresenter
import com.doing.kotlin.baselib.utils.ToastUtil
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
                    override fun onSuccess(t: Boolean) {
                        mView.onRegisterResult(t)
                    }

                    override fun onApiError(code: Int, message: String) {
                        ToastUtil.show(message)
                    }

                    override fun onFailure(e: Throwable) {
                        ToastUtil.show("注册失败")
                    }
                }, mProvider, mView)

    }
}