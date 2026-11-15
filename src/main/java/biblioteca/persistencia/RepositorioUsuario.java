package biblioteca.persistencia;

import biblioteca.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RepositorioUsuario {
    
    private final List<Usuario> usuarios = new ArrayList<>();
    private int proximoId = 1;

    public int gerarId(){
        return proximoId++;
    }

    public void salvar(Usuario usuario){
        usuarios.add(usuario);
    }

    public Usuario buscarPorId(int id){
        for(Usuario usuario : usuarios){
            if(usuario.getId() == id){
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscarPorCpf(String cpf){
        for(Usuario usuario : usuarios){
            if(usuario.getCpf().equals(cpf)){
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscarPorEmail(String email){
        for(Usuario usuario : usuarios){
            if(usuario.getEmail().equalsIgnoreCase(email)){
                return usuario;
            }
        }
        return null;
    }

    public List<Usuario> listarTodos(){
        return new ArrayList<>(usuarios);
    }

    public boolean remover(int id){
        Usuario usuario = buscarPorId(id);
        if(usuario == null){
            return false;
        }
        usuarios.remove(usuario);
        return true;
    }
}
