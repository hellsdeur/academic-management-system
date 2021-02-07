package br.ufpa.poo.sistema;

// generic abstract user

public abstract class Usuario {
	protected String nome;
	//private String username;
	//private String password;
	protected int id;
	
	public Usuario (String nome, /*String username, String password,*/ int id) {
		this.nome = nome;
//		this.username = username;
//		this.password = password;
		this.id = id;
	}
	
//    public int getId() {
//		return this.id;
//	}
//    
//    public String getNome() {
//    	return this.nome;
//    }

}
