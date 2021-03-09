package com.andresivan.turistadroid.ui.missitios

<<<<<<< HEAD
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
=======
import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import com.andresivan.turistadroid.R
import android.os.StrictMode
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.ActivityCompat
import androidx.core.net.toFile
import androidx.fragment.app.Fragment
import com.andresivan.turistadroid.app.MyApp
import com.andresivan.turistadroid.entidades.fotos.FotoController
import com.andresivan.turistadroid.entidades.lugares.Lugar
import com.andresivan.turistadroid.entidades.lugares.LugarController
import com.andresivan.turistadroid.entidades.usuario.Usuario
import com.andresivan.turistadroid.utils.ABase64
import com.andresivan.turistadroid.utils.CifradorContrasena
>>>>>>> main
import com.andresivan.turistadroid.utils.Fotos
import com.andresivan.turistadroid.utils.GeneradorQR
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
<<<<<<< HEAD
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
=======
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import com.andresivan.turistadroid.entidades.*
import com.andresivan.turistadroid.entidades.fotos.Foto
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_sitio_detalle.*
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import com.andresivan.turistadroid.utils.*

class SitioDetalleFragment(
    private var SITIO: Lugar? = null,
    private val MODO_ACCESO_FRAGMENT: ModosAccesos? = ModosAccesos.INSERTAR,
    private val ANTERIOR: MisSitios? = null,
    private val SITIO_POSICION: Int? = null
) : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var USER: Usuario
    private var PERMISOS: Boolean = false
    private lateinit var map: GoogleMap
    private var mapPosition: FusedLocationProviderClient? = null
    private var markerT: Marker? = null
    private var location: Location? = null
    private var ubicacion: LatLng? = null
>>>>>>> main
    private val GAL = 1
    private val CAM = 2
    private lateinit var IMG_URI: Uri
    private val IMG_DIR = "/MisSitios"
    private val IMG_SIZE = 600
    private lateinit var PHOTO: Bitmap
    private var IMG_COMPR_LVL = 60
    private val IMG_PREF = "Sitio"
    private val IMG_EXT = ".jpg"
<<<<<<< HEAD
    private lateinit var tipoSeleccionado:String
    private lateinit var FOTO: Bitmap//Para poder pasar la imagen a bitmap
    private lateinit var IMAGEN_URI: Uri//Ruta de la imagen
    //
    private lateinit var Auth: FirebaseAuth
    private var storage = Firebase.storage
    private lateinit var store : FirebaseFirestore
=======
>>>>>>> main

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
<<<<<<< HEAD
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

=======
        return inflater.inflate(R.layout.fragment_miperfil, container, false)
        inicializarInterfaz()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
>>>>>>> main
        super.onViewCreated(view, savedInstanceState)
        view.setOnTouchListener { view, motionEvent ->
            return@setOnTouchListener true
        }
    }

