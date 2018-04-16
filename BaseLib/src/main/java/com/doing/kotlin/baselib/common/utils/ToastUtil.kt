package com.doing.kotlin.baselib.common.utils

import android.widget.Toast
import com.doing.kotlin.baselib.common.BaseApplication

fun String.toast() {
    ToastUtil.show(this)
}

private class ToastUtil{
    companion object {
        private val mToast: Toast by lazy {
            Toast.makeText(BaseApplication.getContext(),
                    String(), Toast.LENGTH_SHORT).apply {
                show()
            }
        }

        fun show(text: String) {
            mToast.setText(text)
            mToast.show()
        }
    }
}