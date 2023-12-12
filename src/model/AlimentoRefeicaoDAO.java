package model;

import utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlimentoRefeicaoDAO implements DAO<AlimentoRefeicao>{
    @Override
    public AlimentoRefeicao add(AlimentoRefeicao elemento) {
        String sql = "INSERT INTO alimentorefeicao " +
                "(meal_id, food_id, portion, protein, fat, calories, dataCriacao, dataModificacao) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1,elemento.getMeal_id());
            stmt.setLong(2,elemento.getFood_id());
            stmt.setDouble(3,elemento.getPortion());
            stmt.setDouble(4,elemento.getProtein());
            stmt.setDouble(5,elemento.getFat());
            stmt.setDouble(6,elemento.getCalories());
            stmt.setDate(7,java.sql.Date.valueOf(elemento.getDataCriacao()));
            stmt.setDate(8,java.sql.Date.valueOf(elemento.getDataModificacao()));

            stmt.execute();
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return elemento;
    }

    @Override
    public ArrayList<AlimentoRefeicao> list() {
        return null;
    }

    @Override
    public AlimentoRefeicao update(AlimentoRefeicao elemento, int op) {
        return null;
    }

    @Override
    public AlimentoRefeicao delete(AlimentoRefeicao elemento) {
        return null;
    }

    public ArrayList<AlimentoReceita> getMeals(long id) {
        ArrayList<AlimentoReceita> result = new ArrayList<>();
        String sql ="SELECT f.* FROM alimentorefeicao a JOIN alimentoreceita f ON f.id = a.food_id WHERE a.meal_id = ?";
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
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
                    result.add(p);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
