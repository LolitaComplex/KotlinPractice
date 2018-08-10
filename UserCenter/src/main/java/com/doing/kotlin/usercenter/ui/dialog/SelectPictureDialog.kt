package com.doing.kotlin.usercenter.ui.dialog

import android.content.Context
import android.support.design.widget.BottomSheetDialog
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.doing.kotlin.baselib.ui.dialog.BaseBottomDialog
import com.doing.kotlin.usercenter.R

class SelectPictureDialog(mContext: Context) : BaseBottomDialog(mContext), View.OnClickListener {


    private lateinit var mTvTakePhoto: TextView
    private lateinit var mTvAlbum: TextView
    private lateinit var mTvCancel: TextView

    private var mOnTakePhotoClick : (() -> Unit)? = null
    private var mOnOpenAlbumClick : (() -> Unit)? = null

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
        dialog.window.let {
            it.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            it.attributes.dimAmount = 0f
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.mTvTakePhoto -> {
                mOnTakePhotoClick?.invoke()
            }
            R.id.mTvAlbum -> {
                mOnOpenAlbumClick?.invoke()
            }
            R.id.mTvCancel -> {
                dismiss()
            }
        }
    }

    fun setOnTakePhotoClick(onTakePhotoClick: () -> Unit) {
        mOnTakePhotoClick = onTakePhotoClick
    }

    fun setOnOpenAlbumClick(onOpenAlbumClick: () -> Unit) {
        mOnOpenAlbumClick = onOpenAlbumClick
    }

}