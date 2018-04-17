package com.doing.kotlin.baselib.common.ui.activity

import android.os.Bundle
import com.doing.kotlin.baselib.common.BaseApplication
import com.doing.kotlin.baselib.common.injection.component.ActivityComponent
import com.doing.kotlin.baselib.common.injection.component.DaggerActivityComponent
import com.doing.kotlin.baselib.common.injection.module.ActivityModule
import com.doing.kotlin.baselib.common.presenter.BasePresenter
import com.doing.kotlin.baselib.common.presenter.view.BaseView
import javax.inject.Inject

open class BaseMvpActivity<T: BasePresenter<*>> : BaseActivity(), BaseView {

    lateinit var mActivityComponent: ActivityComponent

    @Inject
    protected lateinit var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).mAppComponent)
                .activityModule(ActivityModule(this))
                .build()

    }

    override fun showLoading() {}

    override fun hiddenLoading() {}

    override fun onError() {}

}