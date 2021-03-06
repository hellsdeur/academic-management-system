package br.ufpa.poo.sistema;

import java.util.ArrayList;
import java.util.List;

import br.ufpa.poo.exceptions.ElementCanNotAcessObjectException;
import br.ufpa.poo.exceptions.ElementDoesNotBelongToListException;
import br.ufpa.poo.exceptions.ListAlreadyContainsElementException;
import br.ufpa.poo.exceptions.StringTooShortException;

public class Professor extends Usuario {

	private List<Turma> turmas;
	
	public Professor (String nome, String usuario, String senha, List<Professor> professores)
			throws StringTooShortException, ListAlreadyContainsElementException {
		super (nome, usuario, senha, professores);
		this.turmas = new ArrayList<>();
	}
	
	public void registrar (Turma turma) {
		this.turmas.add(turma);
	}
	
	public Tarefa criarTarefa (Turma turma, String descricao) throws ElementCanNotAcessObjectException {
		Tarefa tarefa = null;
		
		try {
			tarefa = turma.novaTarefa(descricao, this);
		}
		catch (ElementCanNotAcessObjectException e) {
			System.out.println(e.getMessage());
		}
		
		return tarefa;
	}
	
	public void avaliarAluno (Aluno aluno, Turma turma, double nota, int avaliacao) throws ElementDoesNotBelongToListException {
		try {
			turma.avaliar(aluno, nota, avaliacao);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		catch (ElementDoesNotBelongToListException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public List<Turma> getTurma (){
		return this.turmas;
	}
}