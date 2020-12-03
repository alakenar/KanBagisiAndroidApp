package com.example.kanbagisi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_homepage.*

class HomepageActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_homepage)

        val button = findViewById<Button>(R.id.kan_ilanlari_button)

        kan_ilanlari_button.setOnClickListener {

            val intent = Intent(this, KanilanlariActivity::class.java)
            startActivity(intent)

        }

        val button2 = findViewById<Button>(R.id.kan_ilani_ver_button)

        kan_ilani_ver_button.setOnClickListener {

            val intent = Intent(this, KanilaniVerActivity::class.java)
            startActivity(intent)
        }
    }
}