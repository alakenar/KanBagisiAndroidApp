package com.example.kanbagisi

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListeAdapter(private val newList: List<Veriler>,val onClickListener:(Veriler)->Unit) : RecyclerView.Adapter<ListeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListeViewHolder {
        return ListeViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return newList.size
    }

    override fun onBindViewHolder(holder: ListeViewHolder, position: Int) {
        holder.bindTo(newList[position], onClickListener)
    }




}
