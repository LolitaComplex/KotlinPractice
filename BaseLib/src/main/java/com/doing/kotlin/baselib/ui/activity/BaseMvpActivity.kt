package com.doing.kotlin.baselib.ui.activity

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.ActionBar
import android.view.Menu
import com.doing.kotlin.baselib.common.BaseApplication
import com.doing.kotlin.baselib.injection.component.ActivityComponent
import com.doing.kotlin.baselib.injection.component.DaggerActivityComponent
import com.doing.kotlin.baselib.injection.module.ActivityModule
import com.doing.kotlin.baselib.injection.module.LifecycleProviderModule
import com.doing.kotlin.baselib.presenter.BasePresenter
import com.doing.kotlin.baselib.presenter.view.BaseView
import com.doing.kotlin.baselib.ui.dialog.ProgressDialog
import com.doing.kotlin.baselib.ui.widget.GeneralToolbar
import javax.inject.Inject

abstract class BaseMvpActivity<T: BasePresenter<*>> : BaseActivity(), BaseView {

    // ============== 注入相关 ==================
    protected lateinit var mActivityComponent: ActivityComponent
    @Inject
    protected lateinit var mPresenter: T

    private lateinit var mLoadProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).mAppComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()

        mLoadProgressDialog = ProgressDialog.newInstance()
        injection()

        super.onCreate(savedInstanceState)
    }


    // ============== 功能性方法 ==================
    override fun showLoading() {
        mLoadProgressDialog.show(supportFragmentManager)
    }

    override fun hiddenLoading() {
        mLoadProgressDialog.dismiss()
    }

    override fun onError() {}

    // ============== 模板方法 ==================

    protected abstract fun injection()

}