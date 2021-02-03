package sistema;

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
	private Map<Aluno, List<Float>> notas;
	

	public Turma (Disciplina disciplina, Professor professor, int avaliacoes) {
		this.disciplina = disciplina;
		this.professor = professor;
		this.avaliacoes = avaliacoes;
		this.alunos = new ArrayList<>();
		this.tarefas = new ArrayList<>();
		this.notas = new HashMap<>();
	}
	
	public void matricular (Aluno aluno) {
		if (this.alunos.contains(aluno)) {
			throw new IllegalArgumentException("Aluno já matriculado.");
		} else {
			this.alunos.add(aluno);
			List<Float> notasAluno = new ArrayList<>();
			for (int i = 0; i < this.avaliacoes; ++i) {
				notasAluno.add((float) 0);
			}
			notas.put(aluno, notasAluno);
		}
	}
	
	protected void novaTarefa (String descricao) {
		this.tarefas.add(new Tarefa(this.tarefas.size()+1, descricao, this.alunos));
	}
	
	public void registrarTarefa (Aluno aluno, Tarefa tarefa) {
		if (this.tarefas.contains(tarefa)) {
			this.tarefas.get(tarefa.getIndex()).registrar(aluno);
		}
		else {
			throw new IllegalArgumentException("Tarefa inexistente");
		}
	}
	
	public void avaliar (Aluno aluno, Float nota, int avaliacao) {
		if (this.avaliacoes < avaliacoes || avaliacoes < 0) {
			throw new ArrayIndexOutOfBoundsException("Index da avaliação fora dos limites");
		}
		else if (nota < 0 || nota > 10) {
			throw new IllegalArgumentException("Nota deve ser um valor entre 0 e 10.");
		}
		else if (this.alunos.contains(aluno) == false) {
			throw new IllegalArgumentException("Aluno não pertence a essa turma.");
		}
		else {
			List<Float> notasAtualizadas = this.notas.get(aluno);
			notasAtualizadas.set(avaliacao-1, nota);
			this.notas.put(aluno, notasAtualizadas);
		}
	}
	
	public Disciplina getDisciplina () {
		return this.disciplina;
	}

}