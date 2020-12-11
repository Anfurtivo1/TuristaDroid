package com.andresivan.turistadroid.utils;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J&\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007J\u0016\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ \u0010\u0018\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t\u00a8\u0006\u0019"}, d2 = {"Lcom/andresivan/turistadroid/utils/Fotos;", "", "()V", "a\u00f1adirFotoGaleria", "Landroid/net/Uri;", "foto", "nombre", "", "context", "Landroid/content/Context;", "comprimirImagen", "", "fichero", "Ljava/io/File;", "bitmap", "Landroid/graphics/Bitmap;", "compresion", "", "copiarFoto", "path", "crearNombreFoto", "prefijo", "extension", "eliminarFotoGaleria", "salvarFoto", "app_debug"})
public final class Fotos {
    public static final com.andresivan.turistadroid.utils.Fotos INSTANCE = null;
    
    /**
     * Función para obtener el nombre del fichero en base a un prefijo y una extensión
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String crearNombreFoto(@org.jetbrains.annotations.NotNull()
    java.lang.String prefijo, @org.jetbrains.annotations.NotNull()
    java.lang.String extension) {
        return null;
    }
    
    /**
     * Salva un fichero en un directorio
     */
    @org.jetbrains.annotations.Nullable()
    public final java.io.File salvarFoto(@org.jetbrains.annotations.NotNull()
    java.lang.String path, @org.jetbrains.annotations.NotNull()
    java.lang.String nombre, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    /**
     * Copia un bitmap en un path determinado
     */
    public final void copiarFoto(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, @org.jetbrains.annotations.NotNull()
    java.lang.String path, int compresion, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    /**
     * Comprime una imagen
     */
    public final void comprimirImagen(@org.jetbrains.annotations.NotNull()
    java.io.File fichero, @org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, int compresion) {
    }
    
    /**
     * Añade una imagen a la galería
     */
    @org.jetbrains.annotations.Nullable()
    public final android.net.Uri añadirFotoGaleria(@org.jetbrains.annotations.NotNull()
    android.net.Uri foto, @org.jetbrains.annotations.NotNull()
    java.lang.String nombre, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    /**
     * Elimina una imagen de la galería
     */
    public final void eliminarFotoGaleria(@org.jetbrains.annotations.NotNull()
    java.lang.String nombre, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    private Fotos() {
        super();
    }
}