package com.app.workroomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.app.workroomdatabase.databinding.ActivityLoginBinding
import com.app.workroomdatabase.databinding.ActivitySignUpBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var db:AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db= Room.databaseBuilder(this,AppDatabase::class.java, name = "mukesh.db").allowMainThreadQueries().build()

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
        }
        binding.btnLogin.setOnClickListener {
            var email=binding.etEmail.text.toString().trim()
            var password=binding.etPassword.text.toString().trim()

            var getname=intent.getStringExtra("Name")
            var getemail=intent.getStringExtra("Email")
            var getpassword=intent.getStringExtra("Password")
            var getdob=intent.getStringExtra("Dob")
            var getgender=intent.getStringExtra("Gender")

            loginuser(email,password)

        }
    }

    private fun loginuser(email:String,password:String) {
    var user=db.userdao().checklogin(email, password)
        if (user!=null){
            var intent=Intent(this,MainActivity::class.java)
            intent.putExtra("NAME",user.name)
            intent.putExtra("EMAIL",user.email)
            intent.putExtra("PASSWORD",user.password)
            intent.putExtra("DOB",user.dob)
            intent.putExtra("GENDER",user.gender)
            startActivity(intent)
        }
    }
}