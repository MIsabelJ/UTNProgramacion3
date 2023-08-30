package com.utn.ejer02.repositories;

import com.utn.ejer02.entities.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
}
