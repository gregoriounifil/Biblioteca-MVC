package com.example.biblioteca.controller;

import com.example.biblioteca.model.Emprestimo;
import com.example.biblioteca.repository.BibliotecaRepositorio;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final BibliotecaRepositorio repositorio;

    public EmprestimoController(BibliotecaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("emprestimos", repositorio.getEmprestimos());
        return "emprestimos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("usuarios", repositorio.getUsuarios());
        model.addAttribute("livros", repositorio.getLivros());
        model.addAttribute("dataRetirada", LocalDate.now());
        model.addAttribute("dataDevolucao", LocalDate.now().plusDays(7));
        return "emprestimos/form";
    }

    @PostMapping
    public String salvar(@RequestParam int usuarioIndice,
                         @RequestParam int livroIndice,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataRetirada,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataDevolucao) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(repositorio.getUsuarios().get(usuarioIndice));
        emprestimo.setLivro(repositorio.getLivros().get(livroIndice));
        emprestimo.setDataRetirada(dataRetirada);
        emprestimo.setDataDevolucao(dataDevolucao);
        repositorio.getEmprestimos().add(emprestimo);
        return "redirect:/emprestimos";
    }

    @PostMapping("/excluir/{indice}")
    public String excluir(@PathVariable int indice) {
        BibliotecaRepositorio.remover(repositorio.getEmprestimos(), indice);
        return "redirect:/emprestimos";
    }
}
