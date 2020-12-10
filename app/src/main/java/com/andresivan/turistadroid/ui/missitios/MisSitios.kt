package com.andresivan.turistadroid.ui.missitios

import android.graphics.*
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andresivan.turistadroid.R
import com.andresivan.turistadroid.entidades.lugares.Lugar
import com.andresivan.turistadroid.entidades.lugares.LugarController
import com.andresivan.turistadroid.ui.missitios.Filtross.FiltrosControlador
import com.andresivan.turistadroid.ui.missitios.filtros.Filtros
import kotlinx.android.synthetic.main.fragment_missitios.*
import java.text.SimpleDateFormat

class MisSitios : Fragment() {
    // Propiedades
    private var SITIOS = mutableListOf<Lugar>()
    private lateinit var sitiosAdapter: SitiosListAdapter
    private lateinit var tareaSitios: TareaCargarSitios
    private var paintSweep = Paint()
    private var FILTRO = Filtros.NADA

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_missitios, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniciarInterfazUsuario()
    }

    private fun iniciarInterfazUsuario() {
        iniciarSwipeRecarga()
        cargarLugares()
        initSpinner()
        iniciarSwipeHorizontal()
        sitiosRecycler.layoutManager = LinearLayoutManager(context)
        fabNuevo.setOnClickListener {
            Log.i("Lugares", "fabNuevo")
            nuevoElemento() }
    }

    private fun initSpinner() {
        val condicionBusq = resources.getStringArray(R.array.filtros)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, condicionBusq)

        misSitiosSpinnerFiltro.adapter = adapter
        misSitiosSpinnerFiltro.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                FILTRO = FiltrosControlador.indiceFiltrosSpinner(pos)
                visualizarListaItems()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun iniciarSwipeHorizontal() {
        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        borrarElemento(position)
                    }
                    else -> {
                        editarElemento(position)
                    }
                }
            }

            override fun onChildDraw(
                canvas: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val itemView = viewHolder.itemView
                    val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                    val width = height / 3
                    if (dX > 0) {
                        // Pintamos el botón izquierdo
                        btnIzq(canvas, dX, itemView, width)
                    } else {
                        // Caso contrario
                        btnDer(canvas, dX, itemView, width)
                    }
                }
                super.onChildDraw(
                    canvas,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(sitiosRecycler)
    }

    private fun editarElemento(pos: Int) {
        abrirDetalle(SITIOS[pos], ModosAccesos.ACTUALIZAR, this, pos)
    }

    private fun borrarElemento(pos: Int) {
        abrirDetalle(SITIOS[pos], ModosAccesos.ELIMINAR, this, pos)
    }

    private fun btnDer(canvas: Canvas, dX: Float, itemView: View, width: Float) {
        paintSweep.color = Color.RED
        val background = RectF(
            itemView.right.toFloat() + dX,
            itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat()
        )
        canvas.drawRect(background, paintSweep)
        val icon: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_borrar)
        val iconDest = RectF(
            itemView.right.toFloat() - 2 * width, itemView.top.toFloat() + width, itemView.right
                .toFloat() - width, itemView.bottom.toFloat() - width
        )
        canvas.drawBitmap(icon, null, iconDest, paintSweep)
    }

    /**
     * Mostramos el elemento izquierdo
     * @param canvas Canvas
     * @param dX Float
     * @param itemView View
     * @param width Float
     */
    private fun btnIzq(canvas: Canvas, dX: Float, itemView: View, width: Float) {
        paintSweep.setColor(Color.BLUE)
        val background = RectF(
            itemView.left.toFloat(), itemView.top.toFloat(), dX,
            itemView.bottom.toFloat()
        )
        canvas.drawRect(background, paintSweep)
        val icon: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_editar)
        val iconDest = RectF(
            itemView.left.toFloat() + width, itemView.top.toFloat() + width, itemView.left
                .toFloat() + 2 * width, itemView.bottom.toFloat() - width
        )
        canvas.drawBitmap(icon, null, iconDest, paintSweep)
    }

    private fun nuevoElemento() {
        Log.i("Lugares", "Nuevo elemento")
        abrirDetalle(null, ModosAccesos.INSERTAR, this, null)
    }

    private fun abrirDetalle(
        sitio: Lugar?,
        modosAccesos: ModosAccesos?,
        anterior: MisSitios?,
        position: Int?
    ) {
        Log.i("Lugares", "abrir Detalle")
        val sitioDetalle = SitioDetalleFragment(sitio, modosAccesos, anterior, position)
        Log.i("Lugares",sitioDetalle.toString())
        abrirSitioDetalle()
    }

    /**
     * Se abre el fragment de SitioDetalle
     */
    private fun abrirSitioDetalle() {

        val navHostFragment = getFragmentManager()?.findFragmentById(R.id.mobile_navigation) as NavHostFragment
        val navController = navHostFragment.navController

    }

    private fun iniciarSwipeRecarga() {
        sitiosSwipeRefresh.setColorSchemeResources(R.color.colorPrimaryDark)
        sitiosSwipeRefresh.setProgressBackgroundColorSchemeResource(R.color.colorAccent)
        sitiosSwipeRefresh.setOnRefreshListener {
            cargarLugares()
        }
    }

    private fun cargarLugares() {
        tareaSitios = TareaCargarSitios()
    }


    /**
     * Esta clase embebida en el fragment de mis sitios, permite se ejecuta a la par de la aplicación
     */
    inner class TareaCargarSitios : AsyncTask<Void?, Void?, Void?>() {
        // Pre-Tarea
        override fun onPreExecute() {
            if (sitiosSwipeRefresh.isRefreshing) {
                sitiosSwipeRefresh.isRefreshing = false
            }
        }

        // Tarea
        override fun doInBackground(vararg args: Void?): Void? {
            try {
                SITIOS = LugarController.selectAll()!!
            } catch (e: Exception) {
            }
            return null
        }

        //Post-Tarea
        override fun onPostExecute(args: Void?) {
            //ordenarSitios()
            sitiosAdapter = SitiosListAdapter(SITIOS) {
                eventoClicFila(it)
            }
            sitiosRecycler.adapter = sitiosAdapter
            // Avismos que ha cambiado
            sitiosAdapter.notifyDataSetChanged()
            sitiosRecycler.setHasFixedSize(true)
            sitiosSwipeRefresh.isRefreshing = false
            Toast.makeText(context, "Lugares cargados", Toast.LENGTH_LONG).show()
        }

    }

    /**
     * Función que muestra la lista de ubicaciones
     */
    private fun visualizarListaItems() {
        ordenarSitios()
        //sitiosRecycler.adapter = sitiosAdapter
    }

    private fun ordenarSitios() {
        /*
        Log.i("Filtro", "ORDENANDO POR: " + FILTRO.toString())
        when (FILTRO) {
            Filtros.NADA -> this.SITIOS.sortWith { l1: Lugar, l2: Lugar -> l1.id.compareTo(l2.id) }

            Filtros.NOMBRE_ASCENDENTE -> this.SITIOS.sortWith { l1: Lugar, l2: Lugar ->
                l1.nombre.toLowerCase().compareTo(l2.nombre.toLowerCase())
            }
            Filtros.NOMBRE_DESCENDENTE -> this.SITIOS.sortWith { l1: Lugar, l2: Lugar ->
                l2.nombre.toLowerCase().compareTo(l1.nombre.toLowerCase())
            }

            Filtros.FECHA_ASCENDENTE -> this.SITIOS.sortWith { l1: Lugar, l2: Lugar ->
                SimpleDateFormat("dd/MM/yyyy").parse(l1.fecha)
                    .compareTo(SimpleDateFormat("dd/MM/yyyy").parse(l2.fecha))
            }
            Filtros.FECHA_DESCENDENTE -> this.SITIOS.sortWith { l1: Lugar, l2: Lugar ->
                SimpleDateFormat("dd/MM/yyyy").parse(l2.fecha)
                    .compareTo(SimpleDateFormat("dd/MM/yyyy").parse(l1.fecha))
            }

            Filtros.VALORACION_ASCENDENTE -> this.SITIOS.sortWith { l1: Lugar, l2: Lugar ->
                l1.valoracion.compareTo(l2.valoracion)}
            Filtros.VALORACION_ASCENDENTE -> this.SITIOS.sortWith { l1: Lugar, l2: Lugar ->
                l2.votos.compareTo(l1.votos) }
            else -> {
            }
        }
         */
    }

    private fun eventoClicFila(lugar: Lugar) {
        abrirElemento(lugar)
    }

    private fun abrirElemento(lugar: Lugar) {
        Log.i("Mis Sitios", "Abrimos el lugar" + lugar.id)
        abrirDetalle(lugar, ModosAccesos.VISUALIZAR, this, null)
    }

    /**
     * Si cancelamos la tarea
     */
    override fun onStop() {
        super.onStop()
        tareaSitios.cancel(true)
    }

    fun insertarItemLista(lugar: Lugar) {
        this.sitiosAdapter.addItem(lugar)
        sitiosAdapter.notifyDataSetChanged()
    }

    fun eliminarItemLista(pos: Int) {
        this.sitiosAdapter.removeItem(pos)
        sitiosAdapter.notifyDataSetChanged()
    }

    fun actualizarVistaLista() {
        sitiosRecycler.adapter = sitiosAdapter
    }

    fun actualizarItemLista(lugar: Lugar, lugarIndex: Int) {
        this.sitiosAdapter.updateItem(lugar, lugarIndex)
        sitiosAdapter.notifyDataSetChanged()
    }

}


