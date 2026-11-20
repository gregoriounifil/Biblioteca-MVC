package com.example.biblioteca.controller;

import com.example.biblioteca.model.Categoria;
import com.example.biblioteca.repository.BibliotecaRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    private final BibliotecaRepositorio repositorio;

    public CategoriaController(BibliotecaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", repositorio.getCategorias());
        return "categorias/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categorias/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Categoria categoria) {
        repositorio.getCategorias().add(categoria);
        return "redirect:/categorias";
    }

    @PostMapping("/excluir/{indice}")
    public String excluir(@PathVariable int indice) {
        BibliotecaRepositorio.remover(repositorio.getCategorias(), indice);
        return "redirect:/categorias";
    }
}
