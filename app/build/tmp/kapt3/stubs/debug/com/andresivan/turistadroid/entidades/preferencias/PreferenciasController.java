package com.andresivan.turistadroid.entidades.preferencias;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004H\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\tJ.\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006J\u000e\u0010\u0013\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/andresivan/turistadroid/entidades/preferencias/PreferenciasController;", "", "()V", "USER", "Lcom/andresivan/turistadroid/entidades/usuario/Usuario;", "USER_ID", "", "abrirPreferecias", "context", "Landroid/content/Context;", "usuario", "comprobarSesion", "", "crearSesion", "", "correo", "contrasena", "nombreUsuario", "fotoUsuario", "leerSesion", "app_debug"})
public final class PreferenciasController {
    private static java.lang.String USER_ID = "";
    private static com.andresivan.turistadroid.entidades.usuario.Usuario USER;
    public static final com.andresivan.turistadroid.entidades.preferencias.PreferenciasController INSTANCE = null;
    
    public final boolean comprobarSesion(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    /**
     * Función que se encarga de recoger por parámetro los distintos valores pasados por parámetro para crear
     * un usuario en insertarlo en la Base de Datos
     * @param context Context
     */
    public final void crearSesion(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String correo, @org.jetbrains.annotations.NotNull()
    java.lang.String contrasena, @org.jetbrains.annotations.NotNull()
    java.lang.String nombreUsuario, @org.jetbrains.annotations.NotNull()
    java.lang.String fotoUsuario) {
    }
    
    private final com.andresivan.turistadroid.entidades.usuario.Usuario abrirPreferecias(android.content.Context context, com.andresivan.turistadroid.entidades.usuario.Usuario usuario) {
        return null;
    }
    
    /**
     * Leemos la sesion activa
     * @param context Context
     * @return Usuario
     */
    @org.jetbrains.annotations.NotNull()
    public final com.andresivan.turistadroid.entidades.usuario.Usuario leerSesion(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    private PreferenciasController() {
        super();
    }
}