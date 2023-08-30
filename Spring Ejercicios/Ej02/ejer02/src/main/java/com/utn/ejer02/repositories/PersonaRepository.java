package com.utn.ejer02.repositories;

import com.utn.ejer02.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
