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
                5 - Sair
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
            int numeroConta = 0;
            String titular = "";
            double saldo = 0;
            boolean contaAtiva = false;
            int opcao = 0;

            do{
                System.out.println(menu);
                System.out.println("Selecione uma opção:");
                opcao = Integer.parseInt(entrada.nextLine());

                switch (opcao){
                    case 1 -> {
                        System.out.println("Informe o número da conta: ");
                        numeroConta = Integer.parseInt(entrada.nextLine());
                        System.out.println("Informe o titular da conta: ");
                        titular = entrada.nextLine();
                        System.out.println("Informe o saldo inicial: ");
                        saldo = Double.parseDouble(entrada.nextLine());

                        while(saldo < 0){
                            System.out.println("O saldo não deve menor que 0.");
                            System.out.println("Informe um saldo válido: ");
                            saldo = Double.parseDouble(entrada.nextLine());
                        }
                        contaAtiva = true;
                        System.out.println("Conta criada com sucesso!");
                    }
                    case 2 -> {
                        if(contaAtiva){
                            System.out.println("[SAUDO ATULIZADO]");
                            System.out.printf("""
                                    Conta: %d
                                    Titular: %s
                                    Saldo: R$ %.2f
                                    """, numeroConta, titular, saldo);
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
                                saldo += deposito;
                                System.out.println("[SUCESSO] - Operação realizada com sucesso\n");
                                System.out.printf("Saldo atualizado: R$ %.2f.\n", saldo);
                            }


                        }else{
                            System.out.println("[ERRO] - Nenhuma conta ativa!");
                        }
                    }
                    case 4 -> {
                        if(contaAtiva){
                            if(saldo > 0){
                                boolean validacao = false;
                                System.out.println("Informe o valor do saque: ");
                                double saque = Double.parseDouble(entrada.nextLine());
                                if (saque > saldo){
                                    while(saque > saldo){
                                        System.out.println("Saldo insuficiente!");
                                        System.out.println("Informe um valor válido para o saque:");
                                        saque = Double.parseDouble(entrada.nextLine());
                                        validacao = true;
                                    }
                                } else{
                                    validacao = true;
                                }
                                if (validacao){
                                    saldo -= saque;
                                }

                                System.out.println("[SUCESSO] - Operação realizada com sucesso\n");
                                System.out.printf("Saldo atualizado: R$ %.2f.\n", saldo);
                            }else{
                                System.out.println("[AVISO] - Conta sem saldo!");
                            }

                        }else{
                            System.out.println("[ERRO] - Nenhuma conta ativa!");
                        }
                    }
                    case 5 -> {
                        System.out.println("[ENCERRANDO] - Encerrando o sistema");
                        break;
                    }
                    default -> {
                        System.out.println("[ERRO] - Opção Inválida! Tente novamente.");
                    }
                }
            }while(opcao != 5);
        }
        entrada.close();
    }
}