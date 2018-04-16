package com.doing.kotlin.usercenter.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.doing.kotlin.baselib.common.utils.toast
import com.doing.kotlin.usercenter.R
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.intentFor
import java.util.*



class RegisterActivity : AppCompatActivity() {

    companion object {
        const val KEY = "key"
        const val VALUE = "Value"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mBtnGetCode.setOnClickListener {
            "啦啦啦".toast()
            startActivity(intentFor<TestActivity>(KEY to 12,
                    VALUE to Arrays.asList("list")))
        }
    }
}
