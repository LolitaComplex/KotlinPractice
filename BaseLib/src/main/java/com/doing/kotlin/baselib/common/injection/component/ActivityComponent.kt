package com.doing.kotlin.baselib.common.injection.component

import android.app.Activity
import com.doing.kotlin.baselib.common.injection.ActivityScope
import com.doing.kotlin.baselib.common.injection.module.ActivityModule
import dagger.Component

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {
    fun activity(): Activity
}