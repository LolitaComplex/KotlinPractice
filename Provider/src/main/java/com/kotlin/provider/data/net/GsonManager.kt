package com.kotlin.provider.data.net

import com.google.gson.*
import com.google.gson.internal.bind.TreeTypeAdapter
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.ArrayList

class GsonManager private constructor(){

    companion object {
        val sIntance: GsonManager by lazy{ GsonManager() }
    }

    val mGson: Gson

    init {
        val builder = GsonBuilder()
        builder.registerTypeAdapterFactory(SpecialTypeAdapterFactory())
        mGson = builder.create()
    }

    internal class SpecialTypeAdapterFactory : TypeAdapterFactory {

        private val mJsonParser: Gson = GsonBuilder().create()
        private val mTypeList = ArrayList<Class<*>>()

        override fun <T> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T> {
            var deserializer: JsonDeserializer<T>? = null
            val rawType = type.rawType
            for (clazz in mTypeList) {
                if (rawType == clazz) {
                    deserializer = SpecialAdapter()
                    break
                }
            }
            return TreeTypeAdapter(null, deserializer, gson, type, this)
        }

        private inner class SpecialAdapter<T> : JsonDeserializer<T> {

            @Throws(JsonParseException::class)
            override fun deserialize(json: JsonElement, typeOfT: Type,
                                     context: JsonDeserializationContext): T? {
                try {
                    return mJsonParser.fromJson<T>(json, typeOfT)
                } catch (e: Exception) {
                    return null
                }
            }
        }


    }
}
