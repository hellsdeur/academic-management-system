package br.ufpa.poo.sistema;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import br.ufpa.poo.exceptions.ElementCanNotAcessObjectException;
import br.ufpa.poo.exceptions.ElementDoesNotBelongToListException;

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
	void criarProfessor () {
		Sistema sistema = new Sistema();
		Professor prof1 = sistema.novoProfessor("Edsger Dijkstra");
		
		assertEquals(1, sistema.getProfessores().size());
	}
	
	@Test
	void criarAlunos () {
		Sistema sistema = new Sistema();
		Aluno aluno1 = sistema.novoAluno("Donald Knuth");
		Aluno aluno2 = sistema.novoAluno("Linus Torvalds");
		Aluno aluno3 = sistema.novoAluno("Denis Ritchie");
		
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
	void criarConsolidarTurma () throws ElementDoesNotBelongToListException {
		Sistema sistema = new Sistema();
		Professor prof = sistema.novoProfessor("Edsger Dijkstra");
		Disciplina disc = sistema.novaDisciplina("Análise de Algoritmos");
		Turma analiseTarde = sistema.novaTurma(disc, prof, 3);
		Aluno aluno1 = sistema.novoAluno("Donald Knuth");
		
		aluno1.matricular(analiseTarde);
		prof.avaliarAluno(aluno1, analiseTarde, 10, 1);
		prof.avaliarAluno(aluno1, analiseTarde, 9.5, 2);
		prof.avaliarAluno(aluno1, analiseTarde, 7, 3);
		analiseTarde.consolidar();
		
		assertEquals('B', aluno1.getHistorico().getConceito(disc));
	}
	
	@Test
	void matricularAlunoJaMatriculado () {
		Sistema sistema = new Sistema();
		Professor prof = sistema.novoProfessor("Edsger Dijkstra");
		Disciplina disc = sistema.novaDisciplina("Análise de Algoritmos");
		Turma analiseTarde = sistema.novaTurma(disc, prof, 3);
		Aluno aluno1 = sistema.novoAluno("Donald Knuth");
		
		aluno1.matricular(analiseTarde);
		
		aluno1.matricular(analiseTarde);
		assertEquals(1, analiseTarde.getAlunos().size());
	}
	
	@Test
	void lancarNotaEmAvaliacaoForaDosLimites () throws ElementDoesNotBelongToListException {
		Sistema sistema = new Sistema();
		Professor prof = sistema.novoProfessor("Edsger Dijkstra");
		Disciplina disc = sistema.novaDisciplina("Análise de Algoritmos");
		Turma analiseTarde = sistema.novaTurma(disc, prof, 3);
		Aluno aluno1 = sistema.novoAluno("Donald Knuth");
		
		aluno1.matricular(analiseTarde);
		prof.avaliarAluno(aluno1, analiseTarde, 5, 1);
		prof.avaliarAluno(aluno1, analiseTarde, 5, 2);
		prof.avaliarAluno(aluno1, analiseTarde, 5.5, 4);
		analiseTarde.consolidar();
		
		assertEquals('I', aluno1.getHistorico().getConceito(disc));
	}
	
	@Test
	void lancarNotaForaDosLimites () throws ElementDoesNotBelongToListException {
		Sistema sistema = new Sistema();
		Professor prof = sistema.novoProfessor("Edsger Dijkstra");
		Disciplina disc = sistema.novaDisciplina("Análise de Algoritmos");
		Turma analiseTarde = sistema.novaTurma(disc, prof, 3);
		Aluno aluno1 = sistema.novoAluno("Donald Knuth");
		
		aluno1.matricular(analiseTarde);
		prof.avaliarAluno(aluno1, analiseTarde, 10, 1);
		prof.avaliarAluno(aluno1, analiseTarde, 10, 2);
		prof.avaliarAluno(aluno1, analiseTarde, 11, 3);
		analiseTarde.consolidar();
		
		assertEquals('R', aluno1.getHistorico().getConceito(disc));
	}
	
	@Test
	void lancarNotaAlunoForaDaTurma () throws ElementDoesNotBelongToListException {
		Sistema sistema = new Sistema();
		Professor prof = sistema.novoProfessor("Edsger Dijkstra");
		Disciplina disc = sistema.novaDisciplina("Análise de Algoritmos");
		Turma analiseTarde = sistema.novaTurma(disc, prof, 3);
		Aluno aluno1 = sistema.novoAluno("Donald Knuth");
		
		prof.avaliarAluno(aluno1, analiseTarde, 10, 1);
		analiseTarde.consolidar();
		
		assertNull(aluno1.getHistorico().getConceito(disc));
	}
	
	@Test
	void lancarNotaMaxima () throws ElementDoesNotBelongToListException {
		Sistema sistema = new Sistema();
		Professor prof = sistema.novoProfessor("Edsger Dijkstra");
		Disciplina disc = sistema.novaDisciplina("Análise de Algoritmos");
		Turma analiseTarde = sistema.novaTurma(disc, prof, 3);
		Aluno aluno1 = sistema.novoAluno("Donald Knuth");
		
		aluno1.matricular(analiseTarde);
		prof.avaliarAluno(aluno1, analiseTarde, 10, 1);
		prof.avaliarAluno(aluno1, analiseTarde, 10, 2);
		prof.avaliarAluno(aluno1, analiseTarde, 10, 3);
		analiseTarde.consolidar();
		
		assertEquals('E', aluno1.getHistorico().getConceito(disc));
	}
	
	@Test
	void criarTarefa () throws ElementCanNotAcessObjectException {
		Sistema sistema = new Sistema();
		Professor prof = sistema.novoProfessor("Edsger Dijkstra");
		Disciplina disc = sistema.novaDisciplina("Análise de Algoritmos");
		Turma analiseTarde = sistema.novaTurma(disc, prof, 3);
		
		prof.criarTarefa(analiseTarde, "Trabalho de Complexidade de Algoritmos");
		prof.criarTarefa(analiseTarde, "Trabalho de Algoritmos Recursivos");
		prof.criarTarefa(analiseTarde, "Trabalho de Técnicas de Programação");
		
		assertEquals(3, analiseTarde.getTarefas().size());
	}
	
	@Test
	void criarTarefaDisciplinaSemAcesso () throws ElementCanNotAcessObjectException {
		Sistema sistema = new Sistema();
		Professor prof1 = sistema.novoProfessor("Edsger Dijkstra");
		Professor prof2 = sistema.novoProfessor("Gustavo Pinto");
		Disciplina disc = sistema.novaDisciplina("Análise de Algoritmos");
		Turma analiseTarde = sistema.novaTurma(disc, prof1, 3);
		
		prof2.criarTarefa(analiseTarde, "Trabalho de Complexidade de Algoritmos");
		prof2.criarTarefa(analiseTarde, "Trabalho de Algoritmos Recursivos");
		prof2.criarTarefa(analiseTarde, "Trabalho de Técnicas de Programação");
		
		assertEquals(0, analiseTarde.getTarefas().size());
	}
	
	@Test
	void enviarTarefa () throws ElementCanNotAcessObjectException {
		Sistema sistema = new Sistema();
		Professor prof = sistema.novoProfessor("Edsger Dijkstra");
		Aluno aluno1 = sistema.novoAluno("Donald Knuth");
		Disciplina disc = sistema.novaDisciplina("Análise de Algoritmos");
		
		Turma analiseTarde = sistema.novaTurma(disc, prof, 3);
		aluno1.matricular(analiseTarde);
		
		Tarefa tarefa1 = prof.criarTarefa(analiseTarde, "Trabalho de Complexidade de Algoritmos");
		aluno1.submeterTarefa(analiseTarde, tarefa1);
		
		assertEquals(true, tarefa1.getListagem().get(aluno1));
	}
	
	@Test
	void matricularVariasDisciplinas () {
		Sistema sistema = new Sistema();
		Professor prof = sistema.novoProfessor("Edsger Dijkstra");
		Disciplina disc1 = sistema.novaDisciplina("Programação");
		Disciplina disc2 = sistema.novaDisciplina("Teoria da Computação");
		Disciplina disc3 = sistema.novaDisciplina("Análise de Algoritmos");
		Disciplina disc4 = sistema.novaDisciplina("Matemática Concreta");
		
		Turma programacaoTarde = sistema.novaTurma(disc1, prof, 3);
		Turma computacaoTarde = sistema.novaTurma(disc2, prof, 3);
		Turma analiseTarde = sistema.novaTurma(disc3, prof, 3);
		Turma concretaTarde = sistema.novaTurma(disc4, prof, 3);
		
		Aluno aluno1 = sistema.novoAluno("Donald Knuth");
		for (Turma turma: sistema.getTurmas()) {
			aluno1.matricular(turma);
		}
		
		assertEquals(4, aluno1.getHistorico().getDisciplinas().size());
	}

}
