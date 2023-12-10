package model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class TipoDieta {
    private int id;
    private String nome;
    private double carboidrato;
    private double proteina;
    private double gordura;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCarboidrato() {
        return carboidrato;
    }

    public void setCarboidrato(double carboidrato) {
        this.carboidrato = carboidrato;
    }

    public double getProteina() {
        return proteina;
    }

    public void setProteina(double proteina) {
        this.proteina = proteina;
    }

    public double getGordura() {
        return gordura;
    }

    public void setGordura(double gordura) {
        this.gordura = gordura;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoDieta)) return false;
        TipoDieta tipoDieta = (TipoDieta) o;
        return getId() == tipoDieta.getId() && Double.compare(getCarboidrato(), tipoDieta.getCarboidrato()) == 0 && Double.compare(getProteina(), tipoDieta.getProteina()) == 0 && Double.compare(getGordura(), tipoDieta.getGordura()) == 0 && Objects.equals(getNome(), tipoDieta.getNome()) && Objects.equals(getDataCriacao(), tipoDieta.getDataCriacao()) && Objects.equals(getDataModificacao(), tipoDieta.getDataModificacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCarboidrato(), getProteina(), getGordura(), getDataCriacao(), getDataModificacao());
    }

    @Override
    public String toString() {
        return "TipoDieta{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", carboidrato=" + carboidrato +
                ", proteina=" + proteina +
                ", gordura=" + gordura +
                ", dataCriacao=" + dataCriacao +
                ", dataModificacao=" + dataModificacao +
                '}';
    }

}
