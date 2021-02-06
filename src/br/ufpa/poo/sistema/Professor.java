package br.ufpa.poo.sistema;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Usuario {

	private List<Turma> turmas;
	
	public Professor (String nome, int id) {
		super (nome, id);
		this.turmas = new ArrayList<>();
	}
	
	public void registrar (Turma turma) {
		this.turmas.add(turma);
	}
	
	public Tarefa criarTarefa (Turma turma, String descricao) {
		Tarefa tarefa;
		if (this.turmas.contains(turma)) {
			tarefa = turma.novaTarefa(descricao);
		}
		else {
			throw new IllegalArgumentException(this.getNome() + " não tem acesso a disciplina " + turma.getDisciplina().getNome());
		}
		return tarefa;
	}
	
	public void avaliarAluno (Aluno aluno, Turma turma, double nota, int avaliacao) {
		try {
			turma.avaliar(aluno, nota, avaliacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
