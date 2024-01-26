package br.com.fiap.dao;

import br.com.fiap.exception.SalaDaoException;
import br.com.fiap.models.Sala;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaDao {

    private Connection conexao;

    // Consultar Sala pelo ID
    public Sala consultarSala(int id) throws SalaDaoException {
        Sala sala = new Sala();
        conexao = GerenciadorBd.obterConexao();
        PreparedStatement comandoSql = null;

        try {
            comandoSql = conexao.prepareStatement("SELECT * FROM sala WHERE sala_id = ?");
            comandoSql.setInt(1, id);

            ResultSet resultSet = comandoSql.executeQuery();
            if (resultSet.next()) {
                sala.setId(resultSet.getInt("sala_id"));
                sala.setNome_profissional(resultSet.getString("nome_profissional"));
                sala.setRm_profissional(resultSet.getString("rm_profissional"));
                sala.setSenha(resultSet.getString("senha"));
                sala.setFoto(resultSet.getString("foto"));
                sala.setMensagens(resultSet.getString("mensagem"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SalaDaoException("Erro ao consultar sala por ID.", e);
        } finally {
            fecharRecursos(conexao, comandoSql);
        }

        return sala;
    }

    // Alterar Sala
    public void alterarSala(Sala sala) throws SalaDaoException {
        conexao = GerenciadorBd.obterConexao();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("UPDATE sala SET nome_profissional=?, rm_profissional=?, senha=?, foto=?, mensagem=? WHERE sala_id=?");
            comandoSql.setString(1, sala.getNome_profissional());
            comandoSql.setString(2, sala.getRm_profissional());
            comandoSql.setString(3, sala.getSenha());
            comandoSql.setString(4, sala.getFoto());
            comandoSql.setString(5, sala.getMensagens());
            comandoSql.setInt(6, sala.getId());

            comandoSql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SalaDaoException("Erro ao alterar sala.", e);
        } finally {
            fecharRecursos(conexao, comandoSql);
        }
    }

    // Buscar todas as Salas
    public List<Sala> buscarTodasSalas() throws SalaDaoException {
        List<Sala> salas = new ArrayList<>();
        conexao = GerenciadorBd.obterConexao();
        PreparedStatement comandoSql = null;

        try {
            comandoSql = conexao.prepareStatement("SELECT * FROM sala");

            ResultSet resultSet = comandoSql.executeQuery();
            while (resultSet.next()) {
                Sala sala = new Sala();
                sala.setId(resultSet.getInt("sala_id"));
                sala.setNome_profissional(resultSet.getString("nome_profissional"));
                sala.setRm_profissional(resultSet.getString("rm_profissional"));
                sala.setSenha(resultSet.getString("senha"));
                sala.setFoto(resultSet.getString("foto"));
                sala.setMensagens(resultSet.getString("mensagem"));

                salas.add(sala);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SalaDaoException("Erro ao buscar todas as salas.", e);
        } finally {
            // Certifique-se de fechar a conexão e o comandoSql no bloco finally
            fecharRecursos(conexao, comandoSql);
        }

        return salas;
    }

    // Fechar recursos
    private void fecharRecursos(Connection conexao, PreparedStatement comandoSql) {
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
