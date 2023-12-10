package model;

import java.time.LocalDate;
import java.util.Objects;

public class Preferencia {
    private long id;
    private long userId;
    private long foodId;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
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
        Preferencia that = (Preferencia) o;
        return id == that.id && userId == that.userId && foodId == that.foodId && Objects.equals(dataCriacao, that.dataCriacao) && Objects.equals(dataModificacao, that.dataModificacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, foodId, dataCriacao, dataModificacao);
    }

    @Override
    public String toString() {
        return "Preferencias{" +
                "id=" + id +
                ", userId=" + userId +
                ", foodId=" + foodId +
                ", dataCriacao=" + dataCriacao +
                ", dataModificacao=" + dataModificacao +
                '}';
    }
}
