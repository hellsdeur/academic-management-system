package br.ufpa.poo.sistema;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class Historico {
	private Map<Disciplina, Character> conceitos;
	private int CountTurmas;
	
	public Historico () {
		this.conceitos = new HashMap<>();
		this.CountTurmas = 0;
	}
	
	public void registrar (Disciplina disciplina) {
		conceitos.put(disciplina, '-');
		CountTurmas++;
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
	
	public int getCount () {
		return CountTurmas;
	}
}
