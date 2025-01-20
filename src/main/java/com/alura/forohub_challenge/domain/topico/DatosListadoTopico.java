package com.alura.forohub_challenge.domain.topico;

import com.alura.forohub_challenge.domain.curso.DatosCurso;
import com.alura.forohub_challenge.domain.usuario.DatosListadoUsuario;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Status status,
        DatosListadoUsuario autor,
        DatosCurso curso
) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getId() , topico.getTitulo(),topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), new DatosListadoUsuario(topico.getAutor()), new DatosCurso(topico.getCurso()));
    }
}
