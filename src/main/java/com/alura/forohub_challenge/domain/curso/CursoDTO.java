package com.alura.forohub_challenge.domain.curso;

import jakarta.validation.constraints.NotNull;

public record CursoDTO(
        @NotNull
        String nombre,
        @NotNull
        String categoria
) {
}
