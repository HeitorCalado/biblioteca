package biblioteca.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import biblioteca.modelo.Usuario;
import biblioteca.persistencia.RepositorioUsuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NegocioUsuarioTest {
    
    private NegocioUsuario negocio;

    @BeforeEach 
    void preparar(){
        negocio = new NegocioUsuario(new RepositorioUsuario());
    }

    @Test
    void cadastrarUsuarioValido(){
        Usuario usuario = negocio.cadastrar("Heitor", "529.982.247-25", "heitor@gmail.com", "Senha@123");
        assertEquals(1, usuario.getId());
        assertEquals("52998224725", usuario.getCpf());
        assertEquals(1, negocio.listar().size());
    }

    @Test
    void recusaNomeVazio(){
        assertThrows(IllegalArgumentException.class,
            () -> negocio.cadastrar("  ", "529.982.247-25", "heitor@gmail.com", "Senha@123"));
    }

    @Test
    void recusaCpfInvalido(){
        assertThrows(IllegalArgumentException.class,
            () -> negocio.cadastrar("Heitor", "123.456.789-00", "heitor@gmail.com", "Senha@123"));
    }

    @Test
    void recusaCpfRepetido(){
        negocio.cadastrar("Heitor", "529.982.247-25", "heitor@gmail.com", "Senha@123");
        assertThrows(IllegalArgumentException.class,
            () -> negocio.cadastrar("Outro", "52998224725", "outro@gmail.com", "Senha@123"));
    }

    @Test
    void recusaEmailInvalido(){
        assertThrows(IllegalArgumentException.class,
            () -> negocio.cadastrar("Heitor", "529.982.247-25", "heitorgmail.com", "Senha@123"));
    }

    @Test
    void recusaEmailRepetidoMesmoComMaiusculas(){
        negocio.cadastrar("Heitor", "529.982.247-25", "heitor@gmail.com", "Senha@123");
        assertThrows(IllegalArgumentException.class,
            () -> negocio.cadastrar("Outro", "111.444.777-35", "HEITOR@gmail.com", "Senha@123"));
    }

    @Test
    void recusaSenhaFraca(){
        assertThrows(IllegalArgumentException.class,
            () -> negocio.cadastrar("Heitor", "529.982.247-25", "heitor@gmail.com", "senha123"));
    }

    @Test
    void permiteManterOProprioEmail(){
        Usuario usuario = negocio.cadastrar("Heitor", "529.982.247-25", "heitor@gmail.com", "Senha@123");
        negocio.alterarEmail(usuario.getId(), "heitor@gmail.com");
        assertEquals("heitor@gmail.com", negocio.buscar(usuario.getId()).getEmail());
    }

    @Test
    void recusaTrocarParaEmailDeOutroUsuario(){
        negocio.cadastrar("Heitor", "529.982.247-25", "heitor@gmail.com", "Senha@123");
        Usuario maria = negocio.cadastrar("Maria", "111.444.777-35", "maria@gmail.com", "Senha@123");
        assertThrows(IllegalArgumentException.class,
            () -> negocio.alterarEmail(maria.getId(), "heitor@gmail.com"));
    }

    @Test
    void recusaAlterarParaSenhaFraca(){
        Usuario usuario = negocio.cadastrar("Heitor", "529.982.247-25", "heitor@gmail.com", "Senha@123");
        assertThrows(IllegalArgumentException.class,
            () -> negocio.alterarSenha(usuario.getId(), "123"));
    }

    @Test
    void removeUsuario(){
        Usuario usuario = negocio.cadastrar("Heitor", "529.982.247-25", "heitor@gmail.com", "Senha@123");
        negocio.remover(usuario.getId());
        assertEquals(0, negocio.listar().size());
    }

    @Test
    void recusaBuscarIdInexistente(){
        assertThrows(IllegalArgumentException.class, () -> negocio.buscar(99));
    }
}
