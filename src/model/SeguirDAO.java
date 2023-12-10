package model;

import utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class SeguirDAO implements DAO<Seguir>{
    @Override
    public Seguir add(Seguir elemento) {
        String sql = "insert into seguir" +
                "(follower_id,followed_id,dataCriacao,dataModificacao)" +
                "values(?,?,?,?)";
        try(Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1,elemento.getFollower());
            stmt.setLong(2,elemento.getFollowed());
            stmt.setDate(3,java.sql.Date.valueOf(elemento.getDataCriacao()));
            stmt.setDate(4,java.sql.Date.valueOf(elemento.getDataModificacao()));

            stmt.execute();

            System.out.println("Elemento inserido com sucesso.");
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return elemento;
    }

    @Override
    public ArrayList<Seguir> list() {
        return null;
    }

    @Override
    public Seguir update(Seguir elemento, int op) {
        return null;
    }

    @Override
    public Seguir delete(Seguir elemento) {
        return null;
    }


}

