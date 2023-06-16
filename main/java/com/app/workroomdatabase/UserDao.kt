package com.app.workroomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
        @Insert
        fun Insertuser(userdata: Userdata)
        @Query("select * from 'table'")
        fun getAllData():MutableList<Userdata>
        @Query("select * from 'table' where email=:email and password=:password LIMIT 1 ")
        fun checklogin(email:String,password:String):Userdata

        @Delete
        fun deletedata(userdata: Userdata)
        @Query("select * from 'table' where email=:email")
        fun deleteuser(email: String):Userdata

}