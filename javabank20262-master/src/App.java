import br.com.javabank.modelo.Conta;
import br.com.javabank.modelo.Corrente;
import br.com.javabank.modelo.Poupanca;

import java.util.Scanner;

public class App {
    // psvm
    // sout
    // final => constante, sempre por o nome da constante em caixa alta
    public static void main(String[] args) {
        System.out.println("JAVABANK - TERMINAL DO CAIXA");
        Scanner entrada = new Scanner(System.in);
        boolean operadorAutenticado = false;


        String menu =
                """
                
                [MENU] - JAVABANK
                1 - Criar/Abrir conta
                2 - Consultar Saldo
                3 - Realizar Deposito
                4 - Realizar Saque
                5 - Realizar Tranferência
                6 - Sair
                """;

        String menuContas = """
                1 - Conta comum
                2 - Conta Corrente
                3 - Conta poupança
                """;


        final int SENHA_OPERADOR = 8888;

        for(int tentativa = 1; tentativa <= 3; tentativa++){
            System.out.println("Informe a sua SENHA");
            int senha = Integer.parseInt(entrada.nextLine()); // converte a entrada (string) em inteiro

            if(senha == SENHA_OPERADOR){
                System.out.println("[SESSÃO INICIADA] - Bem vindo.");
                operadorAutenticado = true;
                break;
            }else{
                System.out.println("[ALERTA] - Senha incorreta!");
            }
        }

        if(!operadorAutenticado){
            System.out.println("[BLOQUEIO] - Limite de tentativas excedidas!");
        }else{
            int opcao;
            Conta conta1 = null;
            Conta conta2 = null;

            do{
                System.out.println(menu);
                System.out.println("Selecione uma opção:");
                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao){
                    case 1 -> {

                        if (conta1 != null && conta2 != null) {
                            System.out.println("[ERRO] - Sistema não suporta mais cadastros!");
                            continue;
                        }
                        if (conta1 == null){
                            System.out.println("Informe o número da conta: ");
                            int numeroConta = Integer.parseInt(entrada.nextLine());
                            System.out.println("Informe o titular da conta: ");
                            String titular = entrada.nextLine();

                            System.out.println("Que tipo de conta deseja criar?");
                            System.out.println(menuContas);
                            int opc = Integer.parseInt(entrada.nextLine());
                            if (opc == 1) {
                                conta1 = new Conta(numeroConta, titular);
                                System.out.println("Conta comum criada com sucesso!");
                            }else if (opc == 2) {
                                //CORRENTE
                                System.out.println("Digite o limite: ");
                                double limite = Double.parseDouble(entrada.nextLine());
                                conta1 = new Corrente(numeroConta, titular, limite);
                                System.out.println("Conta corrente criada com sucesso!");
                            }else if (opc == 3) {
                                //POUPANÇA
                                System.out.println("Defina a taxa de rendimento");
                                double taxaJuros = Double.parseDouble(entrada.nextLine());
                                conta1 = new Poupanca(numeroConta, titular, taxaJuros);
                                System.out.println("Conta Poupança criada com sucesso!");
                            }else{
                                System.out.println("[ERRO] - Opção inválida!");
                            }
                            continue;
                        } else {
                            System.out.println("Informe o número da conta: ");
                            int numeroConta = Integer.parseInt(entrada.nextLine());
                            System.out.println("Informe o titular da conta: ");
                            String titular = entrada.nextLine();

                            System.out.println(menuContas);
                            int opc = Integer.parseInt(entrada.nextLine());
                            if (opc == 1) {
                                conta2 = new Conta(numeroConta, titular);
                                System.out.println("Conta comum criada com sucesso!");

                            } else if (opc == 2) {
                                //CORRENTE
                                System.out.println("Digite o limite: ");
                                double limite = Double.parseDouble(entrada.nextLine());
                                conta2 = new Corrente(numeroConta, titular, limite);
                                System.out.println("Conta corrente criada com sucesso!");
                            } else if (opc == 3) {
                                //POUPANÇA
                                System.out.println("Defina a taxa de rendimento");
                                double taxaJuros = Double.parseDouble(entrada.nextLine());
                                conta2 = new Poupanca(numeroConta, titular, taxaJuros);
                                System.out.println("Conta Poupança criada com sucesso!");
                            } else {
                                System.out.println("[ERRO] - Opção inválida!");
                            }
                        }
                    }
                    case 2 -> {
                        if (conta1 == null) {
                            System.out.println("[ERRO] - Nenhuma conta ativa!");
                            continue;
                        }

                        System.out.println("Informe o numero da conta: ");
                        int numero =  Integer.parseInt(entrada.nextLine());

                        if(conta1.getNumero() == numero) {
                            System.out.println("[SALDO ATULIZADO]");
                            conta1.exibirSaldo();
                        }else if(conta2 != null && conta2.getNumero() == numero) {
                            System.out.println("[SALDO ATULIZADO]");
                            conta2.exibirSaldo();

                        }else{
                            System.out.println("[ERRO] - Número não encontrado!");
                        }

                    }
                    case 3 -> {
                        if (conta1 == null) {
                            System.out.println("[ERRO] - Nenhuma conta ativa!");
                            continue;
                        }else {
                            System.out.println("Informe o valor do Depósito: ");
                            double deposito = Double.parseDouble(entrada.nextLine());
                            double minDeposito = 2;

                            if (deposito < minDeposito) {
                                System.out.printf("O valor mínimo de depósito é de R$ %.2f.", minDeposito);
                                System.out.println("Informe um valor válido!");
                                continue;
                            } else {
                                System.out.println("Informe o número da conta: ");
                                int numeroConta = Integer.parseInt(entrada.nextLine());
                                if (conta1.getNumero() == numeroConta) {
                                    conta1.depositar(deposito);
                                    System.out.println("[SUCESSO] - Operação realizada com sucesso\n");
                                    System.out.printf("Saldo atualizado: R$ %.2f.\n", conta1.getSaldo());
                                } else if (conta2 != null && conta2.getNumero() == numeroConta) {
                                    conta2.depositar(deposito);
                                    System.out.println("[SUCESSO] - Operação realizada com sucesso\n");
                                    System.out.printf("Saldo atualizado: R$ %.2f.\n", conta2.getSaldo());
                                } else {
                                    System.out.println("[ERRO] - Conta não encontrada!");
                                }
                            }
                        }
                    }
                    case 4 -> {
                        if (conta1 == null) {
                            System.out.println("[ERRO] - Nenhuma conta ativa!");
                            continue;
                        } else {
                            System.out.println("Informe o número da conta: ");
                            int numeroConta = Integer.parseInt(entrada.nextLine());
                            if(numeroConta == conta1.getNumero()) {
                                System.out.println("Informe o valor do saque: ");
                                double saque = Double.parseDouble(entrada.nextLine());
                                if (saque < 2) {
                                    System.out.println("[ERRO] - Valor inválido para saque");
                                    continue;
                                } else {
                                    conta1.sacar(saque);
                                }
                            } else if(conta2 != null && numeroConta == conta2.getNumero()) {
                                    System.out.println("Informe o valor do saque: ");
                                    double saque = Double.parseDouble(entrada.nextLine());
                                    if (saque < 2) {
                                        System.out.println("[ERRO] - Valor inválido para saque");
                                        continue;
                                    } else {
                                        conta2.sacar(saque);
                                    }
                            } else {
                                System.out.println("[ERRO] - Conta não encontrada!");
                            }
                        }
                    }
                    case 5 -> {
                        /*if(){
                            if(conta1.getSaldo() > 0 || conta2.getSaldo() > 0){
                                System.out.println("Informe a conta que deseja realizar a operação: ");
                                System.out.printf("1 - %d\n", conta1.getNumero());
                                System.out.printf("2 - %d\n", conta2.getNumero());
                                int op = Integer.parseInt(entrada.nextLine());
                                if(op == 1){
                                    System.out.println("Informe o valor da transferência: ");
                                    double transf = Double.parseDouble(entrada.nextLine());
                                    conta1.transferir(transf,conta2);
                                    System.out.println("[SUCESSO] - Operação realizada com sucesso\n");
                                    System.out.printf("Saldo atualizado: R$ %.2f.\n", conta1.getSaldo());
                                    System.out.printf("%s recebeu uma transferência de %s\n", conta2.getTitular(),conta1.getTitular());
                                } else if (op == 2) {
                                    System.out.println("Informe o valor do saque: ");
                                    double saque = Double.parseDouble(entrada.nextLine());
                                    conta2.transferir(saque,conta1);
                                    System.out.println("[SUCESSO] - Operação realizada com sucesso\n");
                                    System.out.printf("Saldo atualizado: R$ %.2f.\n", conta2.getSaldo());
                                    System.out.printf("%s recebeu uma transferência de %s\n", conta1.getTitular(),conta2.getTitular());
                                }else{
                                    System.out.println("[ERRO] - Opção inválida!");
                                }
                            }else{
                                System.out.println("[AVISO] - Conta sem saldo!");
                            }
                        }else {
                            System.out.println("[ERRO] - Nenhuma conta ativa!");
                        }*/

                    }
                    case 6 -> {
                        System.out.println("[ENCERRANDO] - Encerrando o sistema");
                    }
                    default -> {
                        System.out.println("[ERRO] - Opção Inválida! Tente novamente.");
                    }
}
            }while(opcao != 6);
        }
        entrada.close();
    }
}