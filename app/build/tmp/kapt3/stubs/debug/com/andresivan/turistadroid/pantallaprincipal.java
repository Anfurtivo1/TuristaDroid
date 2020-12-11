package com.andresivan.turistadroid;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\u0012\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\u0010\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\f2\u0006\u0010%\u001a\u00020&H\u0016J\b\u0010\'\u001a\u00020\fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R \u0010\u0013\u001a\b\u0018\u00010\u0014R\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u000e\"\u0004\b\u001b\u0010\u0010\u00a8\u0006("}, d2 = {"Lcom/andresivan/turistadroid/pantallaprincipal;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "appBarConfiguration", "Landroidx/navigation/ui/AppBarConfiguration;", "camera", "Landroid/hardware/Camera;", "getCamera", "()Landroid/hardware/Camera;", "setCamera", "(Landroid/hardware/Camera;)V", "estado", "", "getEstado", "()Z", "setEstado", "(Z)V", "isFlash", "setFlash", "parameters", "Landroid/hardware/Camera$Parameters;", "getParameters", "()Landroid/hardware/Camera$Parameters;", "setParameters", "(Landroid/hardware/Camera$Parameters;)V", "permisos", "getPermisos", "setPermisos", "encenderLinterna", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onSupportNavigateUp", "app_debug"})
public final class pantallaprincipal extends androidx.appcompat.app.AppCompatActivity {
    private androidx.navigation.ui.AppBarConfiguration appBarConfiguration;
    @org.jetbrains.annotations.NotNull()
    public android.hardware.Camera camera;
    @org.jetbrains.annotations.Nullable()
    private android.hardware.Camera.Parameters parameters;
    private boolean isFlash = false;
    private boolean estado = true;
    private boolean permisos = false;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final android.hardware.Camera getCamera() {
        return null;
    }
    
    public final void setCamera(@org.jetbrains.annotations.NotNull()
    android.hardware.Camera p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.hardware.Camera.Parameters getParameters() {
        return null;
    }
    
    public final void setParameters(@org.jetbrains.annotations.Nullable()
    android.hardware.Camera.Parameters p0) {
    }
    
    public final boolean isFlash() {
        return false;
    }
    
    public final void setFlash(boolean p0) {
    }
    
    public final boolean getEstado() {
        return false;
    }
    
    public final void setEstado(boolean p0) {
    }
    
    public final boolean getPermisos() {
        return false;
    }
    
    public final void setPermisos(boolean p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public boolean onCreateOptionsMenu(@org.jetbrains.annotations.NotNull()
    android.view.Menu menu) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull()
    android.view.MenuItem item) {
        return false;
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    private final void encenderLinterna() {
    }
    
    public pantallaprincipal() {
        super();
    }
}