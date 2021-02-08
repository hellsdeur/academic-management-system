package br.ufpa.poo.sistema;

import java.util.List;

import br.ufpa.poo.exceptions.InvalidPasswordException;
import br.ufpa.poo.exceptions.InvalidUsernameException;

public class Autenticacao {
	private String usuario;
	private String senha;
	
	public Autenticacao (String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}
	
	public Boolean login (List<Usuario> usuarios) throws InvalidPasswordException, InvalidUsernameException {
		for (Usuario u: usuarios) {
			if (u.getUsuario() == this.usuario) {
				if (u.getSenha() == this.senha) {
					return true;
				}
				else {
					throw new InvalidPasswordException("Senha Inválida");
				}
			}
		}
		throw new InvalidUsernameException("Usuário inexistente");
	}
	
}
