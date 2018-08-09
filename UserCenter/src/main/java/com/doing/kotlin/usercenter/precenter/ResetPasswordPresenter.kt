package com.doing.kotlin.usercenter.precenter

import com.doing.kotlin.baselib.data.rx.BaseSubscriber
import com.doing.kotlin.baselib.ext.execute
import com.doing.kotlin.baselib.presenter.BasePresenter
import com.doing.kotlin.baselib.utils.ToastUtil
import com.doing.kotlin.usercenter.precenter.view.ResetPasswordView
import com.doing.kotlin.usercenter.service.impl.UserService
import javax.inject.Inject

class ResetPasswordPresenter @Inject constructor() : BasePresenter<ResetPasswordView>() {

    @Inject
    lateinit var mUserSerivce: UserService

    fun resetPwd(mobile: String, password: String) {
        mUserSerivce.resetPwd(mobile, password)
                .execute(object : BaseSubscriber<Boolean>(){
                    override fun onSuccess(data: Boolean) {
                        mView.onResetPwdResult()
                    }

                    override fun onApiError(code: Int, message: String) {
                        ToastUtil.show(message)
                    }
                }, mProvider)
    }
}
