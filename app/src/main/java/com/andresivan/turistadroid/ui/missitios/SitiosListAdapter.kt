package com.andresivan.turistadroid.ui.missitios

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.andresivan.turistadroid.R
import com.andresivan.turistadroid.entidades.fotos.FotoController
import com.andresivan.turistadroid.entidades.lugares.Lugar
import com.andresivan.turistadroid.entidades.lugares.LugarController
import com.andresivan.turistadroid.utils.ABase64
import kotlinx.android.synthetic.main.item_lugar.view.*

class SitiosListAdapter(
    private val listaSitios: MutableList<Lugar>,
    private val funcionPrincipal: (Lugar) -> Unit

) : RecyclerView.Adapter<SitiosListAdapter.SitioViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SitioViewHolder {
        return SitioViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_lugar, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SitioViewHolder, position: Int) {
        holder.itemLugarNombre.text = listaSitios[position].nombre
        holder.itemLugarFecha.text = listaSitios[position].fecha
        holder.itemLugarTipo.text = listaSitios[position].tipo
        holder.itemLugarVotos.text = listaSitios[position].valoracion.toString()
        holder.itemLugarImagen.setImageBitmap(imagenSitio(listaSitios[position], holder))
        colorBotonFav(position, holder)
        // Queda procesar el botón de favoritos...
        holder.itemLugarFavorito.setOnClickListener {
            eventoBotonFav(position, holder)

        }

        // Programamos el clic de cada fila (itemView)
        holder.itemLugarImagen
            .setOnClickListener {
                // Devolvemos la noticia
                funcionPrincipal(listaSitios[position])
            }
    }


    fun removeItem(position: Int) {
        listaSitios.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, listaSitios.size)
    }

    fun updateItem(item: Lugar, position: Int) {
        listaSitios[position] = item
        notifyItemInserted(position)
        notifyItemRangeChanged(position, listaSitios.size)
    }

    fun addItem(item: Lugar) {
        listaSitios.add(item)
        notifyDataSetChanged()
    }

    private fun eventoBotonFav(position: Int, holder: SitioViewHolder) {
        // Cambiamos el favorito
        listaSitios[position].fav = !listaSitios[position].fav
        // Procesamos el color
        colorBotonFav(position, holder)
        // Procesamos el número de votos
        if (listaSitios[position].fav)
            listaSitios[position].valoracion++
        else
            listaSitios[position].valoracion--

        LugarController.update(listaSitios[position])
        holder.itemLugarVotos.text = listaSitios[position].valoracion.toString()
        Log.i("Favorito", listaSitios[position].fav.toString())
        Log.i("Favorito", listaSitios[position].valoracion.toString())
    }

    override fun getItemCount(): Int {
        return listaSitios.size
    }

    private fun colorBotonFav(

        position: Int,
        holder: SitioViewHolder
    ) {
        if (listaSitios[position].fav)
            holder.itemLugarFavorito.backgroundTintList =
                AppCompatResources.getColorStateList(holder.context, R.color.favYes)
        else
            holder.itemLugarFavorito.backgroundTintList =
                AppCompatResources.getColorStateList(holder.context, R.color.favNo)
    }

    private fun imagenSitio(lugar: Lugar, holder: SitioViewHolder): Bitmap? {
        try {
            val fotoSitio = FotoController.selectById(lugar.imgID)
            return ABase64.toBitmap(fotoSitio?.imgLugar.toString())
        } catch (ex: Exception) {
            return BitmapFactory.decodeResource(holder.context?.resources, R.drawable.ic_sitio);
        }
    }

    class SitioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemLugarImagen = itemView.itemLugarImagen
        var itemLugarNombre = itemView.itemLugarNombre
        var itemLugarFecha = itemView.itemLugarFecha
        var itemLugarTipo = itemView.itemLugarTipo
        var itemLugarVotos = itemView.itemLugar_Valoraciones
        var itemLugarFavorito = itemView.itemSitioFav
        var context = itemView.context

    }
}

