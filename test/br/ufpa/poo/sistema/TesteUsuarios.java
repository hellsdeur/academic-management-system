package br.ufpa.poo.sistema;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.ufpa.poo.exceptions.ListAlreadyContainsElementException;

public class TesteUsuarios {
	
	@Test
	void criarSistema () {
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
	void criarAlunoNomeUsuarioCurto ()
			throws ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Aluno aluno1 = sistema.novoAluno("Donald Knuth", "don", "235711");
		
		assertEquals(0, sistema.getAlunos().size());
	}
	
	@Test
	void criarAlunoSenhaCurta () throws ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Aluno aluno1 = sistema.novoAluno("Donald Knuth", "donknuth", "123");
		
		assertEquals(0, sistema.getAlunos().size());
	}
	
	@Test
	void criarAlunoNomeUsuarioExistente () throws ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Aluno aluno1 = sistema.novoAluno("Donald Knuth", "donknuth", "235711");
		Aluno aluno2 = sistema.novoAluno("Linus Torvalds", "donknuth", "853211");
		
		assertEquals(1, sistema.getAlunos().size());
	}
	
	@Test
	void criarProfessorNomeUsuarioCurto ()
			throws ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Professor prof1 = sistema.novoProfessor("Edsger Dijkstra", "ed", "123456");
		
		assertEquals(0, sistema.getProfessores().size());
	}
	
	@Test
	void criarProfessorSenhaCurta () throws ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Professor prof1 = sistema.novoProfessor("Edsger Dijkstra", "eddijk", "123");
		
		assertEquals(0, sistema.getProfessores().size());
	}
	
	@Test
	void criarProfessorNomeUsuarioExistente () throws ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Professor prof1 = sistema.novoProfessor("Edsger Dijkstra", "eddijk", "123456");
		Professor prof2 = sistema.novoProfessor("Gustavo Pinto", "eddijk", "987654");
		
		assertEquals(1, sistema.getProfessores().size());
	}
	
	@Test
	void autenticarAluno ()throws ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Aluno aluno1 = sistema.novoAluno("Donald Knuth", "donknuth", "235711");
		
		assertTrue(sistema.autenticar("donknuth", "235711"));
	}
	
	@Test
	void autenticarAlunoUsuarioErrado () throws ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Aluno aluno1 = sistema.novoAluno("Donald Knuth", "donknuth", "235711");
		
		assertFalse(sistema.autenticar("donknut", "235711"));
	}
	
	@Test
	void autenticarAlunoSenhaErrada () throws ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Aluno aluno1 = sistema.novoAluno("Donald Knuth", "donknuth", "2357111");
		
		assertFalse(sistema.autenticar("donknuth", "235711"));
	}
	
	@Test
	void autenticarProfessor () throws ListAlreadyContainsElementException {
		Sistema sistema = new Sistema();
		Professor prof1 = sistema.novoProfessor("Edsger Dijkstra", "eddijk", "123456");
		
		assertTrue(sistema.autenticar("eddijk", "123456"));
	}
	
}
