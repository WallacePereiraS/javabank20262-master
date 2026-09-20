package br.com.javabank.modelo;

public class Corrente extends Conta{
    private double limite;

    private double limiteUsado;


    public Corrente(int numero, String titular, double limite){
        //É uma chamada a super classe
        //Chamando o contrutor da classe Conta
        super(numero, titular);
        this.limite = limite;
        this.limiteUsado = 0.0;

    }

    public double getLimite(){
        return this.limite;
    }
    public double getSaldoTotal() {
        double limiteRestante = this.limite - this.limiteUsado;
        return this.saldo + limiteRestante;
    }

    @Override
    public void exibirSaldo() {
        double limiteDisponivel = this.limite - this.limiteUsado;
        System.out.printf("Conta: %d | Titular: %s | Saldo: R$ %.2f | Limite: R$ %.2f | Limite Usado: R$ %.2f\n",
                getNumero(), getTitular(), this.saldo, limite, this.limiteUsado);
    }

    //Reescrita de funçoes (OVERRIDE)
    @Override //Anotação
    public boolean sacar(double valor) {
        if(valor <= getSaldoTotal()) {
            this.saldo -= valor;
            System.out.println("[SUCESSO] - Operação realizada com sucesso\n");

            double limiteRestante;


            if(this.saldo < 0) {
                double valorUsadoDoLimite = this.saldo * -1;
                limiteUsado += valorUsadoDoLimite;
                this.saldo = 0.0;

            }
            limiteRestante = limite - limiteUsado;
            System.out.printf("Saldo atualizado: R$:%.2f + R$:%.2f (limite).\n", this.saldo, limiteRestante);
            return true;
        } else {
            System.out.println("[ERRO] - Você não possui saldo!");
            return false;
        }
    }

}
