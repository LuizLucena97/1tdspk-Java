package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.exception.ProfissionalDaoException;
import br.com.fiap.models.Profissional;

public class ProfissionalDao {

    private Connection conexao;
    
    public ProfissionalDao() {
        conexao = GerenciadorBd.obterConexao();
    }

    public Profissional consultarProfissional(int id) throws ProfissionalDaoException {
        Profissional profissional = new Profissional();
        PreparedStatement comandoSql = null;
        
        try {
            comandoSql = conexao.prepareStatement("SELECT * FROM profissional WHERE id_profissional = ?");
            comandoSql.setInt(1, id);
            
            ResultSet resultSet = comandoSql.executeQuery();
            if (resultSet.next()) {
                profissional = new Profissional();
                profissional.setId(resultSet.getInt("id_profissional"));
                profissional.setNome_profissional(resultSet.getString("nome_profissional"));
                profissional.setRm_profissional(resultSet.getString("rm_profissional"));
                profissional.setSenha(resultSet.getString("senha"));
                profissional.setFoto(resultSet.getString("foto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ProfissionalDaoException("Erro ao consultar profissional por ID.", e);
        } finally {
            fecharRecursos(comandoSql);
        }

        return profissional;
    }

    public List<Profissional> listarProfissionais() throws ProfissionalDaoException {
        List<Profissional> profissionais = new ArrayList<>();
        PreparedStatement comandoSql = null;

        try {
            comandoSql = conexao.prepareStatement("SELECT * FROM profissional");
            ResultSet resultSet = comandoSql.executeQuery();
            while (resultSet.next()) {
                Profissional profissional = new Profissional();
                profissional.setId(resultSet.getInt("id_profissional"));
                profissional.setNome_profissional(resultSet.getString("nome_profissional"));
                profissional.setRm_profissional(resultSet.getString("rm_profissional"));
                profissional.setSenha(resultSet.getString("senha"));
                profissional.setFoto(resultSet.getString("foto"));
                profissionais.add(profissional);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ProfissionalDaoException("Erro ao listar profissionais.", e);
        } finally {
            fecharRecursos(comandoSql);
        }

        return profissionais;
    }

    // Fechar recursos
    private void fecharRecursos(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
          
        }
    }
}
