package com.doing.kotlin.usercenter.ui.activity

import android.support.v7.app.ActionBar
import android.view.MenuItem
import android.view.View
import com.doing.kotlin.baselib.ext.getTrimText
import com.doing.kotlin.baselib.ext.setClickEnable
import com.doing.kotlin.baselib.ui.activity.BaseMvpActivity
import com.doing.kotlin.baselib.utils.ToastUtil
import com.doing.kotlin.usercenter.R
import com.doing.kotlin.usercenter.injection.component.DaggerUserComponent
import com.doing.kotlin.usercenter.injection.module.UserModule
import com.doing.kotlin.usercenter.precenter.LoginPresenter
import com.doing.kotlin.usercenter.precenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity


class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView,  View.OnClickListener {

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        mToolbar = find(R.id.mToolbar)

        mBtnLogin.setClickEnable(mEtMobile, mEtPwd, enableMethod = ::isEnable)

        mBtnLogin.setOnClickListener(this)
        mTvForgetPwd.setOnClickListener(this)
    }

    override fun initActionBar(actionBar: ActionBar) {
        mToolbar?.setNavigationIcon(R.drawable.icon_back)
        mToolbar?.setNavigationOnClickListener {
            ToastUtil.show("返回")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_register -> startActivity<RegisterActivity>()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun injection() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

    override fun onLoginResult(data: Long) {
        startActivity<UserInfoActivity>()

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mBtnLogin -> {
                val mobile = mEtMobile.getTrimText()
                val password = mEtPwd.getTrimText()
                mPresenter.login(mobile, password, "")
            }
            R.id.mTvForgetPwd -> {
                startActivity<ForgetPasswordActivity>()
            }
        }
    }

    private fun isEnable(): Boolean{
        return mEtMobile.getTrimText().isNotEmpty() && mEtMobile.getTrimText().isNotEmpty()
    }
}
