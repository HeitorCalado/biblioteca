package biblioteca.modelo;

public enum Categoria{

    ROMANCE("Romance"),
    FICCAO_CIENTIFICA("Ficção científica"),
    FANTASIA("Fantasia"),
    SUSPENSE("Suspense"),
    TERROR("Terror"),
    BIOGRAFIA("Biografia"),
    HISTORIA("História"),
    TECNOLOGIA("Tecnologia"),
    INFANTIL("Infantil");

    private final String descricao;

    Categoria(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }

    @Override 
    public String toString(){
        return descricao;
    }
}
