package biblioteca.persistencia;

import biblioteca.modelo.Autor;

import java.util.ArrayList;
import java.util.List;

public class RepositorioAutor {
    
    private final List<Autor> autores = new ArrayList<>();
    private int proximoId = 1;

    public int gerarId(){
        return proximoId++;
    }

    public void salvar(Autor autor){
        autores.add(autor);
    }

    public Autor buscarPorId(int id){
        for(Autor autor : autores){
            if(autor.getId() == id){
                return autor;
            }
            
        }
        return null;
    }

    public List<Autor> listarTodos(){
        return new ArrayList<>(autores);
    }

    public boolean remover(int id){
        Autor autor = buscarPorId(id);
        if(autor == null){
            return false;
        }
        autores.remove(autor);
        return true;
    }
}
