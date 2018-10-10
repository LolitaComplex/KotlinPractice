package com.doing.kotlin.app.ui.widget

import android.content.Context
import android.support.annotation.DrawableRes
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.doing.kotlin.app.R

class BottomNavBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : BottomNavigationBar(context, attrs, defStyleAttr) {

    private val mCartRedPoint: TextBadgeItem
    private val mMsgRedPoint: ShapeBadgeItem

    init {
        // 首页
        val homeItem = initBottomNavItem(R.drawable.btn_nav_home_press,
                R.drawable.btn_nav_home_normal, "Home")

        // 分类
        val categoryItem = initBottomNavItem(R.drawable.btn_nav_category_press,
                R.drawable.btn_nav_category_normal, "Category")

        // 购物车
        val cartItem = initBottomNavItem(R.drawable.btn_nav_cart_press,
                R.drawable.btn_nav_cart_normal, "Cart")

        mCartRedPoint = TextBadgeItem()
        mCartRedPoint.hide()
        cartItem.setBadgeItem(mCartRedPoint)


        // 消息
        val messageItem = initBottomNavItem(R.drawable.btn_nav_msg_press,
                R.drawable.btn_nav_msg_normal, "Message")

        mMsgRedPoint = ShapeBadgeItem()
        mMsgRedPoint.setShape(ShapeBadgeItem.SHAPE_OVAL)
        mMsgRedPoint.hide()
        messageItem.setBadgeItem(mMsgRedPoint)

        // 个人中心
        val userItem = initBottomNavItem(R.drawable.btn_nav_user_press,
                R.drawable.btn_nav_user_normal, "User")



//        setMode(BottomNavigationBar.MODE_FIXED)
        setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        setBarBackgroundColor(R.color.common_white)

        addItem(homeItem)
                .addItem(categoryItem)
                .addItem(cartItem)
                .addItem(messageItem)
                .addItem(userItem)
                .setFirstSelectedPosition(0)
                .initialise()
    }

    private fun initBottomNavItem(@DrawableRes resPress: Int, @DrawableRes resNormal: Int, title: String): BottomNavigationItem {
        return BottomNavigationItem(resPress, title)
                .setInactiveIconResource(resNormal)
                .setActiveColor(R.color.text_normal)
                .setInActiveColor(R.color.common_blue)
    }

    fun setCartItemCount(count: Int) {
        if (count == 0) {
            mCartRedPoint.hide()
        } else {
            mCartRedPoint.show()
            mCartRedPoint.setText("$count")
        }
    }

    fun showOrHiddenMsgRedPoint(visible: Boolean) {
        if (visible) {
            mMsgRedPoint.show()
        } else {
            mMsgRedPoint.hide()
        }
    }
}