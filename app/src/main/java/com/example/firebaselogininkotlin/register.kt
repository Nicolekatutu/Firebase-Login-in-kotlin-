package com.example.firebaselogininkotlin

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class register : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val mBtnregister = findViewById<View>(R.id.mBtnRegister) as Button

        mDatabase = FirebaseDatabase.getInstance().getReference("Names")

        mBtnregister.setOnClickListener(View.OnClickListener {
                view -> registeruser()
        })
    }

    private fun registeruser (){
        val mEdtname = findViewById<View>(R.id.mEdtname) as EditText
        val mEdtemail = findViewById<View>(R.id.mEdtemail) as EditText
        val mEdtpassword = findViewById<View>(R.id.mEdtpassword) as EditText

        var name = mEdtname.text.toString()
        var email = mEdtemail.text.toString()
        var password = mEdtpassword.text.toString()
        var progress=ProgressDialog(this)
        progress.setTitle("Registering")
        progress.setMessage("Please wait...")



        if ( !name.isEmpty() && !password.isEmpty() && !email.isEmpty() ) {
            progress.show()
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful){
                    val user = mAuth.currentUser
                    val uid = user!!.uid.toString()
                    mDatabase.child(uid).child("Names").setValue(name)
                    progress.dismiss()
                    Toast.makeText(this, "Registered successfully", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, Timeline::class.java))

                }else{
                    progress.dismiss()
                    Toast.makeText(this, "Error in registering", Toast.LENGTH_LONG).show()
                }
            })
        }
        //else if (name.isEmpty() && password.isEmpty()&& email.isEmpty()){
                   // Toast.makeText(this, "Please fill up the inputs", Toast.LENGTH_LONG).show()
        //}
    }

}
