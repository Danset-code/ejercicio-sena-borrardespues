package com.cultivo.monitoreo.repository;
import com.cultivo.monitoreo.model.Medicion; import org.springframework.data.jpa.repository.JpaRepository;
public interface MedicionRepository extends JpaRepository<Medicion,Long>{}