package model;

import utils.ConnectionFactory;

import javax.swing.text.AbstractDocument;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class PessoaDAO implements DAO<Pessoa>{
    @Override
    public Pessoa add(Pessoa elemento) {
        String sql = "insert into pessoa" +
                     "(nome, sexo, nascimento, usuario, senha, dataCriacao, dataModificacao) " +
                     "values (?,?,?,?,?,?,?)";
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1,elemento.getNome());
            stmt.setString(2,elemento.getSexo()+"");
            stmt.setDate(3,java.sql.Date.valueOf(elemento.getNascimento()));
            stmt.setString(4,elemento.getUsuario());
            stmt.setString(5,elemento.getSenha());
            stmt.setDate(6,java.sql.Date.valueOf(elemento.getDataCriacao()));
            stmt.setDate(7,java.sql.Date.valueOf(elemento.getDataModificacao()));
            stmt.execute();

            ResultSet key = stmt.getGeneratedKeys();
            if (key.next()) {
                elemento.setId(key.getLong(1)); // Supondo que a chave primária seja do tipo LONG
            }
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
                String usuario = rs.getString("usuario");
                String senha = rs.getString("senha");
                Date criacao = rs.getDate("dataCriacao");
                Date modificacao = rs.getDate("dataModificacao");
                LocalDate nascimento = data.toLocalDate();

                Pessoa p = new Pessoa();
                p.setId(id);
                p.setNome(nome);
                p.setSexo(sexo.charAt(0));
                p.setNascimento(nascimento);
                p.setUsuario(usuario);
                p.setSenha(senha);
                p.setDataCriacao(criacao.toLocalDate());
                p.setDataModificacao(modificacao.toLocalDate());
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
                sql = "update pessoa set nome = ?,dataModificacao = ? where id = ?";
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
                sql = "update pessoa set sexo = ?,dataModificacao = ? where id = ?";
                try (Connection connection = new ConnectionFactory().getConnection();
                     PreparedStatement stmt = connection.prepareStatement(sql)) {

                    stmt.setString(1, String.valueOf(elemento.getSexo()));
                    stmt.setDate(2, Date.valueOf(LocalDate.now()));
                    stmt.setLong(3, elemento.getId());

                    stmt.execute();

                    System.out.println("Elemento Atualizado com sucesso.");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 3:
                sql = "update pessoa set nascimento = ?,dataModificacao = ? where id = ?";
                try (Connection connection = new ConnectionFactory().getConnection();
                     PreparedStatement stmt = connection.prepareStatement(sql)) {

                    stmt.setDate(1, Date.valueOf(elemento.getNascimento()));
                    stmt.setDate(2, Date.valueOf(LocalDate.now()));
                    stmt.setLong(3, elemento.getId());

                    stmt.execute();

                    System.out.println("Elemento Atualizado com sucesso.");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 4:
                sql = "update pessoa set usuario = ?,dataModificacao = ? where id = ?";
                try (Connection connection = new ConnectionFactory().getConnection();
                     PreparedStatement stmt = connection.prepareStatement(sql)) {

                    stmt.setString(1, elemento.getUsuario());
                    stmt.setDate(2, Date.valueOf(LocalDate.now()));
                    stmt.setLong(3, elemento.getId());

                    stmt.execute();

                    System.out.println("Elemento Atualizado com sucesso.");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 5:
                sql = "update pessoa set senha = ?,dataModificacao = ? where id = ?";
                try (Connection connection = new ConnectionFactory().getConnection();
                     PreparedStatement stmt = connection.prepareStatement(sql)) {

                    stmt.setString(1, elemento.getSenha());
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


    @Override
    public Pessoa delete(Pessoa elemento) {
        String sql = "delete from pessoa where id = ?";
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

    public Pessoa login(String username, String password) {
        String sql = "SELECT * FROM pessoa WHERE usuario = ? AND senha = ?";
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String nome = rs.getString("nome");
                    String sexo = rs.getString("sexo");
                    Date data = rs.getDate("nascimento");
                    String usuario = rs.getString("usuario");
                    String senha = rs.getString("senha");
                    Date criacao = rs.getDate("dataCriacao");
                    Date modificacao = rs.getDate("dataModificacao");
                    LocalDate nascimento = data.toLocalDate();

                    Pessoa p = new Pessoa();
                    p.setId(id);
                    p.setNome(nome);
                    p.setSexo(sexo.charAt(0));
                    p.setNascimento(nascimento);
                    p.setUsuario(usuario);
                    p.setSenha(senha);
                    p.setDataCriacao(criacao.toLocalDate());
                    p.setDataModificacao(modificacao.toLocalDate());

                    return p;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Pessoa findUser(String name) {
        String sql = "SELECT * FROM pessoa WHERE nome = ?";
        try (Connection connection = new ConnectionFactory().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, name);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String nome = rs.getString("nome");
                    String sexo = rs.getString("sexo");
                    Date data = rs.getDate("nascimento");
                    String usuario = rs.getString("usuario");
                    String senha = rs.getString("senha");
                    Date criacao = rs.getDate("dataCriacao");
                    Date modificacao = rs.getDate("dataModificacao");
                    LocalDate nascimento = data.toLocalDate();

                    Pessoa p = new Pessoa();
                    p.setId(id);
                    p.setNome(nome);
                    p.setSexo(sexo.charAt(0));
                    p.setNascimento(nascimento);
                    p.setUsuario(usuario);
                    p.setSenha(senha);
                    p.setDataCriacao(criacao.toLocalDate());
                    p.setDataModificacao(modificacao.toLocalDate());

                    return p;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
