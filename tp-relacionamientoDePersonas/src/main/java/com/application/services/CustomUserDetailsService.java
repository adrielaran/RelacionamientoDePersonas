package com.application.services;

import com.application.entity.Persona;
import com.application.interfaces.IPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonaService personaService;
    @Autowired
    private IPersona personaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("debug: loadUserByUsername:  " + email);
        Persona persona = personaRepository.findByEmail(email);
        System.out.println("debug: loadUserByUsername:  " + personaRepository);
        System.out.println("debug: loadUserByUsername:  " +  personaRepository.findByEmail(email));

        if (persona == null) throw new UsernameNotFoundException(email);
        return buildUserForAuthentication(persona);
    }

    private UserDetails buildUserForAuthentication(Persona user) {
        List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
        listAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), new BCryptPasswordEncoder().encode(user.getPassword()),
                user.getActive(), true, true, true, listAuthorities);
    }
}
