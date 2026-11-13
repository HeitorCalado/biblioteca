package biblioteca.persistencia;

import biblioteca.modelo.Livro;

import java.util.ArrayList;
import java.util.List;

public class RepositorioLivro {

    private final List<Livro> livros = new ArrayList<>();
    private int proximoId = 1;

    public int gerarId(){
        return proximoId++;
    }

    public void salvar(Livro livro){
        livros.add(livro);
    }

    public Livro buscarPorId(int id){
        for(Livro livro : livros){
            if(livro.getId() == id){
                return livro;
            }
        }
        return null;
    }

    public List<Livro> buscarPorAutor(int idAutor){
        List<Livro> encontrados = new ArrayList<>();
        for(Livro livro : livros){
            if(livro.getAutor().getId() == idAutor){
                encontrados.add(livro);
            }
        }
        return null;
    }

    public List<Livro> listarTodos(){
        return new ArrayList<>(livros);
    }

    public boolean remover(int id){
        Livro livro = buscarPorId(id);
        if(livro == null){
            return false;
        }
        livros.remove(livro);
        return true;
    }
}