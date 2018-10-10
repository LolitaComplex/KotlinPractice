package com.doing.kotlin.app.ui.fragment

import com.doing.kotlin.app.R
import com.doing.kotlin.baselib.ui.fragment.BaseFragment

class CategoryFragment : BaseFragment() {

    companion object {
        fun newInstace(): BaseFragment {
            return CategoryFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_category
    }

    override fun initView() {

    }
}