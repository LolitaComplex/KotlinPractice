package com.doing.kotlin.baselib.common.ext

import android.widget.TextView
import com.doing.kotlin.baselib.common.data.rx.BaseSubscriber
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

fun <T> Observable<T>.excute(subscriber: BaseSubscriber<T>): Subscription {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber)
}

fun TextView.getTrimText(): String {
    return this.text.toString().trim()
}