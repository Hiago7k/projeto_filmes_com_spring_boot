package br.com.alura.screenmatch.Principal;

import java.util.Scanner;

public class Principal {
private Scanner leitura = new Scanner(System.in);
private final String ENDERECO = "https://www.omdbapi.com/?t=";
private final String API_KEY = "&apikey=6f533f65";

    public void exibeMenu(){
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.next();
        //the+walking+dead
    };

}
