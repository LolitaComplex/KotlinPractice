package com.doing.kotlin.baselib.common

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.doing.kotlin.baselib.common.injection.component.AppComponent
import com.doing.kotlin.baselib.common.injection.component.DaggerAppComponent
import com.doing.kotlin.baselib.common.injection.module.AppModule

class BaseApplication : Application() {

    lateinit var mAppComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        BaseApplication.mContext = this

        mAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private lateinit var mContext: Context

        fun getContext(): Context {
            return mContext
        }
    }
}