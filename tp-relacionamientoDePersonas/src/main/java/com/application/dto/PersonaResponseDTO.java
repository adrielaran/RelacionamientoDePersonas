package com.application.dto;

import com.application.entity.Rol;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonaResponseDTO {
    Integer id;
    String nombre;
    String telefono;
    String email;
    String ciudad;
    String nacimiento;
    String localidad;
    Rol rol;
}
