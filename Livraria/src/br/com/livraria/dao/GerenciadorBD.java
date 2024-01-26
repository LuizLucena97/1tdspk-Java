package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GerenciadorBD {
    public Connection obterConexao(){
        Connection conexao = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");


            String url = "jdbc:mysql://localhost:3306/livraria";
            String usuario = "root";
            String senha = "12345678";


            conexao = DriverManager.getConnection(url, usuario, senha);

            return conexao;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
