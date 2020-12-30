package com.example.kanbagisi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.TextureView
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activit_kan_ilani_ver.*
import kotlinx.android.synthetic.main.activity_ilan_detayi.*
import kotlinx.android.synthetic.main.item_ilan.*
import kotlinx.android.synthetic.main.item_ilan.view.*

class İlanDetayiActivity : AppCompatActivity() {


    internal var numara: String?= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_ilan_detayi)

        val veri = intent.extras?.getParcelable<Veriler>("veri")

        //Log.d("veri", veri?.il.toString())


        val textIlan = findViewById<TextView>(R.id.textIlan)

        textIlan.text= veri?.il.toString()+"ilinde ki"+veri?.hastaneadi.toString()+"yatmakta olan"


        val buttonKonum = findViewById<Button>(R.id.konum_button)

        konum_button.setOnClickListener {

            val i = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com.tr/maps/dir//36.842046,30.599106/@36.842046,30.599106,17z?hl=tr")
            )
            startActivity(i)

        }

        val buttonArama = findViewById<Button>(R.id.telefon_button)
        telefon_button.setOnClickListener {

            val numara = veri?.telno?.trim()
                val intent= Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(numara)))
                startActivity(intent)

        }

        val buttonPaylasim = findViewById<Button>(R.id.paylasim_button)

        paylasim_button.setOnClickListener {

            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            startActivity(Intent.createChooser(sharingIntent, "Paylaşmak İçin Seçiniz"))
        }

        }
    }



