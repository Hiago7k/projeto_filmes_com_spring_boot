package br.com.alura.screenmatch;

import br.com.alura.screenmatch.models.DadosEpisodio;
import br.com.alura.screenmatch.models.DadosSerie;
import br.com.alura.screenmatch.models.DadosTemporadas;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=the+walking+dead&apikey=6f533f65");
		System.out.println(json);

//		json = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
//		System.out.println("Segunda requisição");
//		System.out.println(json);

		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);

		System.out.println("**********************************************");
		System.out.println("Requisição e Resposta do EndPoint de episode");
		System.out.println("**********************************************");

		var consumoApiEp = new ConsumoApi();
		var jsonEp = consumoApiEp.obterDados("https://www.omdbapi.com/?t=the+walking+dead&season=1&episode=1&apikey=6f533f65");
		System.out.println(jsonEp);
		DadosEpisodio dadosEp = conversor.obterDados(jsonEp, DadosEpisodio.class);
		System.out.println(dadosEp);

        List<DadosTemporadas>  temporadas = new ArrayList<>();

		for (int i = 1 ; i<= dados.totalTemporadas(); i++){
			var jsonTotalTmp = consumoApiEp.obterDados("https://www.omdbapi.com/?t=the+walking+dead&season=" + i + "apikey=6f533f65");
			DadosTemporadas dadosTemporadas = conversor.obterDados(jsonTotalTmp, DadosTemporadas.class);
			temporadas.add(dadosTemporadas);
		}
		temporadas.forEach(System.out::println);
	}
}
