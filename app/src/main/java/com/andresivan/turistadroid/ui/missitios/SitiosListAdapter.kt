package com.andresivan.turistadroid.ui.missitios

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.andresivan.turistadroid.R
import com.andresivan.turistadroid.app.MyApp
import com.andresivan.turistadroid.entidades.lugares.Lugar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class SitiosListAdapter(
    private val sitiosLst: MutableList<Lugar>

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
        var array = mutableListOf<String>()
        val db = Firebase.firestore

        sitiosLst[index].fav = !sitiosLst[index].fav
        btnFavColor(index, holder)
        if (sitiosLst[index].fav){
            sitiosLst[index].valoracion++
        } else {
            sitiosLst[index].valoracion--
        }

        var lugaresRef = db.collection("Lugares").document(MyApp.idLugares[index])
            lugaresRef.get().addOnSuccessListener { document ->
            if (document != null) {
                Log.d("nVotos", "DocumentSnapshot data: ${document.data}")
                array = document.data!!.get("usuariosVotados") as ArrayList<String>
                añadirUsuarios(db,index, array as ArrayList<String>)
                Log.i("nVotos",array.toString())
            } else {
                Log.d("nVotos", "No such document")
            }
        }
            .addOnFailureListener { exception ->
                Log.d("nVotos", "get failed with ", exception)
            }

        //LugarController.update(sitiosLst[index])
        holder.itemValoracion.text = sitiosLst[index].valoracion.toString()
    }

    private fun añadirUsuarios(
        db: FirebaseFirestore,
        index: Int,
        array: ArrayList<String>
    ) {
        // [START update_document]
        val lugaresRef = db.collection("Lugares").document(MyApp.idLugares[index])
        lugaresRef
            .update("Votos", sitiosLst[index].valoracion)
            .addOnSuccessListener { Log.d("Votos", "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w("Votos", "Error updating document", e) }

        array.add(MyApp.correoUsuario)
        lugaresRef
            .update("usuariosVotados", array)
            .addOnSuccessListener { Log.d("nVotos", "DocumentSnapshot successfully updated!") }
            .addOnFailureListener { e -> Log.w("nVotos", "Error updating document", e) }
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
