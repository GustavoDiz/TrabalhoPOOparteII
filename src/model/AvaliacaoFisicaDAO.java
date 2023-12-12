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
            stmt.setDate(14,java.sql.Date.valueOf(elemento.getDataCriacao()));
            stmt.setDate(15,java.sql.Date.valueOf(elemento.getDataModificacao()));

            stmt.execute();

            System.out.println("Elemento Adicionado com Sucesso");
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
                AvaliacaoFisica af = new AvaliacaoFisica();
                af.setId(rs.getLong("id"));
                af.setIdUser(rs.getLong("pessoa_id"));
                af.setPeso( rs.getDouble("peso"));
                af.setAltura(rs.getDouble("altura"));
                af.setIdade(rs.getInt("idade"));
                af.setPescoco(rs.getDouble("pescoco"));
                af.setCintura(rs.getDouble("cintura"));
                af.setQuadril(rs.getDouble("quadril"));
                af.setAbdomen(rs.getDouble("abdomen"));
                af.setBf(rs.getDouble("bf"));
                af.setImc(rs.getDouble("imc"));
                af.setTbm(rs.getDouble("tbm"));
                af.setMassaGorda(rs.getDouble("massaGorda"));
                af.setMassaMagra(rs.getDouble("massaMagra"));
                af.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                af.setDataModificacao(rs.getDate("dataModificacao").toLocalDate());

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

    public ArrayList<AvaliacaoFisica> myList (long id){
        ArrayList<AvaliacaoFisica> result = new ArrayList<>();
        String sql = "select * from avaliacaofisica where pessoa_id = ?";
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1,id);
            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    AvaliacaoFisica af = new AvaliacaoFisica();
                    af.setId(rs.getLong("id"));
                    af.setIdUser(rs.getLong("pessoa_id"));
                    af.setPeso(rs.getDouble("peso"));
                    af.setAltura(rs.getDouble("altura"));
                    af.setIdade(rs.getInt("idade"));
                    af.setPescoco(rs.getDouble("pescoco"));
                    af.setCintura(rs.getDouble("cintura"));
                    af.setQuadril(rs.getDouble("quadril"));
                    af.setAbdomen(rs.getDouble("abdomen"));
                    af.setBf(rs.getDouble("bf"));
                    af.setImc(rs.getDouble("imc"));
                    af.setTbm(rs.getDouble("tbm"));
                    af.setMassaGorda(rs.getDouble("massaGorda"));
                    af.setMassaMagra(rs.getDouble("massaMagra"));
                    af.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                    af.setDataModificacao(rs.getDate("dataModificacao").toLocalDate());
                    result.add(af);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  result;
    }
    public long getPhysicalAssessmentID (long id){
        String sql = "select * from avaliacaofisica where pessoa_id = ? order by dataCriacao desc limit 1";
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1,id);
            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    return rs.getLong("id");
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

}
