package com.cultivo.monitoreo.service;

import com.cultivo.monitoreo.model.Sensor;
import com.cultivo.monitoreo.repository.SensorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SensorService {

    private final SensorRepository repository;

    public SensorService(SensorRepository repository) {
        this.repository = repository;
    }

    public List<Sensor> findAll() {
        return repository.findAll();
    }

    public Sensor findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Sensor save(Sensor item) {
        return repository.save(item);
    }

    public Sensor update(Long id, Sensor item) {
        item.setId(id);
        return repository.save(item);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
