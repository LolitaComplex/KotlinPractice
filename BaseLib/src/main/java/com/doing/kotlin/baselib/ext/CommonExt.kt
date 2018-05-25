package com.doing.kotlin.baselib.ext

import android.widget.TextView
import com.doing.kotlin.baselib.data.rx.BaseSubscriber
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>, provider: LifecycleProvider<*>): Subscription {
    return this.compose(provider.bindToLifecycle())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber)
}

fun TextView.getTrimText(): String {
    return this.text.toString().trim()
}