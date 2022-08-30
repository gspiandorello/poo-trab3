package E3;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Aplicacao {

    Scanner in = new Scanner(System.in);
    Operacoes op = new Operacoes();


    public void executa() {
        String opcao;
        do {
            apresentaMenu();
            opcao = in.nextLine();
            switch(opcao) {
                case "0":
                    break;
                case "1":
                    String nomeArquivo;
                    System.out.println("Digite o nome do arquivo que deseja ler (com extensão): ");
                    System.out.println("Exemplo: \"iss.csv\"");
                    nomeArquivo = in.nextLine();
                    try{
                        op.leArquivoTexto(nomeArquivo);
                    } catch (IOException | ParseException e){
                        System.out.println("Ocorreu um erro ao ler esse arquivo ou o arquivo não foi encontrado.");
                    }
                    break;
                case "2":
                    String ordem="";
                    System.out.println("Digite uma das opções abaixo para classificar os dados: ");
                    System.out.println("Digite 1 para classificar em ordem crescente pela data.");
                    System.out.println("Digite 2 para classificar em ordem descrecente pela data.");
                    do{
                        ordem = in.nextLine();
                        if(!ordem.equals("1") && !ordem.equals("2")){
                            System.out.println("Escolha a opção 1 ou 2.");
                        }
                    } while(!ordem.equals("1") && !ordem.equals("2"));
                    if (ordem.equals("1")) {
                        if(op.retornaListaCrescente().isEmpty()){
                            System.out.println("ERRO: Não existe nenhum dado.");
                        }
                        else{
                            System.out.println(op.retornaListaCrescente());
                        }
                    }
                    else {
                        if(op.retornaListaDecrescente().isEmpty()){
                            System.out.println("ERRO: Não existe nenhum dado.");
                        }
                        else{
                            System.out.println(op.retornaListaDecrescente());
                        }
                    }
                    break;
                case "3":
                    if(op.consultaTodosDados().isEmpty()){
                        System.out.println("ERRO: Não existe nenhum dado.");
                    }
                    else{
                        System.out.println(op.consultaTodosDados());
                    }
                    break;
                case "4":
                    String dataPesquisa;
                    System.out.println("Digite a data que deseja pesquisar: ");
                    System.out.println("Use o formato \"dd-mm-yyyy\", onde d é dia, m é mes e y é ano");
                    System.out.println("Exemplo 01-01-2021");
                    dataPesquisa = in.nextLine();
                    if(op.pesquisaPorData(dataPesquisa).isEmpty()){
                        System.out.println("ERRO: Nenhum iss foi encontrado na data digitada.");
                    }
                    else{
                        System.out.println(op.pesquisaPorData(dataPesquisa));
                    }
                    break;
                case "5":
                    String nomeDoArquivo;
                    System.out.println("Digite o nome do arquivo (sem extensão) para salvar os dados: ");
                    nomeDoArquivo = in.nextLine();
                    try{
                        op.salvaArquivoTexto(nomeDoArquivo);
                    } catch (Exception e){
                        System.out.println("Ocorreu um erro.");
                    }
                    break;
                default:
                    System.out.println();
                    System.out.println("Opcao inválida! Redigite.");
            }
        } while(!opcao.equals("0"));
    }

    public void apresentaMenu() {
        System.out.println("===============MENU===============");
        System.out.println("Opcões: ");
        System.out.println("[0] Sair");
        System.out.println("[1] Carregar dados abertos");
        System.out.println("[2] Classificar dados por data");
        System.out.println("[3] Consultar todos os dados");
        System.out.println("[4] Consultar dados de uma determinada data");
        System.out.println("[5] Salvar os dados da consulta em arquivo");
        System.out.print("Opção desejada: ");
    }
}
