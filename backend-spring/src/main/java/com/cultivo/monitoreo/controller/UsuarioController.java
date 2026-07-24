package com.cultivo.monitoreo.controller;

import com.cultivo.monitoreo.model.Usuario;
import com.cultivo.monitoreo.service.UsuarioService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Usuario> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Usuario one(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Usuario create(@RequestBody Usuario item) {
        return service.save(item);
    }

    @PutMapping("/{id}")
    public Usuario update(@PathVariable Long id, @RequestBody Usuario item) {
        return service.update(id, item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
