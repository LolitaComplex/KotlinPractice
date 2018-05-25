package com.doing.kotlin.baselib.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.*

abstract class BaseDialogFragment : DialogFragment(){

    var mOnKeyListener: (() -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intiView(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.apply {
            initDialogAttribute(this)
        }
    }

    protected fun initDialogAttribute(dialog: Dialog) {
        val window = dialog.window
        val attributes = window.attributes

        attributes.softInputMode =
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE

        dialog.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                mOnKeyListener?.invoke()
            }
            false
        }


        //TODO 这个貌似alpha操作透明弹窗的，之后实验一下
        attributes.alpha = 0f
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }

    fun getOutTag(): String {
        return this.javaClass.simpleName + " : " + dialog?.hashCode()
    }

    fun show(manager: FragmentManager?) {
        super.show(manager, getOutTag())
    }

    // 模板方法
    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun intiView(container: View)
}
