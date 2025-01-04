package br.com.alura.screenmatch;

import br.com.alura.screenmatch.Model.DadosEpisodio;
import br.com.alura.screenmatch.Model.DadosSerie;
import br.com.alura.screenmatch.services.ConsumoApi;
import br.com.alura.screenmatch.services.ConverteDados;
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
		var consumoApi = new ConsumoApi();
		var json = consumoApi.ObterDados("https://www.omdbapi.com/?t=gilmore+girls&tt3896198&apikey=f34108e3");
		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);
		var jsonEp = consumoApi.ObterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&tt3896198&apikey=f34108e3");
		DadosEpisodio dadosEpisodio = conversor.obterDados(jsonEp, DadosEpisodio.class);
		System.out.println(dadosEpisodio);
	}
}
