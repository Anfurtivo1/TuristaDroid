package com.andresivan.turistadroid.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ.\u0010\r\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\bJ\u0010\u0010\u0011\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a8\u0006\u0012"}, d2 = {"Lcom/andresivan/turistadroid/utils/Utilidades;", "", "()V", "abrirEnlace", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "enlace", "", "conexionDisponible", "", "context", "Landroid/content/Context;", "escribirCorreo", "destinatario", "remitente", "texto", "gpsDisponible", "app_debug"})
public final class Utilidades {
    
    /**
     * Función que nos permite comprobar si tenemos una conexión disponible
     * @param context Contexto actual de nuestro dispositivo
     */
    public final boolean conexionDisponible(@org.jetbrains.annotations.Nullable()
    android.content.Context context) {
        return false;
    }
    
    /**
     * Función que nos permite comprobar si tenemos el GPS disponible
     * @param context Contexto actual de nuestro dispositivo
     */
    public final boolean gpsDisponible(@org.jetbrains.annotations.Nullable()
    android.content.Context context) {
        return false;
    }
    
    /**
     * Función escribir correo, esta función escribir correo nos abre nuestra aplicación de Gmail, justo para enviar un
     * correo a una persona, esa persona, viene dada por parámetros
     * @param activity Activity en la que estamos en ese momento
     * @param destinatario String con el correo del destinatario de la aplicación
     * @param remitente String con el correo del remitente del mensaje
     * @param texto String con el mensaje que queremos enviar al destinatario
     * En este caso no he puesto cómo parámetro el asunto del correo, para poner directamente TuristaDroid, que es el
     * nombre de nuestra aplicación
     */
    public final void escribirCorreo(@org.jetbrains.annotations.Nullable()
    androidx.fragment.app.FragmentActivity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String destinatario, @org.jetbrains.annotations.NotNull()
    java.lang.String remitente, @org.jetbrains.annotations.NotNull()
    java.lang.String texto) {
    }
    
    /**
     * Función que abre una dirección url o enlace, esto nos puede valer para abrir las url de las redes sociales
     * del usuario
     * @param activity Activity actual en la que nos situamos
     * @param enlace String con el enlace a nuestras redes sociales por ejemplo
     */
    public final void abrirEnlace(@org.jetbrains.annotations.Nullable()
    androidx.fragment.app.FragmentActivity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String enlace) {
    }
    
    public Utilidades() {
        super();
    }
}