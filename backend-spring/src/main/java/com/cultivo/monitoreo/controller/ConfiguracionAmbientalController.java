package com.cultivo.monitoreo.controller;

import com.cultivo.monitoreo.model.ConfiguracionAmbiental;
import com.cultivo.monitoreo.service.ConfiguracionAmbientalService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/configuracion")
@CrossOrigin(origins = "http://localhost:5173")
public class ConfiguracionAmbientalController {

    private final ConfiguracionAmbientalService service;

    public ConfiguracionAmbientalController(ConfiguracionAmbientalService service) {
        this.service = service;
    }

    @GetMapping
    public List<ConfiguracionAmbiental> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ConfiguracionAmbiental one(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ConfiguracionAmbiental create(@RequestBody ConfiguracionAmbiental item) {
        return service.save(item);
    }

    @PutMapping("/{id}")
    public ConfiguracionAmbiental update(@PathVariable Long id, @RequestBody ConfiguracionAmbiental item) {
        return service.update(id, item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
