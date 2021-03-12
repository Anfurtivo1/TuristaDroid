package com.andresivan.turistadroid.utils

import java.security.MessageDigest
import kotlin.experimental.and

object CifradorContrasena {

    fun convertirHash(cadena: String, tipo: String = "SHA-256"): String? {
        var md: MessageDigest? = null
        var hash: ByteArray? = null
        try {
            md = MessageDigest.getInstance(tipo)
            hash = md.digest(cadena.toByteArray(charset("UTF-8")))
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return convertToHex(hash)
    }


    private fun convertToHex(hash: ByteArray?): String? {
        val sbff = StringBuffer()
        for (i in hash!!.indices) {
            sbff.append(((hash[i] and 0xff.toByte()) + 0x100).toString(16).substring(1))
        }
        return sbff.toString()
    }

}