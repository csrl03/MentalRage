package com.example.mentalrage.ui

import CardClass
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mentalrage.R

class CardAdapter(private val cardList: List<CardClass>, private val onClick: (CardClass) -> Unit) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardImage: ImageView = itemView.findViewById(R.id.cardImage)
        val cardText: TextView = itemView.findViewById(R.id.cardText)
        val cardContainer: CardView = itemView.findViewById(R.id.cardContainer) // Contenedor de la tarjeta
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tarjeta_categoria_inicio, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = cardList[position]

        // Configurar imagen y texto
        holder.cardImage.setImageResource(item.imageResId)
        holder.cardText.text = item.text

        // Cambiar el color del texto dinámicamente
        holder.cardText.setTextColor(
            Color.parseColor(item.textColor) // Convierte el String en un valor Int de color
        )

        // Cambiar el color de fondo de la tarjeta dinámicamente
        holder.cardContainer.setCardBackgroundColor(
            Color.parseColor(item.background) // Convierte el String en un valor Int de color
        )

        // Configurar clic en cada tarjeta
        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    override fun getItemCount(): Int = cardList.size
}
