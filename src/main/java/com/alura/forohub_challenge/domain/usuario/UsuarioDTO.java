package com.alura.forohub_challenge.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(

        @NotNull
        String nombre,
        @Email
        @NotNull
        String correo,
        @NotNull
        String contrasena
) {
}
