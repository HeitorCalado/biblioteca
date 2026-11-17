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
    void cpfNulo(){
        assertFalse(Validador.cpfValido(null));
    }

    @Test
    void cpfComTamanhoErrado(){
        assertFalse(Validador.cpfValido("123"));
    }

    @Test
    void cpfSemPontuacao(){
        assertTrue(Validador.cpfValido("52998224725"));
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
    void emailNulo(){
        assertFalse(Validador.emailValido(null));
    }

    @Test
    void emailComEspaco(){
        assertFalse(Validador.emailValido("heitor calado@gmail.com"));
    }

    @Test
    void emailComDominioBrasileiro(){
        assertTrue(Validador.emailValido("heitor@ufape.edu.br"));
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
    void senhaNula(){
        assertFalse(Validador.senhaForte(null));
    }

    @Test
    void senhaSemMaiuscula(){
        assertFalse(Validador.senhaForte("senha@123"));
    }

    @Test 
    void senhaSemMinuscula(){
        assertFalse(Validador.senhaForte("SENHA@123"));
    }

    @Test 
    void senhaSemNumero(){
        assertFalse(Validador.senhaForte("Senha@abc"));
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

    @Test 
    void dataBissextaEmAnoNormal(){
        assertThrows(DateTimeParseException.class, () -> Validador.converterData("29/02/2025"));
    }
}
