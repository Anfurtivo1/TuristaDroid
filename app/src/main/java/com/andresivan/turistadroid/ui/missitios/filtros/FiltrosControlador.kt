package com.andresivan.turistadroid.ui.missitios.Filtross

import com.andresivan.turistadroid.ui.missitios.filtros.Filtros

object FiltrosControlador {

    /**
     * Función que elige un valor de la lista de enumerados, según la posicion en la lista de opciones
     * @param posicionFiltros Int indicando la posicion del spinner
     * @return Filtross un atributo del objeto de Filtross
     */
    fun indiceFiltrosSpinner(posicionFiltros: Int): Filtros{
        return when (posicionFiltros) {
            0 -> Filtros.NADA
            1 -> Filtros.NOMBRE_ASCENDENTE
            2 -> Filtros.NOMBRE_DESCENDENTE
            3 -> Filtros.FECHA_ASCENDENTE
            4 -> Filtros.FECHA_DESCENDENTE
            5 -> Filtros.VALORACION_ASCENDENTE
            6 -> Filtros.VALORACION_DESCENDENTE
            else -> Filtros.NADA // si no se selecciona ningún Filtros por defecto nada
        }
    }

}