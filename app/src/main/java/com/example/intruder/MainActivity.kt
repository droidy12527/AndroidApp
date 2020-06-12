package com.example.intruder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mAuth:FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        button.setOnClickListener(){
            val user = editText.text.toString()
            val pass = editText2.text.toString()
            val pass2 = editText3.text.toString()
            if (pass == pass2) {
                firebase_auth(user, pass)
            }else{
                Toast.makeText(applicationContext,"The Passwords do not match", Toast.LENGTH_LONG).show()
            }
        }
        button3.setOnClickListener(){
            val user = editText.text.toString()
            val pass = editText2.text.toString()
            val pass2 = editText3.text.toString()
            if (pass == pass2) {
                firebase_signup(user, pass)
            }
        }
    }
    fun firebase_auth(user:String,pass:String){
        mAuth!!.signInWithEmailAndPassword(user,pass).addOnCompleteListener(this){
            task ->
            if(task.isSuccessful){
                Toast.makeText(applicationContext, "The Login was sucessfull", Toast.LENGTH_LONG).show()
                val currentuser = mAuth!!.currentUser
                val email = currentuser!!.email.toString()
                Log.d("Logar:", currentuser.uid)
                var prath = Intent(this,newactivity::class.java)
                prath.putExtra("email",email)
                startActivity(prath)
            }
            else{
                Toast.makeText(applicationContext, "The Login failed", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun firebase_signup(user:String,pass:String){
        mAuth!!.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(this){
                task ->
            if(task.isSuccessful){
                Toast.makeText(applicationContext, "Registration was Sucessfull", Toast.LENGTH_LONG).show()
                val currentuser = mAuth!!.currentUser
                Log.d("Logar:",currentuser!!.uid)
            }
            else{
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
            }
        }
    }

}
