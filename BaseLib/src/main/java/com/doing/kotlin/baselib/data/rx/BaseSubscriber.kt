package com.doing.kotlin.baselib.data.rx

import android.util.Log
import io.reactivex.FlowableSubscriber
import org.reactivestreams.Subscription

open abstract class BaseSubscriber<T>: FlowableSubscriber<T> {

    protected lateinit var mSubscription: Subscription

    companion object {
        private const val TAG = "BaseSubscriber"
    }

    override fun onSubscribe(s: Subscription) {
        mSubscription = s
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    abstract fun onSuccess(t: T)

    override fun onComplete() {}
    open fun onApiError(code: Int, message: String) {}
    open fun onFailure(e: Throwable) {}

    override fun onError(e: Throwable) {
        Log.e(TAG, e.message, e)
        if (e is BaseException) {
            onApiError(e.mCode, e.mMessage)
        } else {
            onFailure(e)
        }
    }


}
