package biblioteca.fachada;

import biblioteca.modelo.Autor;
import biblioteca.modelo.Categoria;
import biblioteca.modelo.Livro;
import biblioteca.negocio.NegocioAutor;
import biblioteca.negocio.NegocioLivro;
import biblioteca.persistencia.RepositorioAutor;
import biblioteca.persistencia.RepositorioLivro;

import java.time.LocalDate;
import java.util.List;

public class Fachada {
    
    private final NegocioAutor negocioAutor;
    private final NegocioLivro negocioLivro;
    
    public Fachada(){
        RepositorioAutor repositorioAutor = new RepositorioAutor();
        RepositorioLivro repositorioLivro = new RepositorioLivro();

        this.negocioAutor = new NegocioAutor(repositorioAutor, repositorioLivro);
        this.negocioLivro = new NegocioLivro(repositorioLivro, repositorioAutor);
    }

    // ===== AUTORES =======

    public Autor cadastrarAutor(String nome, LocalDate dataNascimento, String estilo){
        return negocioAutor.cadastrar(nome, dataNascimento, estilo);
    }

    public Autor buscarAutor(int id){
        return negocioAutor.buscar(id);
    }

    public List<Autor> listarAutores(){
        return negocioAutor.listar();
    }

    public void alterarNomeAutor(int id, String novoNome){
        negocioAutor.alterarNome(id, novoNome);
    }

    public void alterarEstiloAutor(int id, String novoEstilo){
        negocioAutor.alterarEstilo(id, novoEstilo);
    }

    public void removerAutor(int id){
        negocioAutor.remover(id);
    }

    // ====== LIVROS =====

    public Livro cadastrarLivro(String titulo, Categoria categoria, int anoPublicacao, int idAutor){
        return negocioLivro.cadastrar(titulo, categoria, anoPublicacao, idAutor);
    }

    public Livro buscarLivro(int id){
        return negocioLivro.buscar(id);
    }

    public List<Livro> listarLivros(){
        return negocioLivro.listar();
    }

    public List<Livro> listarLivrosPorAutor(int id){
        return negocioLivro.listarPorAutor(id);
    }

    public void alterarTituloLivro(int id, String novoTitulo){
        negocioLivro.alterarTitulo(id, novoTitulo);
    }

    public void alterarCategoriaLivro(int id, Categoria novaCategoria){
        negocioLivro.alterarCategoria(id, novaCategoria);
    }

    public void removerLivro(int id){
        negocioLivro.remover(id);
    }
}
