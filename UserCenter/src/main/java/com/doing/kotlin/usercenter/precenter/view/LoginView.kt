package com.doing.kotlin.usercenter.precenter.view

import com.doing.kotlin.baselib.presenter.view.BaseView
import com.doing.kotlin.usercenter.data.protocal.UserInfo

interface LoginView : BaseView{

    fun onLoginResult(data: Long)
}