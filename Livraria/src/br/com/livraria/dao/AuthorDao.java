package dao;

import models.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AuthorDao {
    private Connection conexao = null;

    public AuthorDao() {
        this.conexao = new dao.GerenciadorBD().obterConexao();
    }

    public Author buscarPorName(String autorName) {
        Author author = null;
        try (PreparedStatement comandoSql = conexao.prepareStatement("SELECT * FROM authors WHERE name = ?")) {
            comandoSql.setString(1, autorName);
            try (ResultSet rs = comandoSql.executeQuery()) {
                if (rs.next()) {
                    author = new Author();
                    author.setAuthor_Id(rs.getInt(1));
                    author.setName(rs.getString(2));
                    author.setfName(rs.getString(3));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }


    public Author buscarPorfName(String fName){
        Author author = null;
        PreparedStatement comandoSql = null;
        try{
            comandoSql = conexao.prepareStatement("Select * from authors where fName = ?");
            comandoSql.setString(1,fName);
            ResultSet rs = comandoSql.executeQuery();
            if(rs.next()){
                author = new Author();
                author.setAuthor_Id(rs.getInt(1));
                author.setName(rs.getString(2));
                author.setfName(rs.getString(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return author;
    }

    public void incluir(Author author)  {
        PreparedStatement comandoSql = null;
        try{
            String sql = "insert into authors (name, fName)" +
                    " VALUES (?,?)";
            comandoSql = conexao.prepareStatement(sql);
            comandoSql.setString(1, author.getName());
            comandoSql.setString(2, author.getfName());


            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean excluir(int id)  {
        PreparedStatement comandoSql = null;
        try {
            String sql = "DELETE FROM authors WHERE author_id = ?";
            comandoSql = conexao.prepareStatement(sql);
            comandoSql.setInt(1, id);
            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
      public List<Author> listarAuthor() {
        List<Author> authors= new ArrayList<>();
        Connection conexao = null;
        PreparedStatement comandoSql = null;

        try {
            conexao = new dao.GerenciadorBD().obterConexao();
            String sql = "SELECT * FROM authors";
            comandoSql = conexao.prepareStatement(sql);
            ResultSet rs = comandoSql.executeQuery();

            while (rs.next()) {
                Author author = new Author();
                author.setAuthor_Id(rs.getInt("Author_id"));
                author.setName(rs.getString("name"));
                author.setfName(rs.getString("Fname"));
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        
        }

        return authors;
    }

    public void atualizarAutor(String nome, String newName) {
        PreparedStatement comandoSql = null;
        try {
   
            String sql = "UPDATE authors SET name = ? WHERE name = ?";
    
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
      
                statement.setString(1, nome);
                statement.setString(2, newName);
    
   
                int linhasAfetadas = statement.executeUpdate();
    
                if (linhasAfetadas > 0) {
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("             Autor atualizado com sucesso!");
                    System.out.println("-----------------------------------------------------------");
                } else {
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("Nenhum autor foi atualizado. Verifique o nome do autor.");
                    System.out.println("-----------------------------------------------------------");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {

        }
    }

    public void atualizarSobrenomeAutor(String nome2, String newFName) {
        PreparedStatement comandoSql = null;
        try {

            String sql = "UPDATE authors SET fname = ? WHERE name = ?";
    
            try (PreparedStatement statement = conexao.prepareStatement(sql)) {
    
                statement.setString(1, nome2);
                statement.setString(2, newFName);
    
     
                int linhasAfetadas = statement.executeUpdate();
    
                if (linhasAfetadas > 0) {
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("              Autor atualizado com sucesso!");
                    System.out.println("-----------------------------------------------------------");
                } else {
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("Nenhum autor foi atualizado. Verifique o nome do autor.");
                    System.out.println("-----------------------------------------------------------");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {

        }
    }
    
    
}
