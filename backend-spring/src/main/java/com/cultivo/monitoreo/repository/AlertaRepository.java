package com.cultivo.monitoreo.repository;
import com.cultivo.monitoreo.model.Alerta; import org.springframework.data.jpa.repository.JpaRepository;
public interface AlertaRepository extends JpaRepository<Alerta,Long>{}