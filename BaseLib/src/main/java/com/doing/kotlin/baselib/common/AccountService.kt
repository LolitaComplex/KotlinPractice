package com.doing.kotlin.baselib.common

import com.doing.kotlin.baselib.data.db.User
import com.doing.kotlin.baselib.data.db.UserDao
import io.reactivex.Flowable
import io.reactivex.Single

class AccountService internal constructor(){

    private val mUserDao: UserDao by lazy {
        AppConfig.sAppDatabase.userDao()
    }

    var mUser : User? = null

    fun login(user: User) {
        if (isLogin()) {
            mUserDao.remove()
        }
        mUserDao.add(user)
        mUser = user
    }

    fun isLogin(): Boolean {
        return mUser != null || mUserDao.get() != null
    }

    fun logout() {
        mUserDao.remove()
        mUser = null
    }

    fun loginRx(user: User): Single<Long> {
        return if (mUser != null) {
            Single.just(-1)
        } else {
            mUserDao.getRx().map{
                mUser = user
                if (it.isNotEmpty()) {
                    mUserDao.remove().toLong()
                }
                mUserDao.add(user)
            }
        }
    }

    private fun isLoginRx(): Single<Boolean> {
        return if (mUser != null) {
            Single.just(true)
        } else {
            mUserDao.getRx().map { it.size > 0 }
        }
    }
}