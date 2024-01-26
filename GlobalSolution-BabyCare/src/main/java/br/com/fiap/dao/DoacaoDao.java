package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fiap.exception.DoacaoDaoException;
import br.com.fiap.models.Doacao;

public class DoacaoDao {

    private Connection conexao;


    // Inserir
    public void cadastrarDoacao(Doacao doacao) throws DoacaoDaoException {
        conexao = GerenciadorBd.obterConexao();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("INSERT INTO doacao (nome_completo, email, telefone, forma_pagamento, valor) VALUES (?, ?, ?, ?, ?)");
            comandoSql.setString(1, doacao.getNome_completo());
            comandoSql.setString(2, doacao.getEmail());
            comandoSql.setString(3, doacao.getTelefone());
            comandoSql.setString(4, doacao.getForma_pagamento());
            comandoSql.setDouble(5, doacao.getValor());

            comandoSql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DoacaoDaoException("Erro ao cadastrar doação.", e);
        } finally {
            fecharRecursos(comandoSql);
        }
    }
    
    // Método auxiliar para fechar recursos
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
