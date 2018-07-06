package com.doing.kotlin.baselib.injection.component

import android.app.Activity
import android.content.Context
import com.doing.kotlin.baselib.injection.ActivityScope
import com.doing.kotlin.baselib.injection.module.ActivityModule
import com.doing.kotlin.baselib.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle2.LifecycleProvider
import dagger.Component

@ActivityScope
@Component(dependencies = [(AppComponent::class)],
        modules = [(LifecycleProviderModule::class), (ActivityModule::class)])
interface ActivityComponent {
    fun activity(): Activity
    fun context(): Context
    fun lifecycleProviders(): LifecycleProvider<*>
}