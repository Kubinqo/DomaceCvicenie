package com.example.domacecviceniasvlastnouvahou

import Cvik
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Trieda CvikAdapter slúži ako adaptér pre zobrazenie zoznamu cvikov v RecyclerView.
 *
 * @property cvikyList Zoznam cvikov, ktoré sa majú zobraziť.
 */
class CvikAdapter(private val cvikyList: List<Cvik>) : RecyclerView.Adapter<CvikAdapter.CvikViewHolder>() {

    /**
     * Trieda CvikViewHolder predstavuje view holder pre položky cvikov v RecyclerView.
     *
     * @param itemView View položky cviku.
     */
    class CvikViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val obrazokCvikuImageView: ImageView = itemView.findViewById(R.id.obrazokCvikuImageView)
        val nazovCvikuTextView: TextView = itemView.findViewById(R.id.nazovCvikuTextView)
        val popisCvikuTextView: TextView = itemView.findViewById(R.id.popisCvikuTextView)
    }

    /**
     * Vytvára nové inštancie triedy CvikViewHolder na základe layoutu položky cviku.
     *
     * @param parent Rodičovský ViewGroup.
     * @param viewType Typ zobrazených položiek.
     * @return Inštancia CvikViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CvikViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_cvik, parent, false)
        return CvikViewHolder(itemView)
    }

    /**
     * Metóda, ktorá vytvára väzby dát cviku na view holder položky cviku.
     *
     * @param holder View holder položky cviku.
     * @param position Pozícia položky v zozname.
     */
    override fun onBindViewHolder(holder: CvikViewHolder, position: Int) {
        val cvik = cvikyList[position]

        holder.obrazokCvikuImageView.setImageResource(cvik.obrazokId)
        holder.nazovCvikuTextView.text = cvik.nazov
        holder.popisCvikuTextView.text = cvik.popis
    }

    /**
     * Vráti počet itemov v zozname cvikov.
     *
     * @return Počet položiek.
     */
    override fun getItemCount(): Int {
        return cvikyList.size
    }
}
