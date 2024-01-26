package test;

import dao.AuthorDao;
import dao.BookDao;
import dao.BooksAuthorsDao;
import dao.PublisherDao;
import models.Author;
import models.Book;
import models.Publisher;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.sql.SQLException;


public class TesteIncluir {
    
    private static Scanner scanner = new Scanner(System.in);

    public static void executar(Scanner scanner2) {
        scanner = scanner2;

        boolean sair = false;

        while (!sair) {
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("          Menu Cadastrar (Livro, Autor ou Editora)           ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Cadastrar Autor");
            System.out.println("3. Cadastrar Editora");
            System.out.println("0. Sair");
            System.out.println("-----------------------------------------------------------");

            System.out.print("Escolha uma opção: ");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        incluirLivro();
                        break;
                    case 2:
                        incluirAutor();
                        break;
                    case 3:
                        incluirEditora();
                        break;
                    case 0:
                        sair = true;
                        break;
                    default:
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("   Opção inválida. Por favor, escolha uma opção válida.");
                        System.out.println("-----------------------------------------------------------");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("     Erro de entrada: Insira um número inteiro válido.");
                System.out.println("-----------------------------------------------------------");
                scanner.next();
            }
        }
    }

    public static void incluirLivro() {
        BookDao bookDao = new BookDao();
    
        Book books = new Book();
        System.out.println("-------------------------------------------------------------\n" +
                "                     CADASTRAR LIVRO                          \n" +
                "-------------------------------------------------------------");
        System.out.println("Digite o nome do livro: ");
        books.setTitle(scanner.nextLine());
    
        System.out.println("Digite o ISBN: ");
        books.setIsbn(scanner.nextLine());
    
        System.out.println("Digite o preço do livro: ");
        books.setPrice(scanner.nextDouble());
    
        List<Publisher> publishers = new PublisherDao().listarPublisher();
    
        System.out.println("\n---------- Editoras ---------------");
    
        for (int i = 0; i < publishers.size(); i++) {
            System.out.println((i + 1) + ". " + publishers.get(i).getName());
        }
        System.out.println("----------------------------------");
    
        System.out.println("Escolha uma editora (Digite um número):");
        int escolhaEditora = scanner.nextInt();
    
        if (escolhaEditora >= 1 && escolhaEditora <= publishers.size()) {
            Publisher editoraEscolhida = publishers.get(escolhaEditora - 1);
            books.setPublisher_id(editoraEscolhida.getPublishers_id());
    
            scanner.nextLine();
    
            List<Author> authors = new AuthorDao().listarAuthor();
            System.out.println("-----------------------------------");
            System.out.println("\n-------- Autores disponíveis --------");
    
            for (int i = 0; i < authors.size(); i++) {
                System.out.println((i + 1) + ". " + authors.get(i).getName());
            }
    
            System.out.println("--------------------------------------");
            System.out.println("Digite o nome do autor : ");
            String authorName = scanner.nextLine();
    
            Author selectedAuthor = Author.findAuthorByName(authorName, authors);
    
            if (selectedAuthor != null) {
                try {
                    bookDao.incluir(books);
    
                    int author_id = selectedAuthor.getAuthor_id();
    
                    BooksAuthorsDao booksAuthorsDao = new BooksAuthorsDao();
                    booksAuthorsDao.inserirBooksAuthors(books.getIsbn(), author_id);
    
                    System.out.println("\n***********************************************************");
                    System.out.println("                 Livro cadastrado com sucesso!");
                    System.out.println("***********************************************************");
                } catch (Exception e) {
                    System.err.println("Erro de SQL ao cadastrar o livro: Dados duplicados ou inconsistentes ");
                }
            } else {
                System.out.println("-----------------------------------");
                System.out.println("Autor não encontrado. Tente novamente.");
                System.out.println("-----------------------------------");
            }
        } else {
            System.out.println("-----------------------------------------------------------");
            System.out.println("                  Erro! Livro não cadastrado.");
            System.out.println("-----------------------------------------------------------");
        }
    }
    

    public static void incluirAutor() {

        AuthorDao authorDao = new AuthorDao();

        Author authors = new Author();
        System.out.println("-----------------------------------------------------------\n" +
                "                    CADASTRAR AUTOR                          \n" +
                "-----------------------------------------------------------");
        System.out.println("Digite o nome do autor: ");
        authors.setName(scanner.nextLine());

        System.out.println("Digite o sobrenome do autor: ");
        authors.setfName(scanner.nextLine());

        authorDao.incluir(authors);

        System.out.println("***********************************************************");
        System.out.println("               Autor cadastrado com sucesso!");
        System.out.println("***********************************************************");
        

    }

    public static void incluirEditora() {
        PublisherDao publisherDao = new PublisherDao();

        Publisher publishers = new Publisher();
        System.out.println("-----------------------------------------------------------\n" +
                "                    CADASTRAR EDITORA                          \n" +
                "-----------------------------------------------------------");
        System.out.println("Digite o nome da editora: ");
        publishers.setName(scanner.nextLine());

        System.out.println("Digite o site da editora: ");
        publishers.setUrl(scanner.nextLine());

        publisherDao.incluir(publishers);

        System.out.println("***********************************************************");
        System.out.println("              Editora cadastrada com sucesso!");
        System.out.println("***********************************************************");
       
    }

}
