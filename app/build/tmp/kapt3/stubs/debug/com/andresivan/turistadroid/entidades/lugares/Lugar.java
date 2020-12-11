package com.andresivan.turistadroid.entidades.lugares;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b!\b\u0016\u0018\u00002\u00020\u0001BU\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\u0002\u0010\u0010Bo\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\u0002\u0010\u0012J\b\u0010/\u001a\u00020\u0003H\u0016R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\u0011\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0018\"\u0004\b \u0010\u001aR\u001e\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0018\"\u0004\b\"\u0010\u001aR\u001e\u0010\u0007\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0018\"\u0004\b$\u0010\u001aR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0018\"\u0004\b&\u0010\u001aR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010\u0018\"\u0004\b(\u0010\u001aR\u001e\u0010\f\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0018\"\u0004\b*\u0010\u001aR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.\u00a8\u00060"}, d2 = {"Lcom/andresivan/turistadroid/entidades/lugares/Lugar;", "Lio/realm/RealmObject;", "nombre", "", "tipo", "fecha", "latitud", "longitud", "valoracion", "", "favorito", "", "usuarioID", "fotos", "Lio/realm/RealmList;", "Lcom/andresivan/turistadroid/entidades/fotos/Foto;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZLjava/lang/String;Lio/realm/RealmList;)V", "id", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZLjava/lang/String;Lio/realm/RealmList;)V", "getFavorito", "()Z", "setFavorito", "(Z)V", "getFecha", "()Ljava/lang/String;", "setFecha", "(Ljava/lang/String;)V", "getFotos", "()Lio/realm/RealmList;", "setFotos", "(Lio/realm/RealmList;)V", "getId", "setId", "getLatitud", "setLatitud", "getLongitud", "setLongitud", "getNombre", "setNombre", "getTipo", "setTipo", "getUsuarioID", "setUsuarioID", "getValoracion", "()I", "setValoracion", "(I)V", "toString", "app_debug"})
public class Lugar extends io.realm.RealmObject {
    @org.jetbrains.annotations.NotNull()
    @io.realm.annotations.PrimaryKey()
    private java.lang.String id;
    @org.jetbrains.annotations.NotNull()
    @io.realm.annotations.Required()
    private java.lang.String nombre;
    @org.jetbrains.annotations.NotNull()
    @io.realm.annotations.Required()
    private java.lang.String tipo;
    @org.jetbrains.annotations.NotNull()
    @io.realm.annotations.Required()
    private java.lang.String fecha;
    @org.jetbrains.annotations.NotNull()
    @io.realm.annotations.Required()
    private java.lang.String latitud;
    @org.jetbrains.annotations.NotNull()
    @io.realm.annotations.Required()
    private java.lang.String longitud;
    private int valoracion;
    private boolean favorito;
    @org.jetbrains.annotations.NotNull()
    @io.realm.annotations.Required()
    private java.lang.String usuarioID;
    @org.jetbrains.annotations.NotNull()
    private io.realm.RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> fotos;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    public final void setId(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNombre() {
        return null;
    }
    
    public final void setNombre(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTipo() {
        return null;
    }
    
    public final void setTipo(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFecha() {
        return null;
    }
    
    public final void setFecha(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLatitud() {
        return null;
    }
    
    public final void setLatitud(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLongitud() {
        return null;
    }
    
    public final void setLongitud(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getValoracion() {
        return 0;
    }
    
    public final void setValoracion(int p0) {
    }
    
    public final boolean getFavorito() {
        return false;
    }
    
    public final void setFavorito(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUsuarioID() {
        return null;
    }
    
    public final void setUsuarioID(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.realm.RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> getFotos() {
        return null;
    }
    
    public final void setFotos(@org.jetbrains.annotations.NotNull()
    io.realm.RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> p0) {
    }
    
    public Lugar(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String nombre, @org.jetbrains.annotations.NotNull()
    java.lang.String tipo, @org.jetbrains.annotations.NotNull()
    java.lang.String fecha, @org.jetbrains.annotations.NotNull()
    java.lang.String latitud, @org.jetbrains.annotations.NotNull()
    java.lang.String longitud, int valoracion, boolean favorito, @org.jetbrains.annotations.NotNull()
    java.lang.String usuarioID, @org.jetbrains.annotations.NotNull()
    io.realm.RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> fotos) {
        super();
    }
    
    public Lugar() {
        super();
    }
    
    public Lugar(@org.jetbrains.annotations.NotNull()
    java.lang.String nombre, @org.jetbrains.annotations.NotNull()
    java.lang.String tipo, @org.jetbrains.annotations.NotNull()
    java.lang.String fecha, @org.jetbrains.annotations.NotNull()
    java.lang.String latitud, @org.jetbrains.annotations.NotNull()
    java.lang.String longitud, int valoracion, boolean favorito, @org.jetbrains.annotations.NotNull()
    java.lang.String usuarioID, @org.jetbrains.annotations.NotNull()
    io.realm.RealmList<com.andresivan.turistadroid.entidades.fotos.Foto> fotos) {
        super();
    }
}