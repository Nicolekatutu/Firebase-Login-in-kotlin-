package com.example.firebaselogininkotlin

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Timeline : AppCompatActivity() {
    var mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    @SuppressLint("WrongViewCast")
   // var user = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)

        val txtname = findViewById<View>(R.id.txtname) as TextView

        //var uid = user!!.uid

        mDatabase = FirebaseDatabase.getInstance().getReference("Name")

        mDatabase.addValueEventListener( object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val result = snapshot.child("Name").getValue().toString()
                txtname.text = "Welcome" + result
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item!!.itemId == R.id.signout) {
            mAuth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(this, "Signed Out", Toast.LENGTH_LONG).show()

        }
        return super.onOptionsItemSelected(item)
    }

}
