package com.example.domacecviceniasvlastnouvahou

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CvikAdapter(private val cvikyList: List<Cvik>) : RecyclerView.Adapter<CvikAdapter.CvikViewHolder>() {

    class CvikViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val obrazokCvikuImageView: ImageView = itemView.findViewById(R.id.obrazokCvikuImageView)
        val nazovCvikuTextView: TextView = itemView.findViewById(R.id.nazovCvikuTextView)
        val popisCvikuTextView: TextView = itemView.findViewById(R.id.popisCvikuTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CvikViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_cvik, parent, false)
        return CvikViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CvikViewHolder, position: Int) {
        val cvik = cvikyList[position]

        holder.obrazokCvikuImageView.setImageResource(cvik.obrazokId)
        holder.nazovCvikuTextView.text = cvik.nazov
        holder.popisCvikuTextView.text = cvik.opis
    }

    override fun getItemCount(): Int {
        return cvikyList.size
    }
}
