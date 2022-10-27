package com.application.interfaceServices;

import com.application.entity.Persona;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface IPersonaService {
    public List<Persona>listar();
    public Persona listarId(String id);
    public Persona save(Persona persona);
    public void delete(String id);
}

