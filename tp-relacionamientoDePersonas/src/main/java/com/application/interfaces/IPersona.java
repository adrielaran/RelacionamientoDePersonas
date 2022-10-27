package com.application.interfaces;

import com.application.entity.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersona extends CrudRepository<Persona, Integer> {
    public Persona findByEmail(String email);

}
