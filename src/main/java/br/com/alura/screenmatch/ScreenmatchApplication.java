package br.com.alura.screenmatch;

import br.com.alura.screenmatch.Principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();

//        List<DadosTemporadas>  temporadas = new ArrayList<>();
//
//		for (int i = 1 ; i<= dados.totalTemporadas(); i++){
//			var jsonTotalTmp = consumoApiEp.obterDados("https://www.omdbapi.com/?t=the+walking+dead&season=" + i + "&apikey=6f533f65");
//			DadosTemporadas dadosTemporadas = conversor.obterDados(jsonTotalTmp, DadosTemporadas.class);
//			temporadas.add(dadosTemporadas);
//		}
//		temporadas.forEach(System.out::println);
	}
}
