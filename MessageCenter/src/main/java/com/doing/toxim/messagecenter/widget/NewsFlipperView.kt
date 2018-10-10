package com.doing.toxim.messagecenter.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.ViewFlipper
import com.doing.toxim.messagecenter.R
import org.jetbrains.anko.*

/*
    公告组件封装
 */
class NewsFlipperView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {
    private val mFlipperView: ViewFlipper

    init {
        val rootView = View.inflate(context, R.layout.layout_news_flipper, null)
        mFlipperView = rootView.find(R.id.mFlipperView)
        mFlipperView.setInAnimation(context, R.anim.news_bottom_in)
        mFlipperView.setOutAnimation(context, R.anim.news_bottom_out)

        addView(rootView)
    }

    private fun getChildView(content: String): TextView.() -> Unit {
        return {
            this.text = content
            this.textSize = px2sp(dimen(R.dimen.text_small_size))
            this.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        }
    }

    /*
        设置公告数据
     */
    fun setData(data: Array<String>) {
        for (text in data) {
            mFlipperView.textView(getChildView(text))
        }
        mFlipperView.startFlipping()
    }
}
