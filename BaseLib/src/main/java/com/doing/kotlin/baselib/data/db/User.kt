package com.doing.kotlin.baselib.data.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user")
class User (id: String, userIcon: String?, userName: String, userGender: String?, userMobile: String, userSign: String?){

    @PrimaryKey
    val id = id

    val userIcon = userIcon
    val userName = userName
    val userGender = userGender
    val userMobile = userMobile
    val userSign = userSign


    override fun toString(): String {
        return "User(id='$id', userIcon=$userIcon, userName='$userName', userGender=$userGender, userMobile='$userMobile', userSign=$userSign)"
    }


}