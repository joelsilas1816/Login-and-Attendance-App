package com.example.att

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var textInputEmail: TextInputLayout
    private lateinit var textInputPhno: TextInputLayout
    private lateinit var textInputUsername: TextInputLayout
    private lateinit var textInputName: TextInputLayout
    private lateinit var textInputPass: TextInputLayout
    private lateinit var allClear : Button
    private lateinit var toSignIn: Button
    private lateinit var signUp : Button

    lateinit var rootnode : FirebaseDatabase
    lateinit var reference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        findViewById<TextView>(R.id.logo_name).startAnimation(animation)

        toSignIn = findViewById(R.id.already_button)
        toSignIn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_in_left)
        }

        textInputEmail = findViewById(R.id.email)
        textInputName = findViewById(R.id.name)
        textInputPhno = findViewById(R.id.phno)
        textInputUsername = findViewById(R.id.username)
        textInputPass = findViewById(R.id.password)
        allClear = findViewById(R.id.all_clear)
        signUp = findViewById(R.id.sign_up_button)

    }

    fun allClear(view: View) {
        textInputEmail.editText?.text = null
        textInputName.editText?.text = null
        textInputPhno.editText?.text = null
        textInputUsername.editText?.text = null
        textInputPass.editText?.text = null
        showSnackbar(view, 0)
    }

     fun confirmInput(view: View) {

        var a : Boolean = !validateEmail()
        var b : Boolean = !validatePhno()
        var c : Boolean = !validateUsern()
        var d : Boolean = !validateName()
        var e : Boolean = !validatePass()

        if (a and b and c and d and e)
            showSnackbar(view, 1)
        else if (a or b or c or d or e)
            return
        else
        {
            rootnode = FirebaseDatabase.getInstance()
            reference = rootnode.getReference("users")

            var storename : String = textInputName.editText?.text.toString().trim()
            var storeuname : String = textInputUsername.editText?.text.toString().trim()
            var storeemail : String = textInputEmail.editText?.text.toString().trim()
            var storephno : String = textInputPhno.editText?.text.toString()
            var storepass : String = textInputPass.editText?.text.toString()

            var helperObject = UserHelperClass(storename,storephno,storeemail,storeuname,storepass)

            reference.child(storeuname).setValue(helperObject)

            //showSnackbar(view, 2)

            callAlertDialog()

            return
        }
    }

    private fun callAlertDialog(){

        val toInflate : LayoutInflater = LayoutInflater.from(this)
        val toView : View = toInflate.inflate(R.layout.dialogue, null)

        var textOne : TextView = toView.findViewById(R.id.text1)
        var textTwo : TextView = toView.findViewById(R.id.text2)

        textOne.text="Sign Up successful"
        textTwo.text="You are registered"

        val toLoginButton : Button = toView.findViewById(R.id.ok_button)

        toLoginButton.setOnClickListener { startActivity(Intent(applicationContext, Login::class.java)) }

        val build: AlertDialog.Builder = AlertDialog.Builder(this)
        build.setView(toView)
                .setCancelable(false)
        build.create().show()
    }

    private fun showSnackbar(view: View, flag: Int) {
        var txt = when (flag) {
            1 -> "The fields cannot remain empty"
            0 -> "All fields have been cleared"
           // 2 -> "Registration Successful"
            else -> "Invalid"
        }

//        var time : Int = 2000
//        var col : String = "#000000"

        Snackbar.make(view, txt, Snackbar.LENGTH_SHORT)
                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE)
                .setBackgroundTint(Color.parseColor("#000000"))
                .show()
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

    private fun validateUsern(): Boolean {
        var usernInput = textInputUsername.editText?.text.toString().trim()

        var test : Boolean =checkSpecialChars(usernInput)

        when {
            usernInput.isEmpty() -> {
                // textInputUsername.isErrorEnabled = true
                textInputUsername.error = "Please enter username"
                return false
            }
            (usernInput.length > 15) or (" " in usernInput) or (test)
            -> {
                //  textInputUsername.isErrorEnabled = true
                textInputUsername.error = "Use upto 15 characters (alphanumeric only)"
                return false
            }
            else -> {
                textInputUsername.error = null
                textInputUsername.isErrorEnabled = false
                return true
            }
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
}