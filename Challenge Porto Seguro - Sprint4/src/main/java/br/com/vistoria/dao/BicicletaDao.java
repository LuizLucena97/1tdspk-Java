package br.com.vistoria.dao;

import br.com.vistoria.models.Bicicleta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BicicletaDao {

    private Connection conexao;

    public BicicletaDao() {
        conexao = GerenciadorBd.obterConexao();
    }

    // Inserir uma nova bicicleta
    public void cadastrarBicicleta(Bicicleta bicicleta) {
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("INSERT INTO Bicicleta (id, nome, numeroSerie, acessorios, modelo, marca, imagem) VALUES (bicicleta_id_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)");
            comandoSql.setString(1, bicicleta.getNome());
            comandoSql.setString(2, bicicleta.getNumeroSerie());
            comandoSql.setString(3, bicicleta.getAcessorios());
            comandoSql.setString(4, bicicleta.getModelo());
            comandoSql.setString(5, bicicleta.getMarca());
            comandoSql.setString(6, bicicleta.getImagem());
            comandoSql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharRecursos(comandoSql);
        }
    }


    // Atualizar informações de uma bicicleta
    public void atualizarBicicleta(Bicicleta bicicleta) {
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("UPDATE Bicicleta SET id = ?, nome = ?, numeroSerie = ?, acessorios = ?, modelo = ?, marca = ?, imagem = ? WHERE id = ?");
            comandoSql.setInt(1, bicicleta.getId());
            comandoSql.setString(2, bicicleta.getNome());
            comandoSql.setString(3, bicicleta.getNumeroSerie());
            comandoSql.setString(4, bicicleta.getAcessorios());
            comandoSql.setString(5, bicicleta.getModelo());
            comandoSql.setString(6, bicicleta.getMarca());
            comandoSql.setString(7, bicicleta.getImagem());
            comandoSql.setInt(8, bicicleta.getId());
            comandoSql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharRecursos(comandoSql);
        }
    }

    // Buscar bicicleta por ID
    public Bicicleta buscarBicicleta(int id) {
        Bicicleta bicicleta = new Bicicleta();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("SELECT * FROM bicicleta WHERE id = ?");
            comandoSql.setInt(1, id);
            ResultSet resultado = comandoSql.executeQuery();
            if (resultado.next()) {
                bicicleta.setId(resultado.getInt("id"));
                bicicleta.setNome(resultado.getString("nome"));
                bicicleta.setNumeroSerie(resultado.getString("numeroSerie"));
                bicicleta.setAcessorios(resultado.getString("acessorios"));
                bicicleta.setModelo(resultado.getString("modelo"));
                bicicleta.setMarca(resultado.getString("marca"));
                bicicleta.setImagem(resultado.getString("imagem"));
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharRecursos(comandoSql);
        }
        return bicicleta;
    }

    // Excluir bicicleta por ID
    public void excluirBicicleta(int id) {
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("DELETE FROM bicicleta WHERE id = ?");
            comandoSql.setInt(1, id);
            comandoSql.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharRecursos(comandoSql);
        }
    }

    // Listar todas as bicicletas
    public List<Bicicleta> listarBicicletas() {
        List<Bicicleta> bicicletas = new ArrayList<>();
        PreparedStatement comandoSql = null;
        try {
            comandoSql = conexao.prepareStatement("SELECT * FROM bicicleta");
            ResultSet resultado = comandoSql.executeQuery();
            while (resultado.next()) {
                Bicicleta bicicleta = new Bicicleta();
                bicicleta.setId(resultado.getInt("id"));
                bicicleta.setNome(resultado.getString("nome"));
                bicicleta.setNumeroSerie(resultado.getString("numeroSerie"));
                bicicleta.setAcessorios(resultado.getString("acessorios"));
                bicicleta.setModelo(resultado.getString("modelo"));
                bicicleta.setMarca(resultado.getString("marca"));
                bicicleta.setImagem(resultado.getString("imagem"));
                bicicletas.add(bicicleta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharRecursos(comandoSql);
        }
        return bicicletas;
    }

    // Método para fechar recursos (PreparedStatement)
    private void fecharRecursos(PreparedStatement comandoSql) {
        if (comandoSql != null) {
            try {
                comandoSql.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
