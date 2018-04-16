package com.doing.kotlin.baselib.common.ext

import com.doing.kotlin.baselib.common.data.net.BaseSubscriber
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

fun <T> Observable<T>.excute(subscriber: BaseSubscriber<T>): Subscription {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber)
}