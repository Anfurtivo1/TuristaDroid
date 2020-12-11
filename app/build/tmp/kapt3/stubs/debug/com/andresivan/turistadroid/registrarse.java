package com.andresivan.turistadroid;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\"\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u0012\u0010\u0017\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u0010H\u0002J\b\u0010\u001b\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/andresivan/turistadroid/registrarse;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "CAMARA", "", "FOTO", "Landroid/graphics/Bitmap;", "GALERIA", "IMAGEN_COMPRES", "IMAGEN_DIR", "", "IMAGEN_NOMBRE", "IMAGEN_PROPORCION", "IMAGEN_URI", "Landroid/net/Uri;", "elegirFotoGaleria", "", "initPermisos", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "registrarUsuario", "tomarFotoCamara", "app_debug"})
public final class registrarse extends androidx.appcompat.app.AppCompatActivity {
    private final int GALERIA = 1;
    private final int CAMARA = 2;
    private java.lang.String IMAGEN_NOMBRE;
    private android.net.Uri IMAGEN_URI;
    private final java.lang.String IMAGEN_DIR = "/TuristaDroid";
    private final int IMAGEN_PROPORCION = 600;
    private android.graphics.Bitmap FOTO;
    private int IMAGEN_COMPRES = 80;
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void tomarFotoCamara() {
    }
    
    /**
     * Comprobamos los permisos de la aplicación
     */
    private final void initPermisos() {
    }
    
    /**
     * Siempre se ejecuta al realizar una acción
     * @param requestCode Int
     * @param resultCode Int
     * @param data Intent?
     */
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    /**
     * Elige una foto de la galeria
     */
    private final void elegirFotoGaleria() {
    }
    
    private final void registrarUsuario() {
    }
    
    public registrarse() {
        super();
    }
}