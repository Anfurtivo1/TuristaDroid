package com.andresivan.turistadroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.andresivan.turistadroid.app.MyApp
import com.andresivan.turistadroid.entidades.sesion.SesionController
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.usuario.UsuarioControlador
import com.andresivan.turistadroid.utils.CifradorContrasena
import kotlinx.android.synthetic.main.fragment_miperfil.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [miperfil.newInstance] factory method to
 * create an instance of this fragment.
 */
class miperfil : Fragment() {

    var CORREO = ""
    var NOMBRE_USUARIO = ""
    var NOMBRE_FOTO = ""

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
        return inflater.inflate(R.layout.fragment_miperfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        InitUI()
    }

    private fun InitUI() {
        cargarMiPerfilUsuario()
        inicializarEventosBotones()
    }

    private fun inicializarEventosBotones() {
    }

    private fun cargarMiPerfilUsuario() {
        var CorreoElectronico: TextView = miperfil_tv_correo
        var NombreUsuario: TextView = miperfil_tv_nombreUsuario

        for (sesion in SesionController.selectAll()!!){
            Log.i("Sesion",sesion.toString())
            var usuario: Usuario

            usuario = UsuarioControlador.selectById(sesion.idUsuarioActivo)!!

            Log.i("Mi Perfil", usuario.toString())

            CORREO = usuario.correo
            NOMBRE_USUARIO = usuario.nombre
            NOMBRE_FOTO = usuario.fotoUsuario

            Log.i("Mi perfil", "NOMBRE USUARIO: "+NOMBRE_USUARIO)
            Log.i("Mi perfil", "CORREO ELECTRONICO USUARIO: "+ CORREO)
            Log.i("Mi perfil", "NOMBRE_FOTO: "+ NOMBRE_FOTO)


            CorreoElectronico.setText(CORREO)
            NombreUsuario.setText(NOMBRE_USUARIO)

        }
    }


    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment miperfil.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            miperfil().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }


    }

}