package com.doing.kotlin.usercenter.ui.activity

import android.support.v7.app.ActionBar
import android.view.View
import com.doing.kotlin.baselib.ext.getTrimText
import com.doing.kotlin.baselib.ext.isClickEnable
import com.doing.kotlin.baselib.ui.activity.BaseMvpActivity
import com.doing.kotlin.baselib.utils.ToastUtil
import com.doing.kotlin.baselib.utils.toast
import com.doing.kotlin.usercenter.R
import com.doing.kotlin.usercenter.injection.component.DaggerUserComponent
import com.doing.kotlin.usercenter.injection.module.UserModule
import com.doing.kotlin.usercenter.precenter.RegisterPresenter
import com.doing.kotlin.usercenter.precenter.view.RegisterView
import com.kotlin.base.widgets.VerifyButton
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.find


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {

    companion object {
        const val KEY = "key"
        const val VALUE = "Value"
        private const val TAG = "RegisterActivity"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun initView() {
        mToolbar = find(R.id.mToolbar)

        mBtnVerifyCode.setOnVerifyBtnClick(object: VerifyButton.OnVerifyBtnClick{
            override fun onClick() {
                ToastUtil.show("获取验证码")
            }
        })

        mBtnRegister.isClickEnable(mEtMobile, mEtVerifyCode,
                mEtPwd, mEtPwdConfirm, enableMethod = ::isEnable)

        mBtnVerifyCode.setOnClickListener(this)
        mBtnRegister.setOnClickListener(this)

    }

    override fun initActionBar(actionBar: ActionBar) {
        super.initActionBar(actionBar)
        mToolbar?.setNavigationIcon(R.drawable.icon_back)
        mToolbar?.setNavigationOnClickListener {
            ToastUtil.show("返回")
        }
    }

    override fun injection() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

    override fun onRegisterResult(result: Boolean) {
        "注册成功".toast()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mBtnVerifyCode -> {
                mBtnVerifyCode.requestSendVerifyNumber()
            }
            R.id.mBtnRegister -> {
                val mobile = mEtMobile.getTrimText()
                val verifyCode = mEtVerifyCode.getTrimText()
                val pwd = mEtPwd.getTrimText()
                val ensurePwd = mEtPwdConfirm.getTrimText()

                if (pwd == ensurePwd) {
                    mPresenter.register(mobile, pwd, verifyCode)
                } else {
                    "密码输入有误".toast()
                }
            }
        }
    }

    private fun isEnable(): Boolean{
        return mEtMobile.text.isNotEmpty() &&
                mEtVerifyCode.text.isNotEmpty() &&
                mEtPwd.text.isNotEmpty() &&
                mEtPwdConfirm.text.isNotEmpty()
    }
}
