package com.doing.kotlin.baselib.data.rx

import android.util.Log
import com.doing.kotlin.baselib.common.AppConfig
import com.doing.kotlin.baselib.utils.ToastUtil
import com.google.gson.JsonSyntaxException
import io.reactivex.FlowableSubscriber
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.Exceptions
import io.reactivex.internal.operators.flowable.FlowableInternalHelper
import io.reactivex.internal.subscriptions.SubscriptionHelper
import org.reactivestreams.Subscription
import java.util.concurrent.atomic.AtomicReference

abstract class BaseSubscriber<T>: FlowableSubscriber<T>, Subscription,
        AtomicReference<Subscription>(), SingleObserver<T> {

    protected lateinit var mSubscription: Subscription

    override fun onSubscribe(d: Disposable) {
    }

    override fun onSubscribe(s: Subscription) {
        if (SubscriptionHelper.setOnce(this, s)) {
            try {
                val onSubscribe = FlowableInternalHelper.RequestMax.INSTANCE
                onSubscribe.accept(this)
            } catch (ex: Throwable) {
                Exceptions.throwIfFatal(ex)
                s.cancel()
                onError(ex)
            }
        }
        mSubscription = s
    }

    override fun cancel() {
        SubscriptionHelper.cancel(this)
    }

    override fun request(n: Long) {
        get().request(n)
    }

    override fun onNext(data: T) {
        onSuccess(data)
    }

    override fun onSuccess(data: T){
        Log.i(AppConfig.Constant.NET_JSON_TAG, "Data：${data.toString()}")
    }
    open fun onApiError(code: Int, message: String) {}
    open fun onFailure(e: Throwable) {
        ToastUtil.show("网络错误")
    }
    override fun onComplete() {}

    override fun onError(e: Throwable) {
        when (e) {
            is ApiException -> {
                Log.i(AppConfig.Constant.NET_JSON_TAG, "Code：${e.mCode} \t Message：${e.mMessage}")
                onApiError(e.mCode, e.mMessage)
            }
            is JsonSyntaxException -> {
                Log.w(AppConfig.Constant.NET_JSON_TAG, e.message)
                onFailure(e)
            }
            else -> {
                Log.w(AppConfig.Constant.NET_JSON_TAG, e.message, e)
                onFailure(e)
            }
        }
    }

}
