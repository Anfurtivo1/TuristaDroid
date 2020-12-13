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
    private val listaLugares: MutableList<Lugar>,
    // Famos a tener distintas acciones y eventos
    private val accionPrincipal: (Lugar) -> Unit

) : RecyclerView.Adapter<SitiosListAdapter.LugarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugarViewHolder {
        return LugarViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_lugar, parent, false)
        )
    }

    /**
     * Procesamos los lugares y las metemos en un Holder
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: LugarViewHolder, position: Int) {
        holder.itemLugarNombre.text = listaLugares[position].nombre
        holder.itemLugarFecha.text = listaLugares[position].fecha
        holder.itemLugarTipo.text = listaLugares[position].tipo
        holder.itemLugarVotos.text = listaLugares[position].valoracion.toString()
        holder.itemLugarImagen.setImageBitmap(imagenLugar(listaLugares[position], holder))

        // procesamos el ffavorito
        // color
        colorBotonFavorito(position, holder)
        // Queda procesar el botón de favoritos...
        holder.itemLugarFavorito.setOnClickListener {
            eventoBotonFavorito(position, holder)

        }

        // Programamos el clic de cada fila (itemView)
        holder.itemLugarImagen
            .setOnClickListener {
                // Devolvemos la noticia
                accionPrincipal(listaLugares[position])
            }
    }


    /**
     * Elimina un item de la lista
     *
     * @param position
     */
    fun removeItem(position: Int) {
        listaLugares.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, listaLugares.size)
    }

    /**
     * Recupera un Item de la lista
     *
     * @param item
     * @param position
     */
    fun updateItem(item: Lugar, position: Int) {
        listaLugares[position] = item
        notifyItemInserted(position)
        notifyItemRangeChanged(position, listaLugares.size)
    }

    /**
     * Para añadir un elemento
     * @param item
     */
    fun addItem(item: Lugar) {
        listaLugares.add(item)
        notifyDataSetChanged()
    }


    /**
     * Devuelve el número de items de la lista
     *
     * @return
     */
    override fun getItemCount(): Int {
        return listaLugares.size
    }

    /**
     * Devuelve la imagen de un lugar
     * @param lugar Lugar
     * @return Bitmap?
     */
    private fun imagenLugar(lugar: Lugar, holder: LugarViewHolder): Bitmap? {
        try {
            val fotografia = FotoController.selectById(lugar.imgID)
            return ABase64.toBitmap(fotografia?.imgLugar.toString())
        } catch (ex: Exception) {
            return BitmapFactory.decodeResource(holder.context?.resources, R.drawable.ic_sitio);
        }
    }

    /**
     * Procesa el favorito
     * @param position Int
     */
    private fun eventoBotonFavorito(position: Int, holder: LugarViewHolder) {
        // Cambiamos el favorito
        listaLugares[position].fav = !listaLugares[position].fav
        // Procesamos el color
        colorBotonFavorito(position, holder)
        // Procesamos el número de votos
        if(listaLugares[position].fav)
            listaLugares[position].valoracion ++
        else
            listaLugares[position].valoracion --

        LugarController.update(listaLugares[position])
        holder.itemLugarVotos.text = listaLugares[position].valoracion.toString()
        Log.i("Favorito", listaLugares[position].fav.toString())
        Log.i("Favorito", listaLugares[position].valoracion.toString())
    }

    /**
     * Pone el color del fondo del botom de favoritos
     * @param position Int
     * @param holder LugarViewHolder
     */
    private fun colorBotonFavorito(
        position: Int,
        holder: LugarViewHolder
    ) {
        if (listaLugares[position].fav)
            holder.itemLugarFavorito.backgroundTintList =
                AppCompatResources.getColorStateList(holder.context, R.color.favYes)
        else
            holder.itemLugarFavorito.backgroundTintList =
                AppCompatResources.getColorStateList(holder.context, R.color.favNo)
    }

    /**
     * Holder que encapsula los objetos a mostrar en la lista
     */
    class LugarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Elementos graficos con los que nos asociamos
        var itemLugarImagen = itemView.itemLugarImagen
        var itemLugarNombre = itemView.itemLugarNombre
        var itemLugarFecha = itemView.itemLugarFecha
        var itemLugarTipo = itemView.itemLugarTipo
        var itemLugarVotos = itemView.itemLugar_Valoraciones
        var itemLugarFavorito = itemView.itemSitioFav
        var context = itemView.context

    }
}
