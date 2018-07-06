package com.kotlin.provider.data.net

import com.doing.kotlin.baselib.data.net.RetrofitFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppRetrofitFactory : RetrofitFactory(){

    companion object {
        val sInstance: RetrofitFactory by lazy{ AppRetrofitFactory() }
    }

    override fun initRetrofit(): Retrofit {
        val retrofit = super.initRetrofit()
        val builder = retrofit.newBuilder()
        val factories = builder.converterFactories()
        factories.forEach{
            if (it is GsonConverterFactory) {
                factories.remove(it)
            }
        }

        builder.addConverterFactory(SpecialGsonConverterFactory.create(GsonManager.sIntance.mGson))
        return builder.build()
    }
}