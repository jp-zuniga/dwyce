package org.psychcatalog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @GetMapping
    public String home(Model model) {
        List<Map<String, String>> instrumentos = List.of(
                Map.of(
                        "nombre", "Inventario de Ansiedad",
                        "descripcion", "Instrumentos para medir niveles de ansiedad.",
                        "categoria", "Ansiedad",
                        "imagen", "/images/ansiedad.jpg"
                ),
                Map.of(
                        "nombre", "Test de estrés académico.",
                        "descripcion", "Permite identificar factores de estrés.",
                        "categoria", "Estrés",
                        "imagen", "/images/estres.jpg"
                )
        );

        model.addAttribute("titulo", "Catálogo de Instrumentos");
        model.addAttribute("instrumentos", instrumentos);

        return "index";
    }
}
