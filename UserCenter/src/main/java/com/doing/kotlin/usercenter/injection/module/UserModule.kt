package com.doing.kotlin.usercenter.injection.module

import com.doing.kotlin.baselib.common.injection.PerComponentScope
import com.doing.kotlin.usercenter.service.UserServiceImpl
import com.doing.kotlin.usercenter.service.impl.UserService
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