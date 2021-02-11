package br.ufpa.poo.sistema;

import java.util.Scanner;

import br.ufpa.poo.exceptions.ElementCanNotAcessObjectException;
import br.ufpa.poo.exceptions.ElementDoesNotBelongToListException;
import br.ufpa.poo.exceptions.ListAlreadyContainsElementException;

public class Main {

	public static void main(String[] args) throws ListAlreadyContainsElementException, ElementCanNotAcessObjectException, ElementDoesNotBelongToListException {
		Scanner in = new Scanner (System.in);
		int option, login, profcount, readint1, readint2, readprof, readaluno, readturma, check;
		option = 1; profcount = 0; readprof = 0; readaluno = 0; readturma = 0; check = 0;
		String read1, read2, read3;
		double readdouble = 0;

		System.out.println ("Bem vindo, iniciando o sistema...");
		Sistema sis = new Sistema();
		while (option != 6) {
			System.out.println ("[1]- Novo professor");
			System.out.println ("[2]- Novo Aluno");
			System.out.println ("[3]- Abrir Turma");		
			System.out.println ("[4]- Logar");
			System.out.println ("[5]- Sair");
			System.out.println ("-----------------------------------------------------");
			System.out.format("Escolha uma op��o: ");
			option = in.nextInt(); in.nextLine();

			switch (option) {
			case 1:
				System.out.format ("Criando um novo professor, digite o nome: ");
				read1 = in.nextLine();
				System.out.format ("Digite o nome de usu�rio (M�nimo 6 caracteres): ");
				read2 = in.nextLine();
				System.out.format ("Digite uma senha (M�nimo 6 caracteres): ");
				read3 = in.nextLine();
				if (sis.novoProfessor(read1, read2, read3) != null) {
					System.out.println ("Usu�rio criado com sucesso! \n");
				}
				profcount++;
				break;


			case 2:
				System.out.format ("Criando um novo aluno, digite o nome: ");		
				read1 = in.nextLine();		
				System.out.format ("Digite o nome de usu�rio (M�nimo 6 caracteres): ");		
				read2 = in.nextLine();		
				System.out.format ("Digite uma senha (M�nimo 6 caracteres): ");
				read3 = in.nextLine();
				if (sis.novoAluno(read1, read2, read3) != null) {
					System.out.println ("Usu�rio criado com sucesso! \n");
				}
				break;


			case 3:
				if (sis.getProfessores().size() != 0) {
					System.out.format ("Criando uma nova turma, digite o nome da disciplina: ");
					read1 = in.nextLine();				
					for (int i = 0; i < profcount; i++) 
						System.out.println ("["+i+"]- "+sis.getProfessores().get(i).getNome());
					System.out.format ("Escolha um professor para ministrar: ");						
					readint1 = in.nextInt();		
					System.out.format ("Quantas avalia��es ter�o na turma? ");
					readint2 = in.nextInt();
					sis.novaTurma(sis.novaDisciplina(read1), sis.getProfessores().get(readint1), readint2);
					System.out.println ("Turma criada! \n");
				}
				else  
					System.out.println ("N�o h� professores no sistema para registrar a turma \n");
				break;

			case 4:
				login = 0;
				System.out.format("Usu�rio: ");
				read1 = in.nextLine();
				System.out.format("Senha: ");
				read2 = in.nextLine();
				if (sis.autenticar(read1, read2)) {
					System.out.println ("Login autorizado \n");
					for (int i = 0; i < sis.getProfessores().size(); i++) {
						if (sis.getProfessores().get(i).getUsuario().equals(read1)) {
							login = 1;
							readprof = i;
						}
					}
					if (login != 1) {
						for (int i = 0; i < sis.getAlunos().size(); i++) {
							if (sis.getAlunos().get(i).getUsuario().equals(read1)) {
								login = 2;
								readaluno = i;
							}
						}
					}
				}					

				switch (login) {
				case 1: //Login Professor
					if (sis.getProfessores().get(readprof).getTurma().size() != 0) {	
						System.out.println ("Bem vindo ao menu do Professor, selecione a turma desejada: ");
						for (int i = 0; i < sis.getProfessores().get(readprof).getTurma().size(); i++) 
							System.out.println ("["+i+"]-"+sis.getProfessores().get(readprof).getTurma().get(i).getDisciplina().getNome());
						readturma = in.nextInt();						
						while (option != 3) {							
							System.out.println ("[1]- Criar tarefa");
							System.out.println ("[2]- Avaliar aluno");
							System.out.println ("[3]- Consolidar notas");
							System.out.println ("[4]- Logoff");
							System.out.format ("Escolha uma op��o: ");
							option = in.nextInt();
							in.nextLine();
							switch (option) {	
							case 1: //Criar Tarefa
								System.out.format ("Digite a descri��o da tarefa: ");
								read1 = in.nextLine();
								sis.getProfessores().get(readprof).criarTarefa(sis.getProfessores().get(readprof).getTurma().get(readturma), read1);
								System.out.println ("Tarefa criada com sucesso \n");
								break;

							case 2: //Avaliar Aluno
								if (sis.getTurmas().get(readturma).getAlunos().size()!=0) {
									for (int i = 0; i < sis.getTurmas().get(readturma).getAlunos().size(); i++)
									System.out.println ("["+i+"]-"+sis.getTurmas().get(readturma).getAlunos().get(i).getNome());
									System.out.format ("Escolha um aluno, digite outro numero para cancelar: ");
									readaluno = in.nextInt();
									if (readaluno >=0 || readaluno < sis.getTurmas().get(readturma).getAlunos().size()) {
									System.out.println ("Qual avalia��o deseja selecionar?");
									for (int i = 0; i < sis.getTurmas().get(readturma).getAval(); i++) 
										System.out.println ("Avalia��o -"+(i+1));
									readint1 = in.nextInt();
									System.out.format ("Digite a nota: ");
									readdouble = in.nextDouble();
									System.out.println ("Confirma a a��o? [1]-Sim / [0]-N�o");
									check = in.nextInt();
									if (check == 1) {
										sis.getProfessores().get(readprof).getTurma().get(readturma).avaliar(sis.getTurmas().get(readturma).getAlunos().get(readaluno), readdouble, readint1);
										System.out.println ("Nota registrada! \n");
									}
									else
										System.out.println ("Opera��o cancelada \n");																				
									}
									else
										System.out.println ("Opera��o cancelada \n");
								}
								else
									System.out.println ("N�o h� alunos matriculados \n");
								break;
							case 3:
								if (sis.getTurmas().get(readturma).getAlunos().size()!=0) {
								System.out.println ("Deseja consolidar notas da turma "+sis.getTurmas().get(readturma).getDisciplina().getNome()+"? [1]-Sim / [0]-N�o");
								check = in.nextInt();
								if (check == 1) {
									sis.getProfessores().get(readprof).getTurma().get(readturma).consolidar();
									System.out.println ("Notas consolidadas com sucesso! \n");}
								else
									System.out.println ("Opera��o cancelada!\n");
								}
								else
									System.out.println ("N�o h� alunos na turma! \n");
								break;

							default://Logoff
								option = 3;
								System.out.println ("Saindo... \n");
								break;
							}
						}
					}
					else 
						System.out.println ("Professor n�o possui nenhuma turma registrada!");
					break;


				case 2://Login Aluno
					System.out.println ("Bem vindo ao menu do Aluno, o que deseja? ");
					while (option != 5) {
						System.out.println ("[1]- Matricular em uma turma");
						System.out.println ("[2]- Acessar uma turma matriculada");
						System.out.println ("[3]- Ver Historico");
						System.out.println ("[4]- Logoff");
						option = in.nextInt();
						switch (option) {
						case 1: //Matricular
							if (sis.getTurmas().size()!=0) {
							System.out.println ("Escolha uma turma: ");
							for (int i=0; i < sis.getTurmas().size(); i++) {
								System.out.println ("["+i+"]- "+sis.getTurmas().get(i).getDisciplina().getNome());
							}
							readturma = in.nextInt();
							if (readturma >=0 || readturma < sis.getTurmas().size()) {
							sis.getAlunos().get(readaluno).matricular(sis.getTurmas().get(readturma));
							System.out.println ("Matricula conclu�da!");
								}
							}
							else
								System.out.println ("N�o h� turmas");						
							break;
						case 2: //Acessar
							if (sis.getAlunos().get(readaluno).getHistorico().getDisciplinas().size() > 0) {
								System.out.println ("Escolha uma turma: ");
								while (check != 1) {
									Historico historico = sis.getAlunos().get(readaluno).getHistorico();
									int i = 0;
									for (Disciplina disc: historico.getDisciplinas()) {
										System.out.println ("["+i+"]-" + disc.getNome());
										i++;
									}
									readturma = in.nextInt();
									if ((readturma >= 0)&&(readturma <= sis.getAlunos().get(readaluno).getHistorico().getDisciplinas().size()))
										check = 1;
								}
								System.out.println ("Escolha uma opcao: ");
								while (option != 3) {
									System.out.println ("[1]- Tarefas");						
									System.out.println ("[2]- Voltar");
									option = in.nextInt();
									switch (option) {
									case 1://Tarefas
										System.out.println ("Tarefas: ");
										for (int i = 0; i < sis.getTurmas().get(readturma).getTarefas().size(); i++) {
											System.out.println ("["+i+"]-"+sis.getTurmas().get(readturma).getTarefas().get(i).getDesc());
										}
										System.out.println ("Escolha uma tarefa para enviar, digite outro numero para cancelar:");
										readint1 = in.nextInt();
										if ((readint1>-1)&&(readint1<sis.getTurmas().get(readturma).getTarefas().size())) {
											sis.getAlunos().get(readaluno).submeterTarefa(sis.getTurmas().get(readturma), sis.getTurmas().get(readturma).getTarefas().get(readint1));
											System.out.println ("Tarefa enviada! \n");
											}
										break;								
									default://Voltar
										option = 3;
										break;
									}
								}
							}
							else
								System.out.println ("Nao h� turmas matriculadas");
							break;

						case 3://Historico
							System.out.println("Hist�rico: ");
							Historico historico = sis.getAlunos().get(readaluno).getHistorico();
							for (Disciplina disc: historico.getDisciplinas()) {
								System.out.println(disc.getNome() + ": " + historico.getConceito(disc));
							}
							System.out.println ("\n");
							break;

						default://Deslogar							
							System.out.println ("Saindo...\n");
							option = 5;
							break;
						}
					}
					break;

				default://Login falhou
					System.out.println ("Saindo...\n");
					option = 5;
					break;
				}
				break;


			default: //Sair do sistema
				option = 6;	
				break;	
			}
		}
		in.close();
		System.out.println ("Fechando Sistema...");
	}
}
