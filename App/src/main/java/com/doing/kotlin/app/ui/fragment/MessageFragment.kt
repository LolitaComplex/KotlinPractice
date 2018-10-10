package com.doing.kotlin.app.ui.fragment

import com.doing.kotlin.app.R
import com.doing.kotlin.baselib.ui.fragment.BaseFragment

class MessageFragment : BaseFragment() {

    companion object {
        fun newInstace(): BaseFragment {
            return MessageFragment()
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.fragment_message
    }

    override fun initView() {

    }
}