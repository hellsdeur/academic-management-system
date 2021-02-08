package br.ufpa.poo.sistema;

import java.util.List;

import br.ufpa.poo.exceptions.ListAlreadyContainsElementException;
import br.ufpa.poo.exceptions.StringTooShortException;

// generic abstract user

public abstract class Usuario {
	protected String nome;
	private String usuario;
	private String senha;
	protected int id;
	
	public Usuario (String nome, int id, String usuario, String senha, List<Usuario> usuarios)
			throws StringTooShortException, ListAlreadyContainsElementException {
		if (usuario.length() < 6) {
			throw new StringTooShortException("Nome de usuário deve ter pelo menos 6 caracteres.");
		}
		if (usuario.length() < 6) {
			throw new StringTooShortException("Senha deve ter pelo menos 6 caracteres.");
		}
		for (Usuario u: usuarios) {
			if (u.getUsuario() == usuario) {
				throw new ListAlreadyContainsElementException("Nome de usuário já existe.");
			}
		}
		this.nome = nome;
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
	}
	
	protected String getUsuario () {
		return this.usuario;
	}
	
	protected String getSenha () {
		return this.senha;
	}

}
