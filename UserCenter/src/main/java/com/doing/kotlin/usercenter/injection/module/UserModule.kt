package com.doing.kotlin.usercenter.injection.module

import com.doing.kotlin.baselib.injection.PerComponentScope
import com.doing.kotlin.usercenter.precenter.view.RegisterView
import com.doing.kotlin.usercenter.service.UserServiceImpl
import com.doing.kotlin.usercenter.service.impl.UserService
import com.doing.kotlin.usercenter.ui.activity.RegisterActivity
import dagger.Module
import dagger.Provides

@Module
class UserModule{

    @PerComponentScope
    @Provides
    fun providesUserService(service: UserServiceImpl): UserService {
        return service
    }

}