package com.application.controller;

import com.application.entity.Autorizacion;
import com.application.entity.Persona;
import com.application.services.AutorizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.application.services.PersonaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.application.entity.Estado.ACEPTADA;
import static com.application.entity.Estado.ESPERA;
import static com.application.entity.Rol.Administrador;
import static com.application.entity.Rol.Usuario;

@Controller
@RequestMapping
public class controlador {
    @Autowired
    private PersonaService service;

    @Autowired
    private AutorizacionService autorizacionService;
    @GetMapping("/inicioUser")
    public String listar(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Persona personaIngresada = service.findByEmail(auth.getName());
        if(personaIngresada.getRol()==Administrador){
            return "redirect:/inicioAdmin";
        }
        service.save(personaIngresada);
        List<Persona> personas = new ArrayList<>();
        List<Autorizacion> autorizaciones = new ArrayList<>();
        for(Autorizacion autorizacion : autorizacionService.listar()) {
            if ((autorizacion.getAutorizante().getId() != personaIngresada.getId()) && (autorizacion.getEstado()==ESPERA)) {
                autorizaciones.add(autorizacion);
            }
        }
        for(Persona persona: service.listar()) {
            if (persona.getId() != personaIngresada.getId()) {
                personas.add(persona);
            }
        }
        System.out.println(service.listar());
        model.addAttribute("personas", personas);
        model.addAttribute("persona", personaIngresada); //Usuario Actual
        model.addAttribute("personaActualizada", new Persona()); //Objeto para actualizar el usuario
        model.addAttribute("personaAutorizada", new Persona()); //Objeto para autorizar el usuario
        model.addAttribute("autorizaciones", autorizaciones);
        model.addAttribute("aceptar", new Autorizacion());  //Objeto para aceptar la autorizacion
        return "inicioUser";
    }

    @GetMapping("/inicioAdmin")
    private String reporte(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Persona personaIngresada = service.findByEmail(auth.getName());
        model.addAttribute("personas", service.listar());
        model.addAttribute("usuarioActual",personaIngresada);
        model.addAttribute("autorizaciones", autorizacionService.listar());
        return "inicioAdmin";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") Persona user, BindingResult bindingResult){
        System.out.println("PRUEBA: " + user.getEmail());

        return "redirect:/inicioUser";
    }

    @GetMapping("/inicio")
    public String inicio() {
        return "redirect:/inicioUser";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizar(@PathVariable Integer id, @ModelAttribute("personaActualizada") Persona personaActualizada, BindingResult bindingResult) {
        Persona exitsPersona = service.findById(id);
        System.out.println("Persona Existente: " + exitsPersona);
        System.out.println("Actualizado [ID]: " + id);
        if (exitsPersona != null) {
            if(personaActualizada.getNombre()==null) personaActualizada.setNombre(exitsPersona.getNombre());
            if(personaActualizada.getTelefono()==null) personaActualizada.setTelefono(exitsPersona.getTelefono());
            if(personaActualizada.getCiudad()==null) personaActualizada.setCiudad(exitsPersona.getCiudad());
            if(personaActualizada.getLocalidad()==null) personaActualizada.setLocalidad(exitsPersona.getLocalidad());
            if(personaActualizada.getNacimiento()==null) personaActualizada.setNacimiento(exitsPersona.getNacimiento());
            personaActualizada.setEmail(exitsPersona.getEmail());
            personaActualizada.setRol(exitsPersona.getRol());
            personaActualizada.setPassword(exitsPersona.getPassword());
            personaActualizada.setId(id);
            service.save(personaActualizada);
        }
        return "redirect:/inicioUser";
    }

    @PostMapping("/{id}/autorizar")
    public String autorizar(@PathVariable Integer id, @ModelAttribute("autorizar") Persona personaAutorizada, BindingResult bindingResult) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Persona autorizante = service.findByEmail(auth.getName());
        Autorizacion nuevaAutorizacion = new Autorizacion(autorizante, personaAutorizada, ESPERA);
        System.out.println("Persona Autorizada [ID]: " + personaAutorizada.getId());
        for (Autorizacion autorizacion : autorizacionService.listar()) {
            if (autorizacion.getEstado() == ESPERA && autorizacion.getAutorizante() == nuevaAutorizacion.getAutorizante() && autorizacion.getAutorizado() == nuevaAutorizacion.getAutorizado()) {
                System.out.println("AGREGADO");
                return "redirect:/inicioUser";
            }
        }
            autorizacionService.save(nuevaAutorizacion);
            System.out.println("Nueva Autorizacion: " + nuevaAutorizacion);
            return "redirect:/inicioUser";
        }

    @PostMapping("/{id}/aceptar")
    public String aceptar(@PathVariable Integer id,@ModelAttribute("aceptar") Autorizacion autorizacionAceptada, BindingResult bindingResult) {
        Autorizacion autorizacionConfirmada = new Autorizacion(autorizacionAceptada.getId(), autorizacionService.findById(autorizacionAceptada.getId()).get().getAutorizante(), autorizacionService.findById(autorizacionAceptada.getId()).get().getAutorizado(), ACEPTADA);
        System.out.println("Aceptada: " + autorizacionAceptada.getId());
        autorizacionService.save(autorizacionConfirmada);
        return "redirect:/inicioUser";
    }
    @GetMapping("/login")
    public String modeltoLogin(Model model,@ModelAttribute("usuarioActual") Persona user) {
        System.out.println("INGRESO");
        model.addAttribute("user", new Persona());
        model.addAttribute("usuarioActual", new Persona());
        return "login";
    }
}
