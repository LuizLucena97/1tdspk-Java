package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.exception.ArquivoDaoException;
import br.com.fiap.models.Arquivo;

public class ArquivoDao {

    private Connection conexao;

    // Inserir
    public void cadastrarArquivo(Arquivo arquivo) throws ArquivoDaoException {
        conexao = GerenciadorBd.obterConexao();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("INSERT INTO arquivo (nome, arquivo) VALUES (?, ?)");
            comandoSql.setString(1, arquivo.getNome());
            comandoSql.setString(2, arquivo.getArquivo());

            comandoSql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ArquivoDaoException("Erro ao cadastrar arquivo.", e);
        } finally {
            fecharRecursos(conexao, comandoSql);
        }
    }

    // Consultar Arquivo pelo ID
    public Arquivo consultarArquivo(int id) throws ArquivoDaoException {
        Arquivo arquivo = new Arquivo();
        conexao = GerenciadorBd.obterConexao();
        PreparedStatement comandoSql = null;

        try {
            comandoSql = conexao.prepareStatement("SELECT * FROM arquivo WHERE arquivo_id = ?");
            comandoSql.setInt(1, id);

            ResultSet resultSet = comandoSql.executeQuery();
            if (resultSet.next()) {
                arquivo.setId(resultSet.getInt("arquivo_id"));
                arquivo.setNome(resultSet.getString("nome"));
                arquivo.setArquivo(resultSet.getString("arquivo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ArquivoDaoException("Erro ao consultar arquivo por ID.", e);
        } finally {
            fecharRecursos(conexao, comandoSql);
        }

        return arquivo;
    }

    // Outros métodos CRUD e operações necessárias podem ser implementados de maneira semelhante.

    // Consultar todos os arquivos
    public List<Arquivo> consultarTodosArquivos() throws ArquivoDaoException {
        List<Arquivo> listaArquivos = new ArrayList<>();
        conexao = GerenciadorBd.obterConexao();
        PreparedStatement comandoSql = null;

        try {
            comandoSql = conexao.prepareStatement("SELECT * FROM arquivo");
            ResultSet resultSet = comandoSql.executeQuery();

            while (resultSet.next()) {
                Arquivo arquivo = new Arquivo();
                arquivo.setId(resultSet.getInt("arquivo_id"));
                arquivo.setNome(resultSet.getString("nome"));
                arquivo.setArquivo(resultSet.getString("arquivo"));

                listaArquivos.add(arquivo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ArquivoDaoException("Erro ao listar arquivos.", e);
        } finally {
            fecharRecursos(conexao, comandoSql);
        }

        return listaArquivos;
    }

    // Alterar Arquivo pelo ID
    public void alterarArquivo(int id, Arquivo novoArquivo) throws ArquivoDaoException {
        conexao = GerenciadorBd.obterConexao();
        PreparedStatement comandoSql = null;

        try {
            comandoSql = conexao.prepareStatement("UPDATE arquivo SET nome = ?, arquivo = ? WHERE arquivo_id = ?");
            comandoSql.setString(1, novoArquivo.getNome());
            comandoSql.setString(2, novoArquivo.getArquivo());
            comandoSql.setInt(3, id);

            comandoSql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ArquivoDaoException("Erro ao alterar arquivo.", e);
        } finally {
            fecharRecursos(conexao, comandoSql);
        }
    }

    // Deletar um Arquivo pelo ID
    public void deletarArquivo(int id) throws ArquivoDaoException {
        conexao = GerenciadorBd.obterConexao();
        PreparedStatement comandoSql = null;

        try {
            comandoSql = conexao.prepareStatement("DELETE FROM arquivo WHERE arquivo_id = ?");
            comandoSql.setInt(1, id);
            comandoSql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ArquivoDaoException("Erro ao deletar arquivo.", e);
        } finally {
            fecharRecursos(conexao, comandoSql);
        }
    }

    // Método utilitário para fechar recursos
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
