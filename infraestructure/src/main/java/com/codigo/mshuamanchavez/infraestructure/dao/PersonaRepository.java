package com.codigo.mshuamanchavez.infraestructure.dao;

import com.codigo.mshuamanchavez.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
}
