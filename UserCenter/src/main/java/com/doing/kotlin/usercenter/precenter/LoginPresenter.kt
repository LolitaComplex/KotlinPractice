package com.doing.kotlin.usercenter.precenter

import com.doing.kotlin.baselib.data.rx.BaseSubscriber
import com.doing.kotlin.baselib.ext.execute
import com.doing.kotlin.baselib.ext.executeAndShowProgress
import com.doing.kotlin.baselib.presenter.BasePresenter
import com.doing.kotlin.baselib.utils.ToastUtil
import com.doing.kotlin.usercenter.data.protocal.UserInfo
import com.doing.kotlin.usercenter.precenter.view.LoginView
import com.doing.kotlin.usercenter.precenter.view.RegisterView
import com.doing.kotlin.usercenter.service.impl.UserService
import javax.inject.Inject

class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    companion object {
        private const val TAG = "LoginPresenter"
    }

    @Inject
    lateinit var mUserService: UserService

    fun login(mobile: String, pwd: String, pushId: String) {
        mUserService.login(mobile, pwd, pushId)
                .executeAndShowProgress(object : BaseSubscriber<UserInfo>() {

                    override fun onSuccess(data: UserInfo) {
                        mView.onLoginResult(data)
                    }

                    override fun onApiError(code: Int, message: String) {
                        ToastUtil.show(message)
                    }

                    override fun onFailure(e: Throwable) {
                        ToastUtil.show("登录失败")
                    }
                }, mProvider, mView)
    }
}