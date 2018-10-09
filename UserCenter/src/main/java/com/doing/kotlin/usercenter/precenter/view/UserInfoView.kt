package com.doing.kotlin.usercenter.precenter.view

import com.doing.kotlin.baselib.presenter.view.BaseView

interface UserInfoView : BaseView {

    fun onGetUploadTokenResult(token: String)

    fun onEditUserResult()
}