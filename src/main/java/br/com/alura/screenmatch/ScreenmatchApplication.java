package br.com.alura.screenmatch;

import br.com.alura.screenmatch.Model.DadosEpisodio;
import br.com.alura.screenmatch.Model.DadosSerie;
import br.com.alura.screenmatch.Model.DadosTemporada;
import br.com.alura.screenmatch.services.ConsumoApi;
import br.com.alura.screenmatch.services.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Entre com a série que deseja buscar os dados: ");
		Scanner scanner = new Scanner(System.in);
		var serie = scanner.nextLine();
		serie = serie.replace(" ", "+");

		var consumoApi = new ConsumoApi();
		var json = consumoApi.ObterDados("https://www.omdbapi.com/?t="+ serie+ "&tt3896198&apikey=f34108e3");
		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);
		json = consumoApi.ObterDados("https://www.omdbapi.com/?t=" + serie + "&season=1&episode=2&tt3896198&apikey=f34108e3");
		DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio);

		List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i <= dados.totalTemporadas(); i++){
			json = consumoApi.ObterDados("https://www.omdbapi.com/?t=" + serie + "&season=" + i + "&tt3896198&apikey=f34108e3");
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);

		}
	}