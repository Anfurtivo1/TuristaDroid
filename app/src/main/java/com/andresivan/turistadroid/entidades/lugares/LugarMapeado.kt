package com.andresivan.turistadroid.entidades.lugares

object LugarMapeado {

    fun fromDTO(items: List<LugarDTO>): List<Lugar> {
        return items.map { fromDTO(it) }
    }

    fun toDTO(items: List<Lugar>): List<LugarDTO> {
        return items.map { toDTO(it) }
    }

    /**
     * DTO a Modelo
     * @param dto LugarDTO
     * @return Lugar
     */
    fun fromDTO(dto: LugarDTO): Lugar {
        return Lugar(
            dto.id,
            dto.nombre,
            dto.tipo,
            dto.fecha,
            dto.latitud,
            dto.longitud,
            dto.imgID,
            dto.valoracion,
            dto.fav,
            dto.usuarioID
        )
    }

    /**
     * Modelo a DTO
     * @param model Lugar
     * @return LugarDTO
     */
    fun toDTO(lugar: Lugar): LugarDTO {
        return LugarDTO(
            lugar.id,
            lugar.nombre,
            lugar.tipo,
            lugar.fecha,
            lugar.latitud,
            lugar.longitud,
            lugar.imgID,
            lugar.valoracion,
            lugar.fav,
            lugar.usuarioID
        )
    }

}