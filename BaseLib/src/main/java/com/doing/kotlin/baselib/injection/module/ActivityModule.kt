package com.doing.kotlin.baselib.injection.module

import android.app.Activity
import android.content.Context
import com.doing.kotlin.baselib.common.BaseApplication
import com.doing.kotlin.baselib.injection.ActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun providesActivity(): Activity {
        return activity
    }
}