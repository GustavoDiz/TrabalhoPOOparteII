package model;

import com.sun.source.tree.ReturnTree;
import utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PreferenciaDAO  implements DAO<Preferencia>{
    @Override
    public Preferencia add(Preferencia elemento) {
        String sql = "insert into preferencias" +
                "(user_id,food_id,dataCriacao,dataModificacao)" +
                "values (?,?,?,?)";
        try(Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql) ){
            stmt.setLong(1,elemento.getUserId());
            stmt.setLong(2,elemento.getFoodId());
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
    public ArrayList<Preferencia> list() {
        return null;
    }

    @Override
    public Preferencia update(Preferencia elemento, int op) {
        return null;
    }

    @Override
    public Preferencia delete(Preferencia elemento) {
        String sql = "delete from preferencias where id = ?";
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1,elemento.getId());
            stmt.execute();
            System.out.println("Elemento excluído com sucesso.");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<Preferencia> myPreferences(long id){
        ArrayList<Preferencia> preferencias = new ArrayList<>();
        String sql = "SELECT * FROM preferencias WHERE user_id = ?";
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Preferencia preferencia = new Preferencia();
                    preferencia.setUserId(rs.getLong("user_id"));
                    preferencia.setFoodId(rs.getLong("food_id"));
                    preferencia.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                    preferencia.setDataModificacao(rs.getDate("dataModificacao").toLocalDate());

                    preferencias.add(preferencia);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  preferencias;
    }

}
