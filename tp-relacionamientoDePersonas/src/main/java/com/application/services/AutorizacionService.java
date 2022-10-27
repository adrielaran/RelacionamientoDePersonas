package com.application.services;

import com.application.entity.Autorizacion;
import com.application.interfaceServices.IAutorizacionService;
import com.application.interfaces.IAutorizacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AutorizacionService implements IAutorizacionService {
    @Autowired
    private IAutorizacion autorizacionRepository;

    @Override
    public List<Autorizacion> listar() {
        return (List<Autorizacion>)autorizacionRepository.findAll();
    }

    @Override
    public Optional<Autorizacion> listarId(Integer id) {
        return Optional.empty();
    }

    @Override
    public Integer save(Autorizacion autorizacion) {
        autorizacionRepository.save(autorizacion);
        return null;
    }

    @Override
    public void delete(Integer id) {
    }

    public Optional<Autorizacion> findById(Integer id) {
        return this.autorizacionRepository.findById(id);
    }

}
