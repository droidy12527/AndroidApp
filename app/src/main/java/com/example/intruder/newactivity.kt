package com.example.intruder

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.module.AppGlideModule
import com.firebase.ui.storage.images.FirebaseImageLoader
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_newactivity.*
import java.text.SimpleDateFormat
import java.util.*


class newactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newactivity)
        val prathamesh:Intent = intent
        var user_email = prathamesh.getStringExtra("email")
        textView3.text = "$user_email"
        button2.setOnClickListener(){
            loadImage()
        }
    }

    override fun onStart() {
        super.onStart()
        loadImage()
    }

    private fun loadImage(){
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val current = sdf.format(Date())
        val path = SimpleDateFormat("HH-mm")
        val pathx = path.format(Date())
        val storeageRef = Firebase.storage.reference.child("$current/$pathx.jpg")
         storeageRef.downloadUrl.addOnSuccessListener {
             if (it!=null){
                Glide.with(this).load(it).into(hellimage)
             }
         }   .addOnFailureListener {
             Toast.makeText(applicationContext,"The Images are not there in database",Toast.LENGTH_LONG).show()
         }
    }
}
