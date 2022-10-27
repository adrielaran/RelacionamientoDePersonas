package com.application.dto;

import com.application.entity.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaRequestDTO {
    Integer id;
    String nombre;
    String telefono;
    String email;
    String ciudad;
    String nacimiento;
    String localidad;
    Rol rol;
}
