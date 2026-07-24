package com.cultivo.monitoreo.controller;

import com.cultivo.monitoreo.model.Sensor;
import com.cultivo.monitoreo.service.SensorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/sensores")
@CrossOrigin(origins = "http://localhost:5173")
public class SensorController {

    private final SensorService service;

    public SensorController(SensorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Sensor> all() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Sensor one(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Sensor create(@RequestBody Sensor item) {
        return service.save(item);
    }

    @PutMapping("/{id}")
    public Sensor update(@PathVariable Long id, @RequestBody Sensor item) {
        return service.update(id, item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
