package managementsystem;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class ClassTest {

	@Test
	void criarTarefa() {
		Admin a = new Admin("Nome", 1);
		Professor gustavo = a.newProfessor("Gustavo");
	    Class prog = a.newClass("Programação 2", gustavo);
	    Task t = gustavo.createTask(prog, 1, "Tarefa");
	    Assertions.assertEquals("Tarefa", prog.getTasks().get(0).getDesc());	    
	}

}
