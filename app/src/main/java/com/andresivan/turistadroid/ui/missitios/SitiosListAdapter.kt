package com.andresivan.turistadroid.ui.missitios

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.andresivan.turistadroid.R
import com.andresivan.turistadroid.entidades.lugares.Lugar
import com.andresivan.turistadroid.utils.ABase64


class SitiosListAdapter(
    private val sitiosLst: MutableList<Lugar>,
    private val mainFuntcion: (Lugar) -> Unit

) : RecyclerView.Adapter<SitiosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SitiosViewHolder {
        return SitiosViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_lugar, parent, false)
        )
    }

    /**
     * Función que se encarga de cargar el registro dado por parametro es decir, por su posicion,
     * y cargar su inforamción en el formulario
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: SitiosViewHolder, position: Int) {
        holder.itemNombre.text = sitiosLst[position].nombre
        holder.itemFecha.text = sitiosLst[position].fecha
        holder.itemTipo.text = sitiosLst[position].tipo
        holder.itemValoracion.text = sitiosLst[position].valoracion.toString()
        //holder.itemFoto.setImageBitmap(fotoLugar(sitiosLst[position], holder))
        btnFavColor(position, holder)
        holder.itemFav.setOnClickListener {
            btnFavAcciones(position, holder)
        }
        holder.itemFoto.setOnClickListener {
            mainFuntcion(sitiosLst[position])
        }
    }


    /**
     * Función que se encarga de eliminar un registro dado su índice en el que se encontraba
     * anteriormente
     *
     * @param position Int donde se encontraba anteriormente en la lista
     */
    fun eliminarRegistroLista(position: Int) {
        sitiosLst.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, sitiosLst.size)
    }

    /**
     * Función que se encarga de encontrar un registro que hemos modificado anteriormente por su
     * posicion
     *
     * @param item Lugar el registro que hemos modificado
     * @param position Int la posicion en la que se encontraba anteriormente
     */
    fun modificarRegistroLista(item: Lugar, position: Int) {
        sitiosLst[position] = item
        notifyItemInserted(position)
        notifyItemRangeChanged(position, sitiosLst.size)
    }

    /**
     * Función para añadir un nuevo registro a la lista de registros
     * @param item Lugar que añadimos a la lista de registros
     */
    fun addRegistroALista(item: Lugar) {
        sitiosLst.add(item)
        notifyDataSetChanged()
    }


    /**
     * Función que noe permite contar el numero de registros de la lista
     * @return el tamaño de la lista de imagenes
     */
    override fun getItemCount(): Int {
        return sitiosLst.size
    }

    /**
     * Esta función realiza todas las tareas que se necesitan para cuando pulsamos el boton de like
     */
    private fun btnFavAcciones(index: Int, holder: SitiosViewHolder) {
        sitiosLst[index].fav = !sitiosLst[index].fav
        btnFavColor(index, holder)
        if (sitiosLst[index].fav){
            sitiosLst[index].valoracion++
        } else {
            sitiosLst[index].valoracion--
        }

        //LugarController.update(sitiosLst[index])
        holder.itemValoracion.text = sitiosLst[index].valoracion.toString()
    }

    /**
     * Función que según si le damos a like a la publicación, el color de botón cambiará a un color
     * u otro
     */
    private fun btnFavColor(position: Int, holder: SitiosViewHolder) {
        if (sitiosLst[position].fav) {
            holder.itemFav.backgroundTintList =
                AppCompatResources.getColorStateList(holder.context, R.color.favYes)
        }else {
            holder.itemFav.backgroundTintList =
                AppCompatResources.getColorStateList(holder.context, R.color.favNo)
        }
    }



}
