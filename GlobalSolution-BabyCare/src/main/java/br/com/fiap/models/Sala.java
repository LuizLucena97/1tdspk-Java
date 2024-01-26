package br.com.fiap.models;

public class Sala {

	private String nome_profissional;
	
	private String rm_profissional;
	
	private String senha;
	
	private String foto; 
	
	private int id;
	
	private String mensagens;
	
	//Getters & Setters

	public String getNome_profissional() {
		return nome_profissional;
	}

	public void setNome_profissional(String nome_profissional) {
		this.nome_profissional = nome_profissional;
	}

	public String getRm_profissional() {
		return rm_profissional;
	}

	public void setRm_profissional(String rm_profissional) {
		this.rm_profissional = rm_profissional;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMensagens() {
		return mensagens;
	}

	public void setMensagens(String mensagens) {
		this.mensagens = mensagens;
	}

}
