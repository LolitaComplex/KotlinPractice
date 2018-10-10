package com.doing.kotlin.baselib.ui.fragment

import android.app.Activity
import android.content.Context
import com.doing.kotlin.baselib.common.BaseApplication
import com.doing.kotlin.baselib.injection.component.ActivityComponent
import com.doing.kotlin.baselib.injection.component.DaggerActivityComponent
import com.doing.kotlin.baselib.injection.module.ActivityModule
import com.doing.kotlin.baselib.injection.module.LifecycleProviderModule
import com.doing.kotlin.baselib.presenter.BasePresenter
import com.doing.kotlin.baselib.presenter.view.BaseView
import com.doing.kotlin.baselib.ui.dialog.ProgressDialog
import javax.inject.Inject

abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {

    // ============== 注入相关 ==================
    protected lateinit var mActivityComponent: ActivityComponent
    @Inject
    protected lateinit var mPresenter: T
    @Inject
    protected lateinit var mContext: Context

    private lateinit var mLoadProgressDialog: ProgressDialog

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val appComponent = (context.applicationContext as BaseApplication).mAppComponent

        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(ActivityModule(context as Activity))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()

        mLoadProgressDialog = ProgressDialog.newInstance()

        injection()
    }


    override fun showLoading() {
        mLoadProgressDialog.show(fragmentManager)
    }

    override fun hiddenLoading() {
        mLoadProgressDialog.dismiss()
    }

    override fun onError() {}

    // ============== 模板方法 ==================

    protected abstract fun injection()

}