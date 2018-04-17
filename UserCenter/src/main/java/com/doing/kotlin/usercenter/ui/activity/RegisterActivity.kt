package com.doing.kotlin.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.doing.kotlin.baselib.common.ext.getTrimText
import com.doing.kotlin.baselib.common.ui.activity.BaseMvpActivity
import com.doing.kotlin.baselib.common.utils.toast
import com.doing.kotlin.usercenter.R
import com.doing.kotlin.usercenter.injection.component.DaggerUserComponent
import com.doing.kotlin.usercenter.injection.module.UserModule
import com.doing.kotlin.usercenter.precenter.RegisterPresenter
import com.doing.kotlin.usercenter.precenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.intentFor
import java.util.*


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {

    companion object {
        const val KEY = "key"
        const val VALUE = "Value"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this

        mBtnGetCode.setOnClickListener(this)
        mBtnRegister.setOnClickListener(this)
    }

    override fun onRegisterResult(result: Boolean) {
        "注册成功".toast()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mBtnGetCode -> {
                startActivity(intentFor<TestActivity>(KEY to 1234, VALUE to  Arrays.asList("asd")))
            }
            R.id.mBtnRegister -> {
                val mobile = mEtTelNum.getTrimText()
                val pwd = mEtPassword.getTrimText()
                val ensurePwd = mEtEnsurePassword.getTrimText()

                if (pwd == ensurePwd) {
                    mPresenter.register(mobile, pwd, ensurePwd)
                } else {
                    "密码输入有误".toast()
                }
            }
        }
    }
}
