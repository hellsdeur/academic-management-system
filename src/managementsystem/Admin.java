package managementsystem;

public class Admin extends User {
	private int studentId;
	private int professorId;
	
	public Admin(String nome, int id) {
		super(nome, id);
		this.studentId = -1;
		this.professorId = -1;		
	}
	
	public Professor newProfessor (String nome) {
		this.professorId++;
		Professor p = new Professor (nome, this.professorId);
		return p;
	}
	
	public Student newStudent (String nome) {
		this.studentId++;
		Student s = new Student (nome, this.studentId);
		return s;
	}
	
	public Class newClass (String nome, Professor p) {
		Class c = new Class(nome, p);
		p.teach(c);
		return c;
	}
	
	

}
