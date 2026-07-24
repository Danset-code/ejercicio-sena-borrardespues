package com.cultivo.monitoreo.repository;
import com.cultivo.monitoreo.model.Usuario; import org.springframework.data.jpa.repository.JpaRepository;
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{}