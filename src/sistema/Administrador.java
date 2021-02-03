package sistema;

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
	}
	
	public void novoProfessor (String nome) {
		this.professores.add(new Professor(nome, this.professores.size()+1));
	}
	
	public void novoAluno (String nome) {
		this.alunos.add(new Aluno(nome, this.alunos.size()+1));
	}
	
	public void novaDisciplina (String nome) {
		this.disciplinas.add(new Disciplina(nome));
	}
	
	public void novaTurma (Disciplina disciplina, Professor professor, int avaliacoes) {
		this.turmas.add(new Turma(disciplina, professor, avaliacoes));
	}

}
