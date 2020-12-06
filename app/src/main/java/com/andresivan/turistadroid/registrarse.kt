package com.andresivan.turistadroid

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.net.toFile
import com.andresivan.turistadroid.entidades.preferencias.PreferenciasController.crearSesion
import com.andresivan.turistadroid.utils.Fotos
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_registrarse.*
import java.io.IOException

class registrarse : AppCompatActivity() {


    // Variables para la camara de fotos
    private val GALERIA = 1
    private val CAMARA = 2
    private lateinit var IMAGEN_NOMBRE: String
    private lateinit var IMAGEN_URI: Uri
    private val IMAGEN_DIR = "/TuristaDroid"
    private val IMAGEN_PROPORCION = 600
    private lateinit var FOTO: Bitmap
    private var IMAGEN_COMPRES = 80

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)

        initPermisos()

        imgCamaraRegistrarse.setOnClickListener { tomarFotoCamara() }
        imgGaleriaRegistrarse.setOnClickListener { elegirFotoGaleria() }
        btnRegistrarse.setOnClickListener{ registrarUsuario() }

    }

    //Llamamos al intent de la camara
    // https://developer.android.com/training/camera/photobasics.html#TaskPath
    private fun tomarFotoCamara() {
        // Si queremos hacer uso de fotos en alta calidad
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        // Eso para alta o baja
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Nombre de la imagen
        IMAGEN_NOMBRE = Fotos.crearNombreFoto("camara",".jpg")
        // Salvamos el fichero
        val fichero = Fotos.salvarFoto(IMAGEN_DIR, IMAGEN_NOMBRE, this)
        IMAGEN_URI = Uri.fromFile(fichero)

        intent.putExtra(MediaStore.EXTRA_OUTPUT, IMAGEN_URI)
        // Esto para alta y baja
        startActivityForResult(intent, CAMARA)
    }




    /**
     * Comprobamos los permisos de la aplicación
     */
    private fun initPermisos() {
        // Indicamos el permisos y el manejador de eventos de los mismos
        Dexter.withContext(this)
            // Lista de permisos a comprobar
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
            // Listener a ejecutar
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    // ccomprbamos si tenemos los permisos de todos ellos
                    if (report.areAllPermissionsGranted()) {
                        Toast.makeText(applicationContext, "¡Todos los permisos concedidos!", Toast.LENGTH_SHORT).show()
                    }

                    // comprobamos si hay un permiso que no tenemos concedido ya sea temporal o permanentemente
                    if (report.isAnyPermissionPermanentlyDenied) {
                        // abrimos un diálogo a los permisos
                        //openSettingsDialog();
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).withErrorListener { Toast.makeText(applicationContext, "Existe errores! ", Toast.LENGTH_SHORT).show() }
            .onSameThread()
            .check()
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
                        this.FOTO = MediaStore.Images.Media.getBitmap(this?.contentResolver, contentURI);
                    } else {
                        val source: ImageDecoder.Source = ImageDecoder.createSource(this?.contentResolver!!, contentURI)
                        this.FOTO = ImageDecoder.decodeBitmap(source)
                    }
                    // Para jugar con las proporciones y ahorrar en memoria no cargando toda la foto, solo carga 600px max
                    val prop = this.IMAGEN_PROPORCION / this.FOTO.width.toFloat()
                    // Actualizamos el bitmap para ese tamaño, luego podríamos reducir su calidad
                    this.FOTO = Bitmap.createScaledBitmap(this.FOTO, this.IMAGEN_PROPORCION, (this.FOTO.height * prop).toInt(), false)
                    Toast.makeText(this, "¡Foto rescatada de la galería!", Toast.LENGTH_SHORT).show()
                    imgUsuario.setImageBitmap(this.FOTO)
                    // Vamos a copiar nuestra imagen en nuestro directorio
                    // Utilidades.copiarImagen(bitmap, IMAGEN_DIR, IMAGEN_COMPRES, applicationContext)
                } catch (e: IOException) {
                    e.printStackTrace()
                    Toast.makeText(this, "¡Fallo Galeria!", Toast.LENGTH_SHORT).show()
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
                    this.FOTO = MediaStore.Images.Media.getBitmap(this?.contentResolver, IMAGEN_URI)
                } else {
                    val source: ImageDecoder.Source = ImageDecoder.createSource(this?.contentResolver!!, IMAGEN_URI)
                    this.FOTO = ImageDecoder.decodeBitmap(source)
                }

                // Vamos a probar a comprimir
                Fotos.comprimirImagen(IMAGEN_URI.toFile(), this.FOTO, this.IMAGEN_COMPRES)

                // Si estamos en módo publico la añadimos en la biblioteca
                // if (PUBLICO) {
                // Por su queemos guardar el URI con la que se almacena en la Mediastore
                IMAGEN_URI = Fotos.añadirFotoGaleria(IMAGEN_URI, IMAGEN_NOMBRE, this)!!
                // }

                // Mostramos
                imgUsuario.setImageBitmap(this.FOTO)
                Toast.makeText(this, "¡Foto Salvada!", Toast.LENGTH_SHORT).show()
            //} catch (e: Exception) {
                //e.printStackTrace()
                //Toast.makeText(this, "¡Fallo Camara!", Toast.LENGTH_SHORT).show()
            //}
        }
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

    private fun registrarUsuario() {
        var correo = registrarse_et_correo.text.toString()
        var contrasena = registrarse_et_contrasena.text.toString()
        var nombreUsuario = registrarse_et_nombreusu.text.toString()

        if (correo == "" || contrasena == "" || nombreUsuario == ""){
            Toast.makeText(this, "RELLENE TODOS LOS CAMPOS CAMPOS SOLICITADOS PARA REGISTRARSE", Toast.LENGTH_SHORT)
        }else{
            crearSesion(this,  correo, contrasena, nombreUsuario, IMAGEN_NOMBRE)
        }

    }

}