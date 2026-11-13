
package com.example.biblioteca.controller;

import com.example.biblioteca.model.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.time.LocalDate;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @GetMapping
    public String inicio() {
        return "livros/inicio";
    }

    @GetMapping("/livro")
    public String exibirLivro(Model model) {

        Autor autor = new Autor();
        autor.setNome("Machado de Assis");
        autor.setNacionalidade("Brasileiro");
        autor.setObrasPublicadas("Dom Casmurro");

        Categoria categoria = new Categoria();
        categoria.setGenero("Romance");

        Livro livro = new Livro();
        livro.setTitulo("Dom Casmurro");
        livro.setIsbn("978-85-00-00000-00");
        livro.setAutor(autor);
        livro.setCategoria(categoria);

        model.addAttribute("livro", livro);

        Usuario usuario = new Usuario();
        usuario.setNome("Gregório");
        usuario.setId("1");

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setDataRetirada(LocalDate.now());
        emprestimo.setDataDevolucao(LocalDate.now().plusDays(7));

        model.addAttribute("livro", livro);
        model.addAttribute("emprestimo", emprestimo);

        return "livro";
    }
}
