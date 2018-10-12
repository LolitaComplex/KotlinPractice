package com.doing.kotlin.baselib.utils

import android.content.Context
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doing.kotlin.baselib.common.BaseApplication


object UiUtils {

    fun getContext():Context {
        return BaseApplication.mContext
    }

    @ColorInt
    fun getColor(@ColorRes colorRes: Int): Int {
        return ContextCompat.getColor(getContext(), colorRes)
    }

    fun inflate(layoutId: Int): View {
        return LayoutInflater.from(getContext()).inflate(layoutId, null)
    }

    fun inflate(layoutId: Int, parentLayout: ViewGroup): View {
        return LayoutInflater.from(getContext()).inflate(layoutId, parentLayout, false)
    }
}