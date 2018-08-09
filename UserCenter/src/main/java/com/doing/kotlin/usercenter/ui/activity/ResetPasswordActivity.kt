package com.doing.kotlin.usercenter.ui.activity

import android.support.v7.app.ActionBar
import com.doing.kotlin.baselib.ext.getTrimText
import com.doing.kotlin.baselib.ext.setClickEnable
import com.doing.kotlin.baselib.ui.activity.BaseMvpActivity
import com.doing.kotlin.baselib.utils.ToastUtil
import com.doing.kotlin.usercenter.R
import com.doing.kotlin.usercenter.injection.component.DaggerUserComponent
import com.doing.kotlin.usercenter.injection.module.UserModule
import com.doing.kotlin.usercenter.precenter.ResetPasswordPresenter
import com.doing.kotlin.usercenter.precenter.view.ResetPasswordView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.*

class ResetPasswordActivity : BaseMvpActivity<ResetPasswordPresenter>(), ResetPasswordView{


    override fun getLayoutId(): Int {
        return R.layout.activity_reset_pwd
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

        mBtnConfirm.setClickEnable(mEtPwd, mEtPwdConfirm, enableMethod = ::isEnable)

        mBtnConfirm.setOnClickListener {
            val mobile = intent.getStringExtra(ForgetPasswordActivity.MOBILE)
            val pwdText = mEtPwd.getTrimText()
            val pwdConfirmText = mEtPwdConfirm.getTrimText()
            if (mobile.isNotEmpty() && pwdText == pwdConfirmText) {
                mPresenter.resetPwd(mobile, pwdText)
            }
        }
    }

    override fun onResetPwdResult() {
        ToastUtil.show("重置成功")
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
    }

    private fun isEnable(): Boolean {
        return mEtPwd.getTrimText().isNotEmpty() &&
                mEtPwdConfirm.getTrimText().isNotEmpty()
    }
}
