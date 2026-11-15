package biblioteca.validacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

class ValidadorTest {
    
    @Test 
    void cpfValido(){
        assertTrue(Validador.cpfValido("529.982.247-25"));
    }

    @Test
    void cpfComDigitoErrado(){
        assertFalse(Validador.cpfValido("529.982.247-24"));
    }

    @Test 
    void cpfComTodosDigitosIguais(){
        assertFalse(Validador.cpfValido("111.111.111-11"));
    }

    @Test
    void emailValido(){
        assertTrue(Validador.emailValido("heitor@gmail.com"));
    }

    @Test
    void emailSemArroba(){
        assertFalse(Validador.emailValido("heitor.gmail.com"));
    }

    @Test
    void emailSemPontoNoDominio(){
        assertFalse(Validador.emailValido("heitor@gmail"));
    }

    @Test
    void senhaForte(){
        assertTrue(Validador.senhaForte("Senha@123"));
    }

    @Test
    void senhaCurta(){
        assertFalse(Validador.senhaForte("Ab1@"));
    }

    @Test
    void senhaSemEspecial(){
        assertFalse(Validador.senhaForte("Senha1234"));
    }

    @Test
    void dataValida(){
        assertEquals(LocalDate.of(2024,2,29), Validador.converterData("29/02/2024"));
    }

    @Test
    void dataQueNaoExiste(){
        assertThrows(DateTimeParseException.class, () -> Validador.converterData("31/02/2026"));
    }

    @Test
    void dataNoFormatoErrado(){
        assertThrows(DateTimeParseException.class, () -> Validador.converterData("2024-02-29"));
    }
}