<<<<<<< HEAD
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
=======
    /**
     * Esta función es bastante impoertante, es la que se encarga de diferenciar el modo de acceso
     * al fragment de SitioDetalleFragmen, como le pasamos el modo de acceso por parámetro,
     * distinguimos y llamamos a una función u otra
     */
    private fun inicializarInterfaz() {
        ANTERIOR?.actualizarListaRegistros()
        initPermissions()
        initUser()
        /*
        SEGÚN EL MODO QUE UTILIZEMOS PARA ACCEDER AL FRAGMENTE, HAREMOS UNA COSA
        U OTRA
         */
        when (this.MODO_ACCESO_FRAGMENT) {
            //Si queremos insertar
            ModosAccesos.INSERTAR -> modoInsertar()
            //Si queremos ver la info del sitio
            ModosAccesos.VISUALIZAR -> modoVisualizar()
            //Si queremos borrar sitio
            ModosAccesos.ELIMINAR -> modoEliminar()
            //Si queremos modificar un sitio
            ModosAccesos.ACTUALIZAR -> modoActualizar()
            else -> {
            }
        }
        miPos()
        map()
    }

    /**
     * Función que lee el usuario que ha iniciado sesión en la app, que quedo almancenado en una
     * especie de variable estática creada con companion object
     */
    private fun initUser() {
        this.USER = MyApp.USUARIO_ACTIVO
    }

    /**
     * Función que lee los permisos de MyApp
     */
    private fun initPermissions() {
        this.PERMISOS = (activity?.application as MyApp).PERMISOS
    }

    /**
     * Función que permite editar el Fragement de detalles del sitio,
     * modificando la vista del fragment_sitio_detalle para poder
     * insertar
     */
    private fun modoInsertar() {
        detalleSitioValoracion.visibility = View.GONE
        detalleSitioInput.setText("")
        val date = LocalDateTime.now()
        detalleBtnFecha.text = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date)
        detalleBtnFecha.setOnClickListener { chooseDate() }
        detalleSitioFabFuncion.setOnClickListener { insertPlace() }
        detalleCamaraSitio.setOnClickListener { opcionAElegirFoto() }

    }

    /**
     * Función que permite editar el Fragment de detalles del lugar
     * modificando la vista del fragment_sitio_detale para poder
     * modificar el sitio seleccionado
     */
    private fun modoVisualizar() {
        detalleCamaraSitio.visibility = View.GONE
        detalleSitioInput.setText(SITIO?.nombre)
        detalleSitioInput.isEnabled = false
        detalleBtnFecha.text = SITIO?.fecha
        detalleSitioValoracion.text = SITIO?.valoracion.toString() + " likes"
        detalleSpnTipo.setSelection(
            (detalleSpnTipo.adapter as ArrayAdapter<String?>).getPosition(
                SITIO?.tipo
            )
        )
        detalleSpnTipo.isEnabled = false
        val fotografia = FotoController.selectById(SITIO?.imgID.toString())
        this.PHOTO = ABase64.toBitmap(fotografia?.imgLugar.toString())!!
        IMG_URI = Uri.parse(fotografia?.uri)
        detalleFotoSitio.setImageBitmap(this.PHOTO)

        detalleSitioFabFuncion.visibility = View.VISIBLE
        detalleSitioFabFuncion.setImageResource(R.drawable.ic_qr)
        detalleSitioFabFuncion.backgroundTintList =
            AppCompatResources.getColorStateList(requireContext(), R.color.qrCodeColor)
        detalleSitioFabFuncion.setOnClickListener { sharePlace() }
    }

    /**
     * Función que permite editar la vista el Fragment de detalles del lugar
     * modificando la vista del fragment_sitio_detale para poder
     * eliminar el sitio seleccionado
     */
    fun modoEliminar() {
        modoVisualizar()
        detalleSitioFabFuncion.visibility = View.VISIBLE
        detalleSitioFabFuncion.setImageResource(R.drawable.ic_remove)
        detalleSitioFabFuncion.backgroundTintList =
            AppCompatResources.getColorStateList(requireContext(), R.color.removeColor)
        detalleSitioFabFuncion.setOnClickListener { deletePlace() }

    }

    /**
     * Función que permite editar la vista el Fragment de detalles del lugar
     * modificando la vista del fragment_sitio_detale para poder
     * actualizar o modificar el sitio seleccionado
     */
    fun modoActualizar() {
        modoVisualizar()
        detalleSitioFabFuncion.visibility = View.VISIBLE
        detalleSitioFabFuncion.setImageResource(R.drawable.ic_update)
        detalleSitioFabFuncion.backgroundTintList =  AppCompatResources.getColorStateList(requireContext(), R.color.updateColor)
        detalleBtnFecha.setOnClickListener { chooseDate() }
        detalleCamaraSitio.visibility = View.VISIBLE
        detalleCamaraSitio.setOnClickListener { opcionAElegirFoto() }
        detalleSpnTipo.isEnabled = true
        detalleSitioInput.isEnabled = true
        detalleSitioFabFuncion.setOnClickListener { updatePlace() }
    }

    /**
     * Función que nos abre un diálogo para consultarnos si queremos guardar un registro o no
     */
    private fun insertPlace() {
        if (esRegistroValido()) {
            alertDialog("NUEVO", "¿QUIERE GUARDARLO?")
        }
    }

    /**
     * Función que permite registrar el sitio
     *
     * Como ves en la función, nos pide, que controlemos la versión del api, es lo que te consulte
     * el viernes después del examen
     */
    private fun insertar() {
        try {
            val b64 = ABase64.toBase64(this.PHOTO)!!
            val fotografia = Foto(
                id = UUID.randomUUID().toString(),
                imgLugar = b64,
                uri = IMG_URI.toString(),
                hash = CifradorContrasena.convertirHash(b64, "SHA-256").toString(),
                usuarioID = USER.id
            )
            FotoController.insert(fotografia)
            SITIO = Lugar(
                id = UUID.randomUUID().toString(),
                nombre = detalleSitioInput.text.toString().trim(),
                tipo = (detalleSpnTipo.selectedItem as String),
                fecha = detalleBtnFecha.text.toString(),
                latitud = ubicacion?.latitude.toString(),
                altitud = ubicacion?.longitude.toString(),
                imgID = fotografia.id,
                valoracion = 0,
                fav = false,
                usuarioID = USER.id
            )
            LugarController.insert(SITIO!!)
            ANTERIOR?.insertarRegistroLista(SITIO!!)
            Snackbar.make(requireView(), "REGISTRO AÑADIDO", Snackbar.LENGTH_SHORT).show()
            volver()
        } catch (ex: Exception) {
            Toast.makeText(context, "REGISTRO NO AÑADIDO" + ex.localizedMessage, Toast.LENGTH_SHORT).show()
        }
        try {
            IMG_URI.toFile().delete()
        } catch (ex: Exception) {
        }
    }

    /**
     * Función que nos pregunta si queremos eliminar el registro de la bbdd
     */
    private fun deletePlace() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            alertDialog("ELIMINAR", "¿QUIERE ELIMINARLO?")
