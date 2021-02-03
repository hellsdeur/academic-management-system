package sistema;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Tarefa {
	private int index;
	private String descricao;
	private Map<Aluno, Boolean> listagem;
	
	public Tarefa (int index, String descricao, List<Aluno> alunos) {	
		this.index = index;
		this.descricao = descricao;
		this.listagem = new HashMap<>();
		for (Aluno aluno: alunos) {
			this.listagem.put(aluno, false);
		}
	}
	
	public int getIndex() {
		return this.index;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public void registrar (Aluno aluno) {
		if (this.listagem.keySet().contains(aluno)) {
			this.listagem.put(aluno, true);
		}
		else {
			throw new IllegalArgumentException(aluno.getNome() + " não tem acesso a essa disciplina");
		}
	}

}
