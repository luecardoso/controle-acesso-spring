package com.luecardoso.projetos.controleacesso.domain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoutesController {

    @GetMapping("/public")
    public ResponseEntity<String> publicEndpoint() {
            return ResponseEntity.ok("Public endpoint");
    }

    @GetMapping("/private")
    public ResponseEntity<String> privateEndpoint(Authentication authentication) {
        System.out.println(authentication.getClass());
        return ResponseEntity.ok("Private endpoint Usuário conectado: " + authentication.getName());
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminRoute(){
        return ResponseEntity.ok("Admin route ok!");
    }
}
