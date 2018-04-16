package com.doing.kotlin.baselib.common

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        BaseApplication.mContext = this
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private lateinit var mContext: Context

        fun getContext(): Context {
            return mContext
        }
    }
}