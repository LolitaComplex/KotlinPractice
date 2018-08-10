package com.doing.kotlin.usercenter.service.impl

import io.reactivex.Flowable

interface UploadService{

    fun getUploadToken() : Flowable<String>
}