package com.doing.kotlin.baselib.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup

/**
 * Created by Doing on 2018/4/4.
 *
 */
open class MultiItemTypeAdapter<T>(mContext: Context, data: MutableList<T>) : BaseAdapter<T>(mContext, data) {

    private var mDelegateManager: ItemViewDelegateManager<T> = ItemViewDelegateManager()
    private var mOnItemClickListener: OnItemClickListener? = null

    private val isUseItemViewDelegateManager: Boolean
        get() = mDelegateManager.itemViewDelegateCount > 0

    override fun getItemViewType(position: Int): Int {
        return if (!isUseItemViewDelegateManager) {
            super.getItemViewType(position)
        } else mDelegateManager.getItemViewType(mListData[position], position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val itemViewDelegate = mDelegateManager.getItemViewDelegate(viewType)
        val layoutId = itemViewDelegate.mItemViewLayoutId
        val holder = BaseViewHolder.createViewHolder(mContext!!, parent, layoutId)
        onViewHolderCreate(holder)
        setListener(parent, holder, viewType)
        return holder
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        convert(holder, mListData[position], position)
    }

    override fun getItemCount(): Int {
        return mListData.size
    }

    protected open fun convert(holder: BaseViewHolder, data: T, position: Int) {
        mDelegateManager.convert(holder, data, position)
    }

    fun addItemViewDelegate(itemViewDelegate: ItemViewDelegate<T>): MultiItemTypeAdapter<T> {
        mDelegateManager.addDelegate(itemViewDelegate)
        return this
    }

    fun addItemViewDelegate(viewType: Int, itemViewDelegate: ItemViewDelegate<T>): MultiItemTypeAdapter<T> {
        mDelegateManager.addDelegate(viewType, itemViewDelegate)
        return this
    }

    protected fun onViewHolderCreate(holder: BaseViewHolder) = Unit

    protected fun setListener(parent: ViewGroup, holder: BaseViewHolder, viewType: Int) {
        if (!isEnable(viewType)) {
            return
        }
        holder.itemView.setOnClickListener { v ->
            if (mOnItemClickListener != null) {
                val position = holder.adapterPosition
                mOnItemClickListener!!.onItemClick(v, holder, position)
            }
        }

        holder.itemView.setOnLongClickListener { v ->
            if (mOnItemClickListener != null) {
                val position = holder.adapterPosition
                mOnItemClickListener!!.onItemLongClick(v, holder, position)
            }
            false
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }

    protected fun isEnable(viewType: Int): Boolean {
        val itemViewDelegate = mDelegateManager.getItemViewDelegate(viewType)
        return itemViewDelegate.mIsEnable
        //        return true;
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, holder: BaseViewHolder, position: Int)

        fun onItemLongClick(view: View, holder: BaseViewHolder, position: Int)
    }

    class SimpleOnItemClickListener : OnItemClickListener {

        override fun onItemClick(view: View, holder: BaseViewHolder, position: Int) = Unit

        override fun onItemLongClick(view: View, holder: BaseViewHolder, position: Int) = Unit
    }
}
