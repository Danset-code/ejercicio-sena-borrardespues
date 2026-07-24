package com.cultivo.monitoreo.repository;
import com.cultivo.monitoreo.model.Sensor; import org.springframework.data.jpa.repository.JpaRepository;
public interface SensorRepository extends JpaRepository<Sensor,Long>{}