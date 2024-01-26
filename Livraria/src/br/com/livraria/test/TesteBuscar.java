package test;

import dao.BookDao;
import dao.PublisherDao;
import models.Author;
import dao.AuthorDao;
import models.Book;
import models.Publisher;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TesteBuscar {

    private static Scanner scanner = new Scanner(System.in);

    public static void executar(Scanner scan) {
        scanner = scan;

        boolean sair = false;

        while (!sair) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("                       MENU BUSCAR                         ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("1. Buscar Autor");
            System.out.println("2. Buscar Livro");
            System.out.println("3. Buscar Editora");
            System.out.println("0. Sair");
            System.out.println("-----------------------------------------------------------");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        buscarAutor();
                        break;
                    case 2:
                        buscarLivro();
                        break;
                    case 3:
                        buscarEditora();
                        break;
                    case 0:
                        sair = true;
                        break;
                    default:
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("    Opção inválida. Por favor, escolha uma opção válida.");
                        System.out.println("-----------------------------------------------------------");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("     Erro de entrada: Insira um número inteiro válido.");
                System.out.println("-----------------------------------------------------------");
                scanner.next();
            } catch (Exception e) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("     Erro: " + e.getMessage());
                System.out.println("-----------------------------------------------------------");
                 sair = true;
            }
        }
    }

    public static void buscarAutor() {

        AuthorDao dao = new AuthorDao();

        System.out.println("--------------------------------------------  \n" +
                "                 BUSCAR AUTOR               \n" +
                "--------------------------------------------  ");
        System.out.println("1. Buscar autor por nome");
        System.out.println("2. Buscar autor por sobrenome");
        System.out.println("--------------------------------------------  ");
        System.out.println("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            System.out.println("-------------------------------------------- \n" +
                    "Digite o nome do autor que deseja buscar: ");


            String nameAuthor = scanner.nextLine();
            Author author = new AuthorDao().buscarPorName(nameAuthor);

            if (author != null) {
                System.out.println("******************************************** \n" +
                        "               AUTOR ENCONTRADO                 \n" +
                        "******************************************** \n" + author.toString() + "\n" +
                        "******************************************** \n");
            } else {
                System.out.println("--------------------------------------------");
                System.out.println("            Autor não encontrado.");
                System.out.println("--------------------------------------------");
            }
        } else if (opcao == 2) {
            System.out.println("-------------------------------------------- \n" +
                    "Digite o sobrenome do autor que deseja buscar: ");
            String fnameAuthor = scanner.nextLine();
            Author author = dao.buscarPorfName(fnameAuthor);

            if (author != null) {
                System.out.println("******************************************** \n" +
                        "               AUTOR ENCONTRADO                 \n" +
                        "******************************************** \n" + author.toString() + "\n" +
                        "******************************************** \n");
            } else {
                System.out.println("--------------------------------------------");
                System.out.println("            Autor não encontrado.");
                System.out.println("--------------------------------------------");
            }
        } else {
            System.out.println("--------------------------------------------");
            System.out.println("            Opção inválida.");
            System.out.println("--------------------------------------------");
        }

    }

    public static void buscarLivro() {
        Book book = new Book();
        BookDao dao = new BookDao();

        System.out.println("--------------------------------------------  \n" +
                "                 BUSCAR LIVRO               \n" +
                "--------------------------------------------  ");

        System.out.println("Digite um título de livro: ");
        String title = scanner.nextLine();
        book = dao.buscarLivroComAutorPorTitulo(title);

        if (book != null) {
            System.out.println("******************************************** \n" +
                    "               LIVRO ENCONTRADO                 \n" +
                    "******************************************** \n" +
            book.toString() + "\n" +
                    "******************************************** \n");
        } else {
            System.out.println("--------------------------------------------");
            System.out.println("            Livro não encontrado.");
            System.out.println("--------------------------------------------");
        }

    }

    public static void buscarEditora() {
        Publisher publisher = new Publisher();
        PublisherDao dao = new PublisherDao();

        System.out.println("--------------------------------------------  \n" +
                "                BUSCAR EDITORA              \n" +
                "--------------------------------------------  ");

        System.out.println("Digite um nome de editora: ");
        String name = scanner.nextLine();
        publisher = dao.buscarPorNome(name);

        if (publisher != null) {
            System.out.println("******************************************** \n" +
                    "               EDITORA ENCONTRADA                 \n" +
                    "******************************************** \n" +
                    publisher.toString() + "\n" +
                    "******************************************** \n");
        } else {
            System.out.println("--------------------------------------------");
            System.out.println("            Editora não encontrada.");
            System.out.println("--------------------------------------------");
        }
    }
}
