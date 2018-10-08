package com.doing.kotlin.usercenter.precenter

import com.doing.kotlin.baselib.data.rx.BaseSubscriber
import com.doing.kotlin.baselib.ext.execute
import com.doing.kotlin.baselib.presenter.BasePresenter
import com.doing.kotlin.baselib.utils.ToastUtil
import com.doing.kotlin.usercenter.precenter.view.ForgetPasswordView
import com.doing.kotlin.usercenter.service.impl.UserService
import io.reactivex.disposables.Disposable

import javax.inject.Inject

class ForgetPasswordPresenter @Inject constructor() : BasePresenter<ForgetPasswordView>(){

    companion object {
        const val TAG = "ForgetPasswordPresenter"
    }

    @Inject
    lateinit var mUserService: UserService

    fun validate(mobile: String, verifyCode: String) {
        mUserService.forgetPwd(mobile, verifyCode)
                .execute(object : BaseSubscriber<Boolean>(){

                    override fun onSuccess(data: Boolean) {
                        mView.onValidateResult()
                    }

                    override fun onApiError(code: Int, message: String) {
                        ToastUtil.show(message)
                    }

                    override fun onFailure(e: Throwable) {
                        ToastUtil.show("网络错误")
                    }
                }, mProvider)
    }
}
