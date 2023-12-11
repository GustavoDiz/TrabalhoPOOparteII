package model;

import utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;

public class RegistroDietaDAO implements DAO<RegistroDieta>{

    @Override
    public RegistroDieta add(RegistroDieta elemento) {
        String sql = "insert into registrodieta" +
                "(user_id, physical_assessment_id, diet_id, goal, calories, n_meals, dataCriacao, dataModificacao) " +
                "values (?,?,?,?,?,?,?,?)";

        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setLong(1, elemento.getUserID());
            stmt.setString(2, String.valueOf(elemento.getPhysicalAssessmentID()));
            stmt.setString(3, String.valueOf(elemento.getTipoDietaid()));
            stmt.setString(4, String.valueOf(elemento.getGoal()));
            stmt.setDouble(5, elemento.getCalories());
            stmt.setLong(6, elemento.getnMeals());
            stmt.setDate(7,java.sql.Date.valueOf(elemento.getDataCriacao()));
            stmt.setDate(8, java.sql.Date.valueOf(elemento.getDataModificacao()));
            stmt.execute();

            System.out.println("Elemento inserido com sucesso.");
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return elemento;
    }

    public RegistroDieta getRegisterByUser(long id) {
        RegistroDieta armazenar = null;
        String sql = "select * from registrodieta where user_id = ? Order by dataCriacao limit 1";
        RegistroDieta af = null;
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    long idAf = rs.getLong("id");
                    long idUser = rs.getLong("user_id");
                    long physical = rs.getLong("physical_assessment_id");
                    long dietID = rs.getLong("diet_id");
                    long goals = rs.getLong("goal");
                    double calories = rs.getDouble("calories");
                    long meals = rs.getLong("n_meals");
                    Date criacao = rs.getDate("dataCriacao");
                    Date modificacao = rs.getDate("dataModificacao");

                    af = new RegistroDieta();
                    af.setId(idAf);
                    af.setUserID(idUser);
                    af.setPhysicalAssessmentID(physical);
                    af.setTipoDietaid(dietID);
                    af.setCalories(calories);
                    af.setGoal((int) goals);
                    af.setnMeals((int) meals);
                    af.setDataCriacao(criacao.toLocalDate());
                    af.setDataModificacao(modificacao.toLocalDate());

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return af;

    }

    public ArrayList<AvaliacaoFisica> myList (long id){
        ArrayList<AvaliacaoFisica> result = new ArrayList<>();
        String sql = "select * from avaliacaofisica where pessoa_id = ?";
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1,id);
            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    long idAf = rs.getLong("id");
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
                    af.setId(idAf);
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
                    result.add(af);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  result;
    }

    @Override
    public ArrayList<RegistroDieta> list() {
        return null;
    }

    @Override
    public RegistroDieta update(RegistroDieta elemento, int op) {
        return null;
    }

    @Override
    public RegistroDieta delete(RegistroDieta elemento) {
        String sql = "delete from registrodieta where id = ?";
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1,elemento.getId());
            stmt.execute();
            System.out.println("Dieta excluída com sucesso!!.");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return  null;
    }
}
