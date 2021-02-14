package com.andresivan.turistadroid.ui.missitios

import android.app.Activity
import android.app.AlertDialog
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
import android.widget.*
import androidx.core.net.toFile
import androidx.fragment.app.Fragment
import com.andresivan.turistadroid.R
import com.andresivan.turistadroid.app.MyApp
import com.andresivan.turistadroid.entidades.lugares.Lugar
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.utils.Fotos
import com.andresivan.turistadroid.utils.GeneradorQR
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_sitio_detalle.*
import kotlinx.android.synthetic.main.fragment_sitio_detalle.tiposLugares_spinner
import kotlinx.android.synthetic.main.fragment_sitio_detalle_modificar.*
import java.io.IOException

//Foto,Nombre,Tipo,Fecha y puntuacion
class SitioDetalleFragmentModificar(
    private var SITIO: Lugar? = null,
    private val ANTERIOR: MisSitios? = null,
    private val posicion:Int
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
    //
    private lateinit var Auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_sitio_detalle_modificar, container, false)
        //Esto lo usaremos para acceder a nuestra ubicacion actual
        fusedLocationClient= activity?.let { LocationServices.getFusedLocationProviderClient(it) }!!
        Auth = Firebase.auth
        inicializarInterfaz(vista)

        return vista
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnIngresarModificar.setOnClickListener { ingresarLugar() }

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
        /*val lugar = hashMapOf(
            "NombreLugar" to txtNombreLugarModificar.text.toString(),
            "Tipo" to tipoSeleccionado,
            "Latitud" to MyApp.posicion.latitude.toString(),
            "Longitud" to MyApp.posicion.longitude.toString(),
            "Votos" to 0,
            "usuariosVotados" to arrayListOf("")
        )*/
        val lugaresRef = db.collection("Lugares").document(MyApp.idLugares[posicion])
        lugaresRef
            .update("NombreLugar",txtNombreLugarModificar.text.toString())
            .addOnSuccessListener { documentReference ->
                Log.d("Modificar", "Modificacion realizada correctamente:")
            }
            .addOnFailureListener { e ->
                Log.w("Modificar", "Error al modificar lugar", e)

            }

        lugaresRef
            .update("Tipo",tipoSeleccionado)
            .addOnSuccessListener { documentReference ->
                Log.d("Modificar", "Modificacion realizada correctamente:")
            }
            .addOnFailureListener { e ->
                Log.w("Modificar", "Error al modificar lugar", e)

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
     * Siempre se ejecuta al realizar una acción
     * @param requestCode Int
     * @param resultCode Int
     * @param data Intent?
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED)
        if (requestCode == GAL) {
            if (data != null) {
                val contentURI = data.data!!
                try {
                    if (Build.VERSION.SDK_INT < 28) {
                        this.PHOTO = MediaStore.Images.Media.getBitmap(context?.contentResolver, contentURI)
                    } else {
                        val source: ImageDecoder.Source = ImageDecoder.createSource(context?.contentResolver!!, contentURI)
                        this.PHOTO = ImageDecoder.decodeBitmap(source)
                    }
                    val prop = this.IMG_SIZE / this.PHOTO.width.toFloat()
                    this.PHOTO = Bitmap.createScaledBitmap(this.PHOTO, this.IMG_SIZE, (this.PHOTO.height * prop).toInt(), false)
                    val nombre = Fotos.crearNombreFoto(IMG_PREF, IMG_EXT)
                    val fichero = Fotos.copyPhoto(this.PHOTO, nombre, IMG_DIR, IMG_COMPR_LVL, requireContext())
                    IMG_URI = Uri.fromFile(fichero)
                    detalleFotoSitio.setImageBitmap(this.PHOTO)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        } else if (requestCode == CAM) {
            try {
                if (Build.VERSION.SDK_INT < 28) {
                    this.PHOTO = MediaStore.Images.Media.getBitmap(context?.contentResolver, IMG_URI)
                } else {
                    val source: ImageDecoder.Source = ImageDecoder.createSource(context?.contentResolver!!, IMG_URI)
                    this.PHOTO = ImageDecoder.decodeBitmap(source)
                }
                Fotos.comprimirImagen(IMG_URI.toFile(), this.PHOTO, this.IMG_COMPR_LVL)
                detalleFotoSitio.setImageBitmap(this.PHOTO)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}