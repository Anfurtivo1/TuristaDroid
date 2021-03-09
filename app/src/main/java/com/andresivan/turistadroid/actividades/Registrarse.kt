package com.andresivan.turistadroid.actividades

<<<<<<< HEAD
=======
import android.Manifest
>>>>>>> main
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
<<<<<<< HEAD
import android.graphics.drawable.BitmapDrawable
=======
>>>>>>> main
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
=======
import android.os.StrictMode
>>>>>>> main
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
<<<<<<< HEAD
import com.andresivan.turistadroid.R
import com.andresivan.turistadroid.entidades.fotos.Foto
import com.andresivan.turistadroid.utils.ABase64
import com.andresivan.turistadroid.utils.CifradorContrasena
//import com.andresivan.turistadroid.entidades.preferencias.PreferenciasController.crearSesion
import com.andresivan.turistadroid.utils.Fotos
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_registrarse.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap

class Registrarse : AppCompatActivity() {
=======
import androidx.core.net.toFile
import com.andresivan.turistadroid.R
import com.andresivan.turistadroid.entidades.preferencias.PreferenciasController.crearSesion
import com.andresivan.turistadroid.utils.Fotos
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_registrarse.*
import java.io.IOException
import java.lang.Exception
import java.lang.NullPointerException

class registrarse : AppCompatActivity() {
>>>>>>> main


    // Variables para la camara de fotos
    private val GALERIA = 1//Para poder saber que opción se ha elegido
    private val CAMARA = 2//Para poder saber que opción se ha elegido
    private var IMAGEN_NOMBRE: String = ""//donde se va a construir el nombre de la imagen
    private lateinit var IMAGEN_URI: Uri//Ruta de la imagen
    private val IMAGEN_DIR = "/TuristaDroid"//Donde se van a guardar
    private lateinit var FOTO: Bitmap//Para poder pasar la imagen a bitmap
<<<<<<< HEAD
    private lateinit var auth: FirebaseAuth
    private var storage = Firebase.storage
    private lateinit var store : FirebaseFirestore
=======
>>>>>>> main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)
<<<<<<< HEAD
        fabRegCamara.setOnClickListener { tomarFotoCamara() }
        fabRegGaleria.setOnClickListener { elegirFotoGaleria() }
        btnRegistrarse.setOnClickListener{ registrarUsuario() }

