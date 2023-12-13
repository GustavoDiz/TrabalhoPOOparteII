package model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

public class AvaliacaoFisica {
    private long id;
    private long idUser;
    private Pessoa user;
    private double peso;
    private double altura;
    private int idade ;
    private double pescoco;
    private double cintura;
    private double quadril;
    private double abdomen;
    private double bf;
    private  double imc;
    private double tbm;
    private double massaGorda;
    private double massaMagra;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public Pessoa getUser() {
        return user;
    }

    public void setUser(Pessoa user) {
        this.user = user;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getPescoco() {
        return pescoco;
    }

    public void setPescoco(double pescoco) {
        this.pescoco = pescoco;
    }

    public double getCintura() {
        return cintura;
    }

    public void setCintura(double cintura) {
        this.cintura = cintura;
    }

    public double getQuadril() {
        return quadril;
    }

    public void setQuadril(double quadril) {
        this.quadril = quadril;
    }

    public double getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(double abdomen) {
        this.abdomen = abdomen;
    }

    public double getBf() {
        return bf;
    }

    public void setBf(double bf) {
        this.bf = bf;
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public double getTbm() {
        return tbm;
    }

    public void setTbm(double tbm) {
        this.tbm = tbm;
    }

    public double getMassaGorda() {
        return massaGorda;
    }

    public void setMassaGorda(double massaGorda) {
        this.massaGorda = massaGorda;
    }

    public double getMassaMagra() {
        return massaMagra;
    }

    public void setMassaMagra(double massaMagra) {
        this.massaMagra = massaMagra;
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

    public void calculateIMC(){
        DecimalFormat df = new DecimalFormat("0.00",new DecimalFormatSymbols(Locale.ENGLISH));
        double imc = this.peso / Math.pow(this.altura /100,2);
        double result = Double.parseDouble(df.format(imc));
        this.setImc(result);
    }

    public void calculateTMB(double taxa){
        double result;
        DecimalFormat df = new DecimalFormat("0.00",new DecimalFormatSymbols(Locale.ENGLISH));
        if (user.getSexo() == 'M'){
            result = taxa * (66 + ((13.27 * this.peso) + (5 * this.altura) - (6.8 * this.idade)));
        }else{
            result = taxa * (655 + ((9.6 * this.peso) + (1.8 * this.altura) - (4.7 * this.idade)));
        }
        result = Double.parseDouble(df.format(result));
        this.setTbm(result);
    }

    public void calculateBF(){
        double result;
        DecimalFormat df = new DecimalFormat("0.00",new DecimalFormatSymbols(Locale.ENGLISH));
        if(user.getSexo() == 'M'){
            result = (86.010 * Math.log10((this.abdomen - this.pescoco))) - (70.041 * Math.log10(this.altura)) + 36.76;
        }else{
            result = 163.205 * Math.log10((this.cintura + this.quadril)) - 97.684 * Math.log10(this.altura) - 78.387;
        }
        System.out.println(result);
        result = Double.parseDouble(df.format(result));
        this.setBf(result);
        this.setMassaGorda(Double.parseDouble(df.format((result/100) * this.peso)));
        this.setMassaMagra(Double.parseDouble(df.format(this.peso - this.massaGorda)));
    }

    public String idealBodyFat(){
        String type = null;
        int age = this.getIdade();
        double bf = Math.abs(this.getBf());
        if (user.getSexo() == 'M'){
            if (age >= 20 && age <= 29) {
                if (bf < 11) {
                    type = "Atleta";
                } else if (bf >= 11 && bf <= 13) {
                    type = "Bom";
                } else if (bf >= 14 && bf <= 20) {
                    type = "Normal";
                } else if (bf >= 21 && bf <= 23) {
                    type = "Elevado";
                } else {
                    type = "Muito elevado";
                }
            } else if (age >= 30 && age <= 39) {
                if (bf < 12) {
                    type = "Atleta";
                } else if (bf >= 12 && bf <= 14) {
                    type = "Bom";
                } else if (bf >= 15 && bf <= 21) {
                    type = "Normal";
                } else if (bf >= 22 && bf <= 24) {
                    type = "Elevado";
                } else {
                    type = "Muito elevado";
                }
            } else if (age >= 40 && age <= 49) {
                if (bf < 14) {
                    type = "Atleta";
                } else if (bf >= 14 && bf <= 16) {
                    type = "Bom";
                } else if (bf >= 17 && bf <= 23) {
                    type = "Normal";
                } else if (bf >= 24 && bf <= 26) {
                    type = "Elevado";
                } else {
                    type = "Muito elevado";
                }
            } else if (age >= 50 && age <= 59) {
                if (bf < 15) {
                    type = "Atleta";
                } else if (bf >= 15 && bf <= 17) {
                    type = "Bom";
                } else if (bf >= 18 && bf <= 24) {
                    type = "Normal";
                } else if (bf >= 25 && bf <= 27) {
                    type = "Elevado";
                } else {
                    type = "Muito elevado";
                }
            }
        }else{
            if (age >= 20 && age <= 29) {
                if (bf < 16) {
                    type = "Atleta";
                } else if (bf >= 16 && bf <= 19) {
                    type = "Bom";
                } else if (bf >= 20 && bf <= 28) {
                    type = "Normal";
                } else if (bf >= 29 && bf <= 31) {
                    type = "Elevado";
                } else {
                    type = "Muito elevado";
                }
            } else if (age >= 30 && age <= 39) {
                if (bf < 17) {
                    type = "Atleta";
                } else if (bf >= 17 && bf <= 20) {
                    type = "Bom";
                } else if (bf >= 21 && bf <= 29) {
                    type = "Normal";
                } else if (bf >= 30 && bf <= 32) {
                    type = "Elevado";
                } else {
                    type = "Muito elevado";
                }
            } else if (age >= 40 && age <= 49) {
                if (bf < 18) {
                    type = "Atleta";
                } else if (bf >= 18 && bf <= 21) {
                    type = "Bom";
                } else if (bf >= 22 && bf <= 30) {
                    type = "Normal";
                } else if (bf >= 31 && bf <= 33) {
                    type = "Elevado";
                } else {
                    type = "Muito elevado";
                }
            } else if (age >= 50 && age <= 59) {
                if (bf < 19) {
                    type = "Atleta";
                } else if (bf >= 19 && bf <= 22) {
                    type = "Bom";
                } else if (bf >= 23 && bf <= 31) {
                    type = "Normal";
                } else if (bf >= 32 && bf <= 34) {
                    type = "Elevado";
                } else {
                    type = "Muito elevado";
                }
            }
        }

        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvaliacaoFisica that = (AvaliacaoFisica) o;
        return id == that.id && Double.compare(peso, that.peso) == 0 && Double.compare(altura, that.altura) == 0 && idade == that.idade && Double.compare(pescoco, that.pescoco) == 0 && Double.compare(cintura, that.cintura) == 0 && Double.compare(quadril, that.quadril) == 0 && Double.compare(abdomen, that.abdomen) == 0 && Double.compare(bf, that.bf) == 0 && Double.compare(imc, that.imc) == 0 && Double.compare(tbm, that.tbm) == 0 && Double.compare(massaGorda, that.massaGorda) == 0 && Double.compare(massaMagra, that.massaMagra) == 0 && Objects.equals(user, that.user) && Objects.equals(dataCriacao, that.dataCriacao) && Objects.equals(dataModificacao, that.dataModificacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, peso, altura, idade, pescoco, cintura, quadril, abdomen, bf, imc, tbm, massaGorda, massaMagra, dataCriacao, dataModificacao);
    }

    @Override
    public String toString() {
        return String.format("%.2f\t%.2f\t%d\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\t%.2f\n",
                peso, altura, idade, pescoco, cintura, quadril, abdomen, bf, imc, tbm, massaGorda, massaMagra);
    }
}
