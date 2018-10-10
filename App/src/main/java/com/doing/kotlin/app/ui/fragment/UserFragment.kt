package com.doing.kotlin.app.ui.fragment

import com.doing.kotlin.app.R
import com.doing.kotlin.baselib.ui.fragment.BaseFragment

class UserFragment : BaseFragment() {

    companion object {
        fun newInstace(): BaseFragment {
            return UserFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_user
    }

    override fun initView() {

    }
}