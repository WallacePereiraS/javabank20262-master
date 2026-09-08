package br.com.javabank;

import br.com.javabank.modelo.Conta;

import java.util.Scanner;

public class App {
    // psvm
    // sout
    // final => constante, sempre por o nome da constante em caixa alta
    public static void main(String[] args) {
        System.out.println("JAVABANK - TERMINAL DO CAIXA");

        Scanner entrada = new Scanner(System.in);
        boolean operadorAutenticado = false;

        String menu = """
                
                [MENU] - JAVABANK
                1 - Criar/Abrir conta
                2 - Consultar Saldo
                3 - Realizar Deposito
                4 - Realizar Saque
                5 - Realizar Tranferência
                6 - Sair
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

        if(operadorAutenticado == false){
            System.out.println("[BLOQUEIO] - Limite de tentativas excedidas!");
        }else{
            //int numeroConta = 0;
            //String titular = "";
            double saldo = 0;
            boolean contaAtiva = false;
            int opcao = 0;
            Conta conta1 = null;
            Conta conta2 = null;

            do{
                System.out.println(menu);
                System.out.println("Selecione uma opção:");
                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao){
                    case 1 -> {
                        System.out.println("Informe o número da primeira conta: ");
                        int numeroConta1 = Integer.parseInt(entrada.nextLine());
                        System.out.println("Informe o titular da primeira conta: ");
                        String titular1 = entrada.nextLine();
                        conta1 = new Conta(numeroConta1, titular1);

                        System.out.println("Informe o número da segunda conta: ");
                        int numeroConta2 = Integer.parseInt(entrada.nextLine());
                        System.out.println("Informe o titular da segunda conta: ");
                        String titular2 = entrada.nextLine();
                        conta2 = new Conta(numeroConta2, titular2);
                        contaAtiva = true;
                        System.out.println("Contas criadas com sucesso!");
                    }
                    case 2 -> {
                        if(contaAtiva){
                            System.out.println("[SAUDO ATULIZADO]");

                            System.out.printf("""
                                    Conta: %d | Titular: %s | Saldo: R$ %.2f
                                    """, conta1.getNumero(), conta1.getTitular(), conta1.getSaldo()
                            );

                            System.out.printf("""
                                    Conta: %d | Titular: %s | Saldo: R$ %.2f
                                    """, conta2.getNumero(), conta2.getTitular(), conta2.getSaldo()
                            );

                        }else{
                            System.out.println("[ERRO] - Nenhuma conta ativa!");
                        }

                    }
                    case 3 -> {
                        if(contaAtiva){
                            boolean validacao = false;
                            System.out.println("Informe o valor do Depósito: ");
                            double deposito = Double.parseDouble(entrada.nextLine());

                            if(deposito < 0.01){
                                while(deposito < 0.01){
                                    System.out.println("O valor do depósito deve ser um valor maior do que 0.");
                                    System.out.println("Informe um valor válido: ");
                                    deposito = Double.parseDouble(entrada.nextLine());
                                    validacao = true;
                                }
                            }else{
                                validacao = true;
                            }
                            if(validacao){
                                System.out.println("Informe a conta que deseja depositar: ");
                                System.out.printf("1 - %d\n", conta1.getNumero());
                                System.out.printf("2 - %d\n", conta2.getNumero());
                                int op = Integer.parseInt(entrada.nextLine());
                                if(op == 1){
                                    conta1.depositar(deposito);
                                    System.out.println("[SUCESSO] - Operação realizada com sucesso\n");
                                    System.out.printf("Saldo atualizado: R$ %.2f.\n", conta1.getSaldo());
                                } else if (op == 2) {
                                    conta2.depositar(deposito);
                                    System.out.println("[SUCESSO] - Operação realizada com sucesso\n");
                                    System.out.printf("Saldo atualizado: R$ %.2f.\n", conta2.getSaldo());
                                }else{
                                    System.out.println("[ERRO] - Opção inválida!");
                                }
                            }
                        }else{
                            System.out.println("[ERRO] - Nenhuma conta ativa!");
                        }
                    }
                    case 4 -> {
                        if(contaAtiva){

                            if(conta1.getSaldo() > 0 || conta2.getSaldo() > 0){
                                System.out.println("Informe a conta que deseja sacar: ");
                                System.out.printf("1 - %d\n", conta1.getNumero());
                                System.out.printf("2 - %d\n", conta2.getNumero());
                                int op = Integer.parseInt(entrada.nextLine());
                                if(op == 1){
                                    System.out.println("Informe o valor do saque: ");
                                    double saque = Double.parseDouble(entrada.nextLine());
                                    conta1.sacar(saque);
                                    System.out.println("[SUCESSO] - Operação realizada com sucesso\n");
                                    System.out.printf("Saldo atualizado: R$ %.2f.\n", conta1.getSaldo());
                                } else if (op == 2) {
                                    System.out.println("Informe o valor do saque: ");
                                    double saque = Double.parseDouble(entrada.nextLine());
                                    conta2.sacar(saque);
                                    System.out.println("[SUCESSO] - Operação realizada com sucesso\n");
                                    System.out.printf("Saldo atualizado: R$ %.2f.\n", conta2.getSaldo());
                                }else{
                                    System.out.println("[ERRO] - Opção inválida!");
                                }
                            }else{
                                System.out.println("[AVISO] - Conta sem saldo!");
                            }

                        }else{
                            System.out.println("[ERRO] - Nenhuma conta ativa!");
                        }
                    }
                    case 5 -> {
                        if(contaAtiva){
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
                        }

                    }
                    case 6 -> {
                        System.out.println("[ENCERRANDO] - Encerrando o sistema");
                        break;
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