package org.psychcatalog.controllers;

import org.psychcatalog.models.Categoria;
import org.psychcatalog.repositories.CategoriaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listarCategorias(Model model) {
        List<Categoria> categorias = repository.findByOrderByNombreAsc();

        model.addAttribute("categorias", categorias);
        model.addAttribute("titulo", "Lista de Categorías");

        return "categorias/list";
    }
}
