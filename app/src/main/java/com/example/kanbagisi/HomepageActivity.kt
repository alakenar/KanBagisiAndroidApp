package com.example.kanbagisi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_homepage.*
import java.io.IOException

class HomepageActivity : AppCompatActivity() {



    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_homepage)

        auth= FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()


        getToken()

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

        val button3 = findViewById<Button>(R.id.konuma_gore_ilan_bul_button)

        konuma_gore_ilan_bul_button.setOnClickListener {

            val intent = Intent(this, KonumaGoreİlanBulActivity::class.java)
            startActivity(intent)

        }

        val button4 = findViewById<Button>(R.id.verdigim_ilanlar_button)

        verdigim_ilanlar_button.setOnClickListener {

            val intent = Intent(this, VerdigimİlanlarActivity::class.java)
            startActivity(intent)

        }

        val button5 = findViewById<Button>(R.id.nobetci_eczane_button)

        nobetci_eczane_button.setOnClickListener {

            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://ecza.io/"))
            startActivity(i)

        }

        val button6 = findViewById<Button>(R.id.kan_bagis_noktalari_button)

        kan_bagis_noktalari_button.setOnClickListener {

            val i = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://kanver.org/KanHizmetleri/KanBagisiNoktalari")
            )
            startActivity(i)

        }

        val button7 = findViewById<Button>(R.id.hakimizda_button)

        hakimizda_button.setOnClickListener {

            val intent = Intent(this, HakkimizdaActivity::class.java)
            startActivity(intent)

        }
    }



    private fun getToken() {

        val reference = database.getReference("Tokens")

        Thread(Runnable {
            try {
                val newToken = FirebaseInstanceId.getInstance().token

                reference.child(auth.currentUser!!.uid)
                    .setValue(newToken)

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }).start()
} }