package com.doing.kotlin.baselib.data.db

import android.arch.persistence.room.*
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface UserDao{

    @Query("SELECT * FROM user LIMIT 1")
    fun get(): User?

    /**
     * return: 删除的行数
     */
    @Delete
    fun remove(user: User? = get()): Int

    /**
     * return: rowId
     */
    @Insert
    fun add(user: User): Long

    /**
     * return: 影响的行数
     */
    @Update
    fun update(user: User): Int


    @Query("SELECT * FROM user LIMIT 1")
    fun getRx(): Single<MutableList<User>>

//    @Delete
//    fun removeRx(user: User? = get()): Flowable<Long>
//
//    @Insert
//    fun addRx(user: User): Flowable<Long>
//
//    @Update
//    fun updateRx(user: User): Flowable<Long>
}