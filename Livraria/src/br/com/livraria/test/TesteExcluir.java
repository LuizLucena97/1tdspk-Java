package test;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.AuthorDao;
import dao.BookDao;
import dao.BooksAuthorsDao;
import dao.PublisherDao;
import models.Author;

public class TesteExcluir {
    private static Scanner scanner = new Scanner(System.in);

    public static void executar(Scanner scanner2) {
        scanner = scanner2;

        boolean sair = false;

        while (!sair) {
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("          Menu Excluir (Livro, Autor ou Editora)           ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("1. Excluir Livro");
            System.out.println("2. Excluir Autor");
            System.out.println("3. Excluir Editora");
            System.out.println("0. Sair");
            System.out.println("-----------------------------------------------------------");

            System.out.print("Escolha uma opção: ");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        excluirLivro();
                        break;
                    case 2:
                        excluirAutor();
                        break;
                    case 3:
                        excluirEditora();
                        break;
                    case 0:
                        sair = true;
                        break;
                    default:
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
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

    public static void excluirLivro() {
        System.out.println("\n-----------------------------------------------------------\n" +
                "                      EXCLUIR LIVRO                          \n" +
                "-----------------------------------------------------------");
        System.out.print("Digite o nome do livro que deseja excluir: ");
        String title = scanner.nextLine();

        BooksAuthorsDao dao = new BooksAuthorsDao();
        String isbn = dao.buscarIsbnPorTitle(title);

        if (isbn != null) {

            BooksAuthorsDao booksAuthorsDao = new BooksAuthorsDao();
            boolean sucessoExclusaoAutoresLivros = booksAuthorsDao.excluirLivroAutorPorISBN(isbn);

            boolean sucesso1 = new BookDao().excluir(title);

            if (sucesso1 && sucessoExclusaoAutoresLivros) {
                System.out.println("\n***********************************************************");
                System.out.println("               Livro excluído com sucesso.");
                System.out.println("***********************************************************");
            } else {
                System.out.println("\n-----------------------------------------------------------");
                System.out.println("             Não foi possível excluir o livro.");
                System.out.println("-----------------------------------------------------------");
            }
        } else {
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("                  Livro não encontrado.");
            System.out.println("------------------------------------------------------------");
        }
    }

    public static void excluirAutor() {
        System.out.println("\n-----------------------------------------------------------\n" +
                "                      EXCLUIR AUTOR                          \n" +
                "-----------------------------------------------------------");
        System.out.print("Digite o nome do autor que deseja excluir: ");
        String autorNome = scanner.nextLine();

        AuthorDao dao = new AuthorDao();
        Author author = dao.buscarPorName(autorNome);

        if (author != null) {
            System.out.println("\n###################################################################");
            System.out.println("Tem certeza de que deseja excluir os Livros do autor" + author.getName() + "? (S/N)");
            System.out.println("###################################################################");
            String confirmacao = scanner.nextLine().trim().toLowerCase();

            if (confirmacao.equals("s")) {
                BooksAuthorsDao dao2 = new BooksAuthorsDao();
                String isbn = dao2.buscarIsbnPorNomeAutor(autorNome);

                if (isbn != null) {
                    BooksAuthorsDao booksAuthorsDao = new BooksAuthorsDao();
                    boolean sucessoExclusaoAutoresLivros = booksAuthorsDao.excluirLivroAutorPorISBN(isbn);

                    BookDao livroDao = new BookDao();
                    boolean sucessoExclusaoLivros = livroDao.excluirLivrosPorISBN(isbn);

                    int idAuthor = author.getAuthor_id();
                    boolean sucessoExclusaoAuthor = dao.excluir(idAuthor);

                    if (sucessoExclusaoAuthor && sucessoExclusaoAutoresLivros && sucessoExclusaoLivros) {
                        System.out.println("***********************************************************");
                        System.out.println("                 Autor excluído com sucesso!");
                        System.out.println("***********************************************************");
                    } else {
                        System.out.println("\n-----------------------------------------------------------");
                        System.out.println("              Não foi possível excluir o autor.");
                        System.out.println("\n-----------------------------------------------------------");
                    }
                } else {
                    System.out.println("\n-----------------------------------------------------------");
                    System.out.println("          Autor não possui livros associados.");
                    System.out.println("-----------------------------------------------------------");
                }
            } else {
                System.out.println("------------------------------------------------------------");
                System.out.println("            Exclusão cancelada pelo usuário.");
                System.out.println("------------------------------------------------------------");
            }
        } else {
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("          Autor não encontrado no banco de dados.");
            System.out.println("-----------------------------------------------------------");
        }
    }


    public static void excluirEditora() {
        System.out.println("\n-----------------------------------------------------------\n" +
                "                      EXCLUIR EDITORA                       \n" +
                "-----------------------------------------------------------");
        System.out.print("Digite o nome da editora que deseja excluir: ");
        String editoraNome = scanner.nextLine();

        PublisherDao publisherDao = new PublisherDao();

        int publisher_id = publisherDao.buscarIdPorNome(editoraNome);

        if (publisher_id != -1) {

            System.out.println("\n###################################################################");
            System.out.println("Tem certeza de que deseja excluir a editora " + editoraNome + "? (S/N)");
            System.out.println("###################################################################");
            String confirmacao = scanner.nextLine().trim().toLowerCase();

            if (confirmacao.equals("s")) {
                BooksAuthorsDao dao2 = new BooksAuthorsDao();
                String isbn = dao2.buscarIsbnPorPublisherId(publisher_id);

                if (isbn != null) {
                    BooksAuthorsDao booksAuthorsDao = new BooksAuthorsDao();
                    boolean sucessoExclusaoAutoresLivros = booksAuthorsDao.excluirLivroAutorPorISBN(isbn);

                    BookDao livroDao = new BookDao();
                    boolean sucessoExclusaoLivros = livroDao.excluirLivrosPorPublisher_id(publisher_id);

                    boolean sucesso = publisherDao.excluir(publisher_id);

                    if (sucesso && sucessoExclusaoAutoresLivros && sucessoExclusaoLivros) {
                        System.out.println("***********************************************************");
                        System.out.println("                Editora excluída com sucesso!");
                        System.out.println("***********************************************************");
                    } else {
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("           Não foi possível excluir a editora.");
                        System.out.println("-----------------------------------------------------------");
                    }
                } else {
                    System.out.println("\n-----------------------------------------------------------");
                    System.out.println("         A editora não possui livros associados.");
                    System.out.println("-----------------------------------------------------------");
                }
            } else {
                System.out.println("-----------------------------------------------------------");
                System.out.println("             Exclusão cancelada pelo usuário.");
                System.out.println("-----------------------------------------------------------");
            }
        } else {
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("         Editora não encontrada no banco de dados.");
            System.out.println("-----------------------------------------------------------");
        }
    }
}

