package br.ufpa.poo.sistema;

import java.util.List;
import java.util.ArrayList;

public class Administrador extends Usuario {
	private List<Professor> professores;
	private List<Aluno> alunos;
	private List<Disciplina> disciplinas;
	private List<Turma> turmas;
	
	public Administrador(String nome, int id) {
		super(nome, id);
		professores = new ArrayList<>();
		alunos = new ArrayList<>();
		disciplinas = new ArrayList<>();
		turmas = new ArrayList<>();
	}
	
	public Professor novoProfessor (String nome) {
		Professor professor = new Professor(nome, this.professores.size()+1);
		this.professores.add(professor);
		return professor;
	}
	
	public Aluno novoAluno (String nome) {
		Aluno aluno = new Aluno(nome, this.alunos.size()+1);
		this.alunos.add(aluno);
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
	
	public String getNome () {
		return this.nome;
	}
	
	public int getId () {
		return this.getId();
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
