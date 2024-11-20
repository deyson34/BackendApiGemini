package com.example.demo;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiService {

    private final RestTemplate restTemplate;
    private final String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";
    private final String apiKey = "AIzaSyCMteNYeSX2ci-dwVegYVHt7ulGrnJT0jA";

    public GeminiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateContent(String prompt) {
        // Construir la URL con el parámetro clave
        String url = apiUrl + "?key=" + apiKey;

        // Crear el cuerpo de la solicitud
        String requestBody = """
            {
                "contents": [{
                    "parts": [{"text": "%s"}]
                }]
            }
        """.formatted(prompt);

        // Configurar encabezados
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        // Crear la entidad para la solicitud
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            // Realizar la solicitud POST
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Error al comunicarse con la API de Gemini: " + e.getMessage(), e);
        }
    }
}
