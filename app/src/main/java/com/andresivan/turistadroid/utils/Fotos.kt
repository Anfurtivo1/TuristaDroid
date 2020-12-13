package com.andresivan.turistadroid.utils

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.core.net.toFile
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*

object Fotos {
    /**
     * Función para obtener el nombre del fichero en base a un prefijo y una extensión
     */
    fun crearNombreFoto(prefijo: String, extension: String): String {
        return prefijo+"-" + UUID.randomUUID().toString() + extension
    }

    /**
     * Salva un fichero en un directorio
     */
    fun salvarFoto(path: String, nombre: String, context: Context): File? {
        // Directorio publico
        // Almacenamos en nuestro directorio de almacenamiento externo asignado en Pictures
        val dirFotos = File((context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.absolutePath) + path)
        // Solo si queremos crear un directorio y que todo sea público
        //val dirFotos = File(Environment.getExternalStorageDirectory().toString() + path)
        // Si no existe el directorio, lo creamos solo si es publico
        if (!dirFotos.exists()) {
            dirFotos.mkdirs()
        }
        try {
            val f = File(dirFotos, nombre)
            f.createNewFile()
            return f
        } catch (e1: Exception) {
            e1.printStackTrace()
        }
        return null
    }

    /**
     * Copia un bitmap en un path determinado
     */
    fun copiarFoto(bitmap: Bitmap, path: String, compresion: Int, context: Context): File {
        val nombre = crearNombreFoto("camara",".jpg")
        val fichero =
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.absolutePath + path + File.separator + nombre
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, compresion, bytes)
        val fo = FileOutputStream(fichero)
        fo.write(bytes.toByteArray())
        fo.close()
        return File(fichero)
    }

    fun copyPhoto(bitmap: Bitmap, nombre: String, path: String, compresion: Int, context: Context): File {
        val dirFotos =
            File((context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.absolutePath) + path)
        if (!dirFotos.exists()) {
            dirFotos.mkdirs()
        }

        val fichero =
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.absolutePath + path + File.separator + nombre
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, compresion, bytes)
        val fo = FileOutputStream(fichero)
        fo.write(bytes.toByteArray())
        fo.close()
        return File(fichero)
    }
    /**
     * Comprime una imagen
     */
    fun comprimirImagen(fichero: File, bitmap: Bitmap, compresion: Int) {
        // Recuperamos el Bitmap
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, compresion, bytes)
        val fo = FileOutputStream(fichero)
        fo.write(bytes.toByteArray())
        fo.close()
    }

    /**
     * Añade una imagen a la galería
     */
    fun añadirFotoGaleria(foto: Uri, nombre: String, context: Context): Uri? {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "imagen")
        values.put(MediaStore.Images.Media.DISPLAY_NAME, nombre)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")

        return context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

    }
}