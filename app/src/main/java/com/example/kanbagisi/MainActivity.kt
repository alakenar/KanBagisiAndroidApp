package com.example.kanbagisi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_ilan.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

         if (auth.currentUser != null) {
          val i = Intent(this, HakkimizdaActivity::class.java)
          startActivity(i)
          }

        zaten_birhesabiniz_var.setOnClickListener {

            Log.d( "Main Activity", "Giriş ekranını göster")

            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        kayitol_button_register.setOnClickListener {

            val email = email_edittex_register.text.toString()
            val sifre = sifre_edittext_register.text.toString()


            if (email.isEmpty() || sifre.isEmpty()) {
                Toast.makeText(this, "Lütfen tüm alanları doldurun.", Toast.LENGTH_SHORT).show()
            } else if (sifre.length < 6) {
                Toast.makeText(this, "Şifre en az 6 karakter olmalı.", Toast.LENGTH_SHORT).show()
            } else {
                kayitOl(email, sifre)
            }

         }
        }
        private fun kayitOl(email: String, sifre: String) {
            auth.createUserWithEmailAndPassword(email.trim(), sifre)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val firebaseUser = auth.currentUser
                        val userId = firebaseUser!!.uid

                        reference = database.getReference("kullanicilar").child(userId)

                        val hashMap: HashMap<String, String> = HashMap()

                        hashMap["userID"] = userId
                        hashMap["email"] = email.trim()
                        hashMap["sifre"] = sifre

                        reference.setValue(hashMap)

                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        MainActivity().finish()

                        Log.d("MainActivity",  "Email is:" +email)
                        Log.d("MainActivity",  "Password is: $sifre")
                    }
                }.addOnFailureListener { exception ->
                    Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_SHORT).show()
                }


    }
}
