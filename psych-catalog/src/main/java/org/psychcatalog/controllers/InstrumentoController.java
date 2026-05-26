package org.psychcatalog.controllers;

import org.psychcatalog.models.Categoria;
import org.psychcatalog.repositories.InstrumentoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/instrumentos")
public class InstrumentoController {
    private final InstrumentoRepository repository;

    public InstrumentoController(InstrumentoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listar(Model model, @RequestParam(required = false) String search) {
        return "instrumentos/list";
    }
}
