package br.com.alura.screenmatch.Principal;

import br.com.alura.screenmatch.models.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.Scanner;

public class Principal {
private Scanner leitura = new Scanner(System.in);
private ConsumoApi consumo = new ConsumoApi();
private ConverteDados conversor = new ConverteDados();
private final String ENDERECO = "https://www.omdbapi.com/?t=";
private final String API_KEY = "&apikey=6f533f65";

    public void exibeMenu(){
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.next();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
    };
}
