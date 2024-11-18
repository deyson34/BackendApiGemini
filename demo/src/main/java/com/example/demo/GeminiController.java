package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GeminiController {

    @GetMapping("/gemini")
    public ResponseEntity<String> getPrompt(@RequestParam(required = false) String prompt) {
        if (prompt == null || prompt.isEmpty()) {
            return ResponseEntity.badRequest().body("El parámetro 'prompt' es obligatorio y no fue proporcionado.");
        }
        // Procesa el parámetro aquí (cambia según tu lógica)
        String respuesta = "Prompt recibido: " + prompt;
        return ResponseEntity.ok(respuesta);
    }
}
    