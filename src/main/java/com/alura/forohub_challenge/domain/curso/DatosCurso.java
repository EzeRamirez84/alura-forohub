package com.alura.forohub_challenge.domain.curso;

import jakarta.validation.constraints.NotNull;

public record DatosCurso(
        @NotNull
        Long id,
        String nombre,
        String categoria
) {
        public DatosCurso(Curso curso) {
                this(curso.getId(), curso.getNombre(),String.valueOf(curso.getCategoria()));
        }
}
