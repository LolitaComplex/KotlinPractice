package com.doing.kotlin.baselib.utils

import android.widget.Toast
import com.doing.kotlin.baselib.common.BaseApplication

fun String.toast() {
    ToastUtil.show(this)
}

class ToastUtil{
    companion object {
        private var mToast: Toast? = null

        fun show(text: String) {
            if (mToast == null) {
                mToast = Toast.makeText(UiUtils.getContext(), "", Toast.LENGTH_SHORT)
            }

            mToast?.let {
                it.setText(text)
                it.show()
            }
        }
    }
}