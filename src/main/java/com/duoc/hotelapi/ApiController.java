package com.duoc.hotelapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Hotel API", description = "API de validacion DevOps - Duoc UC")
public class ApiController {

    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Verifica que la app esta corriendo")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("mensaje", "Aplicacion funcionando / Demo para el video");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/saludo")
    @Operation(summary = "Saludo", description = "Endpoint de prueba")
    public ResponseEntity<Map<String, String>> saludo() {
        Map<String, String> response = new HashMap<>();
        response.put("saludo", "Hola desde Jenkins + Docker + Tomcat");
        response.put("entorno", "AWS EC2");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/datos")
    @Operation(summary = "Recibir datos", description = "Recibe un JSON y lo retorna")
    public ResponseEntity<Map<String, Object>> recibirDatos(@RequestBody Map<String, Object> body) {
        body.put("status", "recibido");
        body.put("mensaje", "Datos procesados correctamente");
        return ResponseEntity.ok(body);
    }
}