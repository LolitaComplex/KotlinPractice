package com.doing.kotlin.usercenter.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.ViewGroup
import com.doing.kotlin.baselib.utils.toast
import org.jetbrains.anko.*

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test)
        verticalLayout {
            padding = 30
            editText {
                hint = "用户名"
                textSize = 20.0f
                hintTextColor = Color.BLUE
            }

            editText{
                hint = "密码"
                textSize = 20.0f
                hintTextColor = Color.RED
            }

            button{
                text = "好爽"
                gravity = Gravity.CENTER
                textSize = 20.0f
                textColor = Color.BLACK
                width = ViewGroup.LayoutParams.MATCH_PARENT
                height = ViewGroup.LayoutParams.WRAP_CONTENT
                padding = 10
//                onClick {
//                    "Kotlin是全世界的最好语言".toast()
//                }
            }
        }

        intent.getIntExtra(RegisterActivity.KEY, 0).toString().toast()

        Handler().postDelayed({
            intent.getStringArrayListExtra(RegisterActivity.VALUE)
                    .forEach { it.toast() }
        }, 1000)
    }
}
