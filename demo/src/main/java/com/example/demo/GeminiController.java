package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GeminiController {

    @Autowired
    private GeminiService geminiService; // Servicio que llama a la API de Google Gemini

    @GetMapping("/gemini")
    public ResponseEntity<String> getPrompt(@RequestParam(required = false) String prompt) {
        if (prompt == null || prompt.isEmpty()) {
            return ResponseEntity.badRequest().body("El parámetro 'prompt' es obligatorio y no fue proporcionado.");
        }

        try {
            // Llama al servicio que interactúa con la API de Google Gemini
            String respuestaIA = geminiService.generateContent(prompt);  // Cambié el método a generateContent
            return ResponseEntity.ok(respuestaIA);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al procesar el prompt: " + e.getMessage());
        }
    }
}
