package com.example.kanbagisi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()


        sifremi_unuttum.setOnClickListener {

            val intent = Intent(applicationContext, SifremiUnuttumActivity::class.java)
            startActivity(intent)
        }


        kayit_ol_textview.setOnClickListener {
            finish()
        }

        girisyap_button_login.setOnClickListener {

            val email = email_edittext_login.text.toString()
            val sifre = sifre_edittex_login.text.toString()

            Log.d("Login", "E-mail ve şifre ile giriş yapmayı dene: $email/***")


            if (email.isEmpty() || sifre.isEmpty()) {
                Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
            } else {
                auth.signInWithEmailAndPassword(email.trim(), sifre.trim())
                    .addOnSuccessListener {

                        val i = Intent(this, HomepageActivity::class.java)
                        startActivity(i)
                        LoginActivity().finish()

                    }.addOnFailureListener { exception ->
                        Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}