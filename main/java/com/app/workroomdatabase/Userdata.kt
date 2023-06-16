package com.app.workroomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "table")
data class Userdata(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var name:String,
    var email:String,
    var password:String,
    var dob:String,
    var gender:String
)
