package com.doing.kotlin.baselib.common

import android.arch.persistence.room.Room
import com.doing.kotlin.baselib.data.db.AppDatabase
import com.doing.kotlin.baselib.data.net.AppRetrofitFactory
import com.doing.kotlin.baselib.data.net.RetrofitFactory
import com.doing.kotlin.baselib.utils.UiUtils

object AppConfig{

    // 网络管理器
    val sRetrofitFactory: RetrofitFactory by lazy{ AppRetrofitFactory() }

    // Activity栈管理器
    val sActivityManager: AppManager by lazy { AppManager() }

    // 数据库管理器
    val sAppDatabase : AppDatabase by lazy {
        Room.databaseBuilder(UiUtils.getContext(), AppDatabase::class.java, Constant.DB_NAME).build()
    }

    // 账户管理
    val sAccountService: AccountService by lazy {
        AccountService()
    }


    // 通用字段
    object Constant{

        const val TABLE_PREFS = "Kotlin_Practice_Sp"

        const val NET_JSON_TAG = "Network_Json"

        const val DB_NAME = "Kotlin_Db"

        const val HOST = "http://todayismonday.cn/Kotlin_Server/"

        const val IMAGE_SERVER_ADDRESS = "http://osea2fxp7.bkt.clouddn.com/"


    }
}