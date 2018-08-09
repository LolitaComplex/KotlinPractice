package com.doing.kotlin.usercenter.ui.dialog

import android.content.Context
import android.support.design.widget.BottomSheetDialog
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.doing.kotlin.baselib.ui.dialog.BaseBottomDialog
import com.doing.kotlin.baselib.utils.ToastUtil
import com.doing.kotlin.usercenter.R

class SelectPictureDialog(mContext: Context) : BaseBottomDialog(mContext), View.OnClickListener {

    private lateinit var mTvTakePhoto: TextView
    private lateinit var mTvAlbum: TextView
    private lateinit var mTvCancel: TextView

    override fun getLayoutId(): Int {
        return R.layout.dialog_select_picture
    }

    override fun initView(dialog: BottomSheetDialog) {
        mTvTakePhoto = dialog.findViewById(R.id.mTvTakePhoto)!!
        mTvAlbum = dialog.findViewById(R.id.mTvAlbum)!!
        mTvCancel = dialog.findViewById(R.id.mTvCancel)!!

        mTvTakePhoto.setOnClickListener(this)
        mTvAlbum.setOnClickListener(this)
        mTvCancel.setOnClickListener(this)
    }

    //取消全屏黑色背景
    override fun initDialogAttribute(dialog: BottomSheetDialog) {
        dialog.window.let{
            it.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            it.attributes.dimAmount = 0f
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mTvTakePhoto -> {
                ToastUtil.show("拍照")
            }
            R.id.mTvAlbum -> {
                ToastUtil.show("相册")
            }
            R.id.mTvCancel -> {
                dismiss()
            }
        }
    }

}