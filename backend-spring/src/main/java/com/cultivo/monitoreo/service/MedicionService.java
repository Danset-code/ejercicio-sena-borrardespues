package com.cultivo.monitoreo.service;

import com.cultivo.monitoreo.model.Medicion;
import com.cultivo.monitoreo.repository.MedicionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicionService {

    private final MedicionRepository repository;

    public MedicionService(MedicionRepository repository) {
        this.repository = repository;
    }

    public List<Medicion> findAll() {
        return repository.findAll();
    }

    public Medicion findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Medicion save(Medicion item) {
        return repository.save(item);
    }

    public Medicion update(Long id, Medicion item) {
        item.setId(id);
        return repository.save(item);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
