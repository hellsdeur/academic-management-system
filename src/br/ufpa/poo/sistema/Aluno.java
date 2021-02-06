package br.ufpa.poo.sistema;

import java.util.List;
import java.util.ArrayList;

public class Aluno extends Usuario {

	private Historico historico;
	
	public Aluno (String nome, int id) {
		super (nome, id);
		this.historico = new Historico();
	}
	
	public void matricular (Turma turma) {
		if (this.historico.getDisciplinas().contains(turma.getDisciplina())) {
			throw new IllegalArgumentException("Disciplina já consta no histórico.");
		}
		else {
			historico.registrar(turma.getDisciplina());
			turma.matricular(this);
		}
	}
	
	public void submeterTarefa (Turma turma, Tarefa tarefa) {
		turma.registrarTarefa(this, tarefa);
	}
	
	public void consolidarNota (Disciplina disciplina, Character conceito) {
		this.historico.consolidar(disciplina, conceito);
	}
	
	public Historico getHistorico () {
		return this.historico;
	}
}
