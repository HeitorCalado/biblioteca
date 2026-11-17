package biblioteca.negocio;

import biblioteca.modelo.Usuario;
import biblioteca.persistencia.RepositorioUsuario;
import biblioteca.validacao.Validador;

import java.util.List;

public class NegocioUsuario {
    
    private final RepositorioUsuario repositorioUsuario;

    public NegocioUsuario(RepositorioUsuario repositorioUsuario){
        this.repositorioUsuario = repositorioUsuario;
    }

    public Usuario cadastrar(String nome, String cpf, String email, String senha){
        validarTexto(nome, "O nome do usuário");

        if(!Validador.cpfValido(cpf)){
            throw new IllegalArgumentException("CPF inválido.");
        }
        String cpfNumeros = cpf.replaceAll("[^0-9]", "");
        if(repositorioUsuario.buscarPorCpf(cpfNumeros) != null){
            throw new IllegalArgumentException("Já existe um usuário com este CPF.");
        }

        validarEmail(email);
        if(repositorioUsuario.buscarPorEmail(email.trim()) != null){
            throw new IllegalArgumentException("Já existe um usuário com este e-mail.");
        }

        validarSenha(senha);
            
        int id = repositorioUsuario.gerarId();
        Usuario usuario = new Usuario(id, nome.trim(), cpfNumeros, email.trim(), senha);
        repositorioUsuario.salvar(usuario);
        return usuario;
    }

    public Usuario buscar(int id){
        Usuario usuario = repositorioUsuario.buscarPorId(id);
        if(usuario == null){
            throw new IllegalArgumentException("Usuário com id " + id + " não encontrado.");
        }
        return usuario;
    }

    public List<Usuario> listar(){
        return repositorioUsuario.listarTodos();
    }

    public void alterarNome(int id, String novoNome){
        validarTexto(novoNome, "O nome do usuário");
        Usuario usuario = buscar(id);
        usuario.setNome(novoNome.trim());
    }

    public void alterarEmail(int id, String novoEmail){
        validarEmail(novoEmail);
        Usuario usuario = buscar(id);

        Usuario dono = repositorioUsuario.buscarPorEmail(novoEmail.trim());
        if(dono != null && dono.getId() != id){
            throw new IllegalArgumentException("Já existe um usuário com este e-mail.");
        }
        usuario.setEmail(novoEmail.trim());
    }

    public void alterarSenha(int id, String novaSenha){
        validarSenha(novaSenha);
        Usuario usuario = buscar(id);
        usuario.setSenha(novaSenha);
    }

    public void remover(int id){
        buscar(id);
        repositorioUsuario.remover(id);
    }

    private void validarTexto(String texto, String campo){
        if(texto == null || texto.isBlank()){
            throw new IllegalArgumentException(campo + " não pode ser vazio.");
        }
    }

    private void validarEmail(String email){
        if(email == null || !Validador.emailValido(email.trim())){
            throw new IllegalArgumentException("E-mail inválido.");
        }
    }

    private void validarSenha(String senha){
        if(!Validador.senhaForte(senha)){
            throw new IllegalArgumentException(
                "A senha precisa ter 8+ caracteres, com maiúscula, minúscula, número e caractere especial.");
        }
    }
}