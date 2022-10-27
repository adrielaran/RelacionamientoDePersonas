package com.application.services;

import com.application.entity.Persona;
import com.application.interfaceServices.IPersonaService;
import com.application.interfaces.IPersona;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonaService implements IPersonaService{
    @Autowired
    private IPersona personaRepository;

    public Persona findById(Integer id) {
        return personaRepository.findById(id).orElseThrow();
    }

    public Persona findByEmail(String id) {
        return personaRepository.findByEmail(id);
    }

    public Persona save(Persona persona) {
        return personaRepository.save(persona);
    }

    public List<Persona> saveAll(List<Persona> personas){ return (List<Persona>) personaRepository.saveAll(personas); }
    @Override
    public void delete(String id) {

    }

    public Persona update(Integer id, Persona persona) {
        Persona aux = this.findById(id);
        BeanUtils.copyProperties(persona, aux, "id", "createdAt", "updatedAt");

        personaRepository.save(aux);

        return aux;
    }

    public void deleteById(Integer id) {
        personaRepository.delete(this.findById(id));
    }

    public List<Persona> listar(){
        return (List<Persona>) personaRepository.findAll();
    }

    @Override
    public Persona listarId(String id) {
        return null;
    }
}
