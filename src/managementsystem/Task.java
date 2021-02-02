package managementsystem;

// a task registered by a professor must be submitted by a student, and then get graded

public class Task {
	private int index;
	private String description;
	
	public Task (int index, String tarefa) {	
		this.index = index;
		this.description = tarefa;
	}
	
	public int getIndex() {
		return index;
	}

	public String getDesc() {
		return description;
	}
	

}