>>>>>>> main
        }
    }

    /**
<<<<<<< HEAD
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
=======
     * Función que se encarga de eliminar un registro de la base de
     * datos
     */
    private fun delete() {
        try {
            val fotografiaID = SITIO?.imgID.toString()
            val fotografia = FotoController.selectById(fotografiaID)
            FotoController.delete(fotografia!!)
            LugarController.delete(SITIO!!)
            ANTERIOR?.eliminarRegistroLista(SITIO_POSICION!!)
            Snackbar.make(requireView(), "ELIMINADO", Snackbar.LENGTH_SHORT)
                .show()
        } catch (ex: Exception) {
            Toast.makeText(context, "NO ELIMINADO", Toast.LENGTH_SHORT).show()
        }
        volver()
    }

    /**
     * Función que nos pregunta si queremos modificar un registro
     */
    private fun updatePlace() {
        if (esRegistroValido()) {
            alertDialog("Modificar Lugar", "¿Desea modificar este lugar?")
        }
    }

    /**
     * Función que modifica un registro seleccionado anteriormente
     */
    private fun update() {
        try {
            val fotoID = SITIO?.imgID.toString()
            val foto = FotoController.selectById(fotoID)
            val b64 = ABase64.toBase64(this.PHOTO)!!
            with(foto!!) {
                imgLugar = b64
                uri = IMG_URI.toString()
                hash = CifradorContrasena.convertirHash(b64, "").toString()
                usuarioID = USER.id
            }
            FotoController.update(foto)
            with(SITIO!!) {
                nombre = detalleSitioInput.text.toString().trim()
                tipo = (detalleSpnTipo.selectedItem as String)
                fecha = detalleBtnFecha.text.toString()
                latitud = ubicacion?.latitude.toString()
                altitud = ubicacion?.longitude.toString()
            }
            LugarController.update(SITIO!!)
            ANTERIOR?.actualizarRegistroLista(SITIO!!, SITIO_POSICION!!)
            Toast.makeText(context, "MODIFICADO", Toast.LENGTH_SHORT).show()
            volver()

        } catch (ex: Exception) {
            Toast.makeText( context, "NO MODIFICADO" + ex.localizedMessage, Toast.LENGTH_SHORT  ).show()
        }
        try {
            IMG_URI.toFile().delete()
        } catch (ex: Exception) {
        }
    }

    /**
     * Esta función nos permite volver atrás
     */
    private fun volver() {
        activity?.onBackPressed()
    }

    /**
     * Esta función nos muestra el dialogo del datePickerDialog
     */
    private fun chooseDate() {
>>>>>>> main
        val date = LocalDateTime.now()
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, mYear, mMonth, mDay ->
<<<<<<< HEAD
                //detalleBtnFecha.text = (mDay.toString() + "/" + (mMonth + 1) + "/" + mYear)
            }, date.year, date.monthValue - 1, date.dayOfMonth
        )
        datePickerDialog.show()
    }*/
=======
                detalleBtnFecha.text = (mDay.toString() + "/" + (mMonth + 1) + "/" + mYear)
            }, date.year, date.monthValue - 1, date.dayOfMonth
        )
        datePickerDialog.show()
    }

    /**
     * Como anteriormente hemos creado el cuadro de dialogo , en esta función mosrtamos los componentes de este
     */
    private fun alertDialog(titulo: String, texto: String) {
        val builder = AlertDialog.Builder(context)
        with(builder)
        {
            setTitle(titulo)
            setMessage(texto)
            setPositiveButton(R.string.ok) { _, _ ->
                when (MODO_ACCESO_FRAGMENT) {
                    ModosAccesos.INSERTAR -> insertar()
                    ModosAccesos.ELIMINAR -> delete()
                    ModosAccesos.ACTUALIZAR -> update()
                    else -> {
                    }
                }
            }
            setNegativeButton(R.string.no, null)
            show()
        }
    }
>>>>>>> main

    /**
     * Funcion que permite comprobar el contenido del formulario del fragment
     * @return Boolean
     */
    private fun esRegistroValido(): Boolean {
<<<<<<< HEAD
/*        var comprobacion = true
=======
        var comprobacion = true
>>>>>>> main
        if (detalleSitioInput.text?.isEmpty()!!) {
            detalleSitioInput.error = "SIN NOMBRE"
            comprobacion = false
        }
        if (!this::PHOTO.isInitialized) {
            this.PHOTO = (detalleFotoSitio.drawable as BitmapDrawable).bitmap
            Toast.makeText(context, "SIN FOTO", Toast.LENGTH_SHORT).show()
            comprobacion = false
        }
<<<<<<< HEAD
        return comprobacion*/
        return true
=======
        return comprobacion
>>>>>>> main
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

<<<<<<< HEAD
=======
    private fun miPos() {
        mapPosition = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    private fun map() {
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.detalleMapa) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    /**
     * Cuando el mapa ya este preparado, lo que hacemos es configurarlo y mostrar el mapa
     */
    override fun onMapReady(tipoMapa: GoogleMap) {
        map = tipoMapa
        opcionesMapa()
        verMapa()
    }

    /**
     * Funcion para configurar el mapa a nuesrto gusto
     */
    private fun opcionesMapa() {
        map.mapType = GoogleMap.MAP_TYPE_SATELLITE
        val uiSettings: UiSettings = map.uiSettings
        uiSettings.isScrollGesturesEnabled = true
        uiSettings.isTiltGesturesEnabled = true
        uiSettings.isCompassEnabled = true
        uiSettings.isZoomControlsEnabled = true
        uiSettings.isMapToolbarEnabled = true
        map.setMinZoomPreference(6f)
        map.setOnMarkerClickListener(this)
    }

    /**
     * Función que nos permite visualizar el mapa
     */
    private fun verMapa() {
        if (this.PERMISOS) {
            if (ActivityCompat.checkSelfPermission( requireContext(),Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            map.isMyLocationEnabled = true
        }
        markerOptions()
        miPosicion()
    }

    /**
     * Función que permite señalar un marcador o algo en mi posicion actual, en el mapa que aparece
     * abjo en el fragment
     */
    private fun markerOptions() {
        map.setOnMapClickListener { point ->
            markerT?.remove()
            markerT = map.addMarker(
                MarkerOptions().position(point).title("MI UBICACION").snippet(detalleSitioInput.text.toString())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
            )
            map.moveCamera(CameraUpdateFactory.newLatLng(point))
            ubicacion = point
        }
    }

    /**
     * Función que consulta nuestra posicion actual y nos localiza en el mapa
     */
    private fun miPosicion() {
        try {
            if (this.PERMISOS) {
                val local: Task<Location> = mapPosition!!.lastLocation
                local.addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        location = task.result
                        ubicacion = LatLng( location!!.latitude, location!!.longitude)
                        map.moveCamera(CameraUpdateFactory.newLatLng(ubicacion))
                    } else {
                    }
                }
            }
        } catch (e: SecurityException) {
            Snackbar.make(
                requireView(), "POS. NO ENCONTRADA",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

>>>>>>> main
    /**
     * Función que se encarga de construir un cuadro de dialogos para seleccionar el origen de la
     * imagen de lugar, según la opcion que elijamos, abriremos la galeria, para coger una foto, o
     * abriremos la camara para tomar una foto
     */
    private fun opcionAElegirFoto() {
        val opcionesAElegir = arrayOf(
<<<<<<< HEAD
            "ELEGIR FOTO",
=======
            "ELEGIT FOTO",
>>>>>>> main
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
<<<<<<< HEAD
     * Este metodo siempre se ejecutará al realizar una acción
=======
     * Siempre se ejecuta al realizar una acción
>>>>>>> main
     * @param requestCode Int
     * @param resultCode Int
     * @param data Intent?
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
<<<<<<< HEAD
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
                    Toast.makeText(requireContext(), "Se ha conseguido cargar la imagen de la galeria", Toast.LENGTH_SHORT).show()
                    detalleFotoSitio.setImageBitmap(this.FOTO)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(requireContext(), "Ocurrió un fallo al cargar la imagen de la galeria", Toast.LENGTH_SHORT).show()
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
=======
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

    override fun onMarkerClick(p0: Marker?): Boolean {
        return true
    }
>>>>>>> main
}