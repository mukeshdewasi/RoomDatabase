package com.app.workroomdatabase

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.workroomdatabase.databinding.ActivitySignUpBinding
import java.util.Calendar

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    lateinit var db:AppDatabase
    var gender=""
    var dob=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db=Room.databaseBuilder(this,AppDatabase::class.java, name = "mukesh.db").allowMainThreadQueries().build()

        binding.btnSignup.setOnClickListener {
            var Name=binding.etName.text.toString().trim()
            var Email=binding.etEmail.text.toString().trim()
            var Password=binding.etPassword.text.toString().trim()

             var intent= Intent(this,LoginActivity::class.java)
            intent.putExtra("Name",Name)
            intent.putExtra("Email",Email)
            intent.putExtra("Password",Password)
            intent.putExtra("Gender",gender)
            intent.putExtra("Dob",dob)
            startActivity(intent)


            InsertUser(Name,Email,Password,dob,gender)

        }
        binding.etDob.setOnFocusChangeListener { view, status ->
            if(status){
                showDatePicker()
            }
        }

    }

    private fun showDatePicker() {
        var calender=Calendar.getInstance()
        var myear=calender.get(Calendar.YEAR)
        var mmonth=calender.get(Calendar.MONTH)
        var mday=calender.get(Calendar.DAY_OF_MONTH)
        var datepicker=DatePickerDialog(
            this,{view,year,month,dayofmonth ->
                dob="$dayofmonth/${month+1}/$year"
                binding.etDob.setText(dob)
            },myear,mmonth,mday
        )
        datepicker.show()
    }

    private fun InsertUser(name: String, email: String, password: String, dob: String, gender: String) {
        var user=Userdata(
            name=name,
            email=email,
            password = password,
            dob = dob,
            gender = gender
        )
        try {
            db.userdao().Insertuser(user)
            onBackPressed()
            Toast.makeText(applicationContext, "Saved", Toast.LENGTH_SHORT).show()
        }catch (e:Exception){

        }

    }


    fun Radiobuttonclicked(view:View){
        when(view.id){
            R.id.rb_male ->gender="Male"
            R.id.rb_female -> gender="Female"
        }
    }
}