package biblioteca.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import biblioteca.modelo.Autor;
import biblioteca.modelo.Categoria;
import biblioteca.modelo.Livro;
import biblioteca.persistencia.RepositorioAutor;
import biblioteca.persistencia.RepositorioLivro;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NegocioLivroTest {
    
    private NegocioLivro negocioLivro;
    private Autor autor;

    @BeforeEach 
    void preparar(){
        RepositorioAutor repositorioAutor = new RepositorioAutor();
        RepositorioLivro repositorioLivro = new RepositorioLivro();
        NegocioAutor negocioAutor = new NegocioAutor(repositorioAutor, repositorioLivro);
        negocioLivro = new NegocioLivro(repositorioLivro, repositorioAutor);

        autor = negocioAutor.cadastrar("Machado de Assis", LocalDate.of(1839, 6, 21), "Realismo");
    }

    @Test
    void cadastrarLivroValido(){
        Livro livro = negocioLivro.cadastrar("Dom Casmurro", Categoria.ROMANCE, 1899, autor.getId());
        assertEquals(1, livro.getId());
        assertEquals("Machado de Assis", livro.getAutor().getNome());
    }

    @Test 
    void recusaTituloVazio(){
        assertThrows(IllegalArgumentException.class, () -> negocioLivro.cadastrar(" ", Categoria.ROMANCE, 1899, autor.getId()));
    }

    @Test 
    void recusaCategoriaNula(){
        assertThrows(IllegalArgumentException.class, () -> negocioLivro.cadastrar("Dom Casmurro", null, 1899, autor.getId()));
    }

     @Test
    void recusaAnoAntesDe1450(){
        assertThrows(IllegalArgumentException.class,
            () -> negocioLivro.cadastrar("Dom Casmurro", Categoria.ROMANCE, 1449, autor.getId()));
    }

    @Test
    void recusaAnoNoFuturo(){
        int anoQueVem = LocalDate.now().getYear() + 1;
        assertThrows(IllegalArgumentException.class,
            () -> negocioLivro.cadastrar("Dom Casmurro", Categoria.ROMANCE, anoQueVem, autor.getId()));
    }

    @Test
    void recusaAutorInexistente(){
        assertThrows(IllegalArgumentException.class,
            () -> negocioLivro.cadastrar("Dom Casmurro", Categoria.ROMANCE, 1899, 99));
    }

    @Test 
    void alterarCategoria(){
        Livro livro = negocioLivro.cadastrar("Dom Casmurro", Categoria.ROMANCE, 1899, autor.getId());
        negocioLivro.alterarCategoria(livro.getId(), Categoria.SUSPENSE);
        assertEquals(Categoria.SUSPENSE, negocioLivro.buscar(livro.getId()).getCategoria());
    }
}
