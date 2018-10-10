package com.doing.kotlin.app.ui.activity

import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.doing.kotlin.app.R
import com.doing.kotlin.app.ui.fragment.*
import com.doing.kotlin.baselib.ui.activity.BaseActivity
import com.doing.kotlin.baselib.ui.helper.NavHelper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity() {

    private val mNavHelper: NavHelper<String> by lazy {
        NavHelper<String>(this, R.id.mContainer, supportFragmentManager)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


    override fun initView() {
        mNavHelper.add(0, NavHelper.Tab(HomeFragment::class.java, "Home"))
                .add(1, NavHelper.Tab(CategoryFragment::class.java, "Category"))
                .add(2, NavHelper.Tab(CartFragment::class.java, "Cart"))
                .add(3, NavHelper.Tab(MessageFragment::class.java, "Message"))
                .add(4, NavHelper.Tab(UserFragment::class.java, "User"))

        mBottomNavigation.setTabSelectedListener(object :
                BottomNavigationBar.SimpleOnTabSelectedListener(){
            override fun onTabSelected(position: Int) {
                mNavHelper.performClickMenu(position)
            }
        })

        mNavHelper.performClickMenu(mBottomNavigation.currentSelectedPosition)

        Flowable.timer(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mBottomNavigation.setCartItemCount(10)
                    mBottomNavigation.showOrHiddenMsgRedPoint(true)
                }

        Flowable.timer(10, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mBottomNavigation.setCartItemCount(0)
                    mBottomNavigation.showOrHiddenMsgRedPoint(false)
                }


    }
}
