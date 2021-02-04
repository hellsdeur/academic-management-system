package testes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import sistema.*;

class TesteSistema {

	@Test
	void criarAdministrador () {
		Administrador admin1 = new Administrador("Alan Turing", 1);
		assertEquals(admin1.getNome(), "Alan Turing");
	}
	
	@Test
	void criarProfessor () {
		Administrador admin1 = new Administrador("Alan Turing", 1);
		Professor prof1 = admin1.novoProfessor("Edsger Dijkstra");
		assertEquals(1, admin1.getProfessores().size());
	}
	
	@Test
	void criarAlunos () {
		Administrador admin1 = new Administrador("Alan Turing", 1);
		Aluno aluno1 = admin1.novoAluno("Donald Knuth");
		Aluno aluno2 = admin1.novoAluno("Linus Torvalds");
		Aluno aluno3 = admin1.novoAluno("Denis Ritchie");
		assertEquals(3, admin1.getAlunos().size());
	}
	
	@Test
	void criarDisciplinas () {
		Administrador admin1 = new Administrador("Alan Turing", 1);
		Disciplina disc1 = admin1.novaDisciplina("Programação");
		Disciplina disc2 = admin1.novaDisciplina("Teoria da Computação");
		Disciplina disc3 = admin1.novaDisciplina("Análise de Algoritmos");
		Disciplina disc4 = admin1.novaDisciplina("Matemática Concreta");
		assertEquals(5, admin1.getDisciplinas().size());
	}
	
	@Test
	void criarConsolidarTurmas () {
		Administrador admin1 = new Administrador("Alan Turing", 1);
		Professor prof = admin1.novoProfessor("Edsger Dijkstra");
		Disciplina disc = admin1.novaDisciplina("Análise de Algoritmos");
		Turma analiseTarde = admin1.novaTurma(disc, prof, 3);
		Aluno aluno1 = admin1.novoAluno("Donald Knuth");
		Aluno aluno2 = admin1.novoAluno("Linus Torvalds");
		Aluno aluno3 = admin1.novoAluno("Denis Ritchie");
		for (Aluno aluno: admin1.getAlunos()) {
			analiseTarde.matricular(aluno);
		}
		analiseTarde.consolidar();
		
		assertEquals('I', aluno1.getHistorico().getConceito(analiseTarde.getDisciplina()));
	}

}
