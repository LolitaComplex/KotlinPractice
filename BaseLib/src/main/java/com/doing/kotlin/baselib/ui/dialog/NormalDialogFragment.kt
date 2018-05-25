package com.doing.kotlin.baselib.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.support.annotation.ColorInt
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.widget.TextView
import com.doing.kotlin.baselib.utils.UiUtils
import com.doing.kotlin.baselib.R


class NormalDialogFragment : DialogFragment(){

    private var mTitle: String? = null
    private var mMessage: String? = null
    private var mNavigationText: String? = null
    private lateinit var mPositiveText: String

    @ColorInt
    var mButtonTextColor = UiUtils.getColor(R.color.colorPrimary)

    private var mNegativeListener: ((dialog: DialogInterface , witch: Int) -> Unit)? = null
    private var mPositiveListener: ((dialog: DialogInterface , witch: Int) -> Unit)? = null

    companion object {
        fun newInstance(title: String?, message: String?, positiveText: String): NormalDialogFragment {
            return NormalDialogFragment().apply {
                init(title, message, positiveText, "")
            }
        }

        fun newInstance(title: String?, message: String?, positiveText: String, navigationText: String):
                NormalDialogFragment {
            return NormalDialogFragment().apply {
                init(title, message, positiveText, navigationText)
            }
        }
    }

    private fun init(title: String?, message: String?, positiveText: String, navigationText: String?) {
        this.mTitle = title
        this.mMessage = message
        this.mPositiveText = positiveText
        this.mNavigationText = navigationText
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity as Context).apply {
            mTitle?.let {
                setTitle(mTitle)
            }

            mMessage?.let {
                setMessage(mMessage)
            }

            if (TextUtils.isEmpty(mNavigationText) && mNegativeListener != null) {
                setNegativeButton(mNavigationText, mNegativeListener)
            }

            mPositiveListener?.let{
                setPositiveButton(mPositiveText, mPositiveListener)
            }
        }.create()
    }

    private fun getOutTag(): String {
        return "NormalDialogFragment" + mPositiveListener?.hashCode()
    }

    fun show(transaction: FragmentTransaction?): Int {
        return super.show(transaction, getOutTag())
    }

    override fun show(manager: FragmentManager?, tag: String?) {
        super.show(manager, tag)
        Handler().post {
            val alert = AlertDialog::class.java.getDeclaredField("mAlert")
            alert.isAccessible = true
            val alertController = alert.get(dialog)
            var btnField = alertController.javaClass.getDeclaredField("mButtonPositive")
            btnField.isAccessible = true
            val btnPositive = btnField.get(alertController) as TextView
            btnPositive.setTextColor(mButtonTextColor)
            btnField = alertController.javaClass.getDeclaredField("mButtonNegative")
            btnField.isAccessible = true
            val btnNegative = btnField.get(alertController) as TextView
            btnNegative.setTextColor(mButtonTextColor)
        }
    }
}