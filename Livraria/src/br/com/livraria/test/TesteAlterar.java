package test;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dao.AuthorDao;
import dao.BookDao;
import dao.PublisherDao;
import models.Author;
import models.Book;
import models.Publisher;

public class TesteAlterar {

    private static Scanner scanner = new Scanner(System.in);

    public static void executar(Scanner scanner2) {
        scanner = scanner2;

        boolean sair = false;

        while (!sair) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("                       MENU ALTERAR                         ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("1. Aterar Livro");
            System.out.println("2. Alterar Autor");
            System.out.println("3. Alterar Editora");
            System.out.println("0. Sair");
            System.out.println("-----------------------------------------------------------");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1:
                        alterarLivro();
                        break;
                    case 2:
                        alterarAutor();
                        break;
                    case 3:
                        alterarEditora();
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
            }
    }
}

    public static boolean alterarLivro() {

        boolean sair = false;

        while (!sair) {
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("                  MENU ALTERAR LIVRO                       ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("1. Alterar Titulo");
            System.out.println("2. Alterar Preço");
            System.out.println("3. Alterar Editora");
            System.out.println("0. Sair");
            System.out.println("-----------------------------------------------------------");
            System.out.print("Escolha uma opção: ");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                System.out.println("\n-----------------------------------------------------------");
                    System.out.println("Digite o titulo:");
                    String title = scanner.nextLine();

                    Book book = new Book();
                    BookDao dao = new BookDao();
                    book = dao.buscarLivroComAutorPorTitulo(title);

                    System.out.println("-----------------------------------------------------------");
                    System.out.println(book);
                    System.out.println("-----------------------------------------------------------");

                    if (book.getTitle() != null) {
                        System.out.println("Digite NOVO titulo:");
                        String newTitle = scanner.nextLine();

                        book.setTitle(newTitle);

                        dao.atualizarTituloDoLivro(title, newTitle);

                    } else {
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("Livro com título " + title + " não encontrado.");
                        System.out.println("-----------------------------------------------------------");
                    }
                    break;

                case 2:
                    System.out.println("\n-----------------------------------------------------------");
                    System.out.println("Digite o titulo do livro:");
                    String title2 = scanner.nextLine();

                    Book book2 = new Book();
                    BookDao dao2 = new BookDao();
                    book2 = dao2.buscarLivroComAutorPorTitulo(title2);

                    System.out.println("-----------------------------------------------------------");
                    System.out.println(book2);
                    System.out.println("-----------------------------------------------------------");

                    if (book2.getTitle() != null) {
                        System.out.println("Digite NOVO preço:");
                        double newPrice = scanner.nextDouble();

                        book2.setPrice(newPrice);

                        dao2.atualizarPrecoDoLivro(title2, newPrice);

                    } else {
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("Livro com título " + title2 + " não encontrado.");
                        System.out.println("-----------------------------------------------------------");
                    }
                    break;
                case 3:
                System.out.println("\n-----------------------------------------------------------");
                    System.out.println("Digite o titulo do livro:");
                    String title3 = scanner.nextLine();

                    Book book3 = new Book();
                    BookDao dao3 = new BookDao();
                    book3 = dao3.buscarLivroComAutorPorTitulo(title3);

                    if (book3.getTitle() != null) {
                        List<Publisher> publishers = new PublisherDao().listarPublisher();
                        System.out.println("\n---------- Editoras ---------------\n");
                        for (int i = 0; i < publishers.size(); i++) {
                            System.out.println((i + 1) + ". " + publishers.get(i).getName());
                        }
                        System.out.println("-------------------------------------");

                        System.out.println("Escolha uma editora:");
                        int escolhaEditora = scanner.nextInt();

                        if (escolhaEditora >= 1 && escolhaEditora <= publishers.size()) {

                        Publisher editoraEscolhida = publishers.get(escolhaEditora - 1);
                        book3.setPublisher_id(editoraEscolhida.getPublishers_id());
                            
                        dao3.atualizarEditoraDoLivro(title3, editoraEscolhida.getPublishers_id());
                        }

                    } else {
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("Livro com título " + title3 + " não encontrado.");
                        System.out.println("-----------------------------------------------------------");
                    }
                    break;

                    case 0:
                        sair = true;
                         break;
            }

        }
        return sair;
        
    }


    public static boolean alterarAutor() {

        boolean sair = false;

        while (!sair) {
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("                   MENU ALTERAR AUTOR                       ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("1. Alterar Nome");
            System.out.println("2. Alterar Sobrenome");
            System.out.println("0. Sair");
            System.out.println("-----------------------------------------------------------");
            System.out.print("Escolha uma opção: ");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    System.out.println("\n-----------------------------------------------------------");
                    System.out.println("Digite o nome do autor:");
                    String nome = scanner.nextLine();

                    Author author = new Author();
                    AuthorDao dao = new AuthorDao();
                    author = dao.buscarPorName(nome);

                    System.out.println("-----------------------------------------------------------");
                    System.out.println(author);
                    System.out.println("-----------------------------------------------------------");


                    if (author.getName() != null) {
                        System.out.println("Digite NOVO nome:");
                        String newName = scanner.nextLine();

                        dao.atualizarAutor(newName, nome);

                    } else {
                        System.out.println("Autor " + nome + " não encontrado.");
                    }
                    break;

                case 2:
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("Digite o nome do autor:");
                    String nome2 = scanner.nextLine();

                    Author author2 = new Author();
                    AuthorDao dao2 = new AuthorDao();
                    author2 = dao2.buscarPorName(nome2);

                    System.out.println("-----------------------------------------------------------");
                    System.out.println(author2);
                    System.out.println("-----------------------------------------------------------");

                    if (author2.getName() != null) {
                        System.out.println("Digite NOVO sobrenome:");
                        String newFName = scanner.nextLine();

                        dao2.atualizarSobrenomeAutor(newFName, nome2);

                    } else {
                        System.out.println("Autor " + nome2 + " não encontrado.");
                    }
                    break;

                    case 0:
                        sair = true;
                        break;

            }

        }
        return sair;
        
    }

    public static boolean alterarEditora() {

        boolean sair = false;

        while (!sair) {
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("                   MENU ALTERAR EDITORA                      ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("1. Alterar Nome");
            System.out.println("2. Alterar URL");
            System.out.println("0. Sair");
            System.out.println("-----------------------------------------------------------");
            System.out.print("Escolha uma opção: ");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    System.out.println("Digite o nome da editora:");
                    String nome = scanner.nextLine();

                    Publisher publisher = new Publisher();
                    PublisherDao dao = new PublisherDao();
                    publisher = dao.buscarPorNome(nome);

                    System.out.println("-----------------------------------------------------------");
                    System.out.println(publisher);
                    System.out.println("-----------------------------------------------------------");


                    if (publisher.getName() != null) {
                        System.out.println("Digite NOVO nome de editora:");
                        String newName = scanner.nextLine();

                        dao.atualizarPublisherName(newName, nome);

                    } else {
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("          Editora " + nome + " não encontrada.");
                        System.out.println("-----------------------------------------------------------");
                    }
                    break;

                case 2:
                    System.out.println("Digite o nome da editora:");
                    String nome2 = scanner.nextLine();

                    Publisher publisher2 = new Publisher();
                    PublisherDao dao2 = new PublisherDao();
                    publisher2 = dao2.buscarPorNome(nome2);

                    System.out.println("-----------------------------------------------------------");
                    System.out.println(publisher2);
                    System.out.println("-----------------------------------------------------------");

                    if (publisher2.getName() != null) {
                        System.out.println("Digite NOVA URL:");
                        String newUrl = scanner.nextLine();

                        dao2.atualizarUrlPublisher(newUrl, nome2);

                    } else {
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("         Editora " + nome2 + " não encontrada.");
                        System.out.println("-----------------------------------------------------------");
                    }
                    break;

                    case 0:
                        sair = true;
                        break;

            }

        }
        return sair;
        
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

