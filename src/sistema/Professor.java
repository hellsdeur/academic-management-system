package sistema;

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
	
	public void criarTarefa (Turma turma, String descricao) {
		if (this.turmas.contains(turma)) {
			turma.novaTarefa(descricao);
		}
		else {
			throw new IllegalArgumentException(this.getNome() + "não tem acesso a disciplina " + turma.getDisciplina().getNome());
		}
	}
	
	public void avaliarAluno (Aluno aluno, Turma turma, float nota, int avaliacao) {
		try {
			turma.avaliar(aluno, nota, avaliacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
}
