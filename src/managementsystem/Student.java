package managementsystem;

import java.util.ArrayList;
import java.util.List;

// a student can ask for registration, enroll in a class, submit tasks, ...

public class Student extends User {

	private List<String> classes = new ArrayList<>();
	
	
	public Student (String nome, int id) {
		super (nome, id);
	}
	
	public void enrollClass (Class classe){	
		if (classes.contains(classe.getSubject()) == false){
		classes.add(classe.getSubject());
		classe.Matricular(getNome(), getId());
		}
	}
	
	public void submitTask (Class c, Task t) {
		if (classes.contains(c.getSubject())) {
			c.SubmitTask(this, t);
		}
	}
	
	
}
