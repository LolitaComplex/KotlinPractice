package com.doing.kotlin.baselib.ext

import android.widget.TextView
import com.doing.kotlin.baselib.data.rx.BaseSubscriber
import com.doing.kotlin.baselib.presenter.view.BaseView
import com.doing.kotlin.baselib.utils.ToastUtil
import com.kotlin.base.utils.NetWorkUtils
import com.trello.rxlifecycle2.LifecycleProvider
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Flowable<T>.execute(subscriber: BaseSubscriber<T>, provider: LifecycleProvider<*>) {
    executeAndShowProgress(subscriber, provider, null)
}

fun <T> Flowable<T>.executeAndShowProgress(subscriber: BaseSubscriber<T>, provider: LifecycleProvider<*>, baseView: BaseView?) {
    this.compose(provider.bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .doOnSubscribe {
            if (!NetWorkUtils.isNetWorkAvailable()) {
                ToastUtil.show("网络不可用")
                it.cancel()
            } else {
                baseView?.showLoading()
            }
        }
        .subscribeOn(AndroidSchedulers.mainThread())
        .doFinally { baseView?.hiddenLoading() }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(subscriber)
}

fun TextView.getTrimText(): String {
    return this.text.toString().trim()
}