package Main;

import Modelo.Produto;
import org.w3c.dom.ls.LSOutput;
import Utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class Mercado {
    //receber os dados digitado pelo usuario
    private static Scanner input = new Scanner(System.in);
    //criar a lista com os produtos
    public static ArrayList<Produto> produtos;
    //cria uma espécie de sumário, para filtrar os produtos
    public static Map<Produto, Integer> carrinho;

    //classe main, a classe principal do projeto
    public static void main(String[] args) {
        produtos = new ArrayList<>();
        carrinho = new HashMap<>();
        menu();
    }
    private static void menu() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("---------------------Bem-vindo ao mercado do Zé-------------------");
        System.out.println("------------------------------------------------------------------");
        System.out.println("--------------Selecione a operação que deseja realizar------------");
        System.out.println("------------------------------------------------------------------");
        System.out.println("    Opção 1 - Cadastrar");
        System.out.println("    Opção 2 - Listar");
        System.out.println("    Opção 3 - Comprar");
        System.out.println("    Opção 4 - Carrinho");
        System.out.println("    Opção 5 - Sair");
        System.out.println(" ");

        int opcao = input.nextInt();
        switch (opcao) {
            case 1:
                cadastrarProdutos();
                break;
            case 2:
                listarProdutos();
                break;
            case 3:
                comprarProdutos();
                break;
            case 4:
                verCarrinho();
                break;
            case 5:
                System.out.println("Volte sempre!");
                //parar o programa
                System.exit(0);
            default:
                System.out.println("Opção inválida!");
                menu();
                break;
        }
    }

    private static void cadastrarProdutos(){
        //obter o nome e o preco do produto a ser cadastrado
        System.out.println("Nome do produto:");
        String nome = input.next();

        System.out.println("Preço do produto:");
        double preco = input.nextDouble();

        //criando o produto
        Produto produto = new Produto(nome, preco);
        //adicionando o objeto criado na arrayList
        produtos.add(produto);

        System.out.println(produto.getNome()+" cadastrado com sucesso.");
        menu();
    }

    private static void listarProdutos() {
        //verificar se tem algum produto na arrayList para mostrar
        if (produtos.size() > 0) {
            System.out.println("Lista de produtos:\n");

            for (Produto p : produtos) {
                System.out.println(p);
            }
            } else {
                System.out.println("Nenhum produto cadastrado.");
        }
        menu();
    }

    private static void comprarProdutos() {
        //verificar se ja tem o produto na lista
        if (produtos.size() > 0) {
            System.out.println("Informe o código do produto:\n");

            System.out.println("Produtos disponíveis:");
            for (Produto p : produtos) {
                System.out.println(p);
            }
            //receber o id do produto e converter de string para int
            int id = Integer.parseInt(input.next());

            boolean idJaExistente = false;

            //verificar se o id digitado corresponde a um id ja existente na lista
            for (Produto p : produtos) {
                if (id == p.getId()) {
                    int quantidade = 0;
                    //verificando se o produto está no carrinho, se estiver, entao a qt é incrementada
                    //se nao, adiciona pela primeira vez
                    try {
                        quantidade = carrinho.get(p);
                        carrinho.put(p, quantidade + 1);
                    } catch (NullPointerException e) {
                        //se o produto for o primeiro do carrinho
                        carrinho.put(p, 1);
                    }
                    //msg ao usuario que o produto foi adicionado ao carrinho ou sua qt foi incrementada
                    System.out.println(p.getNome() + " adicionado ao carrinho.");
                    idJaExistente = true;
                    //adicionar outro produto ao carrinho ou finalizar a compra
                    if (idJaExistente) {
                        System.out.println("Deseja adicionar outro produto ao carrinho?\n");
                        System.out.println("1 - Adicionar novo produto\n");
                        System.out.println("2 - Finalizar a compra");
                        int option = Integer.parseInt(input.next());

                        if (option == 1) {
                            comprarProdutos();
                        } else {
                            finalizarCompra();
                        }
                    }
                } else {
                    System.out.println("Produto não encontrado.");
                    menu();
                }
            }
        } else {
                System.out.println("Não há produtos cadastrados.");
                menu();
            }
        }

    private static void verCarrinho() {
        System.out.println("---------------------Seu Carrinho-------------------");
        if (carrinho.size() > 0) {
            for (Produto p : carrinho.keySet()) {
                System.out.println("Produto: "+ p +"\nQuantidade: "+ carrinho.get(p));
            }
        } else {
            System.out.println("Carrinho vazio.");
        }
        menu();
    }

    private static void finalizarCompra() {
        double valorTotal = 0.0;
        System.out.println("Seus produtos:");
        for (Produto p: carrinho.keySet()) {
            int quantidade = carrinho.get(p);
            valorTotal += p.getPreco() * quantidade;
            System.out.println(p);
            System.out.println("Quantidade: "+ quantidade);
            System.out.println(" ");
        }
        System.out.println("O valor da sua compra é: R$ "+ Utils.doubleToString(valorTotal));
        carrinho.clear();
        System.out.println("Obrigado pela preferência!");
        menu();
    }
}
