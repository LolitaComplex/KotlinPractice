package com.doing.kotlin.baselib.common.injection.module

import android.app.Activity
import android.content.Context
import com.doing.kotlin.baselib.common.BaseApplication
import com.doing.kotlin.baselib.common.injection.ActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ActivityModule(private val activity: Activity) {

    @ActivityScope
    @Provides
    fun providesActivity(): Activity {
        return activity
    }
}