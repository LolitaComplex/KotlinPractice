package com.doing.kotlin.baselib.ui.adapter

import android.content.Context

import java.util.Collections

class EmptyCommonAdapter private constructor(context: Context, layoutId: Int, data: List<String>) : CommonAdapter<String>(context, layoutId, data) {

    constructor(context: Context) : this(context, android.R.layout.simple_list_item_1, emptyList<String>())

    override fun convert(holder: BaseViewHolder, t: String, position: Int) {

    }
}
