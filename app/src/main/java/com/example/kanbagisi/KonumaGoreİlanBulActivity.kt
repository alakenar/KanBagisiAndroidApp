package com.example.kanbagisi

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_kan_ilanlari.*

class KonumaGoreİlanBulActivity : AppCompatActivity(){

    private lateinit var btnAra: Button
    private lateinit var sehirArama: EditText

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konuma_gore_ilan_bul)

        btnAra= findViewById(R.id.araButton)
        sehirArama=findViewById(R.id.sehirAra)

        btnAra.setOnClickListener {
            val text=sehirArama.text.toString()

            veriAl(text)

        }

        recyclerview.apply {
            this.layoutManager = LinearLayoutManager(this@KonumaGoreİlanBulActivity)
        }

        veriAl("")

    }

    private fun veriAl(il:String?) {

        lateinit var database: DatabaseReference
        lateinit var auth: FirebaseAuth

        database = FirebaseDatabase.getInstance().reference.child("ilanlar")
        auth = FirebaseAuth.getInstance()
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val posts = ArrayList<Veriler>()

                if(il.isNullOrEmpty()){
                    dataSnapshot.children.forEach {
                        val post = it.getValue(Veriler::class.java)
                        posts.add(post!!)
                    }

                    val adapter = ListeAdapter(posts) {}
                    recyclerview.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
                else {

                    dataSnapshot.children.forEach {
                        val post = it.getValue(Veriler::class.java)
                        if(post?.il?.toLowerCase().equals(il.toLowerCase()))
                        posts.add(post!!)
                    }

                    val adapter = ListeAdapter(posts) {

                        val intent = Intent(this@KonumaGoreİlanBulActivity, İlanDetayiActivity::class.java)
                        intent.putExtra("veri", it)
                        startActivity(intent)

                    }
                    recyclerview.adapter = adapter
                    adapter.notifyDataSetChanged()

                }
            }

            override fun onCancelled(error: DatabaseError) {

                //print error.message
            }
        })

    }
}