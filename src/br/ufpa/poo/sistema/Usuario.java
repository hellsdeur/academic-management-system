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
	
	public Usuario (String nomeAluno, int idAluno, String usuarioAluno, String senhaAluno, List<Aluno> alunos)
			throws StringTooShortException, ListAlreadyContainsElementException {
		if (usuarioAluno.length() < 6) {
			throw new StringTooShortException("Nome de usu�rio deve ter pelo menos 6 caracteres.");
		}
		if (senhaAluno.length() < 6) {
			throw new StringTooShortException("Senha deve ter pelo menos 6 caracteres.");
		}
		for (Aluno a: alunos) {
			if (a.getUsuario() == usuarioAluno) {
				throw new ListAlreadyContainsElementException("Nome de usu�rio j� existe.");
			}
		}
		this.nome = nomeAluno;
		this.id = idAluno;
		this.usuario = usuarioAluno;
		this.senha = senhaAluno;
	}
	
	public Usuario (String nomeProfessor, String usuarioProfessor, String senhaProfessor, List<Professor> professores)
			throws StringTooShortException, ListAlreadyContainsElementException {
		if (usuarioProfessor.length() < 6) {
			throw new StringTooShortException("Nome de usu�rio deve ter pelo menos 6 caracteres.");
		}
		if (senhaProfessor.length() < 6) {
			throw new StringTooShortException("Senha deve ter pelo menos 6 caracteres.");
		}
		for (Usuario u: professores) {
			if (u.getUsuario() == usuarioProfessor) {
				throw new ListAlreadyContainsElementException("Nome de usu�rio j� existe.");
			}
		}
		this.nome = nomeProfessor;
		this.id = professores.size();
		this.usuario = usuarioProfessor;
		this.senha = senhaProfessor;
	}
	
	protected String getUsuario () {
		return this.usuario;
	}
	
	protected String getSenha () {
		return this.senha;
	}
	
	protected String getNome () {
		return this.nome;
	}

}


