package model;

import java.time.LocalDate;

public class AlimentoRefeicao {
    private long id;
    private long meal_id;
    private Refeicao meal;
    private long food_id;
    private AlimentoReceita food;
    private double portion;
    private double protein;
    private double fat;
    private double calories;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMeal_id() {
        return meal_id;
    }

    public void setMeal_id(long meal_id) {
        this.meal_id = meal_id;
    }

    public Refeicao getMeal() {
        return meal;
    }

    public void setMeal(Refeicao meal) {
        this.meal = meal;
    }

    public long getFood_id() {
        return food_id;
    }

    public void setFood_id(long food_id) {
        this.food_id = food_id;
    }

    public AlimentoReceita getFood() {
        return food;
    }

    public void setFood(AlimentoReceita food) {
        this.food = food;
    }

    public double getPortion() {
        return portion;
    }

    public void setPortion(double portion) {
        this.portion = portion;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
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
}
