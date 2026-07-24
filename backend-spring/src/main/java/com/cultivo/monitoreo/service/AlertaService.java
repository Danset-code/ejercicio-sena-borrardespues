package com.cultivo.monitoreo.service;
import com.cultivo.monitoreo.model.Alerta; import com.cultivo.monitoreo.repository.AlertaRepository; import org.springframework.stereotype.Service; import java.util.List;
@Service public class AlertaService {
private final AlertaRepository repository;
public AlertaService(AlertaRepository repository){this.repository=repository;}
public List<Alerta> findAll(){return repository.findAll();}
public Alerta findById(Long id){return repository.findById(id).orElseThrow();}
public Alerta save(Alerta item){return repository.save(item);}
public Alerta update(Long id,Alerta item){item.setId(id);return repository.save(item);}
public void delete(Long id){repository.deleteById(id);}
}