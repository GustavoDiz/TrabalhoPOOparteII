package model;

import utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MensagemDAO implements DAO<Mensagem>{
    @Override
    public Mensagem add(Mensagem elemento) {
        String sql = "insert into mensagem" +
                "(sender_id,recipient_id,msg,dataCriacao,dataModificacao)" +
                "values(?,?,?,?,?)";
        try(Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1,elemento.getSenderId());
            stmt.setLong(2,elemento.getRecipientId());
            stmt.setString(3,elemento.getMsg());
            stmt.setDate(4,java.sql.Date.valueOf(elemento.getDataCriacao()));
            stmt.setDate(5,java.sql.Date.valueOf(elemento.getDataModificacao()));

            stmt.execute();

        }catch (SQLException e){
            throw  new RuntimeException(e);
        }

        return elemento;
    }

    @Override
    public ArrayList<Mensagem> list() {
        return null;
    }

    @Override
    public Mensagem update(Mensagem elemento, int op) {
        return null;
    }

    @Override
    public Mensagem delete(Mensagem elemento) {
        return null;
    }

    public ArrayList<Mensagem> myList(long id){
        ArrayList<Mensagem> result = new ArrayList<>();
        String sql = "select m.*, p.nome " +
                "from mensagem m " +
                "JOIN pessoa p ON m.sender_id = p.id " +
                "where recipient_id = ?";
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1,id);
            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    Mensagem m = new Mensagem();
                    m.setSenderId(rs.getLong("sender_id"));
                    m.setRecipientId(rs.getLong("recipient_id"));
                    m.setMsg(rs.getString("msg"));
                    m.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                    m.setDataModificacao(rs.getDate("dataModificacao").toLocalDate());
                    Pessoa p = new Pessoa();
                    p.setNome(rs.getString("nome"));
                    m.setSender(p);
                    result.add(m);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
