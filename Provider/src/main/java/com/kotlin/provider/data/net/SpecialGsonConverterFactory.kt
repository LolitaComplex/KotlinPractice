package com.kotlin.provider.data.net

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type

class SpecialGsonConverterFactory private constructor(val mGson: Gson): Converter.Factory() {

    companion object {
        fun create(gson: Gson): SpecialGsonConverterFactory {
            return SpecialGsonConverterFactory(gson)
        }

        fun create() : SpecialGsonConverterFactory {
            return create(GsonBuilder().create())
        }
    }

    private val mGsonConverterFactory: GsonConverterFactory = GsonConverterFactory.create(mGson)

    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>,
                                       retrofit: Retrofit): Converter<ResponseBody, *> {
        return ApiResponseConverter<Any>(mGson, type)
    }

    override fun requestBodyConverter(type: Type,
                                      parameterAnnotations: Array<Annotation>,
                                      methodAnnotations: Array<Annotation>, retrofit: Retrofit): Converter<*, RequestBody>? {
        return mGsonConverterFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
    }
}