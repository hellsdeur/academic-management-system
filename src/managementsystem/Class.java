package managementsystem;

import java.util.ArrayList;
import java.util.List;

// a class created by a professor can get students enrolled into

public class Class {
	private String subject;
	private String professor;
	private int professorId;
	public List<String> alunos = new ArrayList<>();
	public List<InfoStudent> boletim = new ArrayList<>();
	private List<Task> tasks = new ArrayList<>();

	public Class (String sub, Professor prof) {
		this.professor = prof.getNome();
		this.subject = sub;
		this.professorId = prof.getId();
	}
	
	public void Matricular (String aluno, int id) {
		if (alunos.get(id).contains(aluno)) {
			System.out.println("Aluno j� matriculado");
			return;
	}
		else{
		alunos.add(id, aluno);
		boletim.add(id, new InfoStudent(aluno));
		};
	}
	
	public void SubmitTask (Student a, Task t) {		
		if (this.tasks.contains(t)) {
		boletim.get(a.getId()).getTasks().add(t.getIndex(), true);
		}
	}
	
	public String getProfessor() {
		return(professor);
	}
	
	public List<String> getAluno(){
		return (alunos);
	}
	
	public List<InfoStudent> getBoletim(){
		return (boletim);
	}

	public List<Task> getTasks(){
		return (tasks);
	}
	
	public String getSubject() {
		return subject;
	}

	public void seeTask(){
		System.out.println (this.getTasks().toString());		
	}
}
