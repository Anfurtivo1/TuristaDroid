package com.andresivan.turistadroid.ui

import android.app.Activity
import android.app.AlertDialog
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
import com.andresivan.turistadroid.app.MyApp
import com.andresivan.turistadroid.entidades.sesion.SesionController
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.ui.missitios.SitioDetalleFragment
import com.andresivan.turistadroid.utils.CifradorContrasena
import com.andresivan.turistadroid.utils.Fotos
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_registrarse.*
import kotlinx.android.synthetic.main.fragment_miperfil.*
import org.w3c.dom.Text
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MiPerfil.newInstance] factory method to
 * create an instance of this fragment.
 */
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

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Auth = Firebase.auth
        leerSesionUsuarioActivo()

    }

    private fun leerSesionUsuarioActivo() {
        USUARIO.correo = Auth.currentUser?.email.toString()
        USUARIO.nombre = Auth.currentUser?.displayName.toString()
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
        if (MyApp.loginGoogle){
            abrirPrincipal()
            Toast.makeText(context,"Has iniciado sesion con google, por lo que no puedes cambiar tus datos",Toast.LENGTH_LONG).show()
        }else{
            initUI()
        }

    }

    /**
     * Este metodo lo que hace es inicializar lo necesario del fragment
     */
    private fun initUI() {
        cargarMiPerfilUsuario()
        inicializarEventosBotones()
    }


    /**
     * Aqui lo que vamos a hacer es cargar la informacion del usuario, para luego poder modificarla
     */
    private fun cargarMiPerfilUsuario() {
        var CorreoElectronico: TextView = miperfil_tv_correo
        var NombreUsuario: TextView = miperfil_tv_nombreUsuario

        CorreoElectronico.text = USUARIO.correo
        NombreUsuario.text = USUARIO.nombre
    }

    /**
     * Aqui vamos a inicializar lo que van a hacer los botones de este fragment
     */
    private fun inicializarEventosBotones() {

        btnEditarPerfil.setOnClickListener{editarPerfil()}

        fabMP_Twitter.setOnClickListener{abrirPaginaWeb(USUARIO.cuentaTwitter)}

        fabMP_Camara.setOnClickListener{abrirCamara()}
        fabMP_Galeria.setOnClickListener{abrirGaleria()}
    }

    /**
     * Este es el metodo en si que edita el usuario, pese a dar un error al ejecutarlo, actualiza de forma correcta el usuario
     * Si en el enlace del Twitter se pone un texto que no es una URL valida, dara error al abrir el twitter por esa misma razón
     */
   private fun editarPerfil() {
        var CorreoElectronico: TextView = miperfil_et_nombreusuario
        var contrasena: TextView = miperfil_et_contrasena
        var LinkTwitter: TextView = miperfil_et_twitter

        if(CorreoElectronico.text.isNotEmpty() && LinkTwitter.text.isNotEmpty() && contrasena.text.isNotEmpty()){
            actualizarCorreo(CorreoElectronico.text.toString())
            actualizarContrasena(contrasena.text.toString())
            //actualizarCorreoBD(CorreoElectronico.text.toString())
        }else{
            Toast.makeText(context,"Tienes que rellenar todos los campos",Toast.LENGTH_SHORT).show()
        }
    }

    private fun actualizarCorreo(correoElectronico:String){
        val user = Firebase.auth.currentUser

        user!!.updateEmail(correoElectronico)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context,"Correo modificado correctamente",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"No se pudo actualizar el correo",Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun actualizarContrasena(contrasena:String){

        val user = Firebase.auth.currentUser
        var pass = CifradorContrasena.convertirHash(contrasena)

        user!!.updatePassword(pass.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context,"Contraseña modificada correctamente",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"No se pudo actualizar la contraseña",Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun actualizarCorreoBD(correoElectronico:String){
        val db = Firebase.firestore
        val washingtonRef = db.collection("cities").document("DC")

        // Set the "isCapital" field of the city 'DC'
        washingtonRef
            .update("capital", true)
            .addOnSuccessListener { Log.d("", "DocumentSnapshot successfully updated!") }
            .addOnFailureListener {  }
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

    private fun abrirPrincipal(){
        val cercaDeMi = cercademi()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_host_fragment, cercaDeMi)
        transaction.addToBackStack(null)
        transaction.commit()
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
         * @return A new instance of fragment MiPerfil.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MiPerfil().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }


    }

}