package com.doing.kotlin.baselib.common.ui.activity

import com.doing.kotlin.baselib.common.presenter.BasePresenter
import com.doing.kotlin.baselib.common.presenter.view.BaseView

open class BaseMvpActivity<T: BasePresenter<*>> : BaseActivity(), BaseView {

    protected lateinit var mPresenter: T


    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hiddenLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}