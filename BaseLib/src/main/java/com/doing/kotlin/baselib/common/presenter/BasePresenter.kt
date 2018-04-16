package com.doing.kotlin.baselib.common.presenter

import com.doing.kotlin.baselib.common.presenter.view.BaseView

open class BasePresenter<T : BaseView>{
    lateinit var mView: T
}
