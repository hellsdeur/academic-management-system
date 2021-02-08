package br.ufpa.poo.sistema;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import br.ufpa.poo.exceptions.ElementCanNotAcessObjectException;
import br.ufpa.poo.exceptions.ElementDoesNotBelongToListException;
import br.ufpa.poo.exceptions.ListAlreadyContainsElementException;

class TesteSistema {

	@Test
	void criarAdministrador () {
		Sistema sistema = new Sistema();
		
		assertEquals(0, sistema.getAlunos().size() +
						sistema.getDisciplinas().size() +
						sistema.getProfessores().size() +
						sistema.getDisciplinas().size());
	}
	
	@Test
	void criarProfessor ()
			throws ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Professor prof1 = sistema.novoProfessor("Edsger Dijkstra", "eddijk", "123456");
		
		assertEquals(1, sistema.getProfessores().size());
	}
	
	@Test
	void criarAlunos ()
			throws ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Aluno aluno1 = sistema.novoAluno("Donald Knuth", "donknuth", "235711");
		Aluno aluno2 = sistema.novoAluno("Linus Torvalds", "windowsisbad", "853211");
		Aluno aluno3 = sistema.novoAluno("Denis Ritchie", "memoryleak666", "3115731");
		
		assertEquals(3, sistema.getAlunos().size());
	}
	
	@Test
	void criarDisciplinas () {
		Sistema sistema = new Sistema();
		Disciplina disc1 = sistema.novaDisciplina("Programação");
		Disciplina disc2 = sistema.novaDisciplina("Teoria da Computação");
		Disciplina disc3 = sistema.novaDisciplina("Análise de Algoritmos");
		Disciplina disc4 = sistema.novaDisciplina("Matemática Concreta");
		
		assertEquals(4, sistema.getDisciplinas().size());
	}
	
	@Test
	void criarConsolidarTurma ()
			throws ElementDoesNotBelongToListException, ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Professor prof1 = sistema.novoProfessor("Edsger Dijkstra", "eddijk", "123456");
		Disciplina disc = sistema.novaDisciplina("Análise de Algoritmos");
		Turma analiseTarde = sistema.novaTurma(disc, prof1, 3);
		Aluno aluno1 = sistema.novoAluno("Donald Knuth", "donknuth", "235711");
		
		aluno1.matricular(analiseTarde);
		prof1.avaliarAluno(aluno1, analiseTarde, 10, 1);
		prof1.avaliarAluno(aluno1, analiseTarde, 9.5, 2);
		prof1.avaliarAluno(aluno1, analiseTarde, 7, 3);
		analiseTarde.consolidar();
		
		assertEquals('B', aluno1.getHistorico().getConceito(disc));
	}
	
	@Test
	void matricularAlunoJaMatriculado ()
			throws ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Professor prof1 = sistema.novoProfessor("Edsger Dijkstra", "eddijk", "123456");
		Disciplina disc = sistema.novaDisciplina("Análise de Algoritmos");
		Turma analiseTarde = sistema.novaTurma(disc, prof1, 3);
		Aluno aluno1 = sistema.novoAluno("Donald Knuth", "donknuth", "235711");
		
		aluno1.matricular(analiseTarde);
		
		aluno1.matricular(analiseTarde);
		assertEquals(1, analiseTarde.getAlunos().size());
	}
	
	@Test
	void lancarNotaEmAvaliacaoForaDosLimites ()
			throws ElementDoesNotBelongToListException, ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Professor prof1 = sistema.novoProfessor("Edsger Dijkstra", "eddijk", "123456");
		Disciplina disc = sistema.novaDisciplina("Análise de Algoritmos");
		Turma analiseTarde = sistema.novaTurma(disc, prof1, 3);
		Aluno aluno1 = sistema.novoAluno("Donald Knuth", "donknuth", "235711");
		
		aluno1.matricular(analiseTarde);
		prof1.avaliarAluno(aluno1, analiseTarde, 5, 1);
		prof1.avaliarAluno(aluno1, analiseTarde, 5, 2);
		prof1.avaliarAluno(aluno1, analiseTarde, 5.5, 4);
		analiseTarde.consolidar();
		
		assertEquals('I', aluno1.getHistorico().getConceito(disc));
	}
	
	@Test
	void lancarNotaForaDosLimites ()
			throws ElementDoesNotBelongToListException, ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Professor prof1 = sistema.novoProfessor("Edsger Dijkstra", "eddijk", "123456");
		Disciplina disc = sistema.novaDisciplina("Análise de Algoritmos");
		Turma analiseTarde = sistema.novaTurma(disc, prof1, 3);
		Aluno aluno1 = sistema.novoAluno("Donald Knuth", "donknuth", "235711");
		
		aluno1.matricular(analiseTarde);
		prof1.avaliarAluno(aluno1, analiseTarde, 10, 1);
		prof1.avaliarAluno(aluno1, analiseTarde, 10, 2);
		prof1.avaliarAluno(aluno1, analiseTarde, 11, 3);
		analiseTarde.consolidar();
		
		assertEquals('R', aluno1.getHistorico().getConceito(disc));
	}
	
	@Test
	void lancarNotaAlunoForaDaTurma ()
			throws ElementDoesNotBelongToListException, ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Professor prof1 = sistema.novoProfessor("Edsger Dijkstra", "eddijk", "123456");
		Disciplina disc = sistema.novaDisciplina("Análise de Algoritmos");
		Turma analiseTarde = sistema.novaTurma(disc, prof1, 3);
		Aluno aluno1 = sistema.novoAluno("Donald Knuth", "donknuth", "235711");
		
		prof1.avaliarAluno(aluno1, analiseTarde, 10, 1);
		analiseTarde.consolidar();
		
		assertNull(aluno1.getHistorico().getConceito(disc));
	}
	
	@Test
	void lancarNotaMaxima ()
			throws ElementDoesNotBelongToListException, ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Professor prof1 = sistema.novoProfessor("Edsger Dijkstra", "eddijk", "123456");
		Disciplina disc = sistema.novaDisciplina("Análise de Algoritmos");
		Turma analiseTarde = sistema.novaTurma(disc, prof1, 3);
		Aluno aluno1 = sistema.novoAluno("Donald Knuth", "donknuth", "235711");
		
		aluno1.matricular(analiseTarde);
		prof1.avaliarAluno(aluno1, analiseTarde, 10, 1);
		prof1.avaliarAluno(aluno1, analiseTarde, 10, 2);
		prof1.avaliarAluno(aluno1, analiseTarde, 10, 3);
		analiseTarde.consolidar();
		
		assertEquals('E', aluno1.getHistorico().getConceito(disc));
	}
	
	@Test
	void criarTarefa ()
			throws ElementCanNotAcessObjectException, ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Professor prof1 = sistema.novoProfessor("Edsger Dijkstra", "eddijk", "123456");
		Disciplina disc = sistema.novaDisciplina("Análise de Algoritmos");
		Turma analiseTarde = sistema.novaTurma(disc, prof1, 3);
		
		prof1.criarTarefa(analiseTarde, "Trabalho de Complexidade de Algoritmos");
		prof1.criarTarefa(analiseTarde, "Trabalho de Algoritmos Recursivos");
		prof1.criarTarefa(analiseTarde, "Trabalho de Técnicas de Programação");
		
		assertEquals(3, analiseTarde.getTarefas().size());
	}
	
	@Test
	void criarTarefaDisciplinaSemAcesso ()
			throws ElementCanNotAcessObjectException, ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Professor prof1 = sistema.novoProfessor("Edsger Dijkstra", "eddijk", "123456");
		Professor prof2 = sistema.novoProfessor("Gustavo Pinto", "guspinto", "177013");
		Disciplina disc = sistema.novaDisciplina("Análise de Algoritmos");
		Turma analiseTarde = sistema.novaTurma(disc, prof1, 3);
		
		prof2.criarTarefa(analiseTarde, "Trabalho de Complexidade de Algoritmos");
		prof2.criarTarefa(analiseTarde, "Trabalho de Algoritmos Recursivos");
		prof2.criarTarefa(analiseTarde, "Trabalho de Técnicas de Programação");
		
		assertEquals(0, analiseTarde.getTarefas().size());
	}
	
	@Test
	void enviarTarefa ()
			throws ElementCanNotAcessObjectException, ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Professor prof1 = sistema.novoProfessor("Edsger Dijkstra", "eddijk", "123456");
		Disciplina disc = sistema.novaDisciplina("Análise de Algoritmos");
		Turma analiseTarde = sistema.novaTurma(disc, prof1, 3);
		Aluno aluno1 = sistema.novoAluno("Donald Knuth", "donknuth", "235711");
		
		
		aluno1.matricular(analiseTarde);
		
		Tarefa tarefa1 = prof1.criarTarefa(analiseTarde, "Trabalho de Complexidade de Algoritmos");
		aluno1.submeterTarefa(analiseTarde, tarefa1);
		
		assertEquals(true, tarefa1.getListagem().get(aluno1));
	}
	
	@Test
	void matricularVariasDisciplinas ()
			throws ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Professor prof1 = sistema.novoProfessor("Edsger Dijkstra", "eddijk", "123456");
		Disciplina disc1 = sistema.novaDisciplina("Programação");
		Disciplina disc2 = sistema.novaDisciplina("Teoria da Computação");
		Disciplina disc3 = sistema.novaDisciplina("Análise de Algoritmos");
		Disciplina disc4 = sistema.novaDisciplina("Matemática Concreta");
		
		Turma programacaoTarde = sistema.novaTurma(disc1, prof1, 3);
		Turma computacaoTarde = sistema.novaTurma(disc2, prof1, 3);
		Turma analiseTarde = sistema.novaTurma(disc3, prof1, 3);
		Turma concretaTarde = sistema.novaTurma(disc4, prof1, 3);
		
		Aluno aluno1 = sistema.novoAluno("Donald Knuth", "donknuth", "235711");
		for (Turma turma: sistema.getTurmas()) {
			aluno1.matricular(turma);
		}
		
		assertEquals(4, aluno1.getHistorico().getDisciplinas().size());
	}

}
