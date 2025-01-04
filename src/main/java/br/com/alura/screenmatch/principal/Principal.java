package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.Model.DadosEpisodio;
import br.com.alura.screenmatch.Model.DadosSerie;
import br.com.alura.screenmatch.Model.DadosTemporada;
import br.com.alura.screenmatch.services.ConsumoApi;
import br.com.alura.screenmatch.services.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String APIKEY = "&tt3896198&apikey=f34108e3";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private Scanner leitura = new Scanner(System.in);


    public void menu(){
        System.out.println("Entre com a série a ser buscada: ");
        var nomeSerie = leitura.nextLine();
        var json = consumoApi.ObterDados(ENDERECO + nomeSerie.replace(" ", "+") + APIKEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println("Dados da série selecionada: \n" + dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i <= dados.totalTemporadas(); i++){
			json = consumoApi.ObterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + APIKEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.Titulo())));
    }
}
