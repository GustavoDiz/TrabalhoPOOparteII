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

    public ArrayList<AlimentoReceita> myPreferences(long id){
        ArrayList<AlimentoReceita> preferenciasfoods = new ArrayList<>();
        String sql = "SELECT f.* FROM preferencias p JOIN alimentoreceita f ON p.food_id = f.id  WHERE user_id = ?";
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    AlimentoReceita p = new AlimentoReceita();
                    p.setId(rs.getLong("id"));
                    p.setNome(rs.getString("nome"));
                    p.setCarboidratos(Double.parseDouble(rs.getString("carboidratos")));
                    p.setProteinas(Double.parseDouble(rs.getString("proteinas")));
                    p.setGorduras(Double.parseDouble(rs.getString("gorduras")));
                    p.setPorcao(Double.parseDouble(rs.getString("porcao")));
                    p.setCalorias();
                    p.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                    p.setDataModificacao(rs.getDate("dataModificacao").toLocalDate());

                    preferenciasfoods.add(p);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  preferenciasfoods;
    }

}
