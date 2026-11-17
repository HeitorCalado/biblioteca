package biblioteca.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import biblioteca.modelo.Autor;
import biblioteca.modelo.Categoria;
import biblioteca.persistencia.RepositorioAutor;
import biblioteca.persistencia.RepositorioLivro;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NegocioAutorTest {
    
    private NegocioAutor negocioAutor;
    private NegocioLivro negocioLivro;

    @BeforeEach 
    void preparar(){
        RepositorioAutor repositorioAutor = new RepositorioAutor();
        RepositorioLivro repositorioLivro = new RepositorioLivro();
        negocioAutor = new NegocioAutor(repositorioAutor, repositorioLivro);
        negocioLivro = new NegocioLivro(repositorioLivro, repositorioAutor);
    }

    @Test
    void cadastrarAutorValido(){
        Autor autor = negocioAutor.cadastrar("Machado de Assis", LocalDate.of(1839, 6, 21), "Realismo");
        assertEquals(1, autor.getId());
        assertEquals("Machado de Assis", autor.getNome());
    }

    @Test 
    void recusaNomeVazio(){
        assertThrows(IllegalArgumentException.class, () ->  negocioAutor.cadastrar("", LocalDate.of(1839, 6, 21), "Realismo"));
    }

    @Test 
    void recusaDataNula(){
        assertThrows(IllegalArgumentException.class, () -> negocioAutor.cadastrar("Machado de Assis", null, "Realismo"));
    }

    @Test 
    void recusaDataNoFuturo(){
        LocalDate amanha = LocalDate.now().plusDays(1);
        assertThrows(IllegalArgumentException.class, () -> negocioAutor.cadastrar("Machado de Assis", amanha, "Realismo"));
    }

    @Test 
    void recusaRemoverAutorComLivros(){
        Autor autor = negocioAutor.cadastrar("Machado de Assis", LocalDate.of(1839, 6, 21), "Realismo");
        negocioLivro.cadastrar("Dom Casmurro", Categoria.ROMANCE, 1899, autor.getId());
        assertThrows(IllegalArgumentException.class, () -> negocioAutor.remover(autor.getId()));
    }

    @Test 
    void removeAutorSemLivros(){
        Autor autor = negocioAutor.cadastrar("Machado de Assis", LocalDate.of(1839, 6, 21), "Realismo");
        negocioAutor.remover(autor.getId());
        assertEquals(0, negocioAutor.listar().size());
    }
}
