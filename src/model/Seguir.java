package model;

import java.time.LocalDate;
import java.util.Objects;

public class Seguir {
    private long id;
    private long follower;
    private long followed;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFollower() {
        return follower;
    }

    public void setFollower(long follower) {
        this.follower = follower;
    }

    public long getFollowed() {
        return followed;
    }

    public void setFollowed(long followed) {
        this.followed = followed;
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
        if (o == null || getClass() != o.getClass()) return false;
        Seguir seguir = (Seguir) o;
        return id == seguir.id && follower == seguir.follower && followed == seguir.followed && Objects.equals(dataCriacao, seguir.dataCriacao) && Objects.equals(dataModificacao, seguir.dataModificacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, follower, followed, dataCriacao, dataModificacao);
    }
}
