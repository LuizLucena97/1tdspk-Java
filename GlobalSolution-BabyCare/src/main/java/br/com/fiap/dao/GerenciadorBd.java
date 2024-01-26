package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GerenciadorBd {
    public static Connection obterConexao(){
        Connection conexao = null;
        try{
        	Class.forName("oracle.jdbc.OracleDriver");
            conexao = DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl",
                    "RM550906",
                    "131104"
            );
        }catch (SQLException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
        return conexao;
    }
}