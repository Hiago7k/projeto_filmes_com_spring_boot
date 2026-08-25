package br.com.alura.screenmatch.Principal;

import br.com.alura.screenmatch.models.DadosEpisodio;
import br.com.alura.screenmatch.models.DadosSerie;
import br.com.alura.screenmatch.models.DadosTemporadas;
import br.com.alura.screenmatch.models.Episodio;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
private Scanner leitura = new Scanner(System.in);
private ConsumoApi consumo = new ConsumoApi();
private ConverteDados conversor = new ConverteDados();
private final String ENDERECO = "https://www.omdbapi.com/?t=";
private final String API_KEY = "&apikey=6f533f65";

    public void exibeMenu(){
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);
        System.out.println(nomeSerie);

        List<DadosTemporadas> temporadas = new ArrayList<>();

		for (int i = 1 ; i<= dados.totalTemporadas(); i++){
			var jsonTotalTmp = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
			DadosTemporadas dadosTemporadas = conversor.obterDados(jsonTotalTmp, DadosTemporadas.class);
			temporadas.add(dadosTemporadas);
		}
		temporadas.forEach(System.out::println);

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

//        List<String> nomes =  Arrays.asList("Hiago", "Gabriel", "Everton", "Jesse", "Nico");
//
//        nomes.stream()
//                .sorted()
//                .limit(5)
//                .filter(n -> n.startsWith("J"))
//                .map(n-> n.toUpperCase())
//                .forEach(System.out::println);

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());
//
//        System.out.println("\nTop 5 Episódios");
//        dadosEpisodios.stream()
//                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
//                .peek(e-> System.out.println("Primeiro Filtro(N/A) " + e))
//                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
//                .peek(e-> System.out.println("Ordenação " + e))
//                .limit(10)
//                .peek(e-> System.out.println("Limitando " + e))
//                .map(e -> e.titulo().toUpperCase())
//                .peek(e-> System.out.println("Mapeamento " + e))
//                .forEach(System.out::println);

        List<Episodio> episodios =  temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toList());

        episodios.forEach(System.out::println);

        System.out.println("==================================================================");

        System.out.println("Digite o nome do episódio que deseja");
        String trechoTitulo = leitura.nextLine();
        Optional<Episodio> episodioBuscado = episodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
                .findFirst();
                if(episodioBuscado.isPresent()){
                    System.out.println("Episódio encontrado!");
                    System.out.println("Temporada: " + episodioBuscado.get().getTemporada());
                }else{
                    System.out.println("Episódio não encontrado!");
                }

//
//        System.out.println("A partir de que ano você deseja ver os episódios? ");
//        var ano = leitura.nextInt();
//        leitura.nextLine(); // para desbugar o nextint
//
//        LocalDate dataBusca = LocalDate.of(ano, 1,1);
//        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // criando formtador de data br
//
//        episodios.stream()
//                .filter(e -> e.getDataLancamento()  != null &&  e.getDataLancamento().isAfter(dataBusca))
//                .forEach(e -> System.out.println(
//                        "Temporada: " + e.getTemporada() +
//                        "Episodio: " + e.getTitulo() +
//                        "Data de lançamento: " + e.getDataLancamento().format(formatador)
//                ));
    };
}
