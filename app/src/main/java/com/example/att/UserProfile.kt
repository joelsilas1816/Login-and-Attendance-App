package com.example.att

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserProfile : AppCompatActivity() {

    private lateinit var textInputEmail: TextInputLayout
    private lateinit var textInputPhno: TextInputLayout
    private lateinit var textInputName: TextInputLayout
    private lateinit var textInputPass: TextInputLayout
    private lateinit var updateClick : Button
    private lateinit var exitClick : Button
    private lateinit var proPic : ImageView
    private lateinit var proUserName : TextView
    private lateinit var proName : TextView
     lateinit var reference : DatabaseReference
     var i = 1
     var img : Int? =null

    private lateinit var storename : String
    private lateinit var storeuname : String
    private lateinit var storeemail : String
    private lateinit var storephno : String
    private lateinit var storepass : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        Log.i("fromHere","before Oncreate also happens")

        textInputEmail = findViewById(R.id.email)
        textInputName = findViewById(R.id.name)
        textInputPhno = findViewById(R.id.phno)
        textInputPass = findViewById(R.id.password)
        updateClick = findViewById(R.id.updateButton)
        exitClick = findViewById(R.id.exitButton)
        proName =findViewById(R.id.full_name)
        proPic =findViewById(R.id.profile_image)

        reference= FirebaseDatabase.getInstance().getReference("users")

        userinfo()
    }

    private fun userinfo() {

        if(i==1) {
            var intent: Intent = getIntent()
            storename = intent.getStringExtra("myName")!!
            storeuname = intent.getStringExtra("username")!!
            storeemail = intent.getStringExtra("email")!!
            storephno = intent.getStringExtra("phoneNumber")!!
            storepass = intent.getStringExtra("password")!!
            i=i+1
            Log.d("iValue" , i.toString())
        }

        textInputEmail.editText?.setText(storeemail)
        textInputName.editText?.setText(storename)
        textInputPhno.editText?.setText(storephno)
        textInputPass.editText?.setText(storepass)
        proName.text=textInputName.editText?.text.toString().trim()

        proUserName =findViewById(R.id.user_name)
        proUserName.text=storeuname

        account()
    }

    private fun account() {

        img = when(textInputPhno.editText?.text.toString())
        {
            "8660840432" -> R.drawable.aadandavate
            "9975764023" -> R.drawable.mmbhajibhakare
            "9834651236" -> R.drawable.mmbhajibhakare
            "8308885446" -> R.drawable.sapatil
            "9970121312" -> R.drawable.mjg
            "8669152706" -> R.drawable.sbchoudhari
            "9762802411" -> R.drawable.veena_pawar
            "9823170773" -> R.drawable.rsparte
            "9322289104" -> R.drawable.rsparte
            "9075437472" -> R.drawable.puja_sorate
            "7387993390" -> R.drawable.dswaghole
            "9922007910" -> R.drawable.drpatil
            "7387930039" -> R.drawable.sumitkumar
            else -> R.drawable.user_large
        }

        proPic.setImageResource(img!!)
    }

    fun updateTheValues(view: View) {
var a = isNameChanged()
var b = isEmailChanged()
var c = isPhoneNoChanged()
var d = isPasswordChanged()

        if((a==2) or (b==2) or (c==2) or (d==2))
            return
        else if((a==1) or (b==1) or (c==1) or (d==1))
        {
            //Log.i("TAG","Hello coder debug")
            Toast.makeText(this,"Details are updated successfully",Toast.LENGTH_SHORT).show()
            return
        }
        else
        {
            Toast.makeText(this,"Details are the same and need not be updated",Toast.LENGTH_SHORT).show()
            return
        }
    }

    private fun isPasswordChanged(): Int {
        if(storepass != textInputPass.editText?.text.toString())
        {
            if(validatePass()) {
                reference.child(storeuname).child("password").setValue(textInputPass.editText?.text.toString())
                storepass = textInputPass.editText?.text.toString()
                return 1
            }
            else
                return 2
        }
        else
            return 0
    }

    private fun isPhoneNoChanged(): Int {
        if(storephno != textInputPhno.editText?.text.toString())
        {
            if(validatePhno()) {
                reference.child(storeuname).child("phoneNumber").setValue(textInputPhno.editText?.text.toString())
                storephno = textInputPhno.editText?.text.toString()
                account()
                return 1
            }
            else
                return 2
        }
        else
            return 0
    }

    private fun isEmailChanged(): Int {
        if(storeemail != textInputEmail.editText?.text.toString().trim())
        {
           if(validateEmail()) {
               reference.child(storeuname).child("email").setValue(textInputEmail.editText?.text.toString().trim())
               storeemail = textInputEmail.editText?.text.toString().trim()
               return 1
           }
           else
               return 2
        }
        else
            return 0
    }

    private fun isNameChanged(): Int {
        if(storename != textInputName.editText?.text.toString().trim())
        {
            if(validateName()) {
                reference.child(storeuname).child("myName").setValue(textInputName.editText?.text.toString().trim())
                storename = textInputName.editText?.text.toString().trim()
                proName.text=storename
                return 1
            }
            else
                return 2
        }
        else
            return 0
    }

    private fun validateEmail(): Boolean {
        var emailInput = textInputEmail.editText?.text.toString().trim()
        // OR var emailIncludes = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

        if (emailInput.isEmpty()) {
            // textInputEmail.isErrorEnabled = true
            textInputEmail.error = "Please enter email id"
            return false
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            //  textInputEmail.isErrorEnabled = true
            textInputEmail.error = "Invalid email id"
            return false
        }
        else {
            textInputEmail.error = null
            textInputEmail.isErrorEnabled = false
            return true
        }
    }

    private fun validatePhno(): Boolean {
        var phnoInput = textInputPhno.editText?.text.toString()

        if (phnoInput.isEmpty()) {
            //    textInputPhno.isErrorEnabled = true
            textInputPhno.error = "Please enter phone number"
            return false
        } else if (phnoInput.length < 10) {
            //    textInputPhno.isErrorEnabled = true
            textInputPhno.error = "Please enter a 10 digit phone number"
            return false
        } else {
            textInputPhno.error = null
            textInputPhno.isErrorEnabled = false
            return true
        }
    }

    private fun validateName(): Boolean {
        var nameInput = textInputName.editText?.text.toString().trim()

        if (nameInput.isEmpty()) {
            //   textInputName.isErrorEnabled = true
            textInputName.error = "Please enter name"
            return false
        } else {
            textInputName.error = null
            textInputName.isErrorEnabled = false
            return true
        }
    }

    private fun checkSpecialChars(inputUser : String) : Boolean {

        var invalidChar : String = "(?=.*[@|<>?:~`;.#$%^&{}!*+/-_=])"
        for(i in inputUser)
        {
            for(j in invalidChar)
            {
                if(i==j)
                {
                    return true
                }
            }
        }
        return false
    }

    private fun validatePass(): Boolean {
        var passInput = textInputPass.editText?.text.toString()
        val passIncludes = Regex("^"+"(?=.*[0-9])"+"(?=.*[a-z])"+"(?=.*[A-Z])"+".{4,}" + "$")

        var test : Boolean = !checkSpecialChars(passInput)

        if (passInput.isEmpty()) {
            //   textInputPass.isErrorEnabled = true
            textInputPass.error = "Please enter password"
            return false
        }
        else if(!passInput.matches(passIncludes) or test){
            textInputPass.error = "Weak Password"
            return false
        }
        else
        {
            textInputPass.error = null
            textInputPass.isErrorEnabled = false
            textInputPass.isHelperTextEnabled = true
            textInputPass.helperText = "Strong Password"
            return true
        }
    }

    fun settingUp(view: View) {

        val transfer = Intent(this,SetUp::class.java)
        transfer.putExtra("myName",textInputName.editText?.text.toString())
        transfer.putExtra("myUserName",storeuname)
        transfer.putExtra("profilePic",img)
        startActivity(transfer)
        finish()
    }
}
