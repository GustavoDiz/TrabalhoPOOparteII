package model;

import utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PessoaDAO implements DAO<Pessoa>{
    @Override
    public Pessoa add(Pessoa elemento) {
        String sql = "insert into pessoa" +
                     "(nome, sexo, nascimento, usuario, senha, dataCriacao, dataModificacao) " +
                     "values (?,?,?,?,?,?,?)";
        try (Connection connection = new ConnectionFactory().getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)){
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
    public List<Pessoa> list() {
        return null;
    }

    @Override
    public Pessoa update(Pessoa elemento) {
        return null;
    }

    @Override
    public Pessoa delete(Pessoa elemento) {
        return null;
    }
}
