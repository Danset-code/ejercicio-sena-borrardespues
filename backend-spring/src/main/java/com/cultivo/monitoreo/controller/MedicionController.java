package com.cultivo.monitoreo.controller;

import com.cultivo.monitoreo.model.Medicion;
import com.cultivo.monitoreo.service.MedicionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/mediciones")
@CrossOrigin(origins = "http://localhost:5173")
public class MedicionController {

    private final MedicionService service;

    public MedicionController(MedicionService service) {
        this.service = service;
    }

    // Obtener todas
    @GetMapping
    public List<Medicion> all() {
        return service.findAll();
    }

    // Obtener una por ID
    @GetMapping("/{id}")
    public Medicion one(@PathVariable Long id) {
        return service.findById(id);
    }

    // Crear
    @PostMapping
    public Medicion create(@RequestBody Medicion item) {
        return service.save(item);
    }

    // Actualizar
    @PutMapping("/{id}")
    public Medicion update(
            @PathVariable Long id,
            @RequestBody Medicion item) {

        return service.update(id, item);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}