package com.luecardoso.projetos.controleacesso.domain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoutesController {

    @GetMapping("/public")
    public ResponseEntity<String> publicEndpoint() {
            return ResponseEntity.ok("Public endpoint");
    }

    @GetMapping("/private")
    public ResponseEntity<String> privateEndpoint() {
        return ResponseEntity.ok("Private endpoint");
    }
}
