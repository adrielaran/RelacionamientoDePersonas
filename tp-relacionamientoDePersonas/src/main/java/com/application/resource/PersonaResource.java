package com.application.resource;

import com.application.dto.PersonaMapper;
import com.application.dto.PersonaRequestDTO;
import com.application.dto.PersonaResponseDTO;
import com.application.dto.RequestDTO;
import com.application.entity.Persona;
import com.application.interfaces.IPersona;
import com.application.services.PersonaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/personas")
public class PersonaResource {
    @Autowired
    private PersonaService personaService;
    @Autowired
    private IPersona personaRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PersonaResponseDTO> personas() {
        return PersonaMapper.getInstance((List<Persona>) this.personaRepository.findAll());
    }

   /* @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonaResponseDTO create(@Valid @RequestDTO(PersonaRequestDTO.class) Persona persona) {
        System.out.println("Ingreso");
        return PersonaMapper.getInstance(this.personaService.save(persona));
    } */
   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public PersonaResponseDTO create( @RequestBody Persona persona) {
       System.out.println("Ingreso");
       return PersonaMapper.getInstance(this.personaService.save(persona));
   }
    @PostMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    public List<PersonaResponseDTO> create( @RequestBody List<Persona> personas) {
        System.out.println("Ingreso");
        return PersonaMapper.getInstance(this.personaService.saveAll(personas));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonaResponseDTO search(@PathVariable Integer id) {
        return PersonaMapper.getInstance(this.personaService.findById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonaResponseDTO update(@PathVariable Integer id, @Valid @RequestDTO(PersonaRequestDTO.class) Persona persona) {
        return PersonaMapper.getInstance(this.personaService.update(id, persona));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        this.personaService.deleteById(id);
    }

}