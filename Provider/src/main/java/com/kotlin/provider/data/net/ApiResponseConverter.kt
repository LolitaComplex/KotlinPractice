package com.kotlin.provider.data.net

import com.doing.kotlin.baselib.data.protocal.BaseResponse
import com.doing.kotlin.baselib.data.rx.BaseException
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException
import java.lang.reflect.Type

class ApiResponseConverter<T>(val gson: Gson, val type: Type) : Converter<ResponseBody, T> {

    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T? {
        val json = value.string()
        val result: BaseResponse<*>
        result = gson.fromJson(json, BaseResponse::class.java)
        result.mJson = json
        return dealResult(result)
    }

    private fun dealResult(result: BaseResponse<*>): T? {
        return if (result.status == 0) {
            gson.fromJson<T>(gson.toJson(result.data), this.type)
        } else {
            throw BaseException(result.status, result.message)
        }
    }
}