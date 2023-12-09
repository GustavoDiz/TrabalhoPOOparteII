package model;

import utils.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PessoaDAO implements DAO<Pessoa>{
    @Override
    public Pessoa add(Pessoa elemento) {
        String sql = "insert into pessoa" +
                     "(nome, sexo, nascimento, usuario, senha, dataCriacao, dataModificacao) " +
                     "values (?,?,?,?,?,?,?)";
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1,elemento.getNome());
            stmt.setString(2,elemento.getSexo()+"");
            stmt.setDate(3,java.sql.Date.valueOf(elemento.getNascimento()));
            stmt.setString(4,elemento.getUsuario());
            stmt.setString(5,elemento.getSenha());
            stmt.setDate(6,java.sql.Date.valueOf(elemento.getDataCriacao()));
            stmt.setDate(7,java.sql.Date.valueOf(elemento.getDataModificacao()));

            stmt.execute();

            System.out.println("Elemento inserido com sucesso.");
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return elemento;
    }

    @Override
    public ArrayList<Pessoa> list() {
        String sql = "select * from pessoa";
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery(sql)
                ) {

            while (rs.next()){
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String sexo = rs.getString("sexo");
                Date data = rs.getDate("nascimento");
                LocalDate nascimento = data.toLocalDate();

                Pessoa p = new Pessoa();
                p.setId(id);
                p.setNome(nome);
                p.setSexo(sexo.charAt(0));
                p.setNascimento(nascimento);
                pessoas.add(p);

            }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return  pessoas;
    }

    @Override
    public Pessoa update(Pessoa elemento, int op) {
        String sql;
        switch (op){
            case 1:
                sql = "update pessoa set nome = ? where id = ?";
                break;
            case 2:
                sql = "update pessoa set sexo = ? where id = ?";
                break;
            case 3:
                sql = "update pessoa set nascimento = ? where id = ?";
                break;
            case 4:
                sql = "update pessoa set usuario = ? where id = ?";
                break;
            case 5:
                sql = "update pessoa set senha = ? where id = ?";
                break;
        }

        return null;
    }


    @Override
    public Pessoa delete(Pessoa elemento) {
        String sql = "delete from contatos where id = ?";
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
