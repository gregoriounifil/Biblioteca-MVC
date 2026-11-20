package com.example.biblioteca.controller;

import com.example.biblioteca.model.Autor;
import com.example.biblioteca.repository.BibliotecaRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/autores")
public class AutorController {

    private final BibliotecaRepositorio repositorio;

    public AutorController(BibliotecaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("autores", repositorio.getAutores());
        return "autores/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("autor", new Autor());
        return "autores/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Autor autor) {
        repositorio.getAutores().add(autor);
        return "redirect:/autores";
    }

    @PostMapping("/excluir/{indice}")
    public String excluir(@PathVariable int indice) {
        BibliotecaRepositorio.remover(repositorio.getAutores(), indice);
        return "redirect:/autores";
    }
}
