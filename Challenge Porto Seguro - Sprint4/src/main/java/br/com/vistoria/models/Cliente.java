package br.com.vistoria.models;

public class Cliente {

    private int cd_cliente;
    private String nm_completo;
    private String cpf;
    private String endereco;
    private String telefone;
    private String email;
    private String imagemPerfil; // Alterado para String
    private String dataNascimento;
    private String genero;

    public int getCd_cliente() {
        return cd_cliente;
    }

    public void setCd_cliente(int cd_cliente) {
        this.cd_cliente = cd_cliente;
    }

    public String getNm_completo() {
        return nm_completo;
    }

    public void setNm_completo(String nm_completo) {
        this.nm_completo = nm_completo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagemPerfil() {
        return imagemPerfil;
    }

    public void setImagemPerfil(String imagemPerfil) {
        this.imagemPerfil = imagemPerfil;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
