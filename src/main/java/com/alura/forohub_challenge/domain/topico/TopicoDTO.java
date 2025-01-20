package com.alura.forohub_challenge.domain.topico;

import jakarta.validation.constraints.NotNull;

public record TopicoDTO(
        @NotNull
        Long idAutor,
        @NotNull
        Long idCurso,
        @NotNull
        String titulo,
        @NotNull
        String mensaje
) {
}
