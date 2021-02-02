package com.andresivan.turistadroid.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.andresivan.turistadroid.R
<<<<<<< Updated upstream
import com.andresivan.turistadroid.entidades.sesion.Sesion
=======
import com.andresivan.turistadroid.app.MyApp
>>>>>>> Stashed changes
import com.andresivan.turistadroid.entidades.sesion.SesionController
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.usuario.UsuarioControlador
import com.andresivan.turistadroid.utils.CifradorContrasena
<<<<<<< Updated upstream
=======
import com.andresivan.turistadroid.utils.Fotos
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_registrarse.*
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
class miperfil : Fragment() {
    lateinit var USUARIO: Usuario

    // TODO: Rename and change types of parameters
=======
class MiPerfil : Fragment() {
    var USUARIO: Usuario = Usuario()

    // Variables para la camara de fotos
    private val GALERIA = 1//Para poder saber que opción se ha elegido
    private val CAMARA = 2//Para poder saber que opción se ha elegido
    private var IMAGEN_NOMBRE: String = ""//donde se va a construir el nombre de la imagen
    private lateinit var IMAGEN_URI: Uri//Ruta de la imagen
    private val IMAGEN_DIR = "/TuristaDroid"//Donde se van a guardar
    private lateinit var FOTO: Bitmap//Para poder pasar la imagen a bitmap

    //
    private lateinit var Auth: FirebaseAuth
>>>>>>> Stashed changes
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
<<<<<<< Updated upstream
=======
        Auth = Firebase.auth
        leerSesionUsuarioActivo()

    }

    private fun leerSesionUsuarioActivo() {
        USUARIO.correo = Auth.currentUser?.email.toString()
        USUARIO.nombre = Auth.currentUser?.displayName.toString()

        //USUARIO = USUARIO_ACTIVO
>>>>>>> Stashed changes
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
        //Vamos a inicializar la UI de mi perfil
        initUI()
    }

    private fun initUI() {
        cargarMiPerfilUsuario()
    }



    private fun cargarMiPerfilUsuario() {
        var CorreoElectronico: TextView = miperfil_tv_correo
        var NombreUsuario: TextView = miperfil_tv_nombreUsuario

        for (sesion in SesionController.selectAll()!!){
            Log.i("Sesion",sesion.toString())

            USUARIO = UsuarioControlador.selectById(sesion.idUsuarioActivo)!!

            Log.i("Mi Perfil", USUARIO.toString())

            Log.i("Mi perfil", "NOMBRE USUARIO: "+USUARIO.nombre)
            Log.i("Mi perfil", "CORREO ELECTRONICO USUARIO: "+ USUARIO.correo)
            Log.i("Mi perfil", "NOMBRE_FOTO: "+ USUARIO.fotoUsuario)


            CorreoElectronico.text = USUARIO.correo
            NombreUsuario.text = USUARIO.nombre

            inicializarEventosBotones()

        }
    }

    private fun inicializarEventosBotones() {

        btnEditarPerfil.setOnClickListener{editarPerfil()}

        imgTwitterMiPerfil.setOnClickListener{abrirPaginaWeb(USUARIO.cuentaTwitter)}

        imgCamaraRegistrarse.setOnClickListener{abrirCamara()}
        imgGaleriaRegistrarse.setOnClickListener{abrirGaleria()}
    }

    private fun editarPerfil() {
        for (sesion in SesionController.selectAll()!!){
            if (miperfil_et_nombreusuario.text.toString() != ""){
                USUARIO.nombre = miperfil_et_nombreusuario.text.toString()
            }
            if (miperfil_et_contrasena.text.toString() != ""){
                USUARIO.contrasena = CifradorContrasena.convertirHash(miperfil_et_contrasena.text.toString(), "SHA-256")!!
                Log.i("Mod_perfil -->", "NUEVA COINTRASEÑA --> "+USUARIO.contrasena)
            }
            if (miperfil_et_twitter.text.toString() != ""){
                USUARIO.cuentaTwitter = miperfil_et_twitter.text.toString()
            }
            UsuarioControlador.updateUsuario(USUARIO, sesion.idUsuarioActivo)
            Log.i("MI PERFIL - MOD USU", "USUARIO MODIFICADO")
            //Toast.makeText(this, "USUARIO MODIFICADO", Toast.LENGTH_SHORT)
        }

    }

    private fun abrirGaleria() {
        TODO("Not yet implemented")
    }

    private fun abrirCamara() {
        TODO("Not yet implemented")
    }

    /**
     * Con este metodo podremos abrir el twitter del usuario
     */
    private fun abrirPaginaWeb(paginaWeb:String) {
        var uri: Uri = Uri.parse(paginaWeb)
        var i = Intent(Intent.ACTION_VIEW, uri)
        startActivity(i)
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