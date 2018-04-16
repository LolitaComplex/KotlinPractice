package com.doing.kotlin.usercenter.ui.activity

import android.os.Bundle
import com.doing.kotlin.baselib.common.ui.activity.BaseMvpActivity
import com.doing.kotlin.baselib.common.utils.toast
import com.doing.kotlin.usercenter.R
import com.doing.kotlin.usercenter.precenter.RegisterPresenter
import com.doing.kotlin.usercenter.precenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.*


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {

    companion object {
        const val KEY = "key"
        const val VALUE = "Value"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mPresenter = RegisterPresenter()
        mPresenter.mView = this


        mBtnGetCode.setOnClickListener {
            "啦啦啦".toast()
            startActivity(intentFor<TestActivity>(KEY to 12,
                    VALUE to Arrays.asList("list")))
        }

        mBtnRegister.setOnClickListener {
            mPresenter.register("1351283910", "1231")
        }
    }

    override fun onRegisterResult(result: Boolean) {
        "注册成功".toast()
    }

}
