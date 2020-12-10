package com.andresivan.turistadroid.ui.missitios

import android.graphics.Paint
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.andresivan.turistadroid.R
import com.andresivan.turistadroid.entidades.lugares.Lugar
import com.andresivan.turistadroid.entidades.lugares.LugarController
import com.andresivan.turistadroid.ui.missitios.Filtross.FiltrosControlador
import com.andresivan.turistadroid.ui.missitios.filtros.Filtros
import kotlinx.android.synthetic.main.fragment_missitios.*

class missitios : Fragment() {
    // Propiedades
    private var SITIOS = mutableListOf<Lugar>()
    private lateinit var sitiosAdapter: SitiosListAdapter
    private lateinit var tareaSitios: TareaCargarSitios
    private var paintSweep = Paint()

    // Búsquedas
    private var FILTRO = Filtros.NADA

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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
        iniciarSpinner()
        iniciarSwipeHorizontal()
        lugaresRecycler.layoutManager = LinearLayoutManager(context)
        lugaresFabNuevo.setOnClickListener { nuevoElemento() }
    }

    private fun nuevoElemento(){
        abrirDetalle(null, ModosAccesos.INSERTAR, this, null)
    }

    private fun abrirDetalle(sitio: Lugar?, modosAccesos: ModosAccesos?, anterior: missitios?, position: Int?) {
        val sitioDetalle = SitioDetalleFragment(sitio, modosAccesos, anterior, position)
    }

    private fun iniciarSpinner() {
        val tipoBusqueda = resources.getStringArray(R.array.filtros)
        val adapter = ArrayAdapter(
            context!!,
            android.R.layout.simple_spinner_item, tipoBusqueda)
        misSitiosSpinnerFiltro.adapter = adapter
        misSitiosSpinnerFiltro.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                FILTRO = FiltrosControlador.indiceFiltrosSpinner(position)

                Log.i("Filtro", position.toString())
                Toast.makeText(context!!, "Ordenando por: " + tipoBusqueda[position], Toast.LENGTH_SHORT).show()
                visualizarListaItems()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
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
            ordenarSitios()
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
     * Visualiza la lista de items
     */
    private fun visualizarListaItems() {
        ordenarSitios()
        sitiosRecycler.adapter = sitiosAdapter
    }

    private fun ordenarSitios() {
        Log.i("Filtro", "ORDENANDO POR: "+FILTRO.toString())
        when (FILTRO) {
        }
    }

    /**
     * Si cancelamos la tarea
     */
    override fun onStop() {
        super.onStop()
        tareaSitios.cancel(true)
    }

}