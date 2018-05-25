package com.doing.kotlin.baselib.presenter

import android.content.Context
import com.doing.kotlin.baselib.presenter.view.BaseView
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

open class BasePresenter<T : BaseView>{
    lateinit var mView: T

    @Inject
    lateinit var mProvider : LifecycleProvider<*>

    @Inject
    lateinit var mContext: Context
}
