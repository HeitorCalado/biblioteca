package biblioteca.modelo;

import java.time.LocalDate;
import java.time.Period;

public class Autor{

    private final int id;
    private String nome;
    private final LocalDate dataNascimento;
    private String estilo;

    public Autor(int id, String nome, LocalDate dataNascimento, String estilo){
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.estilo = estilo;
    }

    public int getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public LocalDate getDataNascimento(){
        return dataNascimento;
    }

    public String getEstilo(){
        return estilo;
    }

    public int getIdade(){
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setEstilo(String estilo){
        this.estilo = estilo;
    }

    @Override 
    public String toString(){
        return "Autor #" + id + ": " + nome + " (" + estilo + ")"; 
    }
}
