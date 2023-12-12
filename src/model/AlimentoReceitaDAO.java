package model;

import utils.ConnectionFactory;

import javax.swing.text.AbstractDocument;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AlimentoReceitaDAO implements DAO<AlimentoReceita>{
    @Override
    public AlimentoReceita add(AlimentoReceita elemento) {
        String sql = "insert into alimentoreceita" +
                "(nome, carboidratos, proteinas, gorduras, porcao, dataCriacao, dataModificacao, calorias) " +
                "values (?,?,?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, elemento.getNome());
            stmt.setString(2, String.valueOf(elemento.getCarboidratos()));
            stmt.setString(3, String.valueOf(elemento.getProteinas()));
            stmt.setString(4, String.valueOf(elemento.getGorduras()));
            stmt.setString(5, String.valueOf(elemento.getPorcao()));
            stmt.setDate(6,java.sql.Date.valueOf(elemento.getDataCriacao()));
            stmt.setDate(7, java.sql.Date.valueOf(elemento.getDataModificacao()));
            stmt.setString(8, String.valueOf(elemento.getCalorias()));
            stmt.execute();

            System.out.println("Elemento inserido com sucesso.");
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return elemento;
    }

    @Override
    public ArrayList<AlimentoReceita> list() {
        String sql = "select * from alimentoreceita";
        ArrayList<AlimentoReceita> alimentos = new ArrayList<>();
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()){
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
                alimentos.add(p);
            }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return alimentos;
    }

    @Override
    public AlimentoReceita update(AlimentoReceita elemento, int op) {
        String sql;
        switch (op){
            case 1:
                sql = "update alimentoreceita set nome = ?,dataModificacao = ? where id = ?";
                try (Connection connection = new ConnectionFactory().getConnection();
                     PreparedStatement stmt = connection.prepareStatement(sql)) {

                    stmt.setString(1, elemento.getNome());
                    stmt.setDate(2, Date.valueOf(LocalDate.now()));
                    stmt.setLong(3, elemento.getId());

                    stmt.execute();

                    System.out.println("Elemento Atualizado com sucesso.");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                sql = "update alimentoreceita set carboidratos = ?, calorias = ?,dataModificacao = ? where id = ?";
                try (Connection connection = new ConnectionFactory().getConnection();
                     PreparedStatement stmt = connection.prepareStatement(sql)) {

                    stmt.setString(1, String.valueOf(elemento.getCarboidratos()));
                    stmt.setString(2, String.valueOf(elemento.getCalorias()));
                    stmt.setDate(3, Date.valueOf(LocalDate.now()));
                    stmt.setLong(4, elemento.getId());

                    stmt.execute();

                    System.out.println("Elemento Atualizado com sucesso.");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                break;

            case 3:
            sql = "update alimentoreceita set proteinas = ?, calorias = ?,dataModificacao = ? where id = ?";
            try (Connection connection = new ConnectionFactory().getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, String.valueOf(elemento.getProteinas()));
                stmt.setString(2, String.valueOf(elemento.getProteinas()));
                stmt.setDate(3, Date.valueOf(LocalDate.now()));
                stmt.setLong(4, elemento.getId());

                stmt.execute();

                System.out.println("Elemento Atualizado com sucesso.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            break;

            case 4:
            sql = "update alimentoreceita set gorduras = ?, calorias = ?,dataModificacao = ? where id = ?";
            try (Connection connection = new ConnectionFactory().getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, String.valueOf(elemento.getGorduras()));
                stmt.setString(2, String.valueOf(elemento.getCalorias()));
                stmt.setDate(3, Date.valueOf(LocalDate.now()));
                stmt.setLong(4, elemento.getId());

                stmt.execute();

                System.out.println("Elemento Atualizado com sucesso.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            break;

            case 5:
            sql = "update alimentoreceita set porcao = ?,dataModificacao = ? where id = ?";
            try (Connection connection = new ConnectionFactory().getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, String.valueOf(elemento.getPorcao()));
                stmt.setDate(2, Date.valueOf(LocalDate.now()));
                stmt.setLong(3, elemento.getId());

                stmt.execute();

                System.out.println("Elemento Atualizado com sucesso.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            break;

        }

        return elemento;
    }

    public AlimentoReceita getRecipeByIDFood(int index){
        String sql = "select * from alimentoreceita where id = ?";

        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, index);

        try(ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String carboidratos = rs.getString("carboidratos");
                String proteinas = rs.getString("proteinas");
                String gorduras = rs.getString("gorduras");
                String porcao = rs.getString("porcao");
                Date criacao = rs.getDate("dataCriacao");
                Date modificacao = rs.getDate("dataModificacao");

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
                return p;
            }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }


        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return null;

    }
    @Override
    public AlimentoReceita delete(AlimentoReceita elemento) {
        String sql = "delete from alimentoreceita where id = ?";
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
