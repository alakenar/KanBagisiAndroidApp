package com.example.kanbagisi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_kan_ilanlari.*

class KanilanlariActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_kan_ilanlari)


            recyclerview.apply {
                this.layoutManager = LinearLayoutManager(this@KanilanlariActivity)
            }

            veriAl();
        }

        private fun veriAl() {

            lateinit var database: DatabaseReference

            database = FirebaseDatabase.getInstance().reference.child("ilanlar")

            database.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val posts = ArrayList<Veriler>()

                    dataSnapshot.children.forEach {
                        val post = it.getValue(Veriler::class.java)?.let {

                            posts.add(it)
                            Log.d("veriler", posts.toString())
                        }

                    }
                    val adapter = ListeAdapter(posts) {

                        val intent =
                            Intent(this@KanilanlariActivity, İlanDetayiActivity::class.java)
                        intent.putExtra("veri", it)
                        startActivity(intent)

                    }
                    recyclerview.adapter = adapter
                    adapter.notifyDataSetChanged()

                }

                override fun onCancelled(error: DatabaseError) {
                    //print error.message
                }
            })
        }
    }



