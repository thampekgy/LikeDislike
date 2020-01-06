package com.example.likedislike

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.Preference
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.prefs.AbstractPreferences
import java.util.prefs.Preferences

class MainActivity : AppCompatActivity() {

    //Module-Level
    var like:Int = 0
    var disLike:Int = 0

    //Define a module level Preference
    lateinit var preferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "OnCreate")

        //initalise the preference
        preferences = getPreferences(Context.MODE_PRIVATE)

        //display default values of like and dislike
        textViewUp.text = like.toString()
        textViewDown.text = disLike.toString()



        imageViewUp.setOnClickListener{
            like ++
            textViewUp.text = like.toString()
        }

        imageViewDown.setOnClickListener{
            disLike ++
            textViewDown.text = disLike.toString()
        }

        //Implicit Intent
        btnCall.setOnClickListener {
            val phone = "tel:0123456789"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(phone))
            //Validate the intent is workable
            if (intent.resolveActivity(packageManager)!= null){
                startActivity(intent)
        }else{
                //Inform user to install an app that can handle
                //this intent
            }
        }




    }

    override fun onStart() {
        Log.d("MainActivity", "OnStart")
        super.onStart()
    }

    override fun onPause() {
        Log.d("MainActivity", "OnPause")

        with(preferences.edit()){
            putInt(getString(R.string.like),like)
            putInt(getString(R.string.disLike),disLike)
            commit()
        }

        super.onPause()
    }

    override fun onResume() {
        Log.d("MainActivity", "OnResume")

        //Retrieve the existing like and dislike values
        like = preferences.getInt(getString(R.string.like), 0)
        disLike = preferences.getInt(getString(R.string.disLike), 0)


        textViewUp.text = like.toString()
        textViewDown.text = disLike.toString()

        super.onResume()
    }

    override fun onStop() {
        Log.d("MainActivity", "OnStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MainActivity", "OnDestroy")
        super.onDestroy()
    }
}
