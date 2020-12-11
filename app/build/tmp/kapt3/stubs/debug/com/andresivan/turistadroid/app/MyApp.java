package com.andresivan.turistadroid.app;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\u000eH\u0002J\b\u0010\u0010\u001a\u00020\u000eH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/andresivan/turistadroid/app/MyApp;", "Landroid/app/Application;", "()V", "PERMISOS", "", "getPERMISOS", "()Z", "setPERMISOS", "(Z)V", "nombreBD", "", "versionBD", "", "initPermisos", "", "initRealmBD", "onCreate", "app_debug"})
public final class MyApp extends android.app.Application {
    private final java.lang.String nombreBD = "ANDRESIVAN_MIS_LUGARES";
    private final long versionBD = 1L;
    private boolean PERMISOS = false;
    
    public final boolean getPERMISOS() {
        return false;
    }
    
    public final void setPERMISOS(boolean p0) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    private final void initRealmBD() {
    }
    
    public final void initPermisos() {
    }
    
    public MyApp() {
        super();
    }
}