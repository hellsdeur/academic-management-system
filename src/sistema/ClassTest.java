package sistema;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class ClassTest {

	@Test
	void criarTarefa() {
		Administrador a = new Administrador("Nome", 1);
		Professor gustavo = a.newProfessor("Gustavo");
	    Turma prog = a.newClass("Programação 2", gustavo);
	    Tarefa t = gustavo.createTask(prog, 1, "Tarefa");
	    Assertions.assertEquals("Tarefa", prog.getTasks().get(0).getDesc());	    
	}

}
