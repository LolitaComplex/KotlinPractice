package com.doing.kotlin.usercenter.ui.activity

import android.support.v7.app.ActionBar
import com.doing.kotlin.baselib.ext.getTrimText
import com.doing.kotlin.baselib.ext.setClickEnable
import com.doing.kotlin.baselib.ui.activity.BaseMvpActivity
import com.doing.kotlin.baselib.utils.ToastUtil
import com.doing.kotlin.usercenter.R
import com.doing.kotlin.usercenter.injection.component.DaggerUserComponent
import com.doing.kotlin.usercenter.injection.module.UserModule
import com.doing.kotlin.usercenter.precenter.ForgetPasswordPresenter
import com.doing.kotlin.usercenter.precenter.view.ForgetPasswordView
import com.kotlin.base.widgets.VerifyButton
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

private val s = "mobile"

class ForgetPasswordActivity : BaseMvpActivity<ForgetPasswordPresenter>(), ForgetPasswordView {

    companion object {
        const val MOBILE = "mobile"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_forget_pwd
    }

    override fun injection() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

    override fun initActionBar(actionBar: ActionBar) {
        mToolbar?.setNavigationIcon(R.drawable.icon_back)
        mToolbar?.setNavigationOnClickListener {
            finish()
        }
    }

    override fun initView() {
        mToolbar = find(R.id.mToolbar)

        mBtnVerifyCode.setOnVerifyBtnClick(object: VerifyButton.OnVerifyBtnClick{
            override fun onClick() {
                ToastUtil.show("获取验证码")
            }
        })
        mBtnVerifyCode.setOnClickListener {
            mBtnVerifyCode.requestSendVerifyNumber()
        }

        mBtnNext.setClickEnable(mEtMobile, mEtVerifyCode, enableMethod = ::isEnable)
        mBtnNext.setOnClickListener {
            val mobile = mEtMobile.getTrimText()
            val verifyCode = mEtVerifyCode.getTrimText()
            mPresenter.validate(mobile, verifyCode)
        }
    }

    private fun isEnable(): Boolean {
        return mEtMobile.getTrimText().isNotEmpty() &&
                mEtVerifyCode.getTrimText().isNotEmpty()
    }


    override fun onValidateResult() {
        startActivity<ResetPasswordActivity>(ForgetPasswordActivity.MOBILE to mEtMobile.getTrimText())
    }
}
