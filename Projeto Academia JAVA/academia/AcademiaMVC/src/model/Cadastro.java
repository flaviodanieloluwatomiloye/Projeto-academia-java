package model;

public class Cadastro {
	
	private String nome, senha;
	
    public Cadastro(String nome, String senha) {
    	this.nome = nome;
    	this.senha = senha;
}
    
	public String getNome() { return nome; }

	public void setNome(String nome) { this.nome = nome; }

	public String getSenha() { return senha; }

	public void setSenha(String senha) { this.senha = senha; }
}
