package com.doing.kotlin.usercenter.precenter

import com.doing.kotlin.baselib.data.rx.BaseSubscriber
import com.doing.kotlin.baselib.ext.executeAndShowProgress
import com.doing.kotlin.baselib.presenter.BasePresenter
import com.doing.kotlin.baselib.utils.ToastUtil
import com.doing.kotlin.usercenter.precenter.view.UserInfoView
import com.doing.kotlin.usercenter.service.impl.UploadService
import com.doing.kotlin.usercenter.service.impl.UserService
import javax.inject.Inject

class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var mUploadService: UploadService

    @Inject
    lateinit var mUserService: UserService

    fun getUploadToken() {
        mUploadService.getUploadToken()
                .executeAndShowProgress(object: BaseSubscriber<String>(){
                    override fun onSuccess(data: String) {
                        mView.onGetUploadTokenResult(data)
                    }

                    override fun onApiError(code: Int, message: String) {
                        ToastUtil.show(message)
                    }
                }, mProvider, mView)
    }
}