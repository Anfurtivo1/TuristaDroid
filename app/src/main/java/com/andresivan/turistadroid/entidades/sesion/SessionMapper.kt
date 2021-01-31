package com.andresivan.turistadroid.entidades.sesion

object SessionMapper {

    fun fromDTO(items: List<SesionDTO>): List<Sesion>{
        return items.map { fromDTO(it) }
    }

    fun toDTO(items: List<Sesion>): List<SesionDTO> {
        return items.map { toDTO(it) }
    }

    fun fromDTO(dto: SesionDTO): Sesion {
        return Sesion(
            dto.idUsuarioActivo,
            dto.tiempo_acceso,
            dto.token
        )
    }

    fun toDTO(model: Sesion): SesionDTO {
        return SesionDTO(
            model.idUsuarioActivo,
            model.tiempo_acceso,
            model.token
        )
    }

}