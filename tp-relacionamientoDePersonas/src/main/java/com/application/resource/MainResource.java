package com.application.resource;

import com.application.entity.Persona;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainResource {

    @RequestMapping(value = "/")
    public String index() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/inicio")
    public String dashboard() {
        return "inicio";
    }


}

