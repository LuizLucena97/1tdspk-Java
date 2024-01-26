package br.com.vistoria.dao;

import br.com.vistoria.models.Cliente;

import java.sql.*;

public class ClienteDao {

    private Connection conexao;

    //inserir
    public void cadastrarCliente(Cliente cliente) {
        conexao = GerenciadorBd.obterConexao();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("insert into cliente (cd_cliente, nm_completo, cpf, endereco, telefone, email, dataNascimento, genero, imagemPerfil) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            comandoSql.setInt(1, cliente.getCd_cliente());
            comandoSql.setString(2, cliente.getNm_completo());
            comandoSql.setString(3, cliente.getCpf());
            comandoSql.setString(4, cliente.getEndereco());
            comandoSql.setString(5, cliente.getTelefone());
            comandoSql.setString(6, cliente.getEmail());
            comandoSql.setString(7, cliente.getDataNascimento());
            comandoSql.setString(8, cliente.getGenero());
            
            comandoSql.executeUpdate();
            conexao.close();
            comandoSql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //alterar
    public void atualizarCliente(Cliente cliente) {
        conexao = GerenciadorBd.obterConexao();
        PreparedStatement comandoSql = null;

        try {
            comandoSql = conexao.prepareStatement("UPDATE cliente SET cd_cliente=?, nm_completo=?, cpf=?, endereco=?, telefone=?, email=?, imagemPerfil=?, dataNascimento=?, genero=? WHERE cd_cliente=?");

            comandoSql.setInt(1, cliente.getCd_cliente());
            comandoSql.setString(2, cliente.getNm_completo());
            comandoSql.setString(3, cliente.getCpf());
            comandoSql.setString(4, cliente.getEndereco());
            comandoSql.setString(5, cliente.getTelefone());
            comandoSql.setString(6, cliente.getEmail());
            
            
            comandoSql.setString(7, cliente.getImagemPerfil()); // Armazena a string Base64 diretamente
            

            comandoSql.setString(8, cliente.getDataNascimento());
            comandoSql.setString(9, cliente.getGenero());
            comandoSql.setInt(10, cliente.getCd_cliente());

        
            comandoSql.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Certifique-se de fechar a conexão e o PreparedStatement no bloco finally
            try {
                if (comandoSql != null) {
                    comandoSql.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    public Cliente buscarCliente(int cd_cliente) {
        Cliente cliente = new Cliente();
        conexao = GerenciadorBd.obterConexao();
        PreparedStatement comandoSql = null;

        try {
            comandoSql = conexao.prepareStatement("SELECT * FROM cliente WHERE cd_cliente = ?");
            comandoSql.setInt(1, cd_cliente);

            ResultSet resultado = comandoSql.executeQuery();

            if (resultado.next()) {
                cliente.setCd_cliente(resultado.getInt("cd_cliente"));
                cliente.setNm_completo(resultado.getString("nm_completo"));
                cliente.setCpf(resultado.getString("cpf"));
                cliente.setEndereco(resultado.getString("endereco"));
                cliente.setTelefone(resultado.getString("telefone"));
                cliente.setEmail(resultado.getString("email"));
                cliente.setDataNascimento(resultado.getString("dataNascimento"));
                cliente.setGenero(resultado.getString("genero"));

                // Adiciona o campo imagemPerfilBase64
                cliente.setImagemPerfil(resultado.getString("imagemPerfil"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (comandoSql != null) {
                    comandoSql.close();
                }
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cliente;
    }



    //excluir
    public void excluirCliente(int cd_cliente) {
        String sql = "DELETE FROM cliente WHERE cd_cliente = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, cd_cliente);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
