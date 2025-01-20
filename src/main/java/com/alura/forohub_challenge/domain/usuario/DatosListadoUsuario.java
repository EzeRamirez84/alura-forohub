package com.alura.forohub_challenge.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DatosListadoUsuario(@NotNull Long id, String nombre, String correo) {
    public DatosListadoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getCorreo());
    }
}
