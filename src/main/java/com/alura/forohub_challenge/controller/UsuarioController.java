package com.alura.forohub_challenge.controller;


import com.alura.forohub_challenge.domain.usuario.DatosListadoUsuario;
import com.alura.forohub_challenge.domain.usuario.Usuario;
import com.alura.forohub_challenge.domain.usuario.UsuarioDTO;
import com.alura.forohub_challenge.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public void registrarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO){
        String password = passwordEncoder.encode(usuarioDTO.contrasena());
        Usuario nuevoUsuario = new Usuario(null, usuarioDTO.nombre(), usuarioDTO.correo(), password);
        usuarioRepository.save(nuevoUsuario);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> obtenerUsuarios(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(usuarioRepository.findAll(paginacion).map(DatosListadoUsuario::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoUsuario> obtenerUsuarioPorId(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        var datosUsuario = new DatosListadoUsuario(usuario.getId(), usuario.getNombre(), usuario.getCorreo());
        return ResponseEntity.ok(datosUsuario);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        if(!usuarioRepository.existsById(id)){
            throw new RuntimeException("Este id no existe");
        }else{
            usuarioRepository.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }
}
