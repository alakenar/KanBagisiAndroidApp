package com.example.kanbagisi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)


        girisyap_button_login.setOnClickListener {

            val email= email_edittext_login.text.toString()
            val password= sifre_edittex_login.text.toString()

             Log.d("Login", "E-mail ve şifre ile giriş yapmayı dene: $email/***")



        }
         kayit_ol_textview.setOnClickListener {
             finish()
         }
    }




}