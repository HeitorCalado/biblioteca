package biblioteca;

import biblioteca.fachada.Fachada;
import biblioteca.modelo.Autor;
import biblioteca.modelo.Categoria;
import biblioteca.modelo.Livro;
import biblioteca.modelo.Usuario;
import biblioteca.validacao.Validador;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;

public class Main {
    
    public static void main(String [] args){
        Scanner teclado = new Scanner(System.in);
        Fachada fachada = new Fachada();

        int opcao = -1;

        while(opcao != 0){
             System.out.println();
            System.out.println("========= BIBLIOTECA =========");
            System.out.println("1 - Cadastrar autor");
            System.out.println("2 - Listar autores");
            System.out.println("3 - Alterar nome do autor");
            System.out.println("4 - Remover autor");
            System.out.println("5 - Cadastrar livro");
            System.out.println("6 - Listar livros");
            System.out.println("7 - Listar livros de um autor");
            System.out.println("8 - Alterar título do livro");
            System.out.println("9 - Alterar categoria do livro");
            System.out.println("10 - Remover livro");
            System.out.println("11 - Cadastrar usuário");
            System.out.println("12 - Listar usuários");
            System.out.println("13 - Alterar nome do usuário");
            System.out.println("14 - Alterar e-mail do usuário");
            System.out.println("15 - Alterar senha do usuário");
            System.out.println("16 - Remover usuário");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            try{
                opcao = Integer.parseInt(teclado.nextLine());

                if(opcao < 0 || opcao > 16){
                    System.out.println("Opção inválida. Escolha de 0 a 16.");
                    continue;
                }

                switch (opcao){
                    case 1: 
                        System.out.println("Nome: ");
                        String nome = teclado.nextLine();
                        System.out.println("Data de nascimento (dd/mm/aaaa): ");
                        LocalDate nascimento = Validador.converterData(teclado.nextLine());
                        System.out.println("Estilo: ");
                        String estilo = teclado.nextLine();

                        Autor autor = fachada.cadastrarAutor(nome, nascimento, estilo);
                        System.out.println("Cadastrado: " + autor);
                        break;
                    
                    case 2: 
                        List<Autor> autores = fachada.listarAutores();
                        if(autores.isEmpty()){
                            System.out.println("Nenhum autor cadastrado.");
                        } else{
                            for(Autor a : autores){
                                System.out.println(a);
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Id do autor: ");
                        int idAutorAlterar = Integer.parseInt(teclado.nextLine());
                        System.out.println("Novo nome: ");
                        String novoNome = teclado.nextLine();

                        fachada.alterarNomeAutor(idAutorAlterar, novoNome);
                        System.out.println("Nome alterado.");
                        break;

                    case 4:
                        System.out.println("Id do autor: ");
                        int idAutorRemover = Integer.parseInt(teclado.nextLine());
                        
                        fachada.removerAutor(idAutorRemover);
                        System.out.println("Autor removido.");
                        break;

                    case 5: 
                        System.out.println("Título: ");
                        String titulo = teclado.nextLine();

                        Categoria[] categorias = Categoria.values();
                        for(int i = 0; i < categorias.length; i++){
                            System.out.println((i+1) + " - " + categorias[i]);
                        }
                        System.out.println("Categoria: ");
                        int numeroCategoria = Integer.parseInt(teclado.nextLine());
                        if(numeroCategoria < 1 || numeroCategoria > categorias.length){
                            System.out.println("Categoria inválida.");
                            break;
                        }
                        Categoria categoria = categorias[numeroCategoria - 1];

                        System.out.println("Ano de publicação: ");
                        int ano = Integer.parseInt(teclado.nextLine());
                        System.out.println("Id do autor: ");
                        int idAutor = Integer.parseInt(teclado.nextLine());
                        
                        Livro livro = fachada.cadastrarLivro(titulo, categoria, ano, idAutor);
                        System.out.println("Cadastrado: " + livro);
                        break;

                    case 6:
                        List<Livro> livrosCadastrados = fachada.listarLivros();
                        if(livrosCadastrados.isEmpty()){
                            System.out.println("Nenhum livro cadastrado.");
                        } else{
                            for(Livro l : livrosCadastrados){
                                System.out.println(l);
                            }
                        }
                        break;
                        
                    case 7:
                        System.out.println("Id do autor: ");
                        int idAutorLivros = Integer.parseInt(teclado.nextLine());
                        
                        Autor autorDosLivros = fachada.buscarAutor(idAutorLivros);
                        List<Livro> livrosDoAutor = fachada.listarLivrosPorAutor(idAutorLivros);

                        if(livrosDoAutor.isEmpty()){
                            System.out.println(autorDosLivros.getNome() + " ainda não tem livros cadastrados.");

                        } else{
                            System.out.println("Livros de " + autorDosLivros.getNome() + ": ");
                            for(Livro l : livrosDoAutor){
                                System.out.println(l);
                            }
                        }
                        break;

                    case 8: 
                        System.out.println("Id do livro: ");
                        int idLivroAlterar = Integer.parseInt(teclado.nextLine());
                        System.out.println("Novo título: ");
                        String novoTitulo = teclado.nextLine();

                        fachada.alterarTituloLivro(idLivroAlterar, novoTitulo);
                        System.out.println("Título alterado.");
                        break;

                    case 9:
                        System.out.println("Id do livro: ");
                        int idLivroAlterarCategoria = Integer.parseInt(teclado.nextLine());

                        Categoria[] categoriasLivro = Categoria.values();
                        for(int i = 0; i < categoriasLivro.length; i++){
                            System.out.println((i+1) + " - " + categoriasLivro[i]);
                        }
                        System.out.println("Categoria: ");
                        int numCategoria = Integer.parseInt(teclado.nextLine());
                        if(numCategoria < 1 || numCategoria > categoriasLivro.length){
                            System.out.println("Categoria inválida.");
                            break;
                        }

                        Categoria categoriaC9 = categoriasLivro[ numCategoria - 1];
                        fachada.alterarCategoriaLivro(idLivroAlterarCategoria, categoriaC9);
                        System.out.println("Categoria alterada.");
                        break;

                    case 10: 
                        System.out.println("Id do livro: ");
                        int idLivroRemover = Integer.parseInt(teclado.nextLine());

                        fachada.removerLivro(idLivroRemover);
                        System.out.println("Livro removido.");
                        break;

                    case 11:
                        System.out.println("Nome: ");
                        String nomeUsuario = teclado.nextLine();
                        System.out.println("CPF: ");
                        String cpf = teclado.nextLine();
                        System.out.println("E-mail: ");
                        String email = teclado.nextLine();
                        System.out.println("Senha: ");
                        String senha = teclado.nextLine();

                        Usuario usuario = fachada.cadastrarUsuario(nomeUsuario, cpf, email, senha);
                        System.out.println("Cadastrado: " + usuario);
                        break;

                    case 12:
                        List<Usuario> usuarios = fachada.listarUsuarios();
                        if(usuarios.isEmpty()){
                            System.out.println("Nenhum usuário cadastrado.");
                        } else{
                            for(Usuario u : usuarios){
                                System.out.println(u);
                            }
                        }
                        break;

                    case 13:
                        System.out.println("Id do usuário: ");
                        int idUsuarioNome = Integer.parseInt(teclado.nextLine());
                        System.out.println("Novo nome: ");
                        String novoNomeUsuario = teclado.nextLine();

                        fachada.alterarNomeUsuario(idUsuarioNome, novoNomeUsuario);
                        System.out.println("Nome alterado.");
                        break;

                    case 14:
                        System.out.println("Id do usuário: ");
                        int idUsuarioEmail = Integer.parseInt(teclado.nextLine());
                        System.out.println("Novo e-mail: ");
                        String novoEmail = teclado.nextLine();

                        fachada.alterarEmailUsuario(idUsuarioEmail, novoEmail);
                        System.out.println("E-mail alterado.");
                        break;

                    case 15:
                        System.out.println("Id do usuário: ");
                        int idUsuarioSenha = Integer.parseInt(teclado.nextLine());
                        System.out.println("Nova senha: ");
                        String novaSenha = teclado.nextLine();

                        fachada.alterarSenhaUsuario(idUsuarioSenha, novaSenha);
                        System.out.println("Senha alterada.");
                        break;

                    case 16:
                        System.out.println("Id do usuário: ");
                        int idUsuarioRemover = Integer.parseInt(teclado.nextLine());

                        fachada.removerUsuario(idUsuarioRemover);
                        System.out.println("Usuário removido.");
                        break;

                    case 0:
                        System.out.println("Até mais!");
                        break;
                }                   
            }   catch (NumberFormatException erro){
                System.out.println("Erro: digite um número.");
            }   catch (DateTimeParseException erro){
                System.out.println("Erro: data inválida. Use dd/mm/aaaa.");
            }   catch (IllegalArgumentException erro){
                System.out.println("Erro: " + erro.getMessage());
            }
        }

        teclado.close();
    }
}
