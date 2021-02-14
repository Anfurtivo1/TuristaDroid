package com.andresivan.turistadroid.ui.missitios

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.andresivan.turistadroid.R
import com.andresivan.turistadroid.app.MyApp
import com.andresivan.turistadroid.entidades.fotos.Foto
import com.andresivan.turistadroid.entidades.lugares.Lugar
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.utils.ABase64
import com.andresivan.turistadroid.utils.Fotos
import com.andresivan.turistadroid.utils.GeneradorQR
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_registrarse.*
import kotlinx.android.synthetic.main.fragment_sitio_detalle.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.Serializable
import java.util.*
import kotlin.collections.HashMap

//Foto,Nombre,Tipo,Fecha y puntuacion
class SitioDetalleFragment(
    private var SITIO: Lugar? = null,
    private val ANTERIOR: MisSitios? = null
) : Fragment(){
    private lateinit var fusedLocationClient: FusedLocationProviderClient//Lo que nos dará la ultima ubicacion
    private var USER: Usuario = Usuario()
    private var PERMISOS: Boolean = false
    private val GAL = 1
    private val CAM = 2
    private lateinit var IMG_URI: Uri
    private val IMG_DIR = "/MisSitios"
    private val IMG_SIZE = 600
    private lateinit var PHOTO: Bitmap
    private var IMG_COMPR_LVL = 60
    private val IMG_PREF = "Sitio"
    private val IMG_EXT = ".jpg"
    private lateinit var tipoSeleccionado:String
    private lateinit var FOTO: Bitmap//Para poder pasar la imagen a bitmap
    private lateinit var IMAGEN_URI: Uri//Ruta de la imagen
    //
    private lateinit var Auth: FirebaseAuth
    private var storage = Firebase.storage
    private lateinit var store : FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_sitio_detalle, container, false)
        //Esto lo usaremos para acceder a nuestra ubicacion actual
        fusedLocationClient= activity?.let { LocationServices.getFusedLocationProviderClient(it) }!!
        Auth = Firebase.auth
        inicializarInterfaz(vista)

        return vista
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnIngresar.setOnClickListener { ingresarLugar() }
        detalleCamaraSitio.setOnClickListener { opcionAElegirFoto() }

        accionesSpinner()

        super.onViewCreated(view, savedInstanceState)
        view.setOnTouchListener { view, motionEvent ->
            return@setOnTouchListener true
        }
    }

    private fun accionesSpinner(){
        var items = resources.getStringArray(R.array.tiposLugares)
        tiposLugares_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                tipoSeleccionado=items[position]
            }
        }
    }

    private fun inicializarInterfaz(vista: View) {
        ANTERIOR?.actualizarListaRegistros()
        initPermissions()
        initUser()
        inicializarSpinner(vista)
    }

    private fun ingresarLugar(){
        val db = Firebase.firestore
        tiposLugares_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }

        }
        var imagen:ImageView = requireActivity().findViewById(R.id.detalleFotoSitio)
        val idImagen=UUID.randomUUID().toString()
        var idLugar = UUID.randomUUID()
        imagen.isDrawingCacheEnabled = true
        val bitmap = (imagen.drawable as BitmapDrawable).bitmap
        val base64= ABase64.toBase64(bitmap)
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        val lugar = hashMapOf(
            "idImagen" to idImagen,
            "NombreLugar" to txtNombreLugar.text.toString(),
            "Tipo" to tipoSeleccionado,
            "Latitud" to MyApp.posicion.latitude.toString(),
            "Longitud" to MyApp.posicion.longitude.toString(),
            "Votos" to 0,
            "usuariosVotados" to arrayListOf(""),
            "creadoPor" to MyApp.correoUsuario
        )
        guardarLugarDB(lugar,idLugar,idImagen,data)
            }

    private fun guardarLugarDB(
        lugar: HashMap<String, Serializable>,
        idLugar: UUID,
        idImagen:String,
        data: ByteArray) {
        val db = Firebase.firestore
        db.collection("Lugares").document(idLugar.toString())
            .set(lugar)
            .addOnSuccessListener { documentReference ->
                guardarImagen(data,idImagen,idLugar.toString())
            }
            .addOnFailureListener { e ->
                Log.w("Registro", "Error adding document", e)
        }
    }

    private fun guardarImagen(
        data: ByteArray,
        idImagen: String,
        idLugar: String
    ) {
        var storageRef = storage.reference

        storageRef = storageRef.child("FotosLugares/$idImagen foto.jpg")
        var uploadTask = storageRef.putBytes(data)
        uploadTask.addOnFailureListener {
            Log.i("Imagen","Error al subir la imagen")
        }.addOnSuccessListener { taskSnapshot ->
            val uriImagen = taskSnapshot.metadata!!.reference!!.downloadUrl
            uriImagen.addOnSuccessListener {
                val imagen = Foto(idImagen,it.toString(),idLugar)
                store = FirebaseFirestore.getInstance()
                store.collection("Imagenes").document(idImagen).set(imagen).addOnSuccessListener {
                    Log.i("guardarImagen","Se ha guardado la imagen")
                    //Snackbar.make(this, "Imagen guardada", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun inicializarSpinner(vista: View) {
        val spinner: Spinner = vista.findViewById(R.id.tiposLugares_spinner)
        ArrayAdapter.createFromResource(
            vista.context,
            R.array.tiposLugares,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }

    /**
     * Función que lee el usuario que ha iniciado sesión en la app, que quedo almancenado en una
     * especie de variable estática creada con companion object
     */
    private fun initUser() {

        USER.correo = Auth.currentUser?.email.toString()
        USER.nombre = Auth.currentUser?.displayName.toString()
    }

    /**
     * Función que lee los permisos de MyApp
     */
    private fun initPermissions() {
        this.PERMISOS = (activity?.application as MyApp).PERMISOS
    }

/*    private fun chooseDate() {
        val date = LocalDateTime.now()
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, mYear, mMonth, mDay ->
                //detalleBtnFecha.text = (mDay.toString() + "/" + (mMonth + 1) + "/" + mYear)
            }, date.year, date.monthValue - 1, date.dayOfMonth
        )
        datePickerDialog.show()
    }*/

    /**
     * Funcion que permite comprobar el contenido del formulario del fragment
     * @return Boolean
     */
    private fun esRegistroValido(): Boolean {
/*        var comprobacion = true
        if (detalleSitioInput.text?.isEmpty()!!) {
            detalleSitioInput.error = "SIN NOMBRE"
            comprobacion = false
        }
        if (!this::PHOTO.isInitialized) {
            this.PHOTO = (detalleFotoSitio.drawable as BitmapDrawable).bitmap
            Toast.makeText(context, "SIN FOTO", Toast.LENGTH_SHORT).show()
            comprobacion = false
        }
        return comprobacion*/
        return true
    }

    /**
     * Función que nos permite compartir un lugar mediante un codigo QR con la info de un sitio
     * a su vez usamos la librería de Gson para convertir el objeto a un fichero JSON
     */
    private fun sharePlace() {
        val buildr = AlertDialog.Builder(context)
        val infltr = requireActivity().layoutInflater
        val view = infltr.inflate(R.layout.fragment_compartir_qr, null)
        val qr = GeneradorQR.generarCodigoQR(Gson().toJson(SITIO))
        val codeQR_IV = view.findViewById(R.id.imagenCodigoQR) as ImageView
        codeQR_IV.setImageBitmap(qr)
        buildr
            .setView(view)
            .setIcon(R.drawable.ic_qr)
            .setTitle("¿COMPARTIR QR?")
            .setPositiveButton(R.string.aceptar) { _, _ ->
                compartir(qr)
            }
            .setNegativeButton(R.string.cancelar, null)
        buildr.show()

    }

    /**
     * Función que nos permite compartir los QR que generemos
     * @param qr Bitmap
     */
    private fun compartir(qr: Bitmap) {
        val buildr = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(buildr.build())
        val fichero =
            Fotos.copiarFoto(qr, IMG_DIR, 100, requireContext())
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "img/*"
            putExtra(Intent.EXTRA_STREAM, Uri.fromFile(fichero))
        }
        context?.startActivity(Intent.createChooser(shareIntent, null))
    }

    /**
     * Función que se encarga de construir un cuadro de dialogos para seleccionar el origen de la
     * imagen de lugar, según la opcion que elijamos, abriremos la galeria, para coger una foto, o
     * abriremos la camara para tomar una foto
     */
    private fun opcionAElegirFoto() {
        val opcionesAElegir = arrayOf(
            "ELEGIR FOTO",
            "TOMAR FOTO"
        )
        AlertDialog.Builder(context).setTitle("ACCION")
            .setItems(opcionesAElegir) { dialog, opcion ->
                when (opcion) {
                    0 -> galeria()
                    1 -> camara()
                }
            }.show()
    }

    /**
     * Función que nos permite elegir una foto desde la galería de nuestro móvul
     */
    private fun galeria() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(galleryIntent, GAL)
    }


    /**
     * Función que nos permite sacar una foto desde la camara
     */
    private fun camara() {
        val buildr = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(buildr.build())
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val nombreFoto = Fotos.crearNombreFoto(IMG_PREF, IMG_EXT)
        val file = Fotos.salvarFoto(IMG_DIR, nombreFoto, requireContext())
        IMG_URI = Uri.fromFile(file)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, IMG_URI)
        startActivityForResult(intent, CAM)
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
        if (requestCode == GAL) {
            Log.i("FOTO", "Entramos en Galería")
            //Este if es muy importante ya que si no estuviera y al pulsar el botón de camara, dijeramos que no queremos, petaría la app
            if (data != null) {
                // Obtenemos su URI con su dirección temporal
                Log.i("FOTO",data.toString())
                val contentURI = data.data!!
                try {
                    // Dependiendo de la versión del SDK debemos hacerlo de una manera u otra, el imageDecoder es lo que usaremos para acceder a las fotos con el URI
                    if (Build.VERSION.SDK_INT < 28) {
                        this.FOTO = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, contentURI)
                    } else {
                        val source: ImageDecoder.Source = ImageDecoder.createSource(requireContext().contentResolver, contentURI)
                        this.FOTO = ImageDecoder.decodeBitmap(source)
                    }
                    Toast.makeText(context, "Se ha conseguido cargar la imagen de la galeria", Toast.LENGTH_SHORT).show()
                    imgUsuario.setImageBitmap(this.FOTO)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(context, "Ocurrió un fallo al cargar la imagen de la galeria", Toast.LENGTH_SHORT).show()
                }
            }
        } else if (requestCode == CAM) {
            Log.i("FOTO", "Entramos en Camara")

            try{
                //Aqui se comprueba la version del SDK ya que dependiendo de la version, el acceso a la camara se hace de una forma o de otra
                if (Build.VERSION.SDK_INT < 28) {
                    this.FOTO = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, IMG_URI)
                } else {
                    val source: ImageDecoder.Source = ImageDecoder.createSource(requireContext().contentResolver!!, IMG_URI)
                    this.FOTO = ImageDecoder.decodeBitmap(source)
                }

                // Aqui mostramos la imagen que se ha elegido añadiendole el bitmap de la imagen tomada
                detalleFotoSitio.setImageBitmap(this.FOTO)
                //Mostramos un toast para ver que se ha conseguido hacer lo anterior
                Toast.makeText(context, "¡Foto Salvada!", Toast.LENGTH_SHORT).show()

            }catch (e:IOException){//Este try catch es para que si le damos a volver al ir a tomar la foto, nos envia aqui
                e.printStackTrace()
            }

        }
    }
}