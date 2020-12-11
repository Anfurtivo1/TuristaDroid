package com.andresivan.turistadroid.entidades.fotos;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\b\u001a\u00020\u0004J\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\nJ\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\r\u00a8\u0006\u000e"}, d2 = {"Lcom/andresivan/turistadroid/entidades/fotos/FotoController;", "", "()V", "delete", "", "foto", "Lcom/andresivan/turistadroid/entidades/fotos/Foto;", "insert", "removeAll", "selectAll", "", "selectById", "id", "", "app_debug"})
public final class FotoController {
    public static final com.andresivan.turistadroid.entidades.fotos.FotoController INSTANCE = null;
    
    /**
     * Función que nos permite obtener todas las fotos de un lugar
     * @return MutableList<Foto>? nos devuelve todas las fotos que haya en la base de datos, la interrogación nos indica
     */
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.andresivan.turistadroid.entidades.fotos.Foto> selectAll() {
        return null;
    }
    
    /**
     * Función que nos permite añadir una fotografia a la base de datos
     * @param foto Foto le pasamos por parámatro un objeto del tipo Foto y este será borrado de la base de datos
     */
    public final void insert(@org.jetbrains.annotations.NotNull()
    com.andresivan.turistadroid.entidades.fotos.Foto foto) {
    }
    
    /**
     * Función que nos permite borrar una fotografía de la base de datos
     * @param foto Foto que le pasamos por parámetro de la cual cogeremos su id y lo utilizaremos para
     * localizarla en la base de datos
     */
    public final void delete(@org.jetbrains.annotations.NotNull()
    com.andresivan.turistadroid.entidades.fotos.Foto foto) {
    }
    
    /**
     * Función que recibe el identificador de una fotografia y que nos permite localizarla en la base de datos
     * @param id String el identificador que utlizamos para buscar la foto
     * @return Foto
     */
    @org.jetbrains.annotations.Nullable()
    public final com.andresivan.turistadroid.entidades.fotos.Foto selectById(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    /**
     * Función que nos permite borrara todos las fotos de la base de datos
     */
    public final void removeAll() {
    }
    
    private FotoController() {
        super();
    }
}