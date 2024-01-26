package br.com.fiap.entity;

/*CREATE TABLE Cliente(
  id_cliente int primary key,
  nome_cliente varchar2(60) not null,
  cpf_cliente varchar2(11) not null,
  email_cliente varchar2(60),
  senha varchar2(15) not null
  );
 * */
public class Cliente {

	private int id;
	private String nome;
	private String cpf; //VALIDAÇÃO DE CPF
	private String email;
	private String senha; //CRIPTOGRAFIA
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