        auth = Firebase.auth

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        //updateUI(currentUser)
=======
        imgCamaraRegistrarse.setOnClickListener { tomarFotoCamara() }
        imgGaleriaRegistrarse.setOnClickListener { elegirFotoGaleria() }
        btnRegistrarse.setOnClickListener{ registrarUsuario() }

>>>>>>> main
    }

    /**
     * Este es el metodo que nos permite tomar una foto de la camara
     */
    private fun tomarFotoCamara() {

        // Creamos el intent para poder abrir la camara
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Aqui se va a crear el nombre de la foto, donde primero viene el nombre "camara" y luego se le añade la extension de jpg
        IMAGEN_NOMBRE = Fotos.crearNombreFoto("camara",".jpg")
        //Aqui lo que vamos a hacer es pasarle el directorio de nuestra app (/TuristaDroid)y el nombre de la imagen que vamos a guardar
        val fichero = Fotos.salvarFoto(IMAGEN_DIR, IMAGEN_NOMBRE, this)
        //Conseguimos el URI de la imagen que acabamos de guardar
        IMAGEN_URI = FileProvider.getUriForFile(this, this.applicationContext.packageName.toString() + ".provider", fichero!!)

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
                        this.FOTO = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    } else {
                        val source: ImageDecoder.Source = ImageDecoder.createSource(this.contentResolver, contentURI)
                        this.FOTO = ImageDecoder.decodeBitmap(source)
                    }
                    Toast.makeText(this, "Se ha conseguido cargar la imagen de la galeria", Toast.LENGTH_SHORT).show()
                    imgUsuario.setImageBitmap(this.FOTO)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Ocurrió un fallo al cargar la imagen de la galeria", Toast.LENGTH_SHORT).show()
                }
            }
        } else if (requestCode == CAMARA) {
            Log.i("FOTO", "Entramos en Camara")

            try{
                //Aqui se comprueba la version del SDK ya que dependiendo de la version, el acceso a la camara se hace de una forma o de otra
                if (Build.VERSION.SDK_INT < 28) {
                    this.FOTO = MediaStore.Images.Media.getBitmap(this?.contentResolver, IMAGEN_URI)
                } else {
                    val source: ImageDecoder.Source = ImageDecoder.createSource(this?.contentResolver!!, IMAGEN_URI)
                    this.FOTO = ImageDecoder.decodeBitmap(source)
                }

                // Aqui mostramos la imagen que se ha elegido añadiendole el bitmap de la imagen tomada
                imgUsuario.setImageBitmap(this.FOTO)
                //Mostramos un toast para ver que se ha conseguido hacer lo anterior
                Toast.makeText(this, "¡Foto Salvada!", Toast.LENGTH_SHORT).show()

            }catch (e:IOException){//Este try catch es para que si le damos a volver al ir a tomar la foto, nos envia aqui
                e.printStackTrace()
            }

        }
    }


    /**
     * Este metodo elegirá una foto de la galeria
     */
    private fun elegirFotoGaleria() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(galleryIntent, GALERIA)
    }

    /**
<<<<<<< HEAD
     * Este metodo nos permite guardar un usuario en la API REST
     */
    private fun registrarUsuario() {
        //Recogemos todos los datos del usuario
        var correo = registrarse_et_correo.text.toString()
        var contrasena = registrarse_et_contrasena.text.toString()
        var pass = CifradorContrasena.convertirHash(contrasena)
        var nombreUsuario = registrarse_et_nombreusu.text.toString()
        var imagen:ImageView = findViewById(R.id.imgUsuario)
        //Si esta vacio algún campo, no dejamos que se registre
        if (correo == "" || contrasena == "" || nombreUsuario == ""){
            Toast.makeText(this, "Rellene todos los campos para Registrarse", Toast.LENGTH_SHORT)
        }else{
            val idImagen=UUID.randomUUID().toString()
            val idUsuario=UUID.randomUUID().toString()
            imagen.isDrawingCacheEnabled = true
            val bitmap = (imagen.drawable as BitmapDrawable).bitmap
            val base64=ABase64.toBase64(bitmap)
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()
            registrarUsuarioFireBase(correo,pass.toString())
            val user = hashMapOf(
                "Correo" to correo,
                "Nombre" to nombreUsuario,
                "FotoUsuario" to base64.toString()
            )
            guardarUsuarioBD(user,idUsuario,idImagen,data)
            //mostrarDatosUsuarios()

=======
     * Este metodo nos permite guardar un usuario en la base de datos realizada por Ivan
     */
    private fun registrarUsuario() {
        //Recogemos todos los datos del usuario
        var img:ImageView
        img = findViewById(R.id.imgUsuario)
        var correo = registrarse_et_correo.text.toString()
        var contrasena = registrarse_et_contrasena.text.toString()
        var nombreUsuario = registrarse_et_nombreusu.text.toString()
        IMAGEN_NOMBRE = img.toString()
        //Si esta vacio algún campo, no dejamos que se registre
        if (correo == "" || contrasena == "" || nombreUsuario == ""){
            Toast.makeText(this, "Rellene todos los campos para registrarse", Toast.LENGTH_SHORT)
        }else{
            crearSesion(this,  correo, contrasena, nombreUsuario, IMAGEN_NOMBRE)
>>>>>>> main
        }

    }

<<<<<<< HEAD
    private fun guardarImagen(
        data: ByteArray,
        idImagen: String,
        idUsuario: String
    ) {
        var storageRef = storage.reference

        storageRef = storageRef.child("FotosUsuario/$idImagen foto.jpg")
        var uploadTask = storageRef.putBytes(data)
        uploadTask.addOnFailureListener {
            Log.i("Imagen","Error al subir la imagen")
        }.addOnSuccessListener { taskSnapshot ->
            val uriImagen = taskSnapshot.metadata!!.reference!!.downloadUrl
            uriImagen.addOnSuccessListener {
                val imagen = Foto(idImagen,it.toString(),idUsuario)
                store = FirebaseFirestore.getInstance()
                store.collection("Imagenes").document(idImagen).set(imagen).addOnSuccessListener {
                    Log.i("guardarImagen","Se ha guardado la imagen")
                    //Snackbar.make(this, "Imagen guardada", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }


    private fun guardarUsuarioBD(user: HashMap<String, String>,idUsuario:String,idImagen:String,data: ByteArray) {
        val db = Firebase.firestore
        db.collection("Usuarios")
            .document(idUsuario)
            .set(user)
            .addOnSuccessListener { documentReference ->
                guardarImagen(data,idImagen,idUsuario)
                Log.d("Registro", "DocumentSnapshot added with ID: $idUsuario")
            }
            .addOnFailureListener { e ->
                Log.w("Registro", "Error adding document", e)

            }
    }

    private fun mostrarDatosUsuarios(){
        val db = Firebase.firestore
        db.collection("Usuarios")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Registro", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("Registro", "Error getting documents.", exception)
            }
    }

    private fun registrarUsuarioFireBase(correo:String, pass:String){
        auth.createUserWithEmailAndPassword(correo, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Usuario", "createUserWithEmail:success")
                    Toast.makeText(baseContext, "Registro conseguido.", Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Usuario", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "No se pudo registrar", Toast.LENGTH_SHORT).show()
                }
            }
    }

=======
>>>>>>> main
}