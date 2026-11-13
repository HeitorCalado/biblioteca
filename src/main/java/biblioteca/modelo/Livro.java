package biblioteca.modelo;

public class Livro{
    private final int id;
    private String titulo;
    private Categoria categoria;
    private final int anoPublicacao;
    private final Autor autor;

    public Livro(int id, String titulo, Categoria categoria, int anoPublicacao, Autor autor){
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.anoPublicacao = anoPublicacao;
        this.autor = autor;
    }

    public int getId(){
        return id;
    }

    public String getTitulo(){
        return titulo;
    }

    public Categoria getCategoria(){
        return categoria;
    }

    public int getAnoPublicacao(){
        return anoPublicacao;
    }

    public Autor getAutor(){
        return autor;
    }

    public void setTitulo(){
        this.titulo = titulo;
    }

    public void setCategoria(){
        this.categoria = categoria;
    }

    @Override 
    public String toString(){
        return "Livro #" + id + ": " + titulo
            + " | " + categoria
            + " | " + anoPublicacao
            + " | " + autor.getNome();
    }
}