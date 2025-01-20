package com.alura.forohub_challenge.service;

import com.alura.forohub_challenge.domain.ValidationException;
import com.alura.forohub_challenge.domain.curso.Curso;
import com.alura.forohub_challenge.domain.curso.CursoRepository;
import com.alura.forohub_challenge.domain.topico.*;
import com.alura.forohub_challenge.domain.usuario.Usuario;
import com.alura.forohub_challenge.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Topico crearTopico(TopicoDTO topicoDTO){
        //verificar la existencia del usuario y el curso
        /*Optional<Usuario> autor = usuarioRepository.findById(topicoDTO.idAutor());
        if(autor.isEmpty()){
            throw new RuntimeException("No se encontro el autor con id " + topicoDTO.idAutor());
        }*/

        Usuario autor = usuarioRepository.findById(topicoDTO.idAutor()).orElseGet(
                ()->{throw new ValidationException("No se encontro el autor con id " + topicoDTO.idAutor());});

        Optional<Curso> curso = cursoRepository.findById(topicoDTO.idCurso());
        if(curso.isEmpty()){
            throw new RuntimeException("No se encontro el curso con id " + topicoDTO.idAutor());
        }

        if(topicoRepository.existsByTituloAndMensaje(topicoDTO.titulo(), topicoDTO.mensaje())){
            throw new ValidationException("Ya existe un topico con el mismo mensaje y titulo");
        }

        var topico = new Topico(null, topicoDTO.titulo(), topicoDTO.mensaje(),
                LocalDateTime.now(), Status.fromString("Abierto"), autor, curso.get(), new ArrayList<>());
        return topico;
    }

    @Transactional
    public DatosListadoTopico actualizarTopico(Long id , TopicoUpdate topicoUpdate) {
        Topico topico = topicoRepository.findById(id).orElseGet(()-> {throw new ValidationException("No existe el topico");});
        //actualizar topico, por ahora solo actualiza el titulo y el mensaje
        topico.setTitulo(topicoUpdate.titulo());
        topico.setMensaje(topicoUpdate.mensaje());

        topicoRepository.save(topico);
        return new DatosListadoTopico(topico);
    }
}
