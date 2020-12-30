package com.example.kanbagisi

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sifremi_unuttum.*

class SifremiUnuttumActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sifremi_unuttum)

        buttonSifUnut.setOnClickListener {

            val email = emailSifUnut.text.toString().trim { it <= ' ' }

            if (email.isEmpty()) {
                Toast.makeText(
                    this@SifremiUnuttumActivity,
                    "Lütfen e-mail adresinizi giriniz.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            Toast.makeText(
                                this@SifremiUnuttumActivity,
                                "Lütfen e-mail  adresinizi kontrol edin ve parolanızı sıfırlayın.",
                                Toast.LENGTH_LONG
                            ).show()

                            finish()
                        } else {
                            Toast.makeText(
                                this@SifremiUnuttumActivity,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()

                        }
                    }
            }
        }
    }
}
