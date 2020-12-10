package com.andresivan.turistadroid.ui.missitios

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
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
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_sitio_detalle.*
import java.io.IOException
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class SitioDetalleFragment(
    private var LUGAR: Lugar? = null,
    private val MODO: ModosAccesos? = ModosAccesos.INSERTAR,
    private val ANTERIOR: MisSitios? = null,
    private val LUGAR_INDEX: Int? = null,
) : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    // Mis Variables
    private lateinit var USUARIO: Usuario
    private var PERMISOS: Boolean = false

    // Variables a usar y permisos del mapa
    private lateinit var mMap: GoogleMap
    private var mPosicion: FusedLocationProviderClient? = null
    private var marcadorTouch: Marker? = null
    private var localizacion: Location? = null
    private var posicion: LatLng? = null

    // Variables para la camara
    private val GALERIA = 1
    private val CAMARA = 2
    private lateinit var IMAGEN_URI: Uri
    private val IMG_DIR = "/MisSitios"
    private val IMG_PROPORCION = 600
    private lateinit var FOTO: Bitmap
    private var IMG_COMPR_LVL = 60
    private val IMG_PREF = "Sitio"
    private val IMG_EXT = ".jpg"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sitio_detalle, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnTouchListener { view, motionEvent ->
            return@setOnTouchListener true
        }
        initIU()
    }

    /**
     * Iniciamos los elementos de la interfaz
     */
    private fun initIU() {
        // Actualizo la vista anterior para que no se quede el swipe marcado
        ANTERIOR?.actualizarVistaLista()
        initPermisos()
        initUsuario()
        // Modos de ejecución
        when (this.MODO) {
            ModosAccesos.INSERTAR -> initModoInsertar()
            ModosAccesos.VISUALIZAR -> initModoVisualizar()
            ModosAccesos.ELIMINAR -> initModoEliminar()
            ModosAccesos.ACTUALIZAR -> initModoActualizar()
            else -> {
            }
        }
        leerPoscionGPSActual()
        initMapa()
    }

    /**
     * Lee el usuario
     */
    private fun initUsuario() {
        this.USUARIO = (activity?.application as MyApp).SESION_USUARIO
    }

    /**
     * Lee los permisos
     */
    private fun initPermisos() {
        this.PERMISOS = (activity?.application as MyApp).PERMISOS
    }

    /**
     * Crea todos los elementos en modo insertar
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun initModoInsertar() {
        Log.i("Lugares", "Modo Insertar")
        detalleFavoritos.visibility = View.GONE
        detalleSitioInput.setText("")
        val date = LocalDateTime.now()
        detalleBtnFecha.text = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date)
        detalleBtnFecha.setOnClickListener { escogerFecha() }
        detalleSitioFabFuncion.setOnClickListener { insertarLugar() }
        detalleCamaraSitio.setOnClickListener { initDialogFoto() }

    }

    /**
     * Inicia el modo de Visualizar
     */
    private fun initModoVisualizar() {
        Log.i("Lugares", "Modo Visualizar")
        // Ocultamos o quitamos lo que no queremos ver en este modo
        detalleCamaraSitio.visibility = View.GONE
        detalleSitioInput.setText(LUGAR?.nombre)
        detalleSitioInput.isEnabled = false
        detalleBtnFecha.text = LUGAR?.fecha
        detalleFavoritos.text = LUGAR?.valoracion.toString() + " voto(s)."
        detalleSpnTipo.setSelection(
            (detalleSpnTipo.adapter as ArrayAdapter<String?>).getPosition(
                LUGAR?.tipo
            )
        )
        detalleSpnTipo.isEnabled = false
        val fotografia = FotoController.selectById(LUGAR?.imgID.toString())
        this.FOTO = ABase64.toBitmap(fotografia?.imgLugar.toString())!!
        IMAGEN_URI = Uri.parse(fotografia?.uri)
        detalleFotoSitio.setImageBitmap(this.FOTO)

        detalleSitioFabFuncion.visibility = View.VISIBLE
        detalleSitioFabFuncion.setImageResource(R.drawable.ic_qr)
        detalleSitioFabFuncion.backgroundTintList =
            AppCompatResources.getColorStateList(context!!, R.color.qrCodeColor)
        detalleSitioFabFuncion.setOnClickListener { compartirLugar() }
    }

    /**
     * Inicia el Modo de Modificar
     */
    fun initModoEliminar() {
        Log.i("Lugares", "Modo Eliminar")
        initModoVisualizar()
        detalleSitioFabFuncion.visibility = View.VISIBLE
        detalleSitioFabFuncion.setImageResource(R.drawable.ic_remove)
        detalleSitioFabFuncion.backgroundTintList =
            AppCompatResources.getColorStateList(context!!, R.color.removeColor)
        detalleSitioFabFuncion.setOnClickListener { eliminarLugar() }

    }

    fun initModoActualizar() {
        Log.i("Lugares", "Modo Actualizar")
        initModoVisualizar()
        detalleSitioFabFuncion.visibility = View.VISIBLE
        detalleSitioFabFuncion.setImageResource(R.drawable.ic_update)
        detalleSitioFabFuncion.backgroundTintList =
            AppCompatResources.getColorStateList(context!!, R.color.updateColor)
        detalleBtnFecha.setOnClickListener { escogerFecha() }
        detalleCamaraSitio.visibility = View.VISIBLE
        detalleCamaraSitio.setOnClickListener { initDialogFoto() }
        detalleSpnTipo.isEnabled = true
        detalleSitioInput.isEnabled = true
        // Acción
        detalleSitioFabFuncion.setOnClickListener { actualizarLugar() }

    }

    /**
     * Precondiciones para insertar
     */
    private fun insertarLugar() {
        if (comprobarFormulario()) {
            alertaDialogo("Insertar Lugar", "¿Desea salvar este lugar?")
        }
    }

    /**
     * Inserta en el sistema de persistencia o almacenamiento
     */
    private fun insertar() {
        try {
            // Iderntamos la fotografia
            val b64 = ImageBase64.toBase64(this.FOTO)!!
            val fotografia = Fotos(
                id = UUID.randomUUID().toString(),
                imagen = b64,
                uri = IMAGEN_URI.toString(),
                hash = CifradorContrasena.convertirHash(b64).toString(),
                time = Instant.now().toString(),
                usuarioID = USUARIO.id
            )
            FotoController.insert(fotografia)
            Log.i("Insertar", "Fotografía insertada con éxito con: " + fotografia.id)
            // Insertamos lugar
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                LUGAR = Lugar(
                    id = UUID.randomUUID().toString(),
                    nombre = detalleSitioInput.text.toString().trim(),
                    tipo = (detalleSpnTipo.selectedItem as String),
                    fecha = detalleBtnFecha.text.toString(),
                    latitud = posicion?.latitude.toString(),
                    longitud = posicion?.longitude.toString(),
                    imagenID = fotografia.id,
                    favorito = false,
                    votos = 0,
                    time = Instant.now().toString(),
                    usuarioID = USUARIO.id
                )
            }
            LugarController.insert(LUGAR!!)
            // Actualizamos el adapter
            ANTERIOR?.insertarItemLista(LUGAR!!)
            Snackbar.make(requireView(), "¡Lugar añadido con éxito!", Snackbar.LENGTH_LONG).show();
            Log.i("Insertar", "Lugar insertado con éxito con id" + LUGAR)
            volver()
        } catch (ex: Exception) {
            Toast.makeText(context, "Error al insertar: " + ex.localizedMessage, Toast.LENGTH_LONG)
                .show()
            Log.i("Insertar", "Error al insertar: " + ex.localizedMessage)
        }
        try {
            IMAGEN_URI.toFile().delete()
            Log.i("Insertar", "Borrada la imagen tempral asociada")
        } catch (ex: Exception) {
        }
    }

    /**
     * Precondiciones para eliminar
     */
    private fun eliminarLugar() {
        alertaDialogo("Eliminar Lugar", "¿Desea eliminar este lugar?")
    }

    /**
     * Elimina un objeto de la base de datos
     */
    private fun eliminar() {
        try {
            //Eliminamos lógicamente // Eliminamos el lugar
            val fotografiaID = LUGAR?.imgID.toString()
            val fotografia = FotoController.selectById(fotografiaID)
            FotoController.delete(fotografia!!)
            LugarController.delete(LUGAR!!)
            // Actualizamos el adapter
            ANTERIOR?.eliminarItemLista(LUGAR_INDEX!!)
            Snackbar.make(requireView(), "¡Lugar eliminado con éxito!", Snackbar.LENGTH_LONG).show();
            Log.i("Eliminar", "Lugar eliminado con éxito")
        } catch (ex: Exception) {
            Toast.makeText(context, "Error al eliminar: " + ex.localizedMessage, Toast.LENGTH_LONG)
                .show()
            Log.i("Eliminar", "Error al eliminar: " + ex.localizedMessage)
        }
        // Volvemos
        volver()
    }

    /**
     * Pre condición de actualizar
     */
    private fun actualizarLugar() {
        if (comprobarFormulario()) {
            alertaDialogo("Modificar Lugar", "¿Desea modificar este lugar?")
        }
    }

    /**
     * Actualiza un lugar
     */
    private fun actualizar() {
        try {
            // Actualizamos la fotografía por si hay cambios
            val fotografiaID = LUGAR?.imgID.toString()
            val fotografia = FotoController.selectById(fotografiaID)
            val b64 = ABase64.toBase64(this.FOTO)!!
            Log.i("Actualizar", "Imagenes Distintas")
            with(fotografia!!) {
                imgLugar = b64
                uri = IMAGEN_URI.toString()
                hash = CifradorContrasena.convertirHash(b64).toString()
                usuarioID = USUARIO.id
            }
            FotoController.update(fotografia)
            Log.i("Actualizar", "Fotografía actualizada")
            Log.i("Actualizar", "Actualizamos los lugares")
            with(LUGAR!!) {
                nombre = detalleSitioInput.text.toString().trim()
                tipo = (detalleSpnTipo.selectedItem as String)
                fecha = detalleBtnFecha.text.toString()
                latitud = posicion?.latitude.toString()
                longitud = posicion?.longitude.toString()
            }
            LugarController.update(LUGAR!!)
            // Actualizamos el adapter
            ANTERIOR?.actualizarItemLista(LUGAR!!, LUGAR_INDEX!!)
            Snackbar.make(requireView(), "¡Lugar actualizado con éxito!", Snackbar.LENGTH_LONG).show();
            Log.i("Actualizar", "Lugar actualizado con éxito con id" + LUGAR!!.id)
            // Volvemos
            volver()

        } catch (ex: Exception) {
            Toast.makeText(
                context,
                "Error al actualizar: " + ex.localizedMessage,
                Toast.LENGTH_LONG
            ).show()
            Log.i("Actualizar", "Error al actualizar: " + ex.localizedMessage)
        }
        try {
            IMAGEN_URI.toFile().delete()
            Log.i("Modificar", "Borrada la imagen temporal asociada")
        } catch (ex: Exception) {
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun seleccionarFecha(){
        val date = LocalDateTime.now()

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, mYear, mMonth, mDay ->
                detalleBtnFecha.text = (mDay.toString() + "/" + (mMonth + 1) + "/" + mYear)
            }, date.year, date.monthValue - 1, date.dayOfMonth
        )
        datePickerDialog.show()
    }

    /**
     * Vuelve
     */
    private fun volver() {
        activity?.onBackPressed();
    }

    /**
     * Inicia el DatePicker
     */
    @RequiresApi(Build.VERSION_CODES.O)
    private fun escogerFecha() {
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
    private fun alertaDialogo(titulo: String, texto: String) {
        val builder = AlertDialog.Builder(context)
        with(builder)
        {
            setTitle(titulo)
            setMessage(texto)
            setPositiveButton(R.string.ok) { _, _ ->
                when (MODO) {
                    ModosAccesos.INSERTAR -> insertar()
                    ModosAccesos.ELIMINAR -> eliminar()
                    ModosAccesos.ACTUALIZAR -> actualizar()
                    else -> {
                    }
                }
            }
            setNegativeButton(R.string.no, null)
            show()
        }
    }

    /**
     * Comprueba que no haya campos nulos
     * @return Boolean
     */
    private fun comprobarFormulario(): Boolean {
        var sal = true
        if (detalleSitioInput.text?.isEmpty()!!) {
            detalleSitioInput.error = "El nombre no puede ser vacío"
            sal = false
        }
        if (!this::FOTO.isInitialized) {
            this.FOTO = (detalleFotoSitio.drawable as BitmapDrawable).bitmap
            Toast.makeText(context, "La imagen no puede estar vacía", Toast.LENGTH_SHORT).show()
            sal = false
        }
        return sal
    }

    /**
     * Comparte un lugar con QR
     */
    private fun compartirLugar() {
        val builder = AlertDialog.Builder(context)
        val inflater = requireActivity().layoutInflater
        // https://stackoverflow.com/questions/40189734/bitmap-not-showing-in-dialog
        // https://stackoverflow.com/questions/40189734/bitmap-not-showing-in-dialog
        val vista = inflater.inflate(R.layout.fragment_compartir_qr, null)
        val code = GeneradorQR.generarCodigoQR(Gson().toJson(LUGAR))
        val qrCodeImageView = vista.findViewById(R.id.imagenCodigoQR) as ImageView
        qrCodeImageView.setImageBitmap(code)
        builder
            .setView(vista)
            .setIcon(R.drawable.ic_qr_code)
            .setTitle("¿Compartir mediante QR?")
            // Add action buttons
            .setPositiveButton(R.string.aceptar) { _, _ ->
                compartirQRCode(code)
            }
            .setNegativeButton(R.string.cancelar, null)
        // setNeutralButton("Maybe", neutralButtonClick)
        builder.show()
    }

    /**
     * Comparte un código QR
     * @param code Bitmap
     */
    private fun compartirQRCode(qrCode: Bitmap) {
        Log.i("QR", "Aceptar QR")
        // Politicas de seguridad
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        val nombre = Fotos.crearNombreFoto(IMG_PREF, IMG_EXT)
        val fichero =
            Fotos.copiarFoto(qrCode, nombre, IMG_DIR, 100, requireContext())
        Log.i("QR", "Foto salvada: " + fichero.absolutePath)
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_STREAM, Uri.fromFile(fichero))
        }
        context?.startActivity(Intent.createChooser(shareIntent, null))
        Log.i("QR", "Foto salvada")
    }


    /**
     * FUNCIONALIDAD DEL GPS
     */

    /**
     * Leemos la posición actual del GPS
     */
    private fun leerPoscionGPSActual() {
        mPosicion = LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    private fun initMapa() {
        Log.i("Mapa", "Iniciando Mapa")
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.detalleMapa) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        configurarIUMapa()
        modoMapa()
    }

    /**
     * Configuración por defecto del modo de mapa
     */
    private fun configurarIUMapa() {
        Log.i("Mapa", "Configurando IU Mapa")
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        val uiSettings: UiSettings = mMap.uiSettings
        uiSettings.isScrollGesturesEnabled = true
        uiSettings.isTiltGesturesEnabled = true
        uiSettings.isCompassEnabled = true
        uiSettings.isZoomControlsEnabled = true
        uiSettings.isMapToolbarEnabled = true
        mMap.setMinZoomPreference(12.0f)
        mMap.setOnMarkerClickListener(this)
    }

    /**
     * Actualiza la interfaz del mapa según el modo
     */
    private fun modoMapa() {
        Log.i("Mapa", "Configurando Modo Mapa")
        when (this.MODO) {
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
            mMap.isMyLocationEnabled = true
        }
        activarEventosMarcadores()
        obtenerPosicion()
    }

    /**
     * Modo Mapa Visualizar
     */
    private fun mapaVisualizar() {
        Log.i("Mapa", "Configurando Modo Visualizar")
        posicion = LatLng(LUGAR!!.latitud.toDouble(), LUGAR!!.longitud.toDouble())
        // marcadorTouch?.remove()
        mMap.addMarker(
            MarkerOptions() // Posición
                .position(posicion!!) // Título
                .title(LUGAR!!.nombre) // Subtitulo
                .snippet(LUGAR!!.tipo + " del " + LUGAR!!.fecha) // Color o tipo d icono
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(posicion))
    }

    /**
     * Modo Mapa Actualizar
     */
    private fun mapaActualizar() {
        Log.i("Mapa", "Configurando Modo Actualizar")
        if (this.PERMISOS) {
            mMap.isMyLocationEnabled = true
        }
        activarEventosMarcadores()
        mapaVisualizar()
    }

    /**
     * Activa los eventos de los marcadores
     */
    private fun activarEventosMarcadores() {
        mMap.setOnMapClickListener { point ->
            marcadorTouch?.remove()
            marcadorTouch = mMap.addMarker(
                MarkerOptions() // Posición
                    .position(point) // Título
                    .title("Posición Actual") // Subtitulo
                    .snippet(detalleSitioInput.text.toString())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
            )
            mMap.moveCamera(CameraUpdateFactory.newLatLng(point))
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
                val local: Task<Location> = mPosicion!!.lastLocation
                local.addOnCompleteListener(
                    requireActivity()
                ) { task ->
                    if (task.isSuccessful) {
                        // Actualizamos la última posición conocida
                        //try {
                        localizacion = task.result
                        posicion = LatLng(
                            localizacion!!.latitude,
                            localizacion!!.longitude
                        )
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(posicion));
                    } else {
                        Log.i("GPS", "No se encuetra la última posición.")
                        Log.e("GPS", "Exception: %s", task.exception)
                    }
                }
            }
        } catch (e: SecurityException) {
            Snackbar.make(requireView(),"No se ha encontrado su posoción actual o el GPS está desactivado",
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
        // Si queremos hacer uso de fotos en alta calidad
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val nombre = Fotos.crearNombreFoto(IMG_PREF, IMG_EXT)
        val fichero = Fotos.salvarFoto(IMG_DIR, nombre, requireContext())
        IMAGEN_URI = Uri.fromFile(fichero)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, IMAGEN_URI)
        Log.i("Camara", IMAGEN_URI.path.toString())
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
                        this.FOTO =
                            MediaStore.Images.Media.getBitmap(context?.contentResolver, contentURI);
                    } else {
                        val source: ImageDecoder.Source =
                            ImageDecoder.createSource(context?.contentResolver!!, contentURI)
                        this.FOTO = ImageDecoder.decodeBitmap(source)
                    }

                    val prop = this.IMG_PROPORCION / this.FOTO.width.toFloat()

                    this.FOTO = Bitmap.createScaledBitmap(
                        this.FOTO,
                        this.IMG_PROPORCION,
                        (this.FOTO.height * prop).toInt(),
                        false
                    )

                    val nombre = Fotos.crearNombreFoto(IMG_PREF, IMG_EXT)
                    val fichero =
                        Fotos.copyPhoto(this.FOTO, nombre, IMG_DIR, IMG_COMPR_LVL, requireContext())
                    IMAGEN_URI = Uri.fromFile(fichero)
                    Toast.makeText(context, "¡Foto rescatada de la galería!", Toast.LENGTH_SHORT)
                        .show()
                    detalleFotoSitio.setImageBitmap(this.FOTO)

                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(context, "¡Fallo Galeria!", Toast.LENGTH_SHORT).show()
                }
            }
        } else if (requestCode == CAMARA) {
            Log.i("FOTO", "Entramos en Camara")
            try {
                if (Build.VERSION.SDK_INT < 28) {
                    this.FOTO =
                        MediaStore.Images.Media.getBitmap(context?.contentResolver, IMAGEN_URI)
                } else {
                    val source: ImageDecoder.Source =
                        ImageDecoder.createSource(context?.contentResolver!!, IMAGEN_URI)
                    this.FOTO = ImageDecoder.decodeBitmap(source)
                }
                Log.i("SitioDetalleFragment", IMAGEN_URI.path.toString())
                Fotos.comprimirImagen(IMAGEN_URI.toFile(), this.FOTO, this.IMG_COMPR_LVL)
                detalleFotoSitio.setImageBitmap(this.FOTO)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.i("SitioDetalleFragment", "Fallo camara")
            }
        }
    }
}