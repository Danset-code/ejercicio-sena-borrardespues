package com.cultivo.monitoreo.controller;

import com.cultivo.monitoreo.model.Alerta;
import com.cultivo.monitoreo.service.AlertaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/alertas")
@CrossOrigin(origins = "http://localhost:5173")
public class AlertaController {

    private final AlertaService service;

    public AlertaController(AlertaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Alerta> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Alerta one(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Alerta create(@RequestBody Alerta item) {
        return service.save(item);
    }

    @PutMapping("/{id}")
    public Alerta update(@PathVariable Long id, @RequestBody Alerta item) {
        return service.update(id, item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
