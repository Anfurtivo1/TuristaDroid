package com.andresivan.turistadroid.ui.missitios

import android.app.Activity.RESULT_CANCELED
import android.content.Intent
import android.graphics.*
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andresivan.turistadroid.R
import com.andresivan.turistadroid.app.MyApp
import com.andresivan.turistadroid.entidades.lugares.Lugar
import com.andresivan.turistadroid.ui.missitios.Filtross.FiltrosControlador
import com.andresivan.turistadroid.ui.missitios.filtros.Filtros
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_missitios.*


class MisSitios : Fragment() {

    var SITIOS: MutableList<Lugar> = mutableListOf()
    private lateinit var sitiosAdapter: SitiosListAdapter
    private var fondoAlDeslizar = Paint()
    private var FILTRO_ORDENACIÓN = Filtros.NADA
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_missitios, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        consultar()

        super.onViewCreated(view, savedInstanceState)
        iniciarInterfaz()
    }

    private fun consultar(){
        db.collection("Lugares")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.i("Consulta", "${document.id} => ${document.data}")
                    Log.i("Consulta", document.data.getValue("NombreLugar").toString())

                }
            }
            .addOnFailureListener { exception ->
                Log.d("Consulta", "Error getting documents: ", exception)
            }

    }

    /**
     * Función que crea todas las funcionalidades necesarias para este fragment, lo que hacemos es
     * llamar a las ditintas funciones dentro de esta, en el caso que se quisiera dar nueva
     * funcionalidad a algo visual, seria el lugar correcto para declararla
     */
    private fun iniciarInterfaz() {
        actualizar()
        // COGEMOS DE LA BBDD TODOS LOS REGISTROS
        cargarRegistros()
        //para cuando queremos modificar o borrar un registro
        //deslizarHorizontalmente()
        //Se inicia el listener de lugares
        iniciarListener()
        /*
        este es el momento en el que al floatActionButton que tenemos abajo a la derecha, se le
        asgina una funcionalidad, y esta funcionalidad es la de añadir nuevos lugares
         */
        fabNuevo.setOnClickListener { nuevoRegistro() }
        sitiosAdapter = SitiosListAdapter(MyApp.listaLugares)

    }

    private fun iniciarListener(){
        //Actualizacion tiempo real
        db.collection("Lugares")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w("", "Listen failed.", e)
                    return@addSnapshotListener
                }
                Log.i("Actualizar",value.toString())
                    cargarRegistros()
                }
            }
        //Fin actualizacion tiempo real

    /**
     * Función que permite cargar los filtros de ordenación dentro del spinner del fragment de
     * Mis sitios
     */
    private fun cargarFiltros() {
        //cargamos el array de condiciones de ordenacion de string.xml. llamando al array de filtros que creamos
        val tipoOrdenacion = resources.getStringArray(R.array.filtros)
        // lo cargamos en un adaptador
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item, tipoOrdenacion)
        //y relacionamos el adaptador
        misSitiosSpinnerFiltro.adapter = adapter

        misSitiosSpinnerFiltro.onItemSelectedListener = object :AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                FILTRO_ORDENACIÓN = FiltrosControlador.indiceFiltrosSpinner(position)
                mostrarListaRegistros()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                /*
                NO HACEMOS NADA PORQUE HEMOS INDICADO AL INICIALIZAR LA VARIABLE FILTRO, QUE SI NO
                CAMBIA, POR DEFECTO, ORDENE POR NADA, PORLO QUE CARGARÁN POR ORDEN DE CREACION DEL
                REGISTRO
                 */
            }
        }
    }

    /**
     * Función que permite recargar la pantalla para comprobar si ha habido alguna modificacion, o
     * nuevo registro o alguno eliminado
     */
    private fun actualizar() {
        sitiosSwipeRefresh.setColorSchemeResources(R.color.colorPrimaryDark)
        sitiosSwipeRefresh.setProgressBackgroundColorSchemeResource(R.color.colorAccent)
        sitiosSwipeRefresh.setOnRefreshListener {
            cargarRegistros()
        }
    }

    /**
     * Función que permite al usuario deslizar horizantalmente sobre uno de los items del RecyclerView
     * Le asignamos una funcioanlidad, y un color en el fondo cuando deslizamos
     */
    private fun deslizarHorizontalmente() {
        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or
                    ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            /**
             * Función que se encargar de controlar sobre que direcciñon de la pantalla deslizamos
             * segun la direccion, nos interesa realizar una función u otra, en este caso, se nos
             * pedía que al deslizar, debíamos modificar u eliminar un registro de la BBDD, cargado
             * en el RecyclerView del fragment de Mis sitios
             * @param viewHolder
             * @param direction Int
             */
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                /*
                la posicion es el registro que queremos modificar o eliminar, es la podicion
                que tiene el registro en el array de Lugares
                 */
                val indiceRegistro = viewHolder.adapterPosition
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        borrar(indiceRegistro)
                    }
                    else -> {
                        modificar(indiceRegistro)
                    }
                }
            }
            override fun onChildDraw(canvas: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val itemView = viewHolder.itemView
                    val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                    val width = height / 3
                    if (dX > 0) {
                        pintarDirIzq(canvas, dX, itemView, width)
                    } else {
                        pintarDirDer(canvas, dX, itemView, width)
                    }
                }
                super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        /*
        Le asignamos la funcion del ItemTouchHelper al RecyclerView del fragment
         */
        itemTouchHelper.attachToRecyclerView(sitiosRecycler)
    }

    /**
     * Función que permite pintar hacia la der el fondo de color rojo para poder mostrarnos que vamos
     * a eliminar el registro, a la vez, vamos a mostrar un vector asset, con un loguito pequeño para
     * indicar que lo que estamos haciendo es eliminar
     * @param canvas Canvas
     * @param dX Float
     * @param itemView View
     * @param width Float
     */
    private fun pintarDirDer(canvas: Canvas, dX: Float, itemView: View, width: Float) {
        fondoAlDeslizar.color = Color.RED
        val background = RectF(
            itemView.right.toFloat() + dX,
            itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat()
        )
        canvas.drawRect(background, fondoAlDeslizar)
        val icon: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_borrar)
        val iconDest = RectF(
            itemView.right.toFloat() - 2 * width, itemView.top.toFloat() + width, itemView.right
                .toFloat() - width, itemView.bottom.toFloat() - width
        )
        canvas.drawBitmap(icon, null, iconDest, fondoAlDeslizar)
    }

    /**
     * Función que permite pintar hacia la izq el fondo de color azul para poder mostrarnos que vamos
     * a modificar el registro, a la vez, vamos a mostrar un vector asset, con un loguito pequeño para
     * indicar que vamos a modificar
     * @param canvas Canvas
     * @param dX Float
     * @param itemView View
     * @param width Float
     */
    private fun pintarDirIzq(canvas: Canvas, dX: Float, itemView: View, width: Float) {
        // Pintamos de azul y ponemos el icono
        fondoAlDeslizar.color = Color.BLUE
        val background = RectF(
            itemView.left.toFloat(), itemView.top.toFloat(), dX,
            itemView.bottom.toFloat()
        )
        canvas.drawRect(background, fondoAlDeslizar)
        val icon: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_editar)
        val iconDest = RectF(
            itemView.left.toFloat() + width, itemView.top.toFloat() + width, itemView.left
                .toFloat() + 2 * width, itemView.bottom.toFloat() - width
        )
        canvas.drawBitmap(icon, null, iconDest, fondoAlDeslizar)
    }

    /**
     * Función que se encarga de llamar a la funcion cargarRegistros() que es la función que carga
     * la información de los registros de la bbdd
     */
    private fun cargarRegistros() {
        SITIOS.clear()
        val db = Firebase.firestore
        db.collection("Lugares")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val votoS = document.data.getValue("Votos").toString()
                    val votos = votoS.toInt()
                    val lugar = Lugar(
                        "",
                        document.data.getValue("NombreLugar").toString(),
                        document.data.getValue("Tipo").toString(),
                        "",
                        document.data.getValue("Latitud").toString(),
                        document.data.getValue("Longitud").toString(),
                        "",
                        votos,
                        false,
                        ""
                    )
                    SITIOS.add(lugar)

                }

                sitiosAdapter = SitiosListAdapter(SITIOS)
                if(sitiosRecycler!=null){
                    sitiosRecycler.setHasFixedSize(true)
                    sitiosRecycler.layoutManager = LinearLayoutManager(context)
                    sitiosRecycler.adapter = sitiosAdapter
                    Log.i("LugarCardView",SITIOS.toString())
                    sitiosSwipeRefresh.isRefreshing = false
                }
            }



    }
    /**
     * Esta función permite abrir el fragment de SitiosDetalleFragment
     */
    private fun nuevoRegistro() {
        abrirDetalle(null)
        fabNuevo.hide()
    }

