package com.doing.kotlin.baselib.ui.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.drawable.AnimationDrawable
import android.support.v4.app.DialogFragment
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

    override fun initDialogAttribute(dialog: Dialog) {
        super.initDialogAttribute(dialog)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.LightProgressDialog)
        dialog.setCanceledOnTouchOutside(true)
    }

    override fun intiView(container: View) {
        val ivLoading = container.find<ImageView>(R.id.iv_loading)
        mAnimateDrawable = ivLoading.background as AnimationDrawable?
    }

    override fun onResume() {
        super.onResume()
        mAnimateDrawable?.start()
    }

    override fun onDismiss(dialog: DialogInterface?) {
        mAnimateDrawable?.stop()
        super.onDismiss(dialog)
    }
}