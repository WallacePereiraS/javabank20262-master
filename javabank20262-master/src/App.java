import java.util.Scanner;

public class App {
    //ATALHO ==> PSVM + TAB --> main()
    public static void main(String[] args) {
        //ATALHO ==> SOUT + TAB --> println
        System.out.println("JAVABANK - TERMINAL DO CAIXA");
        //Variaveis
        Scanner entrada = new Scanner(System.in);
        boolean operadorAutenticado = false;

        //CONSTANTE (final)
        final int SENHA_OPERADOR = 8888;

        for(int tentativa = 1; tentativa <= 3; tentativa+=1){
            System.out.print("Informe a sua SENHA: ");
            int senha = Integer.parseInt(entrada.nextLine());

            if(senha == SENHA_OPERADOR){
                System.out.println("[SESSÃO INICIADA] Bem-vindo.");
                operadorAutenticado = true;
                break;
            }else{
                System.out.println("[ALERTA] Senha incorreta");
            }
        }

        if(operadorAutenticado == false){
            System.out.println("[BLOQUEIO] Limite de tentativas excedidas!");
        }else{
            int numeroConta = 0;
            String titular = "";
            double saldo = 0;
            boolean contaAtiva = false;
            int opcao = 0;

            do{
                System.out.println("Escolha uma opção: ");
                System.out.println("1- Criar/Abrir conta");
                System.out.println("2- Consultar Saldo");
                System.out.println("3- Realizar Deposito");
                System.out.println("4- Realizar Saque");
                System.out.println("5- Sair");
                System.out.print("Selecione uma opção: ");
                opcao = Integer.parseInt(entrada.nextLine());

                switch(opcao){
                    case 1 -> {
                        System.out.print("Informe o numero da conta: ");
                        numeroConta = Integer.parseInt(entrada.nextLine());
                        System.out.print("Informe o titular da conta: ");
                        titular = entrada.nextLine();
                        System.out.print("Informe o saldo inicial: ");
                        saldo = Double.parseDouble(entrada.nextLine());

                        while(saldo < 0){
                            System.out.println("O saldo não deve ser um valor negativo.");
                            System.out.print("Informe o saldo: ");
                            saldo = Double.parseDouble(entrada.nextLine());
                        }
                        contaAtiva = true;
                        System.out.println("Conta criado com sucesso");
                    }
                    case 2 -> {
                        if(contaAtiva == true){
                            System.out.println("Conta: " + numeroConta +
                                    " | Titular: " + titular +
                                    " | Saldo atual: R$ " + saldo);

                            System.out.printf("Conta: %d | Titular: %s | Saldo: R$ %.2f \n",
                                    numeroConta, titular, saldo);
                        }else{
                            System.out.println("[ERRO] Nenhuma conta ativa");
                        }
                    }
                    case 3 -> {}
                    case 4 -> {}
                    case 5 -> {
                        System.out.println("[FECHAMENTO] Encerrando o sistema.");
                    }
                    default -> {
                        System.out.println("Opção Invalida. Tente Novamente.");
                    }
                }
            }while(opcao != 5);
        }

        entrada.close();
    }
}