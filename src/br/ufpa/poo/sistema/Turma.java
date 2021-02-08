package br.ufpa.poo.sistema;

import br.ufpa.poo.exceptions.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Turma {
	private Disciplina disciplina;
	private Professor professor;
	private int avaliacoes;
	private List<Aluno> alunos;
	private List<Tarefa> tarefas;
	private Map<Aluno, List<Double>> notas;
	

	public Turma (Disciplina disciplina, Professor professor, int avaliacoes) {
		this.disciplina = disciplina;
		this.professor = professor;
		this.professor.registrar(this);
		this.avaliacoes = avaliacoes;
		this.alunos = new ArrayList<>();
		this.tarefas = new ArrayList<>();
		this.notas = new HashMap<>();
	}
	
	protected void matricular (Aluno aluno) throws ListAlreadyContainsElementException {
		if (this.alunos.contains(aluno)) {
			throw new ListAlreadyContainsElementException("Aluno já matriculado.");
		}
		else {
			this.alunos.add(aluno);
			List<Double> notasAluno = new ArrayList<>();
			for (int i = 0; i < this.avaliacoes; ++i) {
				notasAluno.add((double) 0);
			}
			notas.put(aluno, notasAluno);
		}
	}
	
	protected Tarefa novaTarefa (String descricao, Professor professor) throws ElementCanNotAcessObjectException {
		Tarefa tarefa = new Tarefa(this.tarefas.size(), descricao, this.alunos);
		
		if (this.professor.equals(professor)) {
			this.tarefas.add(tarefa);
		}
		else {
			throw new ElementCanNotAcessObjectException(professor + " não tem acesso a disciplina " + this.getDisciplina().getNome());
		}
		
		return tarefa;
	}
	
	public void registrarTarefa (Aluno aluno, Tarefa tarefa) {
		this.tarefas.get(tarefa.getIndex()).registrarTarefa(aluno);
	}
	
	public void avaliar (Aluno aluno, Double nota, int avaliacao) throws ElementDoesNotBelongToListException {
		if (this.avaliacoes < avaliacao || avaliacao-1 < 0) {
			throw new ArrayIndexOutOfBoundsException("Essa disciplina contém " + this.avaliacoes + " avaliações.");
		}
		else if (nota < 0 || nota > 10) {
			throw new IllegalArgumentException("Nota deve ser um valor entre 0 e 10.");
		}
		else if (this.alunos.contains(aluno) == false) {
			throw new ElementDoesNotBelongToListException("Aluno não pertence a essa turma.");
		}
		else {
			List<Double> notasAtualizadas = this.notas.get(aluno);
			notasAtualizadas.set(avaliacao-1, nota);
			this.notas.put(aluno, notasAtualizadas);
		}
	}
	
	public void consolidar () {
		for (Aluno aluno: this.notas.keySet()) {
			char conceito;
			int media = 0;
			for (Double nota: notas.get(aluno)) {
				media += nota;
			}
			media /= this.avaliacoes;
			if (media < 5.0) {
				conceito = 'I';
			}
			else if (media >= 5.0 && media < 7.0) {
				conceito = 'R';
			}
			else if (media >= 7.0 && media < 9.0) {
				conceito = 'B';
			}
			else {
				conceito = 'E';
			}
			aluno.consolidarNota(this.disciplina, conceito);
		}
	}
	
	public Disciplina getDisciplina () {
		return this.disciplina;
	}
	
	public List<Tarefa> getTarefas () {
		return this.tarefas;
	}
	
	public List<Aluno> getAlunos () {
		return this.alunos;
	}

}
