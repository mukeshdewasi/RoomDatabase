package com.app.workroomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.app.workroomdatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var db:AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db= Room.databaseBuilder(this,AppDatabase::class.java, name = "mukesh.db").allowMainThreadQueries().build()

        var getname=intent.getStringExtra("NAME")
        var getemail=intent.getStringExtra("EMAIL")
        var getpassword=intent.getStringExtra("PASSWORD")
        var getdob=intent.getStringExtra("DOB")
        var getgender=intent.getStringExtra("GENDER")

        binding.tvName.setText("Name : $getname")
        binding.tvEmail.setText("Email :$getemail")
        binding.tvDob.setText("Date Of Birth :$getdob")
        binding.tvGender.setText("Gender : $getgender")

        binding.btnDelete.setOnClickListener {
            deleteUserRecord()
        }
        binding.btnUpdate.setOnClickListener {
            var name=binding.tvName.text.toString().trim()
            var email=binding.tvEmail.text.toString().trim()
            var dob=binding.tvDob.text.toString().trim()
            var gender=binding.tvGender.text.toString().trim()

            var intent=Intent(this,UpdateActivity::class.java)
            intent.putExtra("UNAME",name)
            intent.putExtra("UEMAIL",email)
            intent.putExtra("UDOB",dob)
            intent.putExtra("UGENDER",gender)
            startActivity(intent)
        }
    }

    private fun deleteUserRecord() {
        var userDao=db.userdao()
        var email=intent.getStringExtra("EMAIL")
        var user = userDao.deleteuser(email!!)
        if (user != null) {
            userDao.deletedata(user)
            Toast.makeText(applicationContext, "Deleted", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, SignUpActivity::class.java))
        }else{

        }
    }
}