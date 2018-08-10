package com.doing.kotlin.usercenter.service

import com.doing.kotlin.usercenter.data.repository.UserRepository
import com.doing.kotlin.usercenter.service.impl.UploadService
import io.reactivex.Flowable
import javax.inject.Inject

class UploadServiceImpl @Inject constructor() : UploadService{

    override fun getUploadToken(): Flowable<String> {
        return UserRepository.getUploadApi().getUploadToken()
    }
}