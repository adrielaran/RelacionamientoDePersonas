package com.application.interfaceServices;

import com.application.entity.Autorizacion;
import com.application.entity.Persona;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface IAutorizacionService {
    public List<Autorizacion>listar();
    public Optional<Autorizacion>listarId(Integer id);
    public Integer save(Autorizacion autorizacion);
    public void delete(Integer id);
}
