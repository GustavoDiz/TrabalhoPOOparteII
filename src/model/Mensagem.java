package model;

import java.time.LocalDate;
import java.util.Objects;

public class Mensagem {
    private long id;
    private long senderId;
    private Pessoa sender;
    private long recipientId;
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

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public Pessoa getSender() {
        return sender;
    }

    public void setSender(Pessoa sender) {
        this.sender = sender;
    }

    public long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(long recipientId) {
        this.recipientId = recipientId;
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
    public String toString() {
        return  "\n Usuário "+ sender.getNome() +
                "\n" + msg + '\'' +
                "\n --------------- ";
    }
}
