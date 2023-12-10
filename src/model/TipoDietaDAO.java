package model;

import utils.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TipoDietaDAO implements DAO<TipoDieta>{
    @Override
    public TipoDieta add(TipoDieta elemento) {
        String sql = "insert into tipodieta" +
                "(nome, carboidrato, proteina, gordura, dataCriacao, dataModificacao) " +
                "values (?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, elemento.getNome());
            stmt.setString(2, String.valueOf(elemento.getCarboidrato()));
            stmt.setString(3, String.valueOf(elemento.getProteina()));
            stmt.setString(4, String.valueOf(elemento.getGordura()));
            stmt.setDate(5,java.sql.Date.valueOf(elemento.getDataCriacao()));
            stmt.setDate(6, java.sql.Date.valueOf(elemento.getDataModificacao()));

            stmt.execute();

            System.out.println("Elemento inserido com sucesso.");
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return elemento;
    }

    @Override
    public ArrayList<TipoDieta> list() {
        ArrayList<TipoDieta> dietas = new ArrayList<>();
        String sql = "select * from tipodieta";
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()){
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String carboidratos = rs.getString("carboidrato");
                String proteinas = rs.getString("proteina");
                String gorduras = rs.getString("gordura");
                Date criacao = rs.getDate("dataCriacao");
                Date modificacao = rs.getDate("dataModificacao");

                TipoDieta v = new TipoDieta();
                v.setId(id);
                v.setNome(nome);
                v.setCarboidrato(Double.parseDouble(carboidratos));
                v.setProteina(Double.parseDouble(proteinas));
                v.setGordura(Double.parseDouble(gorduras));
                v.setDataCriacao(criacao.toLocalDate());
                v.setDataModificacao(modificacao.toLocalDate());
                dietas.add(v);
            }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return dietas;
    }

    @Override
    public TipoDieta update(TipoDieta elemento, int op) {
            return null;
        }

    @Override
    public TipoDieta delete(TipoDieta elemento) {
        return null;
    }
}
