package com.doing.kotlin.usercenter.ui.activity

import android.support.v7.app.ActionBar
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.doing.kotlin.baselib.ext.getTrimText
import com.doing.kotlin.baselib.ui.activity.BaseMvpActivity
import com.doing.kotlin.baselib.ui.widget.GeneralToolbar
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
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.textView
import java.util.*


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

//        mBtnGetCode.setOnClickListener(this)
//        mBtnGetCode.setOnVerifyBtnClick(object: VerifyButton.OnVerifyBtnClick{
//            override fun onClick() {
//                ToastUtil.show("获取验证码")
//            }
//        })
//        mBtnGetCode.setOnClickListener {
//            mBtnGetCode.requestSendVerifyNumber()
//        }
//        mBtnRegister.setOnClickListener(this)

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
//        when (v.id) {
//            R.id.mBtnGetCode -> {
////                startActivity(intentFor<TestActivity>(KEY to 1234, VALUE to  Arrays.asList("asd")))
//
//            }
//            R.id.mBtnRegister -> {
//                val mobile = mEtTelNum.getTrimText()
//                val verifyCode = mEtVerifyCode.getTrimText()
//                val pwd = mEtPassword.getTrimText()
//                val ensurePwd = mEtEnsurePassword.getTrimText()
//
//                if (pwd == ensurePwd) {
//                    mPresenter.register(mobile, pwd, verifyCode)
//                } else {
//                    "密码输入有误".toast()
//                }
//            }
//        }
    }
}
