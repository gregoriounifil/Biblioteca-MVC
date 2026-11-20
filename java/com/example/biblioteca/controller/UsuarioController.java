package com.example.biblioteca.controller;

import com.example.biblioteca.model.Usuario;
import com.example.biblioteca.repository.BibliotecaRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final BibliotecaRepositorio repositorio;

    public UsuarioController(BibliotecaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", repositorio.getUsuarios());
        return "usuarios/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/form";
    }

    @PostMapping
    public String salvar(@ModelAttribute Usuario usuario) {
        repositorio.getUsuarios().add(usuario);
        return "redirect:/usuarios";
    }

    @PostMapping("/excluir/{indice}")
    public String excluir(@PathVariable int indice) {
        BibliotecaRepositorio.remover(repositorio.getUsuarios(), indice);
        return "redirect:/usuarios";
    }
}
