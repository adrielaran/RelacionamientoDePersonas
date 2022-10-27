package com.application.dto;

import com.application.entity.Persona;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.application.entity.Rol.Usuario;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PersonaMapper {

    public static PersonaResponseDTO getInstance(Persona persona) {
        System.out.println("Persona a analizar: " + persona.getEmail());
        PersonaResponseDTO dto = PersonaResponseDTO.builder()
                .id(persona.getId())
                .nombre(persona.getNombre())
                .email(persona.getEmail())
                .telefono(persona.getTelefono())
                .ciudad(persona.getCiudad())
                .nacimiento(persona.getNacimiento())
                .localidad(persona.getLocalidad())
                .rol(Usuario)
                .build();
        return dto;
    }
    public static List<PersonaResponseDTO> getInstance(List<Persona> personas) {
        return personas.stream().map(PersonaMapper::getInstance).collect(Collectors.toList());
    }

}
