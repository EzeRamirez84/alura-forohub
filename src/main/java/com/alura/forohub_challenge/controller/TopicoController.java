package com.alura.forohub_challenge.controller;

import com.alura.forohub_challenge.domain.curso.Curso;
import com.alura.forohub_challenge.domain.curso.CursoRepository;
import com.alura.forohub_challenge.domain.topico.*;
import com.alura.forohub_challenge.domain.usuario.DatosListadoUsuario;
import com.alura.forohub_challenge.domain.usuario.Usuario;
import com.alura.forohub_challenge.domain.usuario.UsuarioRepository;
import com.alura.forohub_challenge.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DatosListadoTopico> registrarTopico(@RequestBody @Valid TopicoDTO topicoDTO,
                                                              UriComponentsBuilder uriComponentsBuilder){
        Topico nuevoTopico = topicoService.crearTopico(topicoDTO);
        nuevoTopico = topicoRepository.save(nuevoTopico);
        DatosListadoTopico datosListadoTopico = new DatosListadoTopico(nuevoTopico);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(nuevoTopico.getId()).toUri();
        return ResponseEntity.created(url).body(datosListadoTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> obtenerUsuarios(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> obtenerTopicoPorId(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        var datosUsuario = new DatosListadoTopico(topico);
        return ResponseEntity.ok(datosUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> actualizarTopico(@PathVariable Long id, @RequestBody TopicoUpdate topicoUpdate){
        DatosListadoTopico datosListadoTopico = topicoService.actualizarTopico(id, topicoUpdate);
        return ResponseEntity.ok(datosListadoTopico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        if(!topicoRepository.existsById(id)){
            throw new RuntimeException("Este id no existe: " + id);
        }else{
            topicoRepository.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }


}
