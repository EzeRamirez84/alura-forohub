package com.alura.forohub_challenge.service;

import com.alura.forohub_challenge.domain.ValidationException;
import com.alura.forohub_challenge.domain.curso.Curso;
import com.alura.forohub_challenge.domain.curso.CursoDTO;
import com.alura.forohub_challenge.domain.curso.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso crearCurso(CursoDTO cursoDTO){
        if(cursoRepository.existsByNombre(cursoDTO.nombre())){
            throw new ValidationException("El curso " + cursoDTO.nombre() + " ya existe");
        }
        return new Curso(cursoDTO);
    }
}
