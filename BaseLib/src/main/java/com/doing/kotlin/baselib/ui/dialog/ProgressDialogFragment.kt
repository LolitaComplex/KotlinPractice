package com.doing.kotlin.baselib.ui.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.drawable.AnimationDrawable
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.View
import android.widget.ImageView
import com.doing.kotlin.baselib.R
import org.jetbrains.anko.find

class ProgressDialogFragment : BaseDialogFragment() {

    companion object {
        fun newInstance(): ProgressDialogFragment {
            return ProgressDialogFragment()
        }
    }

    private var mAnimateDrawable: AnimationDrawable? = null

    override fun getLayoutId(): Int {
        return R.layout.progress_dialog
    }

    override fun initDialogAttribute(dialog: Dialog){
        super.initDialogAttribute(dialog)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.LightProgressDialog)
        dialog.setCanceledOnTouchOutside(true)
    }

    override fun intiView(container: View) {
        val ivLoading = container.find<ImageView>(R.id.iv_loading)
        mAnimateDrawable = ivLoading.background as AnimationDrawable?
    }


    override fun show(manager: FragmentManager?, tag: String?) {
        super.show(manager, tag)
        mAnimateDrawable?.start()
    }

    override fun show(transaction: FragmentTransaction?, tag: String?): Int {
        mAnimateDrawable?.start()
        return super.show(transaction, tag)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        mAnimateDrawable?.stop()
    }
}