package br.ufpa.poo.sistema;

import java.util.List;
import java.util.ArrayList;

import br.ufpa.poo.exceptions.InvalidPasswordException;
import br.ufpa.poo.exceptions.InvalidUsernameException;
import br.ufpa.poo.exceptions.ListAlreadyContainsElementException;
import br.ufpa.poo.exceptions.StringTooShortException;

public class Sistema {
	private List<Professor> professores;
	private List<Aluno> alunos;
	private List<Disciplina> disciplinas;
	private List<Turma> turmas;
	
	public Sistema() {
		professores = new ArrayList<>();
		alunos = new ArrayList<>();
		disciplinas = new ArrayList<>();
		turmas = new ArrayList<>();
	}
	
	public Professor novoProfessor (String nome, String usuario, String senha)
			throws ListAlreadyContainsElementException {
		Professor professor = null;
		try {
			professor = new Professor(nome, usuario, senha, this.professores);
			this.professores.add(professor);
		}
		catch (StringTooShortException e) {
			System.out.println(e.getMessage());
		}
		catch (ListAlreadyContainsElementException e) {
			System.out.println(e.getMessage());
		}
		
		return professor;
	}
	
	public Aluno novoAluno (String nome, String usuario, String senha)
			throws ListAlreadyContainsElementException {
		Aluno aluno = null;
		try {
			aluno = new Aluno(nome, this.alunos.size()+1, usuario, senha, this.alunos);
			this.alunos.add(aluno);
		}
		catch (StringTooShortException e) {
			System.out.println(e.getMessage());
		}
		catch (ListAlreadyContainsElementException e) {
			System.out.println(e.getMessage());
		}
		
		return aluno;
	}
	
	public Disciplina novaDisciplina (String nome) {
		Disciplina disciplina = new Disciplina(nome);
		this.disciplinas.add(disciplina);
		return disciplina;
	}
	
	public Turma novaTurma (Disciplina disciplina, Professor professor, int avaliacoes) {
		Turma turma = new Turma(disciplina, professor, avaliacoes);
		this.turmas.add(turma);
		return turma;
	}
	
	public Boolean autenticar (String usuario, String senha) {
		Boolean login = false;
		Autenticacao aut = new Autenticacao(usuario, senha);
		List<Usuario> todos = new ArrayList<>(this.professores);
		todos.addAll(this.alunos);
		try {
			if (aut.login(todos)) {
				login = true;
			}
		}
		catch (InvalidUsernameException e) {
			System.out.println(e.getMessage());
		}
		catch (InvalidPasswordException e) {
			System.out.println(e.getMessage());
		}
		return login;
	}
	
	public List<Professor> getProfessores () {
		return this.professores;
	}
	
	public List<Aluno> getAlunos () {
		return this.alunos;
	}
	
	public List<Disciplina> getDisciplinas () {
		return this.disciplinas;
	}

	public List<Turma> getTurmas () {
		return this.turmas;
	}
}