package model;

import utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class RefeicaoDAO implements DAO<Refeicao> {

    public ArrayList<Refeicao> myList (long id){
        ArrayList<Refeicao> result = new ArrayList<>();
        String sql = "select * from refeicao where user_id = ?";
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1,id);
            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    Refeicao af = new Refeicao();
                    af.setId(rs.getLong("id"));
                    af.setNome(rs.getString("nome"));
                    af.setCodigo_user(rs.getLong("user_id"));
                    af.setCodigo_dietType(rs.getLong("diet_type_id"));
                    af.setCarboidrato(rs.getDouble("carboidrato"));
                    af.setProteina(rs.getDouble("proteina"));
                    af.setGordura(rs.getDouble("gordura"));
                    af.setCalorias(rs.getDouble("calorias"));
                    result.add(af);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  result;
    }

    public Refeicao getMealByID(long index) {
        String sql = "SELECT * FROM refeicao WHERE id = ?";

        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, index);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Refeicao p = new Refeicao();
                    p.setId(rs.getLong("id"));
                    p.setNome(rs.getString("nome"));
                    p.setCodigo_user(rs.getLong("user_id"));
                    p.setCodigo_dietType(rs.getLong("diet_type_id"));
                    p.setCarboidrato(rs.getDouble("carboidrato"));
                    p.setProteina(rs.getDouble("proteina"));
                    p.setGordura(rs.getDouble("gordura"));
                    p.setCalorias(rs.getDouble("calorias"));
                    return p;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Refeicao add(Refeicao elemento) {
        String sql = "insert into refeicao" +
                "(nome, user_id, diet_type_id, carboidrato, proteina, gordura, calorias) " +
                "values (?,?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, elemento.getNome());
            stmt.setLong(2, elemento.getCodigo_user());
            stmt.setLong(3, elemento.getCodigo_dietType());
            stmt.setDouble(4, elemento.getCarboidrato());
            stmt.setDouble(5, elemento.getProteina());
            stmt.setDouble(6, elemento.getGordura());
            stmt.setDouble(7, elemento.getCalorias());

            stmt.execute();

            System.out.println("Elemento inserido com sucesso.");
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return elemento;
    }

    @Override
    public ArrayList<Refeicao> list() {
        return null;
    }

    @Override
    public Refeicao update(Refeicao elemento, int op) {
        String sql = "UPDATE refeicao SET calorias=?,gordura=?,carboidrato=?,proteina=? WHERE id = ?";
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1,elemento.getCalorias());
            stmt.setDouble(2,elemento.getGordura());
            stmt.setDouble(3,elemento.getCarboidrato());
            stmt.setDouble(4,elemento.getProteina());
            stmt.setLong(5,elemento.getId());
            stmt.execute();

            System.out.println("Elemento Atualizado com sucesso.");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return elemento;
    }

    @Override
    public Refeicao delete(Refeicao elemento) {
        String sql = "DELETE FROM refeicao WHERE id = ?";
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
}
