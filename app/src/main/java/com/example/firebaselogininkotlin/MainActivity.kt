package com.example.firebaselogininkotlin

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class MainActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mBtnlogin =  findViewById<View>(R.id.mBtnLogin) as Button
        val mBtnregister =   findViewById<View>(R.id.mBtnRegister) as Button

        mBtnlogin.setOnClickListener(View.OnClickListener {
                view -> login()
        })

        mBtnregister.setOnClickListener(View.OnClickListener {
                view -> register()
        })
        }
    private fun login () {
        val mEdtEmail=findViewById<View>(R.id.mEditemail) as TextView
        val mEdtPassword=findViewById<View>(R.id.mEditpassword) as TextView


    var email = mEdtEmail.text.toString()
    var password = mEdtPassword.text.toString()
        var progress=ProgressDialog(this)
        progress.setTitle("Logging in")
        progress.setMessage("Please wait...")

        if (!email.isEmpty() && !password.isEmpty()) {
            progress.show()
            this.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener ( this, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, Timeline::class.java))
                    progress.dismiss()
                    clear()
                    Toast.makeText(this, "Successful login", Toast.LENGTH_LONG).show()
                }else {
                    progress.dismiss()
                    Toast.makeText(this, "Error Logging in :(", Toast.LENGTH_LONG).show()
                }
            })

        }
        else  {
            Toast.makeText(this, "Please fill up the Credentials :|", Toast.LENGTH_LONG).show()
             }
    }

    private fun register () {
        var progress= ProgressDialog(this)
        progress.setTitle("Loading...")
        progress.setMessage("Please wait")
        progress.show()
        startActivity(Intent(this, register::class.java))
        clear()
        progress.dismiss()

    }
    private fun clear(){
        mEditemail.setText("")
        mEditpassword.setText("")
    }

}




