package com.andresivan.turistadroid.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.andresivan.turistadroid.R
import com.andresivan.turistadroid.entidades.sesion.Sesion
import com.andresivan.turistadroid.entidades.sesion.SesionController
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.usuario.UsuarioControlador
import com.andresivan.turistadroid.utils.CifradorContrasena
import com.andresivan.turistadroid.utils.Fotos
import io.realm.exceptions.RealmError
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.activity_registrarse.*
import kotlinx.android.synthetic.main.fragment_miperfil.*
import kotlinx.android.synthetic.main.fragment_miperfil.imgCamaraRegistrarse
import kotlinx.android.synthetic.main.fragment_miperfil.imgGaleriaRegistrarse
import java.io.IOException

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
    lateinit var USUARIO: Usuario

    // Variables para la camara de fotos
    private val GALERIA = 1//Para poder saber que opción se ha elegido
    private val CAMARA = 2//Para poder saber que opción se ha elegido
    private var IMAGEN_NOMBRE: String = ""//donde se va a construir el nombre de la imagen
    private lateinit var IMAGEN_URI: Uri//Ruta de la imagen
    private val IMAGEN_DIR = "/TuristaDroid"//Donde se van a guardar
    private lateinit var FOTO: Bitmap//Para poder pasar la imagen a bitmap

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
        //Vamos a inicializar la UI de mi perfil
        initUI()
    }

    /**
     * Este metodo lo que hace es inicializar lo necesario del fragment
     */
    private fun initUI() {
        cargarMiPerfilUsuario()
    }


    /**
     * Aqui lo que vamos a hacer es cargar la informacion del usuario, para luego poder modificarla
     */
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

    /**
     * Aqui vamos a inicializar lo que van a hacer los botones de este fragment
     */
    private fun inicializarEventosBotones() {

        btnEditarPerfil.setOnClickListener{editarPerfil()}

        imgTwitterMiPerfil.setOnClickListener{abrirPaginaWeb(USUARIO.cuentaTwitter)}

        imgCamaraRegistrarse.setOnClickListener{abrirCamara()}
        imgGaleriaRegistrarse.setOnClickListener{abrirGaleria()}
    }

    /**
     * Este es el metodo en si que edita el usuario, pese a dar un error al ejecutarlo, actualiza de forma correcta el usuario
     * Si en el enlace del Twitter se pone un texto que no es una URL valida, dara error al abrir el twitter por esa misma razón
     */
    private fun editarPerfil() {
        for (sesion in SesionController.selectAll()!!){
                if (miperfil_et_nombreusuario.text.toString() != ""){
                    USUARIO.nombre = miperfil_et_nombreusuario.text.toString()//Nombre de usuario
                }
                if (miperfil_et_contrasena.text.toString() != ""){
                    USUARIO.contrasena = CifradorContrasena.convertirHash(miperfil_et_contrasena.text.toString(), "SHA-256")!!//Nueva contraseña que luego se convierte
                    Log.i("Mod_perfil -->", "NUEVA COINTRASEÑA --> "+USUARIO.contrasena)
                }
                if (miperfil_et_twitter.text.toString() != ""){
                    USUARIO.cuentaTwitter = miperfil_et_twitter.text.toString()//EL enlace de Twitter
                }
            if (miperfil_et_nombreusuario.text.toString() != "" && miperfil_et_contrasena.text.toString() != "" && miperfil_et_twitter.text.toString() != ""){
                UsuarioControlador.updateUsuario(USUARIO, sesion.idUsuarioActivo)//Si todo esta correcto se ejecuta la actualización del usuario
                Log.i("MI PERFIL - MOD USU", "USUARIO MODIFICADO")
            }

        }

    }
    /**
     * Este metodo elegirá una foto de la galeria
     */
    private fun abrirGaleria() {

            val galleryIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(galleryIntent, GALERIA)//Ejecutamos el OnActivityResult
    }

    /**
     * Este es el metodo que nos permite tomar una foto de la camara
     */
    private fun abrirCamara() {

            // Creamos el intent para poder abrir la camara
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // Aqui se va a crear el nombre de la foto, donde primero viene el nombre "camara" y luego se le añade la extension de jpg
            IMAGEN_NOMBRE = Fotos.crearNombreFoto("camara",".jpg")
            //Aqui lo que vamos a hacer es pasarle el directorio de nuestra app (/TuristaDroid)y el nombre de la imagen que vamos a guardar
            val fichero = Fotos.salvarFoto(IMAGEN_DIR, IMAGEN_NOMBRE, requireContext())
            //Conseguimos el URI de la imagen que acabamos de guardar
            IMAGEN_URI = FileProvider.getUriForFile(requireContext(), requireContext().applicationContext.packageName.toString() + ".provider", fichero!!)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            //En este intent lo que hacemos es que se guarda la imagen con la URI que hemos conseguido antes
            intent.putExtra(MediaStore.EXTRA_OUTPUT, IMAGEN_URI)
            //Le pasamos el intent que hemos creado antes y le pasamos la variable camara al activityOnResult, porque si no no sabemos que opcion hemos elegido
            startActivityForResult(intent, CAMARA)//Ejecutamos el OnActivityResult
    }

    /**
     * Este metodo siempre se ejecutará al realizar una acción
     * @param requestCode Int
     * @param resultCode Int
     * @param data Intent?
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //En caso de que al echar la foto le demos a cancelar, saldrá este log
        if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("FOTO", "Se ha cancelado")
        }
        // Si hemos elegido la galería realizará lo siguiente
        if (requestCode == GALERIA) {
            Log.i("FOTO", "Entramos en Galería")
            //Este if es muy importante ya que si no estuviera y al pulsar el botón de camara, dijeramos que no queremos, petaría la app
            if (data != null) {
                // Obtenemos su URI con su dirección temporal
                Log.i("FOTO",data.toString())
                val contentURI = data.data!!
                try {
                    // Dependiendo de la versión del SDK debemos hacerlo de una manera u otra, el imageDecoder es lo que usaremos para acceder a las fotos con el URI
                    if (Build.VERSION.SDK_INT < 28) {
                        this.FOTO = MediaStore.Images.Media.getBitmap(context?.contentResolver, contentURI)
                    } else {
                        val source: ImageDecoder.Source = ImageDecoder.createSource(context?.contentResolver!!, contentURI)
                        this.FOTO = ImageDecoder.decodeBitmap(source)
                    }
                    Toast.makeText(context, "Se ha conseguido cargar la imagen de la galeria", Toast.LENGTH_SHORT).show()
                    imgMiPerfil.setImageBitmap(this.FOTO)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(context, "Ha ocurrido un error al recuperar la imagen de la galeria", Toast.LENGTH_SHORT).show()
                }
            }
        } else if (requestCode == CAMARA) {
            Log.i("FOTO", "Entramos en Camara")

            try{
                //Aqui se comprueba la version del SDK ya que dependiendo de la version, el acceso a la camara se hace de una forma o de otra
                if (Build.VERSION.SDK_INT < 28) {
                    this.FOTO = MediaStore.Images.Media.getBitmap(context?.contentResolver, IMAGEN_URI)
                } else {
                    val source: ImageDecoder.Source = ImageDecoder.createSource(context?.contentResolver!!, IMAGEN_URI)
                    this.FOTO = ImageDecoder.decodeBitmap(source)
                }

                // Aqui mostramos la imagen que se ha elegido añadiendole el bitmap de la imagen tomada
                imgMiPerfil.setImageBitmap(this.FOTO)
                //Mostramos un toast para ver que se ha conseguido hacer lo anterior
                Toast.makeText(context, "Se ha podido cargar la foto", Toast.LENGTH_SHORT).show()

            }catch (e: IOException){//Este try catch es para que si le damos a volver al ir a tomar la foto, nos envia aqui
                e.printStackTrace()
            }

        }
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