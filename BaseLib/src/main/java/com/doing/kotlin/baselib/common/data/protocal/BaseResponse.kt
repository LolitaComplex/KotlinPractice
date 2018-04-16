package com.doing.kotlin.baselib.common.data.protocal

class BaseResponse<T>(val status: Int, val message: String,
                      val data: T) {

}