import java.util.InputMismatchException;
import java.util.Scanner;

public class TestePrincipal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean sair = false;

        while (!sair) {
            System.out.println("\n**********************************************************");
            System.out.println("                       LIVRARIA                            ");
            System.out.println("***********************************************************");
            System.out.println("1. Buscar Item");
            System.out.println("2. Incluir Item");
            System.out.println("3. Excluir Item");
            System.out.println("4. Alterar Item");
            System.out.println("0. Sair");
            System.out.println("***********************************************************");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1:
                        test.TesteBuscar.executar(scanner);
                        break;
                    case 2:
                        test.TesteIncluir.executar(scanner);
                        break;
                    case 3:
                        test.TesteExcluir.executar(scanner);
                        break;
                    case 4:
                        test.TesteAlterar.executar(scanner);
                        break;
                    case 0:
                        System.out.println("-----------------------------------------------------------");
                        System.out.println("              Saindo do programa. Até logo!");
                        System.out.println("-----------------------------------------------------------");
                        sair = true; 
                        break;
                    default:
                        System.err.println("-----------------------------------------------------------");
                        System.err.println("    Opção inválida. Por favor, escolha uma opção válida.");
                        System.err.println("-----------------------------------------------------------");
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("-----------------------------------------------------------");
                System.err.println("     Erro de entrada: Insira um número inteiro válido.");
                System.err.println("-----------------------------------------------------------");
                scanner.nextLine();
            }
        }

        scanner.close(); 
    }
}