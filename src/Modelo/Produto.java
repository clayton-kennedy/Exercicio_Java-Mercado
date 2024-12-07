package Modelo;

import Utils.Utils;

public class Produto {
    //contador para gerar um novo id sempre que um produto for adicionado ao estoque
    private static int count = 1;

    private int id;
    private String nome;
    private double preco;

    //constructor
    public Produto (String nome, double preco){
        this.id = count;
        this.nome = nome;
        this.preco = preco;
        Produto.count += 1;
    }
    public int getId(){
        return id;
    }
    public String getNome() {
        return nome;
    }
    public double getPreco() {
        return preco;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String toString() {
        return "Id: "+this.getId()+
                "\nNome: "+this.getNome()+
                "\nPreço: "+ Utils.doubleToString(this.getPreco());
    }
}
