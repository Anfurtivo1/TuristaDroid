package com.andresivan.turistadroid

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toFile
import androidx.fragment.app.Fragment
import com.andresivan.turistadroid.app.MyApp
import com.andresivan.turistadroid.entidades.sesion.SesionController
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.usuario.UsuarioControlador
import com.andresivan.turistadroid.utils.CifradorContrasena
import com.andresivan.turistadroid.utils.Fotos
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

    // Variables para la camara de fotos
    private val GALERIA = 1
    private val CAMARA = 2
    private var IMAGEN_NOMBRE: String = ""
    private lateinit var IMAGEN_URI: Uri
    private val IMAGEN_DIR = "/TuristaDroid"
    private val IMAGEN_PROPORCION = 600
    private lateinit var FOTO: Bitmap
    private var IMAGEN_COMPRES = 80

    lateinit var USUARIO: Usuario

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

    /**
     * Siempre se ejecuta al realizar una acción
     * @param requestCode Int
     * @param resultCode Int
     * @param data Intent?
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i("FOTO", "Opción::--->$requestCode")
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("FOTO", "Se ha cancelado")
        }
        // Procesamos la foto de la galeria
        if (requestCode == GALERIA) {
            Log.i("FOTO", "Entramos en Galería")
            if (data != null) {
                // Obtenemos su URI con su dirección temporal
                val contentURI = data.data!!
                try {
                    // Obtenemos el bitmap de su almacenamiento externo
                    // Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    // Dependeindo de la versión del SDK debemos hacerlo de una manera u otra
                    if (Build.VERSION.SDK_INT < 28) {
                        this.FOTO = MediaStore.Images.Media.getBitmap(context?.contentResolver!!, contentURI)
                    } else {
                        val source: ImageDecoder.Source = ImageDecoder.createSource(context?.contentResolver!!, contentURI)
                        this.FOTO = ImageDecoder.decodeBitmap(source)
                    }
                    // Para jugar con las proporciones y ahorrar en memoria no cargando toda la foto, solo carga 600px max
                    val prop = this.IMAGEN_PROPORCION / this.FOTO.width.toFloat()
                    // Actualizamos el bitmap para ese tamaño, luego podríamos reducir su calidad
                    this.FOTO = Bitmap.createScaledBitmap(this.FOTO, this.IMAGEN_PROPORCION, (this.FOTO.height * prop).toInt(), false)
                    Toast.makeText(requireContext(), "¡Foto rescatada de la galería!", Toast.LENGTH_SHORT).show()
                    imgUsuario.setImageBitmap(this.FOTO)
                    // Vamos a copiar nuestra imagen en nuestro directorio
                    // Utilidades.copiarImagen(bitmap, IMAGEN_DIR, IMAGEN_COMPRES, applicationContext)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(requireContext(), "¡Fallo Galeria!", Toast.LENGTH_SHORT).show()
                }
            }
        } else if (requestCode == CAMARA) {
            Log.i("FOTO", "Entramos en Camara")
            // Cogemos la imagen, pero podemos coger la imagen o su modo en baja calidad (thumbnail)
            //try {
            // Esta línea para baja calidad
            //thumbnail = (Bitmap) data.getExtras().get("data");
            // Esto para alta
            //val source: ImageDecoder.Source = ImageDecoder.createSource(contentResolver, IMAGEN_URI)
            //val foto: Bitmap = ImageDecoder.decodeBitmap(source)

            if (Build.VERSION.SDK_INT < 28) {
                this.FOTO = MediaStore.Images.Media.getBitmap(context?.contentResolver!!, IMAGEN_URI)
            } else {
                val source: ImageDecoder.Source = ImageDecoder.createSource(context?.contentResolver!!, IMAGEN_URI)
                this.FOTO = ImageDecoder.decodeBitmap(source)
            }

            // Vamos a probar a comprimir
            Fotos.comprimirImagen(IMAGEN_URI.toFile(), this.FOTO, this.IMAGEN_COMPRES)

            // Si estamos en módo publico la añadimos en la biblioteca
            // if (PUBLICO) {
            // Por su queemos guardar el URI con la que se almacena en la Mediastore
            IMAGEN_URI = Fotos.añadirFotoGaleria(IMAGEN_URI, IMAGEN_NOMBRE, requireContext())!!
            // }

            // Mostramos
            imgMiPerfil.setImageBitmap(this.FOTO)
            Toast.makeText(requireContext(), "¡Foto Salvada!", Toast.LENGTH_SHORT).show()
            //} catch (e: Exception) {
            //e.printStackTrace()
            //Toast.makeText(this, "¡Fallo Camara!", Toast.LENGTH_SHORT).show()
            //}
        }
    }


    private fun abrirGaleria() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(galleryIntent, GALERIA)
    }

    private fun abrirCamara() {
        // Si queremos hacer uso de fotos en alta calidad
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        // Eso para alta o baja
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Nombre de la imagen
        IMAGEN_NOMBRE = Fotos.crearNombreFoto("camara",".jpg")
        // Salvamos el fichero
        val fichero = Fotos.salvarFoto(IMAGEN_DIR, IMAGEN_NOMBRE, requireContext())
        IMAGEN_URI = Uri.fromFile(fichero)

        intent.putExtra(MediaStore.EXTRA_OUTPUT, IMAGEN_URI)
        // Esto para alta y baja
        startActivityForResult(intent, CAMARA)
    }

    private fun abrirPaginaWeb(paginaWeb:String) {
        var uri: Uri = Uri.parse(paginaWeb)
        var i:Intent = Intent(Intent.ACTION_VIEW, uri)
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