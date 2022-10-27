package com.application.interfaces;
import com.application.entity.Autorizacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAutorizacion extends CrudRepository<Autorizacion, Integer> {
}