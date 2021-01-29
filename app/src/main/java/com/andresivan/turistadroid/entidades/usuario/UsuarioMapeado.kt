package com.andresivan.turistadroid.entidades.usuario

object UsuarioMapeado {

    fun fromDTO(items: List<UsuarioDTO>): List<Usuario> {
        return items.map { fromDTO(it) }
    }

    fun toDTO(items: List<Usuario>): List<UsuarioDTO> {
        return items.map { toDTO(it) }
    }

    fun fromDTO(dto: UsuarioDTO): Usuario {
        return Usuario(
            dto.id,
            dto.correo,
            dto.contrasena,
            dto.nombre,
            dto.fotoUsuario,
            dto.cuentaTwitter
        )
    }

    fun toDTO(usuario: Usuario): UsuarioDTO {
        return UsuarioDTO(
            usuario.id,
            usuario.correo,
            usuario.contrasena,
            usuario.nombre,
            usuario.fotoUsuario,
            usuario.cuentaTwitter
        )
    }

}