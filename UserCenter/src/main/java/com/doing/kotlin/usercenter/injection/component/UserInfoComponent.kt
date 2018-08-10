package com.doing.kotlin.usercenter.injection.component

import com.doing.kotlin.baselib.injection.PerComponentScope
import com.doing.kotlin.baselib.injection.component.ActivityComponent
import com.doing.kotlin.usercenter.injection.module.UploadModule
import com.doing.kotlin.usercenter.injection.module.UserModule
import com.doing.kotlin.usercenter.ui.activity.UserInfoActivity
import dagger.Component

@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [UserModule::class, UploadModule::class])
interface UserInfoComponent{

    fun inject(activity: UserInfoActivity)
}