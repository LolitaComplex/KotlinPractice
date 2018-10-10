package com.doing.kotlin.app.ui.fragment

import com.doing.kotlin.app.R
import com.doing.kotlin.baselib.ui.fragment.BaseFragment

class CartFragment : BaseFragment() {

    companion object {
        fun newInstace(): BaseFragment {
            return CartFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_cart
    }

    override fun initView() {

    }
}