package com.example.kanbagisi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        kayitol_button_register.setOnClickListener {

            val email= email_edittex_register.text.toString()
            val sifre = sifre_edittext_register.text.toString()


            Log.d("MainActivity",  "Email is:" +email)
            Log.d("MainActivity",  "Password is: $sifre")


        }

        zaten_birhesabiniz_var.setOnClickListener {

            Log.d( "Main Activity", "Giriş ekranını göster")

            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }



    }
}
