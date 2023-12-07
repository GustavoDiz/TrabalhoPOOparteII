package model;

import java.time.LocalDate;
import java.util.Objects;

public class Pessoa {
    private long id;
    private String nome;
    private Sexo sexo;
    private LocalDate nascimento;
    private String usuario;
    private String senha;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;


    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
    public String toString() {
        return "\n id=" + id + "\n nome=" + nome + "\n sexo=" + sexo + "\n nascimento=" + nascimento + "\n login=" + usuario + "\n senha=" + senha + "\n dataCriacao=" + dataCriacao + "\n dataModificacao=" + dataModificacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return id == pessoa.id && sexo == pessoa.sexo && Objects.equals(nome, pessoa.nome) && Objects.equals(nascimento, pessoa.nascimento) && Objects.equals(usuario, pessoa.usuario) && Objects.equals(senha, pessoa.senha) && Objects.equals(dataCriacao, pessoa.dataCriacao) && Objects.equals(dataModificacao, pessoa.dataModificacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sexo, nascimento, usuario, senha, dataCriacao, dataModificacao);
    }
}