/*    *//**
     * Función que se encarga de añadir nuevo registro al adaptador y notificar que el RecylcerView
     * ha sido modificado
     * @param item Lugar que se añade al recyclerView
     *//*
    fun insertarRegistroLista(item: Lugar) {
        this.sitiosAdapter.addRegistroALista(item)
        sitiosAdapter.notifyDataSetChanged()
    }*/

    /**
     * Esta función permite abrir el fragment de SitiosDetalleFragment en modo ACTUALIZAR,
     * lo que hace es abrir el fragment en modo ACTUALIZAR
     * @param position Int es la posicion del item en la que aparece en la lista
     */
    private fun modificar(position: Int) {
        abrirDetalle(SITIOS[position])
    }

    /**
     * Borra el elemento en la posición seleccionada
     * @param position Int
     */
    private fun borrar(position: Int) {
        abrirDetalle(SITIOS[position])
    }

    fun actualizarListaRegistros() {
        sitiosRecycler.adapter = sitiosAdapter
    }

/*    *//**
     * Abre el elemento en la posición didicada
     * @param lugar Lugar
     *//*
    private fun abrirRegistro(lugar: Lugar) {
        Log.i("Lugares", "Visualizando el elemento: " + lugar.id)
        abrirDetalle(lugar)
    }*/

    /**
     * Función que se encarga de abrir el fragment de SitioDetalleFragment según el modo de acceso
     * que utilicemos
     * @param lugar Lugar?
     * @param modo Modo?
     * @param anterior LugaresFragment?
     * @param position Int?
     */
    private fun abrirDetalle(lugar: Lugar?) {
        val lugarDetalle = SitioDetalleFragment(lugar)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.misSitios, lugarDetalle)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    /**
     * Función que se encarga de mostrar todos los registros en el recycler view
     */
    private fun mostrarListaRegistros() {

    }

    /**
     * Si paramos cancelamos la tarea
     */
    override fun onStop() {
        super.onStop()
    }
}

