package br.com.javabank.testes;

import br.com.javabank.modelo.Conta;

public class TesteConta {
    //ATALHO MAIN ==> PSVM + TAB
    public static void main(String[] args) {
        //INSTANCIAÇÃO
        Conta c1 = new Conta(100,"Carrascal");
        Conta c2 = new Conta(101,"Ana");
        Conta c3 = c1;
        Conta c4 = new Conta(102,"Renata");

        System.out.println("Saldo C1: " + c1.getSaldo());
        System.out.println("Saldo C2: " + c2.getSaldo());

        System.out.println(c3.getTitular());
        System.out.println(c1.getTitular());

        System.out.println("Saldo atual [c1]: " + c1.getSaldo());
        c1.depositar(500);
        System.out.println("Saldo atual [c1]: " + c1.getSaldo());
        c1.sacar(300);
        System.out.println("Saldo atual [c1]: " + c1.getSaldo());

    }
}
