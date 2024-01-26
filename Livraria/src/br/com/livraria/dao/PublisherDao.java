package dao;

import models.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherDao {

    private Connection conexao = null;

    public PublisherDao() {
        this.conexao = new GerenciadorBD().obterConexao();
    }

    public Publisher buscarPorNome(String name) {
        Publisher book = null;
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("Select * from publishers where name = ?");
            comandoSql.setString(1, name);
            ResultSet rs = comandoSql.executeQuery();
            if (rs.next()) {
                book = new Publisher();
                book.setPublisher_id(rs.getInt(1));
                book.setName(rs.getString(2));
                book.setUrl(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public int buscarIdPorNome(String editoraNome) {
        int id = -1;
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("SELECT p.publisher_id " +
                    "FROM publishers p " +
                    "WHERE p.name = ?");

            comandoSql.setString(1, editoraNome);
            ResultSet rs = comandoSql.executeQuery();

            if (rs.next()) {
                id = rs.getInt("publisher_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void incluir(Publisher publisher) {
        PreparedStatement comandoSql = null;
        try {
            String sql = "insert into Publishers(name, url)" +
                    " VALUES (?,?)";
            comandoSql = conexao.prepareStatement(sql);

            comandoSql.setString(1, publisher.getName());
            comandoSql.setString(2, publisher.getUrl());

            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean excluir(int publisher_id) {
        PreparedStatement comandoSql = null;
        try {
            String sql = "DELETE FROM publishers WHERE publisher_id = ?";
            comandoSql = conexao.prepareStatement(sql);
            comandoSql.setInt(1, publisher_id);
            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<Publisher> listarPublisher() {
        List<Publisher> publishers = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comandoSql = null;

        try {
            conexao = new GerenciadorBD().obterConexao(); 
            String sql = "SELECT * FROM publishers";
            comandoSql = conexao.prepareStatement(sql);
            ResultSet rs = comandoSql.executeQuery();

            while (rs.next()) {
                Publisher publisher = new Publisher();
                publisher.setPublisher_id(rs.getInt("publisher_id"));
                publisher.setName(rs.getString("name"));
                publisher.setUrl(rs.getString("url"));
                publishers.add(publisher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return publishers;
    }

    public void atualizarPublisherName(String nome, String newName) {
        PreparedStatement comandoSql = null;
        try {
          
            String sql = "UPDATE publishers SET name = ? WHERE name = ?";
    
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
               
                statement.setString(1, nome);
                statement.setString(2, newName);
    
               
                int linhasAfetadas = statement.executeUpdate();
    
                if (linhasAfetadas > 0) {
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("           Editora atualizada com sucesso!");
                    System.out.println("-----------------------------------------------------------");
                } else {
                    System.out.println("Nenhuma Editora foi atualizada. Verifique o nome da editora.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {

        }
    }

    public void atualizarUrlPublisher(String nome2, String newUrl) {
        PreparedStatement comandoSql = null;
        try {
         
            String sql = "UPDATE publishers SET url = ? WHERE name = ?";
    
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
          
                statement.setString(1, nome2);
                statement.setString(2, newUrl);
    
              
                int linhasAfetadas = statement.executeUpdate();
    
                if (linhasAfetadas > 0) {
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("           Editora atualizada com sucesso!");
                    System.out.println("-----------------------------------------------------------");
                } else {
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("Nenhuma Editora foi atualizada. Verifique o nome da editora.");
                    System.out.println("-----------------------------------------------------------");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {

        }
    }

}
