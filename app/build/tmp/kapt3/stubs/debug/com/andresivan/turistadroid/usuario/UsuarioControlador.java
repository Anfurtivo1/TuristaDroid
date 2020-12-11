package com.andresivan.turistadroid.usuario;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\f\u001a\u00020\u0004J\u0010\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0012"}, d2 = {"Lcom/andresivan/turistadroid/usuario/UsuarioControlador;", "", "()V", "delete", "", "usuario", "Lcom/andresivan/turistadroid/entidades/usuario/Usuario;", "existeUsuario", "", "correo", "", "insert", "removeAll", "selectByCorreo", "selectById", "id", "", "update", "app_debug"})
public final class UsuarioControlador {
    public static final com.andresivan.turistadroid.usuario.UsuarioControlador INSTANCE = null;
    
    /**
     * Función para insertar un nuevo usuario en la Base de datos de Realm
     * @param usuario Un objeto del tipo Usuario, con su id, nombre, etc...
     */
    public final void insert(@org.jetbrains.annotations.NotNull()
    com.andresivan.turistadroid.entidades.usuario.Usuario usuario) {
    }
    
    /**
     * Función que nos permite borrar un usuario de la base de datos
     * @param usuario Un objeto del tipo usuario, en este, usaremos su id para localizar el usuario que queremos borrar
     */
    public final void delete(@org.jetbrains.annotations.NotNull()
    com.andresivan.turistadroid.entidades.usuario.Usuario usuario) {
    }
    
    /**
     * Función que nos permite modificar un usuario.
     * @param usuario el usuario que queremos actualizar
     */
    public final void update(@org.jetbrains.annotations.NotNull()
    com.andresivan.turistadroid.entidades.usuario.Usuario usuario) {
    }
    
    /**
     * Función que nos permite buscar un usuario en nuestra base de datos, está función la podemos usar para iniciar
     * sesión o antes de registrarnos para comprobar si ya existe algún usuario con ese valor
     * @param correo String
     * @return usuario Puede que devuelva algún usuario o no por eso en el tipo de valor que devuelve ponemos Usuario?
     */
    @org.jetbrains.annotations.Nullable()
    public final com.andresivan.turistadroid.entidades.usuario.Usuario selectByCorreo(@org.jetbrains.annotations.NotNull()
    java.lang.String correo) {
        return null;
    }
    
    public final boolean existeUsuario(@org.jetbrains.annotations.NotNull()
    java.lang.String correo) {
        return false;
    }
    
    /**
     * Función que nos permite buscar un usuario en nuestra base de datos, está función es igual que la anterior, pero
     * esta vez, buscamos por el id del usuario
     * @param id String
     * @return usuario Puede que devuelva algún usuario o no por eso en el tipo de valor que devuelve ponemos Usuario?
     */
    @org.jetbrains.annotations.Nullable()
    public final com.andresivan.turistadroid.entidades.usuario.Usuario selectById(long id) {
        return null;
    }
    
    /**
     * Función que nos permite borrar todos los usuarios de la base de datos
     * Esta función no recibe parámetro como las anteriores, debido a que no nos hace falta ya que queremos borrar
     * a todos los usuarios que existan
     */
    public final void removeAll() {
    }
    
    private UsuarioControlador() {
        super();
    }
}