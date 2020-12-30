package com.example.kanbagisi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_kan_ilanlari.*

class VerdigimİlanlarActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_verdigim_ilanlar)


        recyclerview.apply {
            this.layoutManager = LinearLayoutManager(this@VerdigimİlanlarActivity)
        }

        veriAl()

    }

    private fun veriAl() {

        lateinit var database: DatabaseReference
        lateinit var auth: FirebaseAuth

        database = FirebaseDatabase.getInstance().reference.child("ilanlar")
        auth = FirebaseAuth.getInstance()
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val posts = ArrayList<Veriler>()

                dataSnapshot.children.forEach {
                    val post = it.getValue(Veriler::class.java)

                  Log.d("ifüstü", post.toString())
                    if (post?.userId == auth.currentUser?.uid) {
                        posts.add(post!!)
                        Log.d("ifici", post.toString())
                    }
                }

                val adapter = ListeAdapter(posts) {

                    val intent = Intent(this@VerdigimİlanlarActivity, İlanDetayiActivity::class.java)
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
