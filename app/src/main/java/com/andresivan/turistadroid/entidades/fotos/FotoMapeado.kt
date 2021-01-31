package com.andresivan.turistadroid.entidades.fotos

object FotoMapeado {

    fun fromDTO(items: List<FotoDTO>): List<Foto> {
        return items.map { fromDTO(it) }
    }

   fun toDTO(items: List<Foto>): List<FotoDTO> {
        return items.map { toDTO(it) }
    }


    /**
     * Realiza la conversión de DTO al objeto Foto
     * @param dto FotoDTO
     * @return Foto
     */
    fun fromDTO(dto: FotoDTO): Foto {
        return Foto(
            dto.id,
            dto.imgLugar,
            dto.uri,
            dto.hash,
            dto.usuarioID
        )
    }

    /**
     * Realiza la conversión del objeto de tipo Foto a DTO
     * @param model Fotografia
     * @return FotografiaDTO
     */
    fun toDTO(foto: Foto): FotoDTO {
        return FotoDTO(
            foto.id,
            foto.imgLugar,
            foto.uri,
            foto.hash,
            foto.usuarioID
        )
    }

}