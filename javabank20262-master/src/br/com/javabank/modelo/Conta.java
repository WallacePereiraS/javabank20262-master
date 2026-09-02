package br.com.javabank.modelo;

public class Conta {
    private int numero;
    private String titular;
    private double saldo;

    public Conta(int numero, String titular){
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0;
    }

    public String getTitular(){
        return this.titular;
    }

    public void setTitular(String titular){
        // this --> aponta para a própria classe
        if(titular.length() > 1){
            this.titular = titular;
        }else{
            System.out.println("O titular deve ter mais de 1 caractere!");
        }

    }

    public int getNumero(){
        return this.numero;
    }

    public double getSaldo(){
        return this.saldo;
    }

    public boolean depositar(double valor){
        if(valor > 0){
            saldo += valor;
            return true;
        }else{
            return false;
        }
    }

    public boolean sacar(double valor){
        // && ==> AND
        // || ==> OR
        // !  ==> NOT
        if(valor > 0 && valor <= saldo){
            saldo -= valor;
            return true;
        }
        else{
            return false;
        }
    }

    public boolean transferir(double valor, Conta favorecido){
        if(sacar(valor) == true){
            favorecido.depositar(valor);
            return true;
        }else{
            return false;
        }
    }
}
