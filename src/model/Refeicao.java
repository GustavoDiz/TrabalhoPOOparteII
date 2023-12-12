package model;

import java.time.LocalDate;
import java.util.Objects;

public class Refeicao {
    private long id;
    private String nome;
    private long codigo_user;
    private long codigo_dietType;
    private double carboidrato;
    private double proteina;
    private double gordura;
    private double calorias;

    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCodigo_user() {
        return codigo_user;
    }

    public void setCodigo_user(long codigo_user) {
        this.codigo_user = codigo_user;
    }

    public long getCodigo_dietType() {
        return codigo_dietType;
    }

    public void setCodigo_dietType(long codigo_dietType) {
        this.codigo_dietType = codigo_dietType;
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

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
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
        if (!(o instanceof Refeicao)) return false;
        Refeicao refeicao = (Refeicao) o;
        return getId() == refeicao.getId() && getCodigo_user() == refeicao.getCodigo_user() && getCodigo_dietType() == refeicao.getCodigo_dietType() && Double.compare(getCarboidrato(), refeicao.getCarboidrato()) == 0 && Double.compare(getProteina(), refeicao.getProteina()) == 0 && Double.compare(getGordura(), refeicao.getGordura()) == 0 && Double.compare(getCalorias(), refeicao.getCalorias()) == 0 && Objects.equals(getNome(), refeicao.getNome()) && Objects.equals(getDataCriacao(), refeicao.getDataCriacao()) && Objects.equals(getDataModificacao(), refeicao.getDataModificacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCodigo_user(), getCodigo_dietType(), getCarboidrato(), getProteina(), getGordura(), getCalorias(), getDataCriacao(), getDataModificacao());
    }

    @Override
    public String toString() {
        return "Refeicao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", codigo_user=" + codigo_user +
                ", codigo_dietType=" + codigo_dietType +
                ", carboidrato=" + carboidrato +
                ", proteina=" + proteina +
                ", gordura=" + gordura +
                ", calorias=" + calorias +
                ", dataCriacao=" + dataCriacao +
                ", dataModificacao=" + dataModificacao +
                '}';
    }
}