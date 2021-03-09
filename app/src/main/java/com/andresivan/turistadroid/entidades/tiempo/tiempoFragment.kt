package com.andresivan.turistadroid.entidades.tiempo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.andresivan.turistadroid.R
import com.andresivan.turistadroid.app.MyApp
import com.andresivan.turistadroid.entidades.Weather.WeatherResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_tiempo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [tiempoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class tiempoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tiempo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        llamarAPITiempo()
        btnRecargarPagina.setOnClickListener { recargarPagina() }
    }


    private fun recargarPagina() {
        llamarAPITiempo()

    }

    private fun llamarAPITiempo(){
        val clientREST = ElTiempoAPI.service
        val call = clientREST.getCurrentWeatherData(MyApp.posicion.latitude.toString(), MyApp.posicion.longitude.toString(),
            ElTiempoAPI.API_KEY, ElTiempoAPI.UNITS, ElTiempoAPI.LANG)
        call.enqueue(object : Callback<WeatherResponse> {

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.code() == 200) {
                    Toast.makeText(requireContext(),"Conectado con exito",Toast.LENGTH_SHORT).show()
                    val weatherResponse = response.body()!!
                    txtCiudad.text = weatherResponse.name
                    txtPais.text = weatherResponse.sys?.country
                    var sdf = SimpleDateFormat("HH:mm   dd/MM/yyyy")
                    var date = Date((weatherResponse.dt.toLong() * 1000))
                    txtHora.text =  sdf.format(date)
                    txtTemperatura.text = weatherResponse.main?.temp.toString() + "º"
                    Picasso.get()
                        .load("http://openweathermap.org/img/wn/"+weatherResponse.weather[0].icon+"@2x.png")
                        .resize(200, 200)
                        .into(imgTiempo)
                    txtTempMax.text = "Temperatura (Max): " + weatherResponse.main?.temp_max + "º"
                    txtTempMin.text = "Temperatura (Min): " + weatherResponse.main?.temp_min + "º"
                    sdf = SimpleDateFormat("HH:mm")
                    date = Date((weatherResponse.sys!!.sunrise * 1000))
                    txtAmanecer.text = sdf.format(date)
                    date = Date((weatherResponse.sys!!.sunset * 1000))
                    txtPuestaSol.text = sdf.format(date)



                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Toast.makeText(requireContext(),"Error al conectar",Toast.LENGTH_SHORT).show()
            }



        })
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment tiempoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            tiempoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}