  
package br.ufpa.poo.sistema;

import java.util.List;

import br.ufpa.poo.exceptions.ListAlreadyContainsElementException;
import br.ufpa.poo.exceptions.StringTooShortException;

import java.util.ArrayList;

public class Aluno extends Usuario {

	private Historico historico;
	
	public Aluno (String nome, int id, String usuario, String senha, List<Aluno> alunos)
			throws StringTooShortException, ListAlreadyContainsElementException {
		super (nome, id, usuario, senha, alunos);
		this.historico = new Historico();
	}
	
	public void matricular (Turma turma) {
		try {
			historico.registrar(turma.getDisciplina());
			turma.matricular(this);
		}
		catch (ListAlreadyContainsElementException e) {
			e.getMessage();
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