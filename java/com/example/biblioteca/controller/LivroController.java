package com.example.biblioteca.controller;

import com.example.biblioteca.model.Livro;
import com.example.biblioteca.repository.BibliotecaRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final BibliotecaRepositorio repositorio;

    public LivroController(BibliotecaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("livros", repositorio.getLivros());
        return "livros/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("autores", repositorio.getAutores());
        model.addAttribute("categorias", repositorio.getCategorias());
        return "livros/form";
    }

    @PostMapping
    public String salvar(@RequestParam String titulo,
                         @RequestParam String isbn,
                         @RequestParam int autorIndice,
                         @RequestParam int categoriaIndice) {
        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setIsbn(isbn);
        livro.setAutor(repositorio.getAutores().get(autorIndice));
        livro.setCategoria(repositorio.getCategorias().get(categoriaIndice));
        repositorio.getLivros().add(livro);
        return "redirect:/livros";
    }

    @PostMapping("/excluir/{indice}")
    public String excluir(@PathVariable int indice) {
        BibliotecaRepositorio.remover(repositorio.getLivros(), indice);
        return "redirect:/livros";
    }
}
