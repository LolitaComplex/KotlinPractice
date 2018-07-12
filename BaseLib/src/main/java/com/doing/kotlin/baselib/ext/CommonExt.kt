package com.doing.kotlin.baselib.ext

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.doing.kotlin.baselib.data.rx.BaseSubscriber
import com.doing.kotlin.baselib.presenter.view.BaseView
import com.doing.kotlin.baselib.ui.listener.SimpleTextWatcher
import com.doing.kotlin.baselib.utils.ToastUtil
import com.doing.kotlin.baselib.utils.NetWorkUtils
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

fun EditText.getTrimText(): String {
    return this.text.toString().trim()
}

fun Button.isClickEnable(vararg edits: TextView, enableMethod: () -> Boolean) {
    val button = this
    for (edit in edits) {
        edit.addTextChangedListener(object: SimpleTextWatcher(){

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val enable = enableMethod.invoke()
                button.isEnabled = enable
                button.isClickable = enable
                button.isLongClickable = enable
            }
        })
    }
}