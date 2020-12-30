package com.example.kanbagisi

import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_ilan.view.*

class ListeViewHolder (val viewGroup: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_ilan , viewGroup, false)) {

    fun bindTo(veriler: Veriler, onClickListener: (Veriler) -> Unit) {

        itemView.ilanİl.text = veriler.il
        itemView.ilanKanGrubu.text = veriler.kangrubu

        itemView.itemContainer.setOnClickListener {

            onClickListener(veriler)

            }
        }

    }



