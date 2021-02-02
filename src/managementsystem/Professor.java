package managementsystem;

import java.util.ArrayList;
import java.util.List;

// a professor can create a class, evaluate a task, grade students, ...

public class Professor extends User {

	private List<Class> classes = new ArrayList<>();
	
	public Professor (String nome, int id) {
		super (nome, id);
	}
	
	public void teach (Class c) {
	if (classes.contains(c) == false) {
	classes.add(c);
		}
	}
	
	
	public Task createTask (Class c, int index, String desc) {
		Task t = new Task(index, desc);
		if (c.getTasks().contains(t) == false) {
			c.getTasks().add(t);		
		}
		return t;		
	}
	
	public void grade (Student aluno, Class c, int index, float nota) {
		classes.get(classes.indexOf(c)).getBoletim().get(aluno.getId()).setNotas(index, nota);
	}
	

	
}
