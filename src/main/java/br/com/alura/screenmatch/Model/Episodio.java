package br.com.alura.screenmatch.Model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    private Integer temporada;
    private String Titulo;
    private Integer numeroEp;
    private Double avaliacao;
    private LocalDate dataLancamento;

    public Episodio(Integer numeroTemp, DadosEpisodio d) {
        this.temporada = numeroTemp;
        this.numeroEp = d.numeroEp();
        this.Titulo = d.Titulo();
        try {
            this.avaliacao = (Double.valueOf(d.avaliacao()));
        } catch (NumberFormatException e) {
            this.avaliacao = 0.0;
        }
        try {
            this.dataLancamento = (LocalDate.parse(d.dataLancamento()));
        } catch (DateTimeParseException e) {
            this.dataLancamento = null;
        }
    }

    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public Integer getNumeroEp() {
        return numeroEp;
    }

    public void setNumeroEp(Integer numeroEp) {
        this.numeroEp = numeroEp;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    public String toString() {
        return  "temporada=" + temporada +
                ", Titulo='" + Titulo + '\'' +
                ", numeroEp=" + numeroEp +
                ", avaliacao=" + avaliacao +
                ", dataLancamento=" + dataLancamento;
    }
}
