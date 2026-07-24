package com.cultivo.monitoreo.service;

import com.cultivo.monitoreo.model.Usuario;
import com.cultivo.monitoreo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Usuario save(Usuario item) {
        return repository.save(item);
    }

    public Usuario update(Long id, Usuario item) {
        item.setId(id);
        return repository.save(item);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
