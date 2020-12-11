package com.andresivan.turistadroid.entidades.usuario;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0017\b\u0016\u0018\u00002\u00020\u0001B/\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\bBA\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\nJ\b\u0010\u0019\u001a\u00020\u0003H\u0016R\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000eR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001e\u0010\t\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u000eR\u001e\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lcom/andresivan/turistadroid/entidades/usuario/Usuario;", "Lio/realm/RealmObject;", "correo", "", "contrasena", "nombre", "fotoUsuario", "cuentaTwitter", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "id", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getContrasena", "()Ljava/lang/String;", "setContrasena", "(Ljava/lang/String;)V", "getCorreo", "setCorreo", "getCuentaTwitter", "setCuentaTwitter", "getFotoUsuario", "setFotoUsuario", "getId", "setId", "getNombre", "setNombre", "toString", "app_debug"})
public class Usuario extends io.realm.RealmObject {
    @org.jetbrains.annotations.NotNull()
    @io.realm.annotations.PrimaryKey()
    private java.lang.String id;
    @org.jetbrains.annotations.NotNull()
    @io.realm.annotations.Required()
    private java.lang.String correo;
    @org.jetbrains.annotations.NotNull()
    @io.realm.annotations.Required()
    private java.lang.String contrasena;
    @org.jetbrains.annotations.NotNull()
    @io.realm.annotations.Required()
    private java.lang.String nombre;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String fotoUsuario;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String cuentaTwitter;
    
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
    public final java.lang.String getCorreo() {
        return null;
    }
    
    public final void setCorreo(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getContrasena() {
        return null;
    }
    
    public final void setContrasena(@org.jetbrains.annotations.NotNull()
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
    public final java.lang.String getFotoUsuario() {
        return null;
    }
    
    public final void setFotoUsuario(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCuentaTwitter() {
        return null;
    }
    
    public final void setCuentaTwitter(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public Usuario(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String correo, @org.jetbrains.annotations.NotNull()
    java.lang.String contrasena, @org.jetbrains.annotations.NotNull()
    java.lang.String nombre, @org.jetbrains.annotations.NotNull()
    java.lang.String fotoUsuario, @org.jetbrains.annotations.NotNull()
    java.lang.String cuentaTwitter) {
        super();
    }
    
    public Usuario() {
        super();
    }
    
    public Usuario(@org.jetbrains.annotations.NotNull()
    java.lang.String correo, @org.jetbrains.annotations.NotNull()
    java.lang.String contrasena, @org.jetbrains.annotations.NotNull()
    java.lang.String nombre, @org.jetbrains.annotations.NotNull()
    java.lang.String fotoUsuario, @org.jetbrains.annotations.NotNull()
    java.lang.String cuentaTwitter) {
        super();
    }
}