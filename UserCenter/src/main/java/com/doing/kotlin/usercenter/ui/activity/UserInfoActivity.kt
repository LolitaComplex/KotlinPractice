package com.doing.kotlin.usercenter.ui.activity

import android.support.v7.app.ActionBar
import com.doing.kotlin.baselib.ui.activity.BaseMvpActivity
import com.doing.kotlin.usercenter.R
import com.doing.kotlin.usercenter.injection.component.DaggerUserComponent
import com.doing.kotlin.usercenter.injection.module.UserModule
import com.doing.kotlin.usercenter.precenter.view.UserInfoPresenter
import com.doing.kotlin.usercenter.ui.dialog.SelectPictureDialog
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.find

class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_user_info
    }

    override fun injection() {
        DaggerUserComponent.builder()
                .activityComponent(mActivityComponent)
                .userModule(UserModule())
                .build()
                .inject(this)
    }

    override fun initActionBar(actionBar: ActionBar) {
        mToolbar?.setNavigationIcon(R.drawable.icon_back)
        mToolbar?.setNavigationOnClickListener {
            finish()
        }
    }

    override fun initView() {
        mToolbar = find(R.id.mToolbar)

        mRlUserIconView.setOnClickListener {
            SelectPictureDialog(this@UserInfoActivity).show()
        }
    }
}