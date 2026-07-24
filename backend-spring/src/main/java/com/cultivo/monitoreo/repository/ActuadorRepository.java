package com.cultivo.monitoreo.repository;
import com.cultivo.monitoreo.model.Actuador; import org.springframework.data.jpa.repository.JpaRepository;
public interface ActuadorRepository extends JpaRepository<Actuador,Long>{}