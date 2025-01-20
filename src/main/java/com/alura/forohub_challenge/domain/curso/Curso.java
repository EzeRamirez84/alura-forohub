package com.alura.forohub_challenge.domain.curso;

import com.alura.forohub_challenge.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cursos")
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @OneToMany(mappedBy = "curso")
    private List<Topico> topicos;

    public Curso(CursoDTO cursoDTO) {
        this.nombre = cursoDTO.nombre();
        this.categoria = Categoria.fromString(cursoDTO.categoria());
    }
}

