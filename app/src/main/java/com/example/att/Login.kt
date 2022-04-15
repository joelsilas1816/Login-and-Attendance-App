package com.example.att

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.*

class Login : AppCompatActivity() {

    private lateinit var textInputUsername: TextInputLayout
    private lateinit var textInputPass: TextInputLayout
    private lateinit var toSignUp: Button
    private lateinit var progress : ProgressBar

    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val animation = AnimationUtils.loadAnimation(this, R.anim.bounce)
        findViewById<TextView>(R.id.logo_name).startAnimation(animation)

        toSignUp = findViewById(R.id.new_user)
        toSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.slide_out_right, android.R.anim.slide_out_right)
        }
        textInputUsername = findViewById(R.id.username)
        textInputPass = findViewById(R.id.password)
        progress = findViewById(R.id.probar)
    }

    fun confirmSignIn(view: View) {

        var a : Boolean = !validateUsern()
        var b : Boolean = !validatePass()

        if (a and b)
            showSnackbar(view, 1)
        else if (a or b)
            return
        else
        {
            isUser()
            return
        }
    }

     fun isUser() {

         val progress : ProgressBar = findViewById(R.id.probar)
         progress.isVisible=true
        var unameEntered : String = textInputUsername.editText?.text.toString().trim()
        var passEntered : String = textInputPass.editText?.text.toString()

        ref = FirebaseDatabase.getInstance().getReference("users")

        var checkUser : Query = ref.orderByChild("username").equalTo(unameEntered)

         checkUser.addValueEventListener( object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    textInputUsername.error=null
                    textInputUsername.isErrorEnabled=false

                    var passFromDb : String = snapshot.child(unameEntered).child("password").value as String
                    Log.d("Login passFromDb ", passFromDb)
                    Log.d("Login passEntered ", passEntered)

                    if(passFromDb == passEntered){
                        textInputPass.error=null
                        textInputPass.isErrorEnabled=false
                        Log.i("Login1", "password equals")

                        var nameFromDb : String = snapshot.child(unameEntered).child("myName").value as String
                        var emailFromDb : String = snapshot.child(unameEntered).child("email").value as String
                        var phnoFromDb : String = snapshot.child(unameEntered).child("phoneNumber").value as String

                        val intention = Intent(applicationContext,UserProfile::class.java)
                        intention.putExtra("myName",nameFromDb)
                        intention.putExtra("email",emailFromDb)
                        intention.putExtra("username",unameEntered)
                        intention.putExtra("phoneNumber",phnoFromDb)
                        intention.putExtra("password",passEntered)
                        //showSnackbar(view, 2)

                        Log.i("Login2", "password equals")

                        /////////////

                        val toInflate : LayoutInflater = LayoutInflater.from(this@Login)
                        val toView : View = toInflate.inflate(R.layout.dialogue, null)

                        val build: AlertDialog.Builder = AlertDialog.Builder(this@Login)
                        build.setView(toView)
                                .setCancelable(false)
                        build.create().show()

                        val toUserProfile : Button = toView.findViewById(R.id.ok_button)

                        toUserProfile.setOnClickListener { startActivity(intention) }

                        ////////////
                     }
                    else{
                        progress.isVisible=false
                        textInputPass.error="Invalid Password"
                        textInputPass.requestFocus()
                    }
                }
                else{
                    progress.isVisible=false
                    textInputUsername.error="User does not exist"
                        textInputUsername.requestFocus()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        });
    }

    private fun showSnackbar(view: View, flag: Int) {
        var txt = when (flag) {
            1 -> "The fields cannot remain empty"
            0 -> "All fields have been cleared"
           // 2 -> "Sign In Successful"
            else -> "Invalid"
        }

        var time : Int = 2000
        var col : String = "#000000"

        /*if(flag==2)
        {
            time=3000
            col = "#FF6200EE"
        }*/

        Snackbar.make(view, txt, time)
                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE)
                .setBackgroundTint(Color.parseColor(col))
                .show()
    }

    private fun validateUsern(): Boolean {
        var usernInput = textInputUsername.editText?.text.toString().trim()
        var invalidChar : String = "(?=.*[@|<>?:~`;.#$%^&{}!*+/-_=])"
        var test = false

        for(i in usernInput)
        {
            for(j in invalidChar)
            {
                if(i==j)
                {
                    test=true
                    break
                }
            }
        }

        when {
            usernInput.isEmpty() -> {
                // textInputUsername.isErrorEnabled = true
                textInputUsername.error = "Please enter username"
                return false
            }
            (usernInput.length > 15) or (" " in usernInput) or (test)
            -> {
                //  textInputUsername.isErrorEnabled = true
                textInputUsername.error = "User does not exist"
                return false
            }
            else -> {
                textInputUsername.error = null
                textInputUsername.isErrorEnabled = false
                return true
            }
        }
    }

    private fun validatePass(): Boolean {
        var passInput = textInputPass.editText?.text.toString()

        if (passInput.isEmpty()) {
            textInputPass.isErrorEnabled = true
            textInputPass.error = "Please enter password"
            return false
        } else {
            textInputPass.error = null
            textInputPass.isErrorEnabled = false
            return true
        }
    }
}
