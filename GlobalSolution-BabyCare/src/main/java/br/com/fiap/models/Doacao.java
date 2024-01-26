package br.com.fiap.models;

public class Doacao {
	
	private int id_doacao;
	
	private String nome_completo;
	
	private String email;
	
	private String telefone;
	
	private String forma_pagamento;
	
	private double valor;
	
	
	//Getters & Setters

	public int getId_doacao() {
		return id_doacao;
	}

	public void setId_doacao(int id_doacao) {
		this.id_doacao = id_doacao;
	}

	public String getNome_completo() {
		return nome_completo;
	}

	public void setNome_completo(String nome_completo) {
		this.nome_completo = nome_completo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getForma_pagamento() {
		return forma_pagamento;
	}

	public void setForma_pagamento(String forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
	}
	
}
