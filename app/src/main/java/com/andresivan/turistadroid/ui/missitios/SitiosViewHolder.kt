package com.andresivan.turistadroid.ui.missitios

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.item_lugar.view.*

class SitiosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var itemFoto: ImageView = itemView.itemLugarImagen
    var itemNombre: TextView = itemView.itemLugarNombre
    var itemFecha: TextView = itemView.itemLugarFecha
    var itemTipo: TextView = itemView.itemLugarTipo
    var itemValoracion: TextView = itemView.itemLugar_Valoraciones
    var itemFav: FloatingActionButton = itemView.itemSitioFav
    var context: Context = itemView.context
}