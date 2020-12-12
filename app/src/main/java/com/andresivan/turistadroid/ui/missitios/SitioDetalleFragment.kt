package com.andresivan.turistadroid.ui.missitios

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
import android.util.Log
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
import com.andresivan.turistadroid.utils.Fotos
import com.andresivan.turistadroid.utils.GeneradorQR
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
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
    private var posicion: LatLng? = null
    private val GALERIA = 1
    private val CAMARA = 2
    private lateinit var IMG_URI: Uri
    private val IMG_DIR = "/MisSitios"
    private val IMG_SIZE = 600
    private lateinit var PHOTO: Bitmap
    private var IMG_COMPR_LVL = 60
    private val IMG_PREF = "Sitio"
    private val IMG_EXT = ".jpg"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_miperfil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnTouchListener { view, motionEvent ->
            return@setOnTouchListener true
        }
        inicializarInterfaz()
    }

    /**
     * Iniciamos los elementos de la interfaz
     */
    private fun inicializarInterfaz() {
        ANTERIOR?.actualizarVistaLista()
        initPermissions()
        initUser()
        /*
        SEGÚN EL MODO QUE UTILIZEMOS PARA ACCEDER AL FRAGMENTE, HAREMOS UNA COSA
        U OTRA
         */
        when (this.MODO_ACCESO_FRAGMENT) {
            //Si queremos insertar
            ModosAccesos.INSERTAR -> initInsertMode()
            //Si queremos ver la info del sitio
            ModosAccesos.VISUALIZAR -> initVisualizeMode()
            //Si queremos borrar sitio
            ModosAccesos.ELIMINAR -> initDeleteMode()
            //Si queremos modificar un sitio
            ModosAccesos.ACTUALIZAR -> initUpdateMode()
            else -> {
            }
        }
        obtenerMiPosicionActual()
        initMap()
    }

    /**
     * Lee el usuario
     */
    private fun initUser() {
        this.USER = MyApp.USUARIO_ACTIVO
    }

    /**
     * Lee los permisos
     */
    private fun initPermissions() {
        this.PERMISOS = (activity?.application as MyApp).PERMISOS
    }

    /**
     * Función que permite editar el Fragement de detalles del sitio,
     * modificando la vista del fragment_sitio_detalle para poder
     * insertar
     */
    private fun initInsertMode() {
        Log.i("Lugares", "Modo Insertar")
        detalleSitioValoracion.visibility = View.GONE
        detalleSitioInput.setText("")
        val date = LocalDateTime.now()
        detalleBtnFecha.text = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date)
        detalleBtnFecha.setOnClickListener { chooseDate() }
        detalleSitioFabFuncion.setOnClickListener { insertPlace() }
        detalleCamaraSitio.setOnClickListener { initDialogFoto() }

    }

    /**
     * Función que permite editar el Fragment de detalles del lugar
     * modificando la vista del fragment_sitio_detale para poder
     * modificar el sitio seleccionado
     */
    private fun initVisualizeMode() {
        Log.i("Lugares", "Modo Visualizar")
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
    fun initDeleteMode() {
        Log.i("Lugares", "Modo Eliminar")
        initVisualizeMode()
        detalleSitioFabFuncion.visibility = View.VISIBLE
        detalleSitioFabFuncion.setImageResource(R.drawable.ic_remove)
        detalleSitioFabFuncion.backgroundTintList =
            AppCompatResources.getColorStateList(requireContext(), R.color.removeColor)
        detalleSitioFabFuncion.setOnClickListener { deletePlace() }

    }

    fun initUpdateMode() {
        Log.i("Lugares", "Modo Actualizar")
        initVisualizeMode()
        detalleSitioFabFuncion.visibility = View.VISIBLE
        detalleSitioFabFuncion.setImageResource(R.drawable.ic_update)
        detalleSitioFabFuncion.backgroundTintList =
            AppCompatResources.getColorStateList(requireContext(), R.color.updateColor)
        detalleBtnFecha.setOnClickListener { seleccionarFecha() }
        detalleCamaraSitio.visibility = View.VISIBLE
        detalleCamaraSitio.setOnClickListener { initDialogFoto() }
        detalleSpnTipo.isEnabled = true
        detalleSitioInput.isEnabled = true
        detalleSitioFabFuncion.setOnClickListener { updatePlace() }
    }

    /**
     * Precondiciones para insertar
     */
    private fun insertPlace() {
        if (chequearComponentes()) {
            alertDialog("Insertar Lugar", "¿Desea salvar este lugar?")
        }
    }

    /**
     * Inserta en el sistema de persistencia o almacenamiento
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
            Log.i("Insertar", "Fotografía insertada con éxito con: " + fotografia.id)
            // Insertamos lugar
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                SITIO = Lugar(
                    id = UUID.randomUUID().toString(),
                    nombre = detalleSitioInput.text.toString().trim(),
                    tipo = (detalleSpnTipo.selectedItem as String),
                    fecha = detalleBtnFecha.text.toString(),
                    latitud = posicion?.latitude.toString(),
                    longitud = posicion?.longitude.toString(),
                    imgID = fotografia.id,
                    valoracion = 0,
                    fav = false,
                    usuarioID = USER.id
                )
            }
            LugarController.insert(SITIO!!)

            ANTERIOR?.insertarItemLista(SITIO!!)

            Snackbar.make(requireView(), "¡Añadido!", Snackbar.LENGTH_LONG).show();

            volver()
        } catch (ex: Exception) {
            Toast.makeText(
                context,
                "Se ha producido un error" + ex.localizedMessage,
                Toast.LENGTH_LONG
            ).show()
        }
        try {
            IMG_URI.toFile().delete()
        } catch (ex: Exception) {
        }
    }

    /**
     * Precondiciones para eliminar
     */
    private fun deletePlace() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            alertDialog("Eliminar Lugar", "¿Desea eliminar este lugar?")
        }
    }

    /**
     * Función que se encarga de eliminar un registro de la base de
     * datos
     */
    private fun delete() {
        try {
            val fotografiaID = SITIO?.imgID.toString()
            val fotografia = FotoController.selectById(fotografiaID)
            FotoController.delete(fotografia!!)
            LugarController.delete(SITIO!!)
            ANTERIOR?.eliminarItemLista(SITIO_POSICION!!)
            Snackbar.make(requireView(), "ELIMINADO", Snackbar.LENGTH_LONG)
                .show();
        } catch (ex: Exception) {
            Toast.makeText(context, "ERROR: " + ex.localizedMessage, Toast.LENGTH_LONG)
                .show()
        }
        volver()
    }

    /**
     * Pre condición de actualizar
     */
    private fun updatePlace() {
        if (chequearComponentes()) {
            alertDialog("Modificar Lugar", "¿Desea modificar este lugar?")
        }
    }

    /**
     * Actualiza un lugar
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
                latitud = posicion?.latitude.toString()
                longitud = posicion?.longitude.toString()
            }
            LugarController.update(SITIO!!)
            ANTERIOR?.actualizarItemLista(SITIO!!, SITIO_POSICION!!)
            Snackbar.make(requireView(), "ACTUALIZADO", Snackbar.LENGTH_LONG).show();
            volver()

        } catch (ex: Exception) {
            Toast.makeText(
                context,
                "Error al actualizar: " + ex.localizedMessage,
                Toast.LENGTH_LONG
            ).show()
        }
        try {
            IMG_URI.toFile().delete()
        } catch (ex: Exception) {
        }
    }

    private fun seleccionarFecha() {
        val date = LocalDateTime.now()

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, mYear, mMonth, mDay ->
                detalleBtnFecha.text = (mDay.toString() + "-" + (mMonth + 1) + "-" + mYear)
            }, date.year, date.monthValue - 1, date.dayOfMonth
        )
        datePickerDialog.show()
    }

    /**
     * Vuelve
     */
    private fun volver() {
        activity?.onBackPressed()
    }

    /**
     * Inicia el DatePicker
     */
    private fun chooseDate() {
        val date = LocalDateTime.now()
        //Abrimos el DataPickerDialog
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, mYear, mMonth, mDay ->
                detalleBtnFecha.text = (mDay.toString() + "/" + (mMonth + 1) + "/" + mYear)
            }, date.year, date.monthValue - 1, date.dayOfMonth
        )
        datePickerDialog.show()
    }

    /**
     * Dialogo de opciones
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

    /**
     * Funcion que permite comprobar el contido del formulario del fragment
     * @return Boolean
     */
    private fun chequearComponentes(): Boolean {
        var comprobacion = true
        if (detalleSitioInput.text?.isEmpty()!!) {
            detalleSitioInput.error = "El nombre no puede ser vacío"
            comprobacion = false
        }
        if (!this::PHOTO.isInitialized) {
            this.PHOTO = (detalleFotoSitio.drawable as BitmapDrawable).bitmap
            Toast.makeText(context, "No puede estar vacío el campo de la foto", Toast.LENGTH_SHORT).show()
            comprobacion = false
        }
        return comprobacion
    }

    /**
     * Función que nos permite compartir un QR con la info de un sitio
     */
    private fun sharePlace() {
        val buildr = AlertDialog.Builder(context)
        val infltr = requireActivity().layoutInflater
        val view = infltr.inflate(R.layout.fragment_compartir_qr, null)
        val code = GeneradorQR.generarCodigoQR(Gson().toJson(SITIO))
        val codeQR_IV = view.findViewById(R.id.imagenCodigoQR) as ImageView
        codeQR_IV.setImageBitmap(code)
        buildr
            .setView(view)
            .setIcon(R.drawable.ic_qr)
            .setTitle("¿DESEA COMPARTIR EL CODIGO QR?")
            .setPositiveButton(R.string.aceptar) { _, _ ->
                compartirQRCode(code)
            }
            .setNegativeButton(R.string.cancelar, null)
        buildr.show()

    }

    /**
     * Comparte un código QR
     * @param code Bitmap
     */
    private fun compartirQRCode(qrCode: Bitmap) {
        Log.i("QR", "Aceptar QR")

        val buildr = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(buildr.build())

        val nombre = Fotos.crearNombreFoto(IMG_PREF, IMG_EXT)

        val fichero =
            Fotos.copiarFoto(qrCode, IMG_DIR, 100, requireContext())
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_STREAM, Uri.fromFile(fichero))
        }
        context?.startActivity(Intent.createChooser(shareIntent, null))
    }

    private fun obtenerMiPosicionActual() {
        mapPosition = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    private fun initMap() {
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.detalleMapa) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        configurarIUMapa()
        modoMapa()
    }

    /**
     * Configuración por defecto del modo de mapa
     */
    private fun configurarIUMapa() {
        Log.i("Mapa", "Configurando IU Mapa")
        map.mapType = GoogleMap.MAP_TYPE_HYBRID
        val uiSettings: UiSettings = map.uiSettings
        uiSettings.isScrollGesturesEnabled = true
        uiSettings.isTiltGesturesEnabled = true
        uiSettings.isCompassEnabled = true
        uiSettings.isZoomControlsEnabled = true
        uiSettings.isMapToolbarEnabled = true
        map.setMinZoomPreference(12.0f)
        map.setOnMarkerClickListener(this)
    }

    /**
     * Actualiza la interfaz del mapa según el modo
     */
    private fun modoMapa() {
        Log.i("Mapa", "Configurando Modo Mapa")
        when (this.MODO_ACCESO_FRAGMENT) {
            ModosAccesos.INSERTAR -> mapaInsertar()
            ModosAccesos.VISUALIZAR -> mapaVisualizar()
            ModosAccesos.ELIMINAR -> mapaVisualizar()
            ModosAccesos.ACTUALIZAR -> mapaActualizar()
            else -> {
            }
        }
    }

    /**
     * Modo insertar del mapa
     */
    private fun mapaInsertar() {
        if (this.PERMISOS) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            map.isMyLocationEnabled = true
        }
        activarEventosMarcadores()
        obtenerPosicion()
    }

    /**
     * Modo Mapa Visualizar
     */
    private fun mapaVisualizar() {
        Log.i("Mapa", "Configurando Modo Visualizar")
        posicion = LatLng(SITIO!!.latitud.toDouble(), SITIO!!.longitud.toDouble())
        // marcadorTouch?.remove()
        map.addMarker(
            MarkerOptions() // Posición
                .position(posicion!!) // Título
                .title(SITIO!!.nombre) // Subtitulo
                .snippet(SITIO!!.tipo + " del " + SITIO!!.fecha) // Color o tipo d icono
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
        )
        map.moveCamera(CameraUpdateFactory.newLatLng(posicion))
    }

    /**
     * Modo Mapa Actualizar
     */
    private fun mapaActualizar() {
        Log.i("Mapa", "Configurando Modo Actualizar")
        if (this.PERMISOS) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            map.isMyLocationEnabled = true
        }
        activarEventosMarcadores()
        mapaVisualizar()
    }

    /**
     * Activa los eventos de los marcadores
     */
    private fun activarEventosMarcadores() {
        map.setOnMapClickListener { point ->
            markerT?.remove()
            markerT = map.addMarker(
                MarkerOptions() // Posición
                    .position(point) // Título
                    .title("Posición Actual") // Subtitulo
                    .snippet(detalleSitioInput.text.toString())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
            )
            map.moveCamera(CameraUpdateFactory.newLatLng(point))
            posicion = point
        }
    }

    /**
     * Obtiene la posición
     */
    private fun obtenerPosicion() {
        Log.i("Mapa", "Opteniendo posición")
        try {
            if (this.PERMISOS) {
                // Lo lanzamos como tarea concurrente
                val local: Task<Location> = mapPosition!!.lastLocation
                local.addOnCompleteListener(
                    requireActivity()
                ) { task ->
                    if (task.isSuccessful) {
                        // Actualizamos la última posición conocida
                        //try {
                        location = task.result
                        posicion = LatLng(
                            location!!.latitude,
                            location!!.longitude
                        )
                        map.moveCamera(CameraUpdateFactory.newLatLng(posicion));
                    } else {
                        Log.i("GPS", "No se encuetra la última posición.")
                        Log.e("GPS", "Exception: %s", task.exception)
                    }
                }
            }
        } catch (e: SecurityException) {
            Snackbar.make(
                requireView(), "No se ha encontrado su posoción actual o el GPS está desactivado",
                Snackbar.LENGTH_LONG
            ).show();
            Log.e("Exception: %s", e.message.toString())
        }
    }

    /**
     * Evento al pulsar el marcador
     * @param marker Marker
     * @return Boolean
     */
    override fun onMarkerClick(marker: Marker): Boolean {
        Log.i("Mapa", marker.toString())
        return false
    }

    /**
     * FUNCIONALIDAD DE LA CAMARA
     */

    /**
     * Muestra el diálogo para tomar foto o elegir de la galería
     */
    private fun initDialogFoto() {
        val fotoDialogoItems = arrayOf(
            "Galería",
            "Cámara"
        )
        AlertDialog.Builder(context).setTitle("Seleccionar Acción")
            .setItems(fotoDialogoItems) { dialog, modo ->
                when (modo) {
                    0 -> elegirFotoGaleria()
                    1 -> tomarFotoCamara()
                }
            }.show()
    }

    /**
     * Elige una foto de la galeria
     */
    private fun elegirFotoGaleria() {
        val galleryIntent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(galleryIntent, GALERIA)
    }


    private fun tomarFotoCamara() {
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val nombre = Fotos.crearNombreFoto(IMG_PREF, IMG_EXT)
        val fichero = Fotos.salvarFoto(IMG_DIR, nombre, requireContext())

        IMG_URI = Uri.fromFile(fichero)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, IMG_URI)

        startActivityForResult(intent, CAMARA)
    }

    /**
     * Siempre se ejecuta al realizar una acción
     * @param requestCode Int
     * @param resultCode Int
     * @param data Intent?
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i("SIT DET FRAGMENT - FOTO", "Opción::--->$requestCode")
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) {
            Log.i("SIT DET FRAGMENT - FOTO", "Cancelada")
        }

        if (requestCode == GALERIA) {
            Log.i("FOTO", "Entramos en Galería")
            if (data != null) {
                // Obtenemos su URI con su dirección temporal
                val contentURI = data.data!!
                try {
                    // Obtenemos el bitmap de su almacenamiento externo
                    // Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    if (Build.VERSION.SDK_INT < 28) {
                        this.PHOTO =
                            MediaStore.Images.Media.getBitmap(context?.contentResolver, contentURI);
                    } else {
                        val source: ImageDecoder.Source =
                            ImageDecoder.createSource(context?.contentResolver!!, contentURI)
                        this.PHOTO = ImageDecoder.decodeBitmap(source)
                    }

                    val prop = this.IMG_SIZE / this.PHOTO.width.toFloat()

                    this.PHOTO = Bitmap.createScaledBitmap(
                        this.PHOTO,
                        this.IMG_SIZE,
                        (this.PHOTO.height * prop).toInt(),
                        false
                    )

                    val nombre = Fotos.crearNombreFoto(IMG_PREF, IMG_EXT)
                    val fichero =
                        Fotos.copyPhoto(
                            this.PHOTO,
                            nombre,
                            IMG_DIR,
                            IMG_COMPR_LVL,
                            requireContext()
                        )
                    IMG_URI = Uri.fromFile(fichero)

                    detalleFotoSitio.setImageBitmap(this.PHOTO)

                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(context, "ERROR - GALERIA", Toast.LENGTH_SHORT).show()
                }
            }
        } else if (requestCode == CAMARA) {
            try {
                if (Build.VERSION.SDK_INT < 28) {
                    this.PHOTO =
                        MediaStore.Images.Media.getBitmap(context?.contentResolver, IMG_URI)
                } else {
                    val source: ImageDecoder.Source =
                        ImageDecoder.createSource(context?.contentResolver!!, IMG_URI)
                    this.PHOTO = ImageDecoder.decodeBitmap(source)
                }
                Log.i("SitioDetalleFragment", IMG_URI.path.toString())
                Fotos.comprimirImagen(IMG_URI.toFile(), this.PHOTO, this.IMG_COMPR_LVL)
                detalleFotoSitio.setImageBitmap(this.PHOTO)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("SitioDetalleFragment", "Fallo camara")
            }
        }
    }
}