package com.doing.kotlin.baselib.data.protocal

class BaseResponse<T>(val status: Int, val message: String,
                      val data: T) {

    var mJson: String = ""

}