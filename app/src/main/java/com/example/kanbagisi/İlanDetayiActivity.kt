package com.example.kanbagisi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_ilan_detayi.*

class İlanDetayiActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_ilan_detayi)

           val veri= intent.extras?.getParcelable<Veriler>("veri")

           Log.d("veri", veri.toString())

        textView.text
    }

}