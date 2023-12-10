package model;

import utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostDAO implements DAO<Post>{
    @Override
    public Post add(Post elemento) {
        String sql = "insert into post" +
                     "(user_id,msg,dataCriacao,dataModificacao)"+
                     "values (?,?,?,?)";
        try(Connection connection = new ConnectionFactory().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql) ){
            stmt.setLong(1,elemento.getUserId());
            stmt.setString(2,elemento.getMsg());
            stmt.setDate(3,java.sql.Date.valueOf(elemento.getDataCriacao()));
            stmt.setDate(4,java.sql.Date.valueOf(elemento.getDataModificacao()));

            stmt.execute();

            System.out.println("Elemento inserido com sucesso.");
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
        return elemento;
    }

    @Override
    public ArrayList<Post> list() {
        return null;
    }

    @Override
    public Post update(Post elemento, int op) {
        return null;
    }

    @Override
    public Post delete(Post elemento) {
        return null;
    }

    public ArrayList<Post> listMyPosts(long id){
        String sql = "SELECT p.id, p.msg, p.user_id, p.dataCriacao, p.dataModificacao, pessoa.nome " +
                "FROM post p " +
                "INNER JOIN seguir s ON p.user_id = s.followed_id " +
                "INNER JOIN pessoa ON p.user_id = pessoa.id " +
                "WHERE s.follower_id = ?";

        ArrayList<Post> posts = new ArrayList<>();
        try (
                Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Post post = new Post();
                    post.setId(rs.getLong("id"));
                    post.setMsg(rs.getString("msg"));
                    post.setUserId(rs.getLong("user_id"));
                    post.setDataCriacao(rs.getDate("dataCriacao").toLocalDate());
                    post.setDataModificacao(rs.getDate("dataModificacao").toLocalDate());

                    Pessoa pessoa = new Pessoa();
                    pessoa.setNome(rs.getString("nome"));
                    post.setUser(pessoa);

                    posts.add(post);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return posts;
    }


}
