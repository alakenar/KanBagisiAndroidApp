package com.example.kanbagisi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class BaslangicEkraniActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_baslangic_ekrani)

        val background = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(2000)
                    val intent = Intent(this@BaslangicEkraniActivity, MainActivity::class.java)
                    startActivity(intent)
                }catch (e : Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}






