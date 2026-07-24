package com.cultivo.monitoreo.service;
import com.cultivo.monitoreo.model.Actuador; import com.cultivo.monitoreo.repository.ActuadorRepository; import org.springframework.stereotype.Service; import java.util.List;
@Service public class ActuadorService {
private final ActuadorRepository repository;
public ActuadorService(ActuadorRepository repository){this.repository=repository;}
public List<Actuador> findAll(){return repository.findAll();}
public Actuador findById(Long id){return repository.findById(id).orElseThrow();}
public Actuador save(Actuador item){return repository.save(item);}
public Actuador update(Long id,Actuador item){item.setId(id);return repository.save(item);}
public void delete(Long id){repository.deleteById(id);}
}