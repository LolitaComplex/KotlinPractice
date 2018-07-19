package com.doing.kotlin.usercenter.precenter

import com.doing.kotlin.baselib.presenter.BasePresenter
import com.doing.kotlin.usercenter.precenter.view.LoginView
import com.doing.kotlin.usercenter.precenter.view.RegisterView
import com.doing.kotlin.usercenter.service.impl.UserService
import javax.inject.Inject

class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    companion object {
        private const val TAG = "RegisterPresenter"
    }

    @Inject
    lateinit var mUserService: UserService

    fun login(mobile: String, pwd: String) {

    }
}