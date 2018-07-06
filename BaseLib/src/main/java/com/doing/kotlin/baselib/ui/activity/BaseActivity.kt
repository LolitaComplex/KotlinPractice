package com.doing.kotlin.baselib.ui.activity

import android.os.Bundle
import com.doing.kotlin.baselib.common.AppManager
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

open class BaseActivity: RxAppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.sInstance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.sInstance.finishActivity(this)
    }
}