package model;

import java.time.LocalDate;
import java.util.Objects;

public class RegistroDieta {
    private long id;
    private long userID;
    private  long physicalAssessmentID;
    private long TipoDietaid;
    private int goal;
    private double calories;
    private int nMeals;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getPhysicalAssessmentID() {
        return physicalAssessmentID;
    }

    public void setPhysicalAssessmentID(long physicalAssessmentID) {
        this.physicalAssessmentID = physicalAssessmentID;
    }

    public long getTipoDietaid() {
        return TipoDietaid;
    }

    public void setTipoDietaid(long tipoDietaid) {
        TipoDietaid = tipoDietaid;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public int getnMeals() {
        return nMeals;
    }

    public void setnMeals(int nMeals) {
        this.nMeals = nMeals;
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
        if (!(o instanceof RegistroDieta)) return false;
        RegistroDieta that = (RegistroDieta) o;
        return getId() == that.getId() && getUserID() == that.getUserID() && getPhysicalAssessmentID() == that.getPhysicalAssessmentID() && getTipoDietaid() == that.getTipoDietaid() && getGoal() == that.getGoal() && Double.compare(getCalories(), that.getCalories()) == 0 && getnMeals() == that.getnMeals() && Objects.equals(getDataCriacao(), that.getDataCriacao()) && Objects.equals(getDataModificacao(), that.getDataModificacao());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserID(), getPhysicalAssessmentID(), getTipoDietaid(), getGoal(), getCalories(), getnMeals(), getDataCriacao(), getDataModificacao());
    }

    @Override
    public String toString() {
        return "RegistroDieta{" +
                "id=" + id +
                ", userID=" + userID +
                ", physicalAssessmentID=" + physicalAssessmentID +
                ", TipoDietaid=" + TipoDietaid +
                ", goal=" + goal +
                ", calories=" + calories +
                ", nMeals=" + nMeals +
                ", dataCriacao=" + dataCriacao +
                ", dataModificacao=" + dataModificacao +
                '}';
    }
}

