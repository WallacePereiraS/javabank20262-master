package br.com.javabank.modelo;

public class Conta {
    //ENCAPSULAMENTO
    //public --> qualquer classe tem acesso a todos os membros
    //private --> apenas a propria classe tem acesso aos membros
    private int numero;
    private String titular;
    protected double saldo;


    //Construtor
    public Conta(){
        //Contrutor vazio (default)
    }

    public Conta(int numero, String titular){
        //Construtor com parametros
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0;
    }

    //GET --> retornar o valor de uma propriedade
    public String getTitular(){
        return this.titular;
    }

    public int getNumero(){
        return this.numero;
    }


    public double getSaldo(){
        return this.saldo;
    }

    public void exibirSaldo() {
        System.out.printf("Conta: %d | Titular: %s | Saldo: R$ %.2f\n",
                this.numero, this.titular, this.saldo);
    }

    //SET --> alterar o valor de uma propriedade
    public void setTitular(String titular){
        //this --> aponta para a propria classe
        if(titular.length() > 1){
            this.titular = titular;
        }else{
            System.out.println("O titular deve possuir pelo 2 caracteres");
        }
    }

    public boolean depositar(double valor){
        if(valor > 0){
            this.saldo += valor;
            return true;
        }else{
            return false;
        }
    }

    public boolean sacar(double valor){
        // && ==> AND
        // || ==> OR
        // !  ==> NOT
        if(valor > 0 && valor <= this.saldo){
            this.saldo -= valor;
            return true;
        }
        else{
            return false;
        }
    }

    public boolean transferir(double valor, Conta favorecido){
        if(favorecido != null && sacar(valor) == true){
            favorecido.depositar(valor);
            return true;
        }else{
            return false;
        }
    }
}