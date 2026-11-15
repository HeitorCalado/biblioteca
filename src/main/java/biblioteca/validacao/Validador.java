package biblioteca.validacao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class Validador {

    private static final DateTimeFormatter FORMATO_DATA =
        DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    private Validador(){
    }


    public static boolean cpfValido(String cpf){
        if(cpf == null){
            return false;
        }

        String numeros = cpf.replaceAll("[^0-9]", "");

        if(numeros.length() != 11){
            return false;
        } 

        if(numeros.chars().distinct().count() == 1){
            return false;
        }

        int soma = 0;
        for(int i = 0; i < 9; i++){
            soma += (numeros.charAt(i) - '0') * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        if(primeiroDigito >= 10){
            primeiroDigito = 0;
        }

        soma = 0;
        for(int i = 0; i < 10; i++){
            soma += (numeros.charAt(i) - '0') * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if(segundoDigito >= 10){
            segundoDigito = 0;
        }

        return primeiroDigito == (numeros.charAt(9) - '0')
                && segundoDigito == (numeros.charAt(10) - '0');
    }

    public static boolean emailValido(String email){
        if(email == null){
            return false;
        }
        return email.matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$");
    }

    public static boolean senhaForte(String senha){
        if(senha == null || senha.length() < 8){
            return false;
        }

        boolean temMaiuscula = false;
        boolean temMinuscula = false;
        boolean temNumero = false;
        boolean temEspecial = false;

        for(char c : senha.toCharArray()){
            if(Character.isUpperCase(c)){
                temMaiuscula = true;
            } else if(Character.isLowerCase(c)){
                temMinuscula = true;
            } else if(Character.isDigit(c)){
                temNumero = true;
            } else{
                temEspecial = true;
            }
        }

        return temMaiuscula && temMinuscula && temNumero && temEspecial;
    }

    public static LocalDate converterData(String texto){
        return LocalDate.parse(texto, FORMATO_DATA);
    }
}
