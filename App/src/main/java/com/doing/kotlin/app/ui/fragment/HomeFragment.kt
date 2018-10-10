package com.doing.kotlin.app.ui.fragment

import com.doing.kotlin.app.R
import com.doing.kotlin.baselib.ui.fragment.BaseFragment

class HomeFragment : BaseFragment() {

    companion object {
        fun newInstace(): BaseFragment {
            return HomeFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {

    }
}