package com.example.biblioteca.repository;

import com.example.biblioteca.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class BibliotecaRepositorio {

    private final List<Autor> autores = new ArrayList<>();
    private final List<Categoria> categorias = new ArrayList<>();
    private final List<Livro> livros = new ArrayList<>();
    private final List<Usuario> usuarios = new ArrayList<>();
    private final List<Emprestimo> emprestimos = new ArrayList<>();

    public BibliotecaRepositorio() {
        Autor autor = new Autor();
        autor.setNome("Machado de Assis");
        autor.setNacionalidade("Brasileiro");
        autor.setObrasPublicadas("Dom Casmurro");
        autores.add(autor);

        Categoria categoria = new Categoria();
        categoria.setGenero("Romance");
        categorias.add(categoria);

        Livro livro = new Livro();
        livro.setTitulo("Dom Casmurro");
        livro.setIsbn("978-85-00-00000-00");
        livro.setAutor(autor);
        livro.setCategoria(categoria);
        livros.add(livro);

        Usuario usuario = new Usuario();
        usuario.setNome("Gregório");
        usuario.setId("1");
        usuarios.add(usuario);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setDataRetirada(LocalDate.now());
        emprestimo.setDataDevolucao(LocalDate.now().plusDays(7));
        emprestimos.add(emprestimo);
    }

    public List<Autor> getAutores() { return autores; }
    public List<Categoria> getCategorias() { return categorias; }
    public List<Livro> getLivros() { return livros; }
    public List<Usuario> getUsuarios() { return usuarios; }
    public List<Emprestimo> getEmprestimos() { return emprestimos; }

    public static <T> void remover(List<T> lista, int indice) {
        if (indice >= 0 && indice < lista.size()) {
            lista.remove(indice);
        }
    }
}
