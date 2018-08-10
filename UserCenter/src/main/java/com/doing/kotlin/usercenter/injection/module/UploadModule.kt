package com.doing.kotlin.usercenter.injection.module

import com.doing.kotlin.baselib.injection.PerComponentScope
import com.doing.kotlin.usercenter.service.UploadServiceImpl
import com.doing.kotlin.usercenter.service.impl.UploadService
import dagger.Module
import dagger.Provides

@Module
class UploadModule{

    @PerComponentScope
    @Provides
    fun providerUploadService(service: UploadServiceImpl) : UploadService{
        return service
    }
}
