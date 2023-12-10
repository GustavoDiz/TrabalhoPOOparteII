package model;

import java.time.LocalDate;
import java.util.Objects;

public class AlimentoReceita{
    private long id;
    private String nome;
    private double carboidratos;
    private double proteinas;
    private double gorduras;
    private double calorias;
    private double porcao;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    public long getId(){
        return id;
    }
    public String getNome(){
        return nome;
    }
    public double getCarboidratos(){
        return carboidratos;
    }
    public double getProteinas(){
        return proteinas;
    }
    public double getGorduras(){
        return gorduras ;
    }
    public double getCalorias(){
        return calorias;
    }
    public double getPorcao(){
        return porcao;
    }

    public LocalDate getDataCriacao() { return dataCriacao; }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCarboidratos(double carboidratos) {
        this.carboidratos = carboidratos;
    }

    public void setProteinas(double proteinas) {
        this.proteinas = proteinas;
    }

    public void setGorduras(double gorduras) {
        this.gorduras = gorduras;
    }
    public void setCalorias() {
        this.calorias = (4*carboidratos) + (4*proteinas) + (9*gorduras);
    }
    public void setPorcao(double porcao){
        this.porcao = porcao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }
    @Override
    public String toString() {
        return String.format("\n%d\t%s\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\n",
                id, nome, carboidratos, proteinas, gorduras, calorias, porcao);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlimentoReceita)) return false;
        AlimentoReceita that = (AlimentoReceita) o;
        return getId() == that.getId() && Double.compare(getCarboidratos(), that.getCarboidratos()) == 0 && Double.compare(getProteinas(), that.getProteinas()) == 0 && Double.compare(getGorduras(), that.getGorduras()) == 0 && Double.compare(getCalorias(), that.getCalorias()) == 0 && Double.compare(getPorcao(), that.getPorcao()) == 0 && Objects.equals(getNome(), that.getNome()) && Objects.equals(dataCriacao, that.dataCriacao) && Objects.equals(dataModificacao, that.dataModificacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCarboidratos(), getProteinas(), getGorduras(), getCalorias(), getPorcao(), dataCriacao, dataModificacao);
    }

}
