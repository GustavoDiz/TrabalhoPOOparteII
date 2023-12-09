package model;

import utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class AvaliacaoFisicaDAO implements DAO<AvaliacaoFisica> {

    @Override
    public AvaliacaoFisica add(AvaliacaoFisica elemento) {
        String sql = "insert into avaliacaofisica" +
                     "(pessoa_id,peso,altura,idade,pescoco,cintura,quadril,abdomen,bf,imc,tbm,massaGorda,massaMagra,dataCriacao,dataModificacao)"+
                     "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try(Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1,elemento.getIdUser());
            stmt.setDouble(2,elemento.getPeso());
            stmt.setDouble(3,elemento.getAltura());
            stmt.setInt(4,elemento.getIdade());
            stmt.setDouble(5,elemento.getPescoco());
            stmt.setDouble(6,elemento.getCintura());
            stmt.setDouble(7,elemento.getQuadril());
            stmt.setDouble(8,elemento.getAbdomen());
            stmt.setDouble(9,elemento.getBf());
            stmt.setDouble(10,elemento.getImc());
            stmt.setDouble(11,elemento.getTbm());
            stmt.setDouble(12,elemento.getMassaGorda());
            stmt.setDouble(13,elemento.getMassaMagra());
            stmt.setDate(6,java.sql.Date.valueOf(elemento.getDataCriacao()));
            stmt.setDate(7,java.sql.Date.valueOf(elemento.getDataModificacao()));
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return elemento;

    }

    @Override
    public ArrayList<AvaliacaoFisica> list() {
        String sql = "select * from avaliacaofisica";
        ArrayList<AvaliacaoFisica> avaliacoes = new ArrayList<>();
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()){
                long id = rs.getLong("id");
                long idUser = rs.getLong("pessoa_id");
                double peso = rs.getDouble("peso");
                double altura = rs.getDouble("altura");
                int idade = rs.getInt("idade");
                double pescoco = rs.getDouble("pescoco");
                double cintura = rs.getDouble("cintura");
                double quadril = rs.getDouble("quadril");
                double abdomen = rs.getDouble("abdomen");
                double bf = rs.getDouble("bf");
                double imc = rs.getDouble("imc");
                double tbm = rs.getDouble("tbm");
                double massaGorda = rs.getDouble("massaGorda");
                double massaMagra = rs.getDouble("massaMagra");
                Date criacao = rs.getDate("dataCriacao");
                Date modificacao = rs.getDate("dataModificacao");

                AvaliacaoFisica af = new AvaliacaoFisica();
                af.setId(id);
                af.setIdUser(idUser);
                af.setPeso(peso);
                af.setAltura(altura);
                af.setIdade(idade);
                af.setPescoco(pescoco);
                af.setCintura(cintura);
                af.setQuadril(quadril);
                af.setAbdomen(abdomen);
                af.setBf(bf);
                af.setImc(imc);
                af.setTbm(tbm);
                af.setMassaGorda(massaGorda);
                af.setMassaMagra(massaMagra);
                af.setDataCriacao(criacao.toLocalDate());
                af.setDataModificacao(modificacao.toLocalDate());

                avaliacoes.add(af);
            }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }

        return  avaliacoes;
    }

    @Override
    public AvaliacaoFisica update(AvaliacaoFisica elemento, int op) {
        return null;
    }

    @Override
    public AvaliacaoFisica delete(AvaliacaoFisica elemento) {
        String sql = "delete from avaliacaofisica where id = ?";
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1,elemento.getId());
            stmt.execute();
            System.out.println("Elemento excluído com sucesso.");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return  null;
    }
}
