package com.doing.kotlin.baselib.data.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppRetrofitFactory : RetrofitFactory(){

    override fun initRetrofit(): Retrofit {
        val retrofit = super.initRetrofit()
        val builder = retrofit.newBuilder()
        val factories = builder.converterFactories()
        factories.forEach{
            if (it is GsonConverterFactory) {
                factories.remove(it)
            }
        }

        builder.addConverterFactory(SpecialJsonConverterFactory.create(JsonManager.sInstance.mJson))
        return builder.build()
    }
}