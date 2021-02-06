package br.ufpa.poo.sistema;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class Historico {
	private Map<Disciplina, Character> conceitos;
	
	public Historico () {
		this.conceitos = new HashMap<>();
	}
	
	public void registrar (Disciplina disciplina) {
		conceitos.put(disciplina, '-');
	}
	
	public void consolidar (Disciplina disciplina, Character conceito) {
		conceitos.put(disciplina, conceito);
	}
	
	public Set<Disciplina> getDisciplinas () {
		return conceitos.keySet();
	}
	
	public Character getConceito(Disciplina disciplina) {
		return conceitos.get(disciplina);
	}
}
