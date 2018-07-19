package com.doing.kotlin.usercenter.injection.component

import com.doing.kotlin.baselib.injection.PerComponentScope
import com.doing.kotlin.baselib.injection.component.ActivityComponent
import com.doing.kotlin.usercenter.injection.module.UserModule
import com.doing.kotlin.usercenter.ui.activity.LoginActivity
import com.doing.kotlin.usercenter.ui.activity.RegisterActivity
import dagger.Component

@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(UserModule::class)])
interface UserComponent {

    fun inject(activity: RegisterActivity)

    fun inject(activity: LoginActivity)
}