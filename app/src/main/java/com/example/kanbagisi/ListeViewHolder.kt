package com.example.kanbagisi

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_ilan.view.*

class ListeViewHolder (val viewGroup: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_ilan , viewGroup, false)) {

    fun bindTo(veriler: Veriler, onClickListener:(Veriler)->Unit) {

        itemView.il.text=veriler.il
        itemView.adSoyad.text=veriler.adsoyad
        itemView.hastaneAdi.text=veriler.hastaneadi
        itemView.kanGrubu.text=veriler.kangrubu
        itemView.telNo.text=veriler.telno

        itemView.itemContainer.setOnClickListener{
        onClickListener(veriler)




        }

    }

}


