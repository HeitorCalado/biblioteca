package biblioteca.negocio;

import biblioteca.modelo.Autor;
import biblioteca.modelo.Categoria;
import biblioteca.modelo.Livro;
import biblioteca.persistencia.RepositorioAutor;
import biblioteca.persistencia.RepositorioLivro;

import java.time.LocalDate;
import java.util.List;

public class NegocioLivro {
    
    private static final int ANO_MINIMO = 1450;

    private final RepositorioLivro repositorioLivro;
    private final RepositorioAutor repositorioAutor;

    public NegocioLivro(RepositorioLivro repositorioLivro, RepositorioAutor repositorioAutor){
        this.repositorioLivro = repositorioLivro;
        this.repositorioAutor = repositorioAutor;
    }

    public Livro cadastrar(String titulo, Categoria categoria, int anoPublicacao, int idAutor){
        validarTitulo(titulo);

        if(categoria == null){
            throw new IllegalArgumentException("A categoria é obrigatória.");
        }
        
        int anoAtual = LocalDate.now().getYear();
        if(anoPublicacao < ANO_MINIMO || anoPublicacao > anoAtual){
            throw new IllegalArgumentException("O ano de publicação deve estar entre " 
                                                + ANO_MINIMO + " e " + anoAtual + ".");
        }
        
        Autor autor = repositorioAutor.buscarPorId(idAutor);
        if (autor == null){
            throw new IllegalArgumentException("Autor com id "
                                                 + idAutor + " não encontrado.");
        }
        int id = repositorioAutor.gerarId();
        Livro livro = new Livro(id, titulo.trim(), categoria, anoPublicacao, autor);
        repositorioLivro.salvar(livro);
        return livro;
    }

    public Livro buscar(int id){
        Livro livro = repositorioLivro.buscarPorId(id);
        if(livro == null){
            throw new IllegalArgumentException("Livro com id " + id + "não encontrado.");
        }
        return livro;
    }

    public List<Livro> listar(){
        return repositorioLivro.listarTodos();
    }

    public List<Livro> listarPorAutor(int idAutor){
        return repositorioLivro.buscarPorAutor(idAutor);
    }

    public void alterarTitulo(int id, String novoTitulo){
        validarTitulo(novoTitulo);
        Livro livro = buscar(id);
        livro.setTitulo(novoTitulo.trim());
    }

    public void alterarCategoria(int id, Categoria novCategoria){
        if(novCategoria == null){
            throw new IllegalArgumentException("A categoria é obrigatória.");
        }
        Livro livro = buscar(id);
        livro.setCategoria(novCategoria);
    }

    public void remover(int id){
        buscar(id);
        repositorioLivro.remover(id);
    }

    private void validarTitulo(String titulo){
        if (titulo == null || titulo.isBlank()){
            throw new IllegalArgumentException("O título não pode ser vazio.");
        }
    }
}
