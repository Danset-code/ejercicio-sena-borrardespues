package com.cultivo.monitoreo.controller;

import com.cultivo.monitoreo.model.Actuador;
import com.cultivo.monitoreo.service.ActuadorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/actuadores")
@CrossOrigin(origins = "http://localhost:5173")
public class ActuadorController {

    private final ActuadorService service;

    public ActuadorController(ActuadorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Actuador> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Actuador one(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Actuador create(@RequestBody Actuador item) {
        return service.save(item);
    }

    @PutMapping("/{id}")
    public Actuador update(@PathVariable Long id, @RequestBody Actuador item) {
        return service.update(id, item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
