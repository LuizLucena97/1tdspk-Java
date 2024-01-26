package dao;

import models.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDao {

    private Connection conexao = null;

    public BookDao() {
        this.conexao = new GerenciadorBD().obterConexao();
    }

    public Book buscarLivroComAutorPorTitulo(String title) {
        Book book = null;
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("SELECT b.*, a.name AS author_name, p.name AS publishers_name " +
                    "FROM books b " +
                    "JOIN booksauthors ba ON b.isbn = ba.isbn " +
                    "JOIN authors a ON ba.author_id = a.author_id " +
                    "JOIN publishers p ON b.publisher_id = p.publisher_id " +
                    "WHERE b.title = ?");

            comandoSql.setString(1, title);
            ResultSet rs = comandoSql.executeQuery();

            if (rs.next()) {
                book = new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setTitle(rs.getString("title"));
                book.setPrice(rs.getDouble("price"));
                book.setPublisherName(rs.getString("publishers_name"));
                book.setAuthorName(rs.getString("author_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public void incluir(Book books) {
        PreparedStatement comandoSql = null;
        try {
            if (books.getPublisher_id() == 0) {

                System.out.println("O livro deve ter uma editora associada.");
                return; 
            }

            String sql = "INSERT INTO books (title, isbn, price, publisher_id) VALUES (?,?,?,?)";
            comandoSql = conexao.prepareStatement(sql);
            comandoSql.setString(1, books.getTitle());
            comandoSql.setString(2, books.getIsbn());
            comandoSql.setDouble(3, books.getPrice());
            comandoSql.setInt(4, books.getPublisher_id());
   

            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean excluir(String title) {
        PreparedStatement comandoSql = null;
        try {
            String sql = "DELETE FROM books WHERE title = ?";
            comandoSql = conexao.prepareStatement(sql);
            comandoSql.setString(1, title);
            comandoSql.executeUpdate();

            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean excluirLivrosPorISBN(String isbn) {
        PreparedStatement comandoSql = null;
        try {
            String sql = "DELETE FROM books WHERE isbn = ?";
            comandoSql = conexao.prepareStatement(sql);
            comandoSql.setString(1, isbn);
            comandoSql.executeUpdate();

            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean excluirLivrosPorPublisher_id(int publisher_id) {
        PreparedStatement comandoSql = null;
        try {
            String sql = "DELETE FROM books WHERE publisher_id = ?";
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

    public void atualizarTituloDoLivro(String title, String newTitle) {
        PreparedStatement comandoSql = null;

        try {
            String sql = "UPDATE books SET title = ? WHERE title = ?";
            comandoSql = conexao.prepareStatement(sql);

            comandoSql.setString(1, newTitle);
            comandoSql.setString(2, title);

            int linhasAfetadas = comandoSql.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("          Livro atualizado com sucesso.");
                System.out.println("-----------------------------------------------------------");
            } else {
                System.out.println("-----------------------------------------------------------");
                System.out.println("            Nenhum livro foi atualizado.");
                System.out.println("-----------------------------------------------------------");
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

    public void atualizarIsbnDoLivro(String title, String newISBN) {
        PreparedStatement comandoSql = null;

        try {
            String sql = "UPDATE books SET isbn = ? WHERE title = ?";
            comandoSql = conexao.prepareStatement(sql);

            comandoSql.setString(1, newISBN);
            comandoSql.setString(2, title);

            int linhasAfetadas = comandoSql.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("           Livro atualizado com sucesso.");
                System.out.println("-----------------------------------------------------------");
            } else {
                System.out.println("-----------------------------------------------------------");
                System.out.println("            Nenhum livro foi atualizado.");
                System.out.println("-----------------------------------------------------------");
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

    public void atualizarPrecoDoLivro(String title2, double newPrice) {
        PreparedStatement comandoSql = null;

        try {
            String sql = "UPDATE books SET price = ? WHERE title = ?";
            comandoSql = conexao.prepareStatement(sql);

            comandoSql.setDouble(1, newPrice);
            comandoSql.setString(2, title2);

            int linhasAfetadas = comandoSql.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("            Livro atualizado com sucesso.");
                System.out.println("-----------------------------------------------------------");
            } else {
                System.out.println("-----------------------------------------------------------");
                System.out.println("             Nenhum livro foi atualizado.");
                System.out.println("-----------------------------------------------------------");
            }
        } catch (SQLException e) {

        }
    }

    public void atualizarEditoraDoLivro(String title3, int newPublisherId) {
        PreparedStatement comandoSql = null;

        try {
            String sql = "UPDATE books SET publisher_id = ? WHERE title = ?";
            comandoSql = conexao.prepareStatement(sql);

            comandoSql.setInt(1, newPublisherId);
            comandoSql.setString(2, title3);

            int linhasAfetadas = comandoSql.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("-----------------------------------------------------------");
                System.out.println("            Livro atualizado com sucesso.");
                System.out.println("-----------------------------------------------------------");
            } else {
                System.out.println("-----------------------------------------------------------");
                System.out.println("             Nenhum livro foi atualizado.");
                System.out.println("-----------------------------------------------------------");
            }
        } catch (SQLException e) {

        }
    }

}
