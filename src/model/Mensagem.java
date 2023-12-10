package model;

import java.time.LocalDate;
import java.util.Objects;

public class Mensagem {
    private long id;
    private long sender;
    private Pessoa recipient;
    private String msg;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    public Pessoa getRecipient() {
        return recipient;
    }

    public void setRecipient(Pessoa recipient) {
        this.recipient = recipient;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
        Mensagem mensagem = (Mensagem) o;
        return id == mensagem.id && sender == mensagem.sender && Objects.equals(recipient, mensagem.recipient) && Objects.equals(msg, mensagem.msg) && Objects.equals(dataCriacao, mensagem.dataCriacao) && Objects.equals(dataModificacao, mensagem.dataModificacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, recipient, msg, dataCriacao, dataModificacao);
    }
}
