package com.doing.kotlin.baselib.ui.activity

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.ActionBar
import android.view.Menu
import com.doing.kotlin.baselib.common.AppConfig
import com.doing.kotlin.baselib.common.AppManager
import com.doing.kotlin.baselib.ui.fragment.BaseFragment
import com.doing.kotlin.baselib.ui.widget.GeneralToolbar
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

abstract class BaseActivity: RxAppCompatActivity(){

    // ============== 通用成员 ==================
    protected var mToolbar: GeneralToolbar? = null
    protected lateinit var mContext: Context
    var mCurrentFragment: BaseFragment? = null
        private set

    protected val TAG: String by lazy {
        javaClass.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppConfig.sActivityManager.addActivity(this)
        mContext = this

        setContentView(getLayoutId())
        initView()

        mToolbar?.let {
            setSupportActionBar(mToolbar)
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            initActionBar(supportActionBar!!)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AppConfig.sActivityManager.finishActivity(this)
    }

    // ============== GeneralToolbar相关的方法 ==================
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        mToolbar?.let {
            menuInflater.inflate(it.mMenuId, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        mCurrentFragment?.let {
            if (!it.onBackPressed()) {
                super.onBackPressed()
            }
        }
    }

    // ============== 模板方法 ==================
    @LayoutRes
    protected abstract fun getLayoutId(): Int
    protected abstract fun initView()

    protected open fun initActionBar(actionBar: ActionBar) {}
}