package com.app.workroomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [Userdata::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun userdao():UserDao

}