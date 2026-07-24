package com.cultivo.monitoreo.service;
import com.cultivo.monitoreo.model.ConfiguracionAmbiental; import com.cultivo.monitoreo.repository.ConfiguracionAmbientalRepository; import org.springframework.stereotype.Service; import java.util.List;
@Service public class ConfiguracionAmbientalService {
private final ConfiguracionAmbientalRepository repository;
public ConfiguracionAmbientalService(ConfiguracionAmbientalRepository repository){this.repository=repository;}
public List<ConfiguracionAmbiental> findAll(){return repository.findAll();}
public ConfiguracionAmbiental findById(Long id){return repository.findById(id).orElseThrow();}
public ConfiguracionAmbiental save(ConfiguracionAmbiental item){return repository.save(item);}
public ConfiguracionAmbiental update(Long id,ConfiguracionAmbiental item){item.setId(id);return repository.save(item);}
public void delete(Long id){repository.deleteById(id);}
}