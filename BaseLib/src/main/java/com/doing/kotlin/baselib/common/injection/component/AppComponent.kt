package com.doing.kotlin.baselib.common.injection.component

import android.content.Context
import com.doing.kotlin.baselib.common.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {
    fun context(): Context
}