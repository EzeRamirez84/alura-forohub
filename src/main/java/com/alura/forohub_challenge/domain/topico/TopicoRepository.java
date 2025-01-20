package com.alura.forohub_challenge.domain.topico;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensaje(@NotNull String titulo , @NotNull String mensaje);
}
