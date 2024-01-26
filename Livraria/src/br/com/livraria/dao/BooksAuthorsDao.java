package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class BooksAuthorsDao {

    private Connection conexao = null;

    public BooksAuthorsDao() {
        this.conexao = new GerenciadorBD().obterConexao();
    }

    
    public void inserirBooksAuthors(String isbn, int author_id) {
        try {
            String sql = "INSERT INTO BooksAuthors (isbn, author_id, seq_no) VALUES (?, ?, ?)";
            PreparedStatement comandoSql = conexao.prepareStatement(sql);
            comandoSql.setString(1, isbn); 
            comandoSql.setInt(2, author_id); 

            Random random = new Random();
            int seq_no = random.nextInt(4) + 1; 
            comandoSql.setInt(3, seq_no);

            comandoSql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
     
        }
    }

    public String buscarIsbnPorNomeAutor(String autorName) {
        String isbn = null;
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("SELECT b.isbn " +
                    "FROM booksauthors ba " +
                    "JOIN authors a ON ba.author_id = a.author_id " +
                    "JOIN books b ON ba.isbn = b.isbn " +
                    "WHERE a.name = ?");

            comandoSql.setString(1, autorName);
            ResultSet rs = comandoSql.executeQuery();

            if (rs.next()) {
                isbn = rs.getString("isbn");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isbn;
    }

    public String buscarIsbnPorTitle(String title) {
        String isbn = null;
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("SELECT b.isbn " +
                    "FROM books b " +
                    "JOIN booksauthors ba ON b.isbn = ba.isbn " +
                    "WHERE b.title = ?");

            comandoSql.setString(1, title);
            ResultSet rs = comandoSql.executeQuery();

            if (rs.next()) {
                isbn = rs.getString("isbn");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isbn;
    }

    public String buscarIsbnPorPublisherId(int publisher_id) {
        String isbn = null;
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("SELECT ba.isbn " +
                    "FROM booksauthors ba " +
                    "JOIN books b ON ba.isbn = b.isbn " +
                    "WHERE b.publisher_id = ?");

            comandoSql.setInt(1, publisher_id);
            ResultSet rs = comandoSql.executeQuery();

            if (rs.next()) {
                isbn= rs.getString("isbn");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isbn;
    }

    public boolean excluirLivroAutorPorISBN(String isbn) {
        PreparedStatement comandoSql = null;

        try {
            String sql = "DELETE FROM booksauthors WHERE isbn = ?";
            comandoSql = conexao.prepareStatement(sql);
            comandoSql.setString(1, isbn);
            comandoSql.executeUpdate();

            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  true;
    }

    public void atualizarIsbnDoLivroAutor(String isbn, String newISBN) {
        PreparedStatement comandoSql = null;

        try {
            String sql = "UPDATE booksauthors SET isbn = ? WHERE isbn = ?";
            comandoSql = conexao.prepareStatement(sql);

            comandoSql.setString(1, newISBN);
            comandoSql.setString(2, isbn);

            int linhasAfetadas = comandoSql.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("BooksAuthors atualizado com sucesso.");
            } else {
                System.out.println("Nenhum livro foi atualizado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
    
            if (comandoSql != null) {
                try {
                    comandoSql.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



