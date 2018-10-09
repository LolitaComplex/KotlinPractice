package com.doing.kotlin.usercenter.precenter

import com.doing.kotlin.baselib.common.AppConfig
import com.doing.kotlin.baselib.data.db.User
import com.doing.kotlin.baselib.data.rx.BaseSubscriber
import com.doing.kotlin.baselib.ext.executeAndShowProgress
import com.doing.kotlin.baselib.presenter.BasePresenter
import com.doing.kotlin.baselib.utils.ToastUtil
import com.doing.kotlin.usercenter.data.protocal.UserInfo
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

    fun editUser(userIcon: String?, userName: String, gender: String, sign: String) {
        mUserService.editUser(userIcon, userName, gender, sign)
                .map {
                   AppConfig.sAccountService.update(User(it.id, it.userIcon,
                           it.userName, it.userGender, it.userMobile,
                           it.userSign))
                }
                .executeAndShowProgress(object : BaseSubscriber<Int>() {
                    override fun onSuccess(data: Int) {
                        mView.onEditUserResult()
                    }
                }, mProvider, mView)
    }
}