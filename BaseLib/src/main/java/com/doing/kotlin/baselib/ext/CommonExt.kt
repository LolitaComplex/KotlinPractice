package com.doing.kotlin.baselib.ext

import android.widget.TextView
import com.doing.kotlin.baselib.data.rx.BaseSubscriber
import com.trello.rxlifecycle2.LifecycleProvider
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Flowable<T>.execute(subscriber: BaseSubscriber<T>, provider: LifecycleProvider<*>) {
    this.compose(provider.bindToLifecycle())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber)
}

fun TextView.getTrimText(): String {
    return this.text.toString().trim()
}