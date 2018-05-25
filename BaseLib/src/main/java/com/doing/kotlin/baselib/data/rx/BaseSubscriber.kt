package com.doing.kotlin.baselib.data.rx

import android.util.Log
import rx.Subscriber

open class BaseSubscriber<T>: Subscriber<T>() {

    companion object {
        private const val TAG = "BaseSubscriber"
    }

    override fun onNext(t: T) {
    }

    override fun onCompleted() {
        Log.d(TAG, "onCompleted")
    }

    override fun onError(e: Throwable?) {
        Log.e(TAG, "onError", e)
    }
}
