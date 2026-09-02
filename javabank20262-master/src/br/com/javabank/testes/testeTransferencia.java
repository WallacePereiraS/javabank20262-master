package br.com.javabank.testes;

import br.com.javabank.modelo.Conta;

public class testeTransferencia {
    public static void main(String[] args) {
        Conta c1 = new Conta(1001, "Carrascal");
        Conta c2 = new Conta(1002, "Paquetá");

        c1.depositar(1000);
        System.out.printf("\nSaldo do %s: R$ %.2f", c1.getTitular(),c1.getSaldo());

        System.out.printf("\nSaldo do %s: R$ %.2f", c2.getTitular(),c2.getSaldo());

        c1.transferir(500,c2);

        System.out.printf("\n%s recebeu uma transferência.", c2.getTitular());
        System.out.printf("\nSaldo do %s: R$ %.2f", c2.getTitular(),c2.getSaldo());

        System.out.printf("\nSaldo do %s: R$ %.2f", c1.getTitular(),c1.getSaldo());
    }
}
