package com.example.att

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class SetUp : AppCompatActivity() {

    lateinit var myname : TextView
    lateinit var myusername : TextView
    lateinit var faculty : TextView
    lateinit var lectureSub : TextInputLayout
    lateinit var proPic : ImageView
     var proimg : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_up)

         myname =findViewById(R.id.full_name)
         myusername =findViewById(R.id.user_name)
        proPic=findViewById(R.id.profile_img)
         faculty  =findViewById(R.id.faculty)
         lectureSub  =findViewById(R.id.lectureEntered)

        var calendar : Calendar = Calendar.getInstance()
        var simpleDateTime = SimpleDateFormat("HH:mm:ss a")
        var timeResult : String = simpleDateTime.format(calendar.time)
//
//        // OR var dateAndTime : String = SimpleDateTime("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().time)
//
//        //while(true)
//            dt.text=date1 + textTime.text.toString() + date2

        val gotTransfer : Intent = getIntent()
        myname.text=gotTransfer.getStringExtra("myName")
        myusername.text=gotTransfer.getStringExtra("myUserName")
        proimg=gotTransfer.getIntExtra("profilePic",0)

        proPic.setImageResource(proimg!!)
        faculty.text = "Faculty   : " + myname.text

//        var timeString : String
//
//        Log.d("Time", timeResult)
//
//        if("08:30:00 AM"<=timeResult && timeResult<"09:30:00 AM"){
//            timeString = "08:30 AM to 09:30 AM"
//        }
//        else if("09:30:00 AM"<=timeResult && timeResult<"10:30:00 AM"){
//            timeString = "09:30 AM to 10:30 AM"
//        }
//        else if("10:30:00 AM"<=timeResult && timeResult<"11:30:00 AM"){
//            timeString = "10:30 AM to 11:30 AM"
//        }
//        else if("11:30:00 AM"<=timeResult && timeResult<"12:30:00 PM"){
//            timeString = "11:30 AM to 12:30 PM"
//        }
//        else if("12:30:00 PM"<=timeResult && timeResult<"01:00:00 PM"){
//            timeString = "Recess Time"
//        }
//        else if("01:00:00 PM"<=timeResult && timeResult<"02:00:00 PM"){
//            timeString = "01:00 PM to 02:00 PM"
//        }
//        else if("02:00:00 PM"<=timeResult && timeResult<"03:00:00 PM"){
//            timeString = "02:00 PM to 03:00 PM"
//        }
//        else if("03:00:00 PM"<=timeResult && timeResult<"04:00:00 PM"){
//            timeString = "03:00 PM to 04:00 PM"
//        }
//        else{
//            timeString = "Not College Hours"
//        }
//
//        Log.d("TimeSystem", timeResult)
//        Log.d("TimeDuration", timeString)

//        duration.text="Duration: " + timeString

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.login_icon,menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun toMark(view: View) {
            // lectureSub.isErrorEnabled=false
            if(lectureSub.editText?.text.toString().isNotEmpty()) {

                val prev = Intent(this@SetUp,StudentNameList::class.java)
                prev.putExtra("lectName",lectureSub.editText?.text.toString())

                startActivity(prev)
//                Log.i("Error" , "I'm here")
                finish()
            }

    else
        lectureSub.error="Please enter Lecture name"
}}