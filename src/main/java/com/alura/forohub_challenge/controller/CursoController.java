package com.alura.forohub_challenge.controller;

import com.alura.forohub_challenge.domain.curso.Curso;
import com.alura.forohub_challenge.domain.curso.CursoDTO;
import com.alura.forohub_challenge.domain.curso.CursoRepository;
import com.alura.forohub_challenge.domain.curso.DatosCurso;
import com.alura.forohub_challenge.domain.topico.DatosListadoTopico;
import com.alura.forohub_challenge.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<DatosCurso> registrarCurso(@RequestBody @Valid CursoDTO cursoDTO,
                                                     UriComponentsBuilder uriComponentsBuilder){
        Curso nuevoCurso = cursoService.crearCurso(cursoDTO);
        nuevoCurso = cursoRepository.save(nuevoCurso);
        DatosCurso datosCurso = new DatosCurso(nuevoCurso);
        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(nuevoCurso.getId()).toUri();
        return ResponseEntity.created(url).body(datosCurso);
    }

    @GetMapping
    public ResponseEntity<Page<DatosCurso>> obtenerUsuarios(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(cursoRepository.findAll(paginacion).map(DatosCurso::new));
    }
}
