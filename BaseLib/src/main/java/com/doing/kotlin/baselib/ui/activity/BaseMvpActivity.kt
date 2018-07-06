package com.doing.kotlin.baselib.ui.activity

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.Menu
import com.doing.kotlin.baselib.common.BaseApplication
import com.doing.kotlin.baselib.injection.component.ActivityComponent
import com.doing.kotlin.baselib.injection.component.DaggerActivityComponent
import com.doing.kotlin.baselib.injection.module.ActivityModule
import com.doing.kotlin.baselib.injection.module.LifecycleProviderModule
import com.doing.kotlin.baselib.presenter.BasePresenter
import com.doing.kotlin.baselib.presenter.view.BaseView
import com.doing.kotlin.baselib.ui.dialog.ProgressDialogFragment
import com.doing.kotlin.baselib.ui.widget.GeneralToolbar
import javax.inject.Inject

abstract class BaseMvpActivity<T: BasePresenter<*>> : BaseActivity(), BaseView {

    // ============== 注入相关 ==================
    protected lateinit var mActivityComponent: ActivityComponent
    @Inject
    protected lateinit var mPresenter: T
    @Inject
    protected lateinit var mContext: Context

    // ============== 通用成员 ==================
    protected lateinit var mToolbar: GeneralToolbar

    private lateinit var mLoadProgressDialog: ProgressDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivityComponent = DaggerActivityComponent.builder()
                .appComponent((application as BaseApplication).mAppComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()

        setContentView(getLayoutId())
        injection()
        initView()

        mLoadProgressDialog = ProgressDialogFragment.newInstance()
    }

    // Toolbar相关的方法
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(mToolbar.mMenuId, menu)
        return super.onCreateOptionsMenu(menu)
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
    @LayoutRes
    protected abstract fun getLayoutId(): Int
    protected abstract fun injection()
    protected abstract fun initView()

}