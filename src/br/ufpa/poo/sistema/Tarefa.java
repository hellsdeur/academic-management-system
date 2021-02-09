package br.ufpa.poo.sistema;

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
	
	public void registrarTarefa (Aluno aluno) {
		this.listagem.put(aluno, true);
	}
	
	public Map<Aluno, Boolean> getListagem () {
		return this.listagem;
	}
	
	public String getDesc () {
		return descricao;
	}

}
