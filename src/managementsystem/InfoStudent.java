package managementsystem;

import java.util.ArrayList;
import java.util.List;

public class InfoStudent {
	private String aluno;
	private List<Boolean> taskcheck = new ArrayList<>();
	private float[] notas;
	
	public InfoStudent (String aluno) {
		this.aluno = aluno;		
	}
	
	public List<Boolean> getTasks() {
		return taskcheck;
	}
	
	public float[] getNotas() {
		return notas;
	}
	
	public void setNotas (int index, float nota) {
		this.notas[index] = nota;
	}
	

}
