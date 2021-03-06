package com.doing.kotlin.usercenter.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.support.v7.app.ActionBar
import android.util.Log
import android.view.MenuItem
import com.doing.kotlin.baselib.common.AppConfig
import com.doing.kotlin.baselib.data.image.ImageUtils
import com.doing.kotlin.baselib.ext.getTrimText
import com.doing.kotlin.baselib.ui.activity.BaseMvpActivity
import com.doing.kotlin.baselib.utils.DateUtils
import com.doing.kotlin.usercenter.R
import com.doing.kotlin.usercenter.injection.component.DaggerUserInfoComponent
import com.doing.kotlin.usercenter.injection.module.UploadModule
import com.doing.kotlin.usercenter.injection.module.UserModule
import com.doing.kotlin.usercenter.precenter.UserInfoPresenter
import com.doing.kotlin.usercenter.precenter.view.UserInfoView
import com.doing.kotlin.usercenter.ui.dialog.SelectPictureDialog
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.InvokeParam
import com.jph.takephoto.model.TContextWrap
import com.jph.takephoto.model.TResult
import com.jph.takephoto.permission.InvokeListener
import com.jph.takephoto.permission.PermissionManager
import com.jph.takephoto.permission.PermissionManager.TPermissionType
import com.jph.takephoto.permission.TakePhotoInvocationHandler
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.find
import java.io.File


class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), TakePhoto.TakeResultListener, InvokeListener
    , UserInfoView{


    private var mInvokeParam: InvokeParam? = null

    // 选择完毕后初始化
    private var mUploadFile: String? = ""
    // 上传完毕后初始化或者第一次进入时
    private var mImageUrl: String? = ""

    private val mBottomDialog by lazy {
        SelectPictureDialog(this)
    }

    private val mTakePhoto by lazy {
        TakePhotoInvocationHandler.of(this).bind(
                TakePhotoImpl(this,this)) as TakePhoto
    }

    private val mUploadManager by lazy {
        UploadManager()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        mTakePhoto.onCreate(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        mTakePhoto.onSaveInstanceState(outState)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_user_info
    }

    override fun injection() {
        DaggerUserInfoComponent.builder()
                .activityComponent(mActivityComponent)
                .uploadModule(UploadModule())
                .userModule(UserModule())
                .build()
                .inject(this)

        mPresenter.mView = this
    }

    override fun initActionBar(actionBar: ActionBar) {
        mToolbar?.setNavigationIcon(R.drawable.icon_back)
        mToolbar?.setNavigationOnClickListener {
            finish()
        }
    }

    override fun initView() {
        mToolbar = find(R.id.mToolbar)

        initUserInfo()


        mRlUserIconView.setOnClickListener {
            mBottomDialog.show()
        }

        mBottomDialog.setOnTakePhotoClick {
            mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
            mTakePhoto.onPickFromCapture(Uri.fromFile(createTempFile()))
        }

        mBottomDialog.setOnOpenAlbumClick {
            mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
            mTakePhoto.onPickFromGallery()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_register -> saveUserInfo()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveUserInfo() {
        val userName = mEtUserName.getTrimText()
        val gender = if (mRbGenderMale.isChecked) "0" else "1"
        val sign = mEtUserSign.getTrimText()
        mPresenter.editUser(mImageUrl, userName, gender, sign)
    }

    /**
     * 把用户缓存数据绑定到View上
     */
    private fun initUserInfo() {
        val userInfo = AppConfig.sAccountService.mUser
        mImageUrl = userInfo?.userIcon
        ImageUtils.setCircleUrl(mIvUserIcon, userInfo?.userIcon, R.drawable.icon_default_user)
        mEtUserName.setText(userInfo?.userName)
        val isFemale = userInfo?.userGender.equals("1")
        mRbGenderMale.isChecked = isFemale
        mRbGenderFemale.isChecked = !isFemale
        mTvUserMobile.text = userInfo?.userMobile
        mEtUserSign.setText(userInfo?.userSign)
    }

    private fun createTempFile(): File {
        val tempFile = "${DateUtils.curTime}.png"
        return if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), tempFile)
        } else {
            File(filesDir, tempFile)
        }
    }


    override fun takeSuccess(result: TResult?) {
        Log.d(TAG, result?.image?.originalPath)
        Log.i(TAG, result?.image?.compressPath)

        mUploadFile = result?.image?.compressPath
        mPresenter.getUploadToken()
    }

    override fun takeCancel() {
    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.e(TAG, msg)
    }


    override fun invoke(invokeParam: InvokeParam?): PermissionManager.TPermissionType {
        val type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam?.method!!)
        if (TPermissionType.WAIT == type) {
            this.mInvokeParam = invokeParam
        }
        return type
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionManager.handlePermissionsResult(this, type, mInvokeParam, this)
    }

    override fun onGetUploadTokenResult(token: String) {
        mUploadManager.put(mUploadFile, null, token, { _, _, response ->
            mImageUrl = AppConfig.Constant.IMAGE_SERVER_ADDRESS + response?.getString("hash")
            ImageUtils.setCircleUrl(mIvUserIcon,
                    mImageUrl, R.drawable.icon_default_user)
        }, null)
    }

    override fun onEditUserResult() {
        finish()
    }

}