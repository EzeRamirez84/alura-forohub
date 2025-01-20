package com.alura.forohub_challenge.domain.curso;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    boolean existsByNombre(@NotNull String nombre);
}
