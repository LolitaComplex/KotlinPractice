package com.doing.kotlin.baselib.ui.adapter

import android.graphics.Canvas
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Class description here
 *
 * @author doing
 * @version 1.0.0
 * @since 2017-12-26.
 */

class GridSpacingItemDecoration(private val mSpanCount: Int, private val mHorizontalAroundSpace: Float, private val mVerticalAroundSpace: Float,
                                private val mHorizontalCenterSpace: Float, private val mVerticalCenterSpace: Float,
                                @RecyclerView.Orientation private val mOrientation: Int) : RecyclerView.ItemDecoration() {


    constructor(spanCount: Int, aroundSpace: Float, centerSpace: Float) :
            this(spanCount, aroundSpace, aroundSpace, centerSpace, centerSpace, RecyclerView.VERTICAL)

    constructor(spanCount: Int, aroundSpace: Float, centerSpace: Float, @RecyclerView.Orientation orientation: Int) :
            this(spanCount, aroundSpace, aroundSpace, centerSpace, centerSpace, orientation)

    constructor(spanCount: Int, aroundSpace: Float, verticalCenterSpace: Float, horizontalCenterSpace: Float) :
            this(spanCount, aroundSpace, aroundSpace, verticalCenterSpace, horizontalCenterSpace, RecyclerView.VERTICAL)

    constructor(spanCount: Int, aroundSpace: Float, verticalCenterSpace: Float, horizontalCenterSpace: Float, @RecyclerView.Orientation orientation: Int) :
            this(spanCount, aroundSpace, aroundSpace, verticalCenterSpace, horizontalCenterSpace, orientation)


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        val position = parent.getChildAdapterPosition(view)

        if (mOrientation == RecyclerView.VERTICAL) {

        } else {

        }

        //控制水平方向
        val column = position % mSpanCount
        val eachHorizontalOffset = (2 * mHorizontalAroundSpace + (mSpanCount - 1f) * mHorizontalCenterSpace) / mSpanCount
        if (column == 0 && column == mSpanCount - 1) {
            outRect.right = mHorizontalAroundSpace.toInt()
            outRect.left = outRect.right
        } else if (column == 0) {
            outRect.left = mHorizontalAroundSpace.toInt()
            outRect.right = (eachHorizontalOffset - mHorizontalAroundSpace).toInt()
        } else if (column == mSpanCount - 1) {
            outRect.left = (eachHorizontalOffset - mHorizontalAroundSpace).toInt()
            outRect.right = mHorizontalAroundSpace.toInt()
        } else {
            outRect.left = (eachHorizontalOffset / 2).toInt()
            outRect.right = outRect.left
        }

        //控制竖直方向
        val itemCount = parent.adapter.itemCount.toFloat()
        val rowCount = Math.ceil((itemCount / mSpanCount).toDouble()).toInt()
        val row = Math.ceil(((position + 1f) / mSpanCount).toDouble()).toInt()
        val eachVerticalOffset = (2 * mVerticalAroundSpace + (rowCount - 1f) * mVerticalCenterSpace) / rowCount
        if (row == 1 && row == rowCount) {
            outRect.bottom = mVerticalAroundSpace.toInt()
            outRect.top = outRect.bottom
        } else if (row == 1) {
            outRect.top = mVerticalAroundSpace.toInt()
            outRect.bottom = (eachVerticalOffset - mVerticalAroundSpace).toInt()
        } else if (row == rowCount) {
            outRect.top = (eachVerticalOffset - mVerticalAroundSpace).toInt()
            outRect.bottom = mVerticalAroundSpace.toInt()
        } else {
            outRect.top = (eachVerticalOffset / 2).toInt()
            outRect.bottom = outRect.top
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
    }
}
