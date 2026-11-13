package biblioteca.negocio;

import biblioteca.modelo.Autor;
import biblioteca.persistencia.RepositorioAutor;
import biblioteca.persistencia.RepositorioLivro;

import java.time.LocalDate;
import java.util.List;

public class NegocioAutor{

    private final RepositorioAutor repositorioAutor;
    private final RepositorioLivro repositorioLivro;

    public NegocioAutor(RepositorioAutor repositorioAutor, RepositorioLivro repositorioLivro){
        this.repositorioAutor = repositorioAutor;
        this.repositorioLivro = repositorioLivro;
    }

    public Autor cadastrar(String nome, LocalDate dataNascimento, String estilo){
        validarTexto(nome, "O nome do autor");
        validarTexto(estilo, "O estilo do autor");

        if(dataNascimento == null){
            throw new IllegalArgumentException("A data de nascimento é obrigatória.");
        }
        if(dataNascimento.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("A data de nascimento não pode ser no futuro.");
        }
        
        int id = repositorioAutor.gerarId();
        Autor autor = new Autor(id, nome.trim(), dataNascimento, estilo.trim());
        repositorioAutor.salvar(autor);
        return autor;
    }

    public Autor buscar(int id){
        Autor autor = repositorioAutor.buscarPorId(id);
        if(autor == null){
            throw new IllegalArgumentException("Autor com id " + id + " não encontrado.");
        }
        return autor;
    }

    public List<Autor> listar(){
        return repositorioAutor.listarTodos();
    }

    public void alterarNome(int id, String novoNome){
        validarTexto(novoNome, "O nome do autor");
        Autor autor = buscar(id);
        autor.setNome(novoNome.trim());
    }

    public void alterarEstilo(int id, String novoEstilo){
        validarTexto(novoEstilo, " O estilo do autor");
        Autor autor = buscar(id);
        autor.setEstilo(novoEstilo.trim());
    }

    public void remover(int id){
        buscar(id);

        if(!repositorioLivro.buscarPorAutor(id).isEmpty()){
            throw new IllegalArgumentException(
                 "Não é possível remover: este autor ainda tem livros cadastrados.");
        }

        repositorioAutor.remover(id);
    }

    private void validarTexto(String texto, String campo){
        if(texto == null || texto.isBlank()){
            throw new IllegalArgumentException(campo + " não pode ser vazio.");
        }
    }
}