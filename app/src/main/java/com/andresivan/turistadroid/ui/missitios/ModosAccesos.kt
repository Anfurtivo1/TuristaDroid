package com.andresivan.turistadroid.ui.missitios

/**
 * Esta forma de acceeder al fragment es idea del proyecto que subiste, ya que nos estabamos liando
 * mucho con las llamadas a los fragments.
 *
 * Pero la idea de esto es que según el item que pulsemos, o botón, o deslicemos, haremos una
 * llamada al fragment de SitiosDetalleFragment de una forma u otra
 *
 */
enum class ModosAccesos {
    /*
    si pulsamos sobre el FloatActionButton de insertar un lugar, llamamos al fragment en modo
    insertar, y visualizaremos los componentes necesarios para insertar un nuevo registro
     */
    INSERTAR,
    /*
    si deslizamos a la derecha, eliminamos el registro
     */
    ELIMINAR,
    /*
    si deslizamos a la izquierda, llamamos al fragment en modo modificar, y visualizaremos los
    componentes necesarios para modificar un registro existente
     */
    ACTUALIZAR,
    /*
    si pulsamos sobre cualquiera de las tarjetas de los registros del Rcycler View, que contiene el
    CardView, llamamos al fragment en modo consultar, y ocultaremos ciertos componentes, para no
    poder ni añadir ni modificar
     */
    VISUALIZAR
}