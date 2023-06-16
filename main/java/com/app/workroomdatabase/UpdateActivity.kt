package com.app.workroomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.room.Room
import com.app.workroomdatabase.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateBinding
    lateinit var db:AppDatabase
    var gender=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db= Room.databaseBuilder(this,AppDatabase::class.java, name = "mukesh.db").allowMainThreadQueries().build()

        var uname=intent.getStringExtra("UNAME")
        var uemail=intent.getStringExtra("UEMAIL")
        var udob=intent.getStringExtra("UDOB")
        var ugender=intent.getStringExtra("UGENDER")

        binding.etName.setText(uname)
        binding.etDob.setText(udob)
        binding.etEmail.setText(uemail)

    }
    fun Radiobuttonclicked(view: View){
        when(view.id){
            R.id.rb_male ->gender="Male"
            R.id.rb_female -> gender="Female"
        }
    }
}