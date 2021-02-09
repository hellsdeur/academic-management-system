package br.ufpa.poo.sistema;

import java.util.Scanner;

import br.ufpa.poo.exceptions.ElementCanNotAcessObjectException;
import br.ufpa.poo.exceptions.ListAlreadyContainsElementException;

public class Main {

	public static void main(String[] args) throws ListAlreadyContainsElementException, ElementCanNotAcessObjectException {
		Scanner in = new Scanner (System.in);
		int option, login, profcount, alunocount, readint1, readint2, readprof, readaluno, readturma, check;
		option = 1; login = 0; profcount = 0; alunocount = 0; readprof = 0; readaluno = 0; readturma = 0; check = 0;
		String read1, read2, read3;

		System.out.println ("Bem vindo, iniciando o sistema...");
		Sistema sis = new Sistema();
		while (option != 0) {
			System.out.println ("[1]- Novo professor");
			System.out.println ("[2]- Novo Aluno");
			System.out.println ("[3]- Abrir Turma");
			System.out.println ("[4]- Consolidar Turma");
			System.out.println ("[5]- Logar");
			System.out.println ("[0]- Sair");
			System.out.println ("-----------------------------------------------------");
			System.out.format("Escolha uma opção: ");
			option = in.nextInt(); in.nextLine();

			switch (option) {
			case 1:
				System.out.format ("Criando um novo professor, digite o nome: ");
				read1 = in.nextLine();
				System.out.format ("Digite o nome de usuário: ");
				read2 = in.nextLine();
				System.out.format ("Digite uma senha: ");
				read3 = in.nextLine();
				sis.novoProfessor(read1, read2, read3);
				System.out.println (sis.getProfessores().get(profcount).getUsuario());
				System.out.println (sis.getProfessores().get(profcount).getSenha());
				profcount++;
				break;


			case 2:
				System.out.format ("Criando um novo aluno, digite o nome: ");		
				read1 = in.nextLine();		
				System.out.format ("Digite o nome de usuário: ");		
				read2 = in.nextLine();		
				System.out.format ("Digite uma senha: ");
				read3 = in.nextLine();
				sis.novoAluno(read1, read2, read3);
				System.out.println (sis.getAlunos().get(alunocount).getUsuario());
				System.out.println (sis.getAlunos().get(alunocount).getSenha());
				alunocount++;
				break;


			case 3:
				if (sis.getProfessores().size() != 0) {
					System.out.format ("Criando uma nova turma, digite o nome da disciplina: ");
					read1 = in.nextLine();
					System.out.println ("Escolha um professor para ministrar: ");
					for (int i = 0; i < profcount; i++) {
						System.out.println ("["+i+"]- "+sis.getProfessores().get(i).getNome());
					}
					readint1 = in.nextInt();		
					System.out.println ("Quantas avaliacoes terao a turma?");
					readint2 = in.nextInt();
					sis.novaTurma(sis.novaDisciplina(read1), sis.getProfessores().get(readint1), readint2);
				}
				else  
					System.out.println ("Nao ha professores no sistema para registrar a turma");
				break;


			case 4:
				if (sis.getTurmas().size() != 0) {
					for (int i = 0; i < sis.getTurmas().size(); i++) 
						System.out.println ("["+i+"]- "+sis.getTurmas().get(i).getDisciplina().getNome());		
					System.out.format ("Escolha uma turma para consolidar: ");
					readturma = in.nextInt();
					if (readturma>=0 && readturma<sis.getTurmas().size()) {
						sis.getTurmas().get(readturma).consolidar();
						System.out.println ("Turma consolidada com sucesso \n");
					}
					else
						System.out.println ("Numero incorreto");
				}
				else
					System.out.println ("Nao ha turmas");
				break;

			case 5:
				System.out.println ("Usuario: ");
				read1 = in.nextLine();
				System.out.println ("Senha: ");
				read2 = in.nextLine();
				if (sis.autenticar(read1, read2) == true) {
					System.out.println ("Login autorizado");
					for (int i = 0; i < sis.getProfessores().size(); i++) {
						if (sis.getProfessores().get(i).getUsuario().equals(read1) && sis.getProfessores().get(i).getSenha().equals(read2)) {
							login = 1;
							readprof = i;
						}
					}
					if (login != 1) {
						for (int i = 0; i < sis.getAlunos().size(); i++) {
							if (sis.getAlunos().get(i).getUsuario().equals(read1) && sis.getAlunos().get(i).getSenha().equals(read2)) {
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
						while (option != 0) {
							System.out.println ("Escolha uma opcao: ");
							System.out.println ("[1]- Criar tarefa");
							System.out.println ("[2]- Avaliar Aluno");
							System.out.println ("[0]- Logoff");
							option = in.nextInt();
							switch (option) {	
							case 1: //Criar Tarefa
								System.out.println ("Digite a descricao da tarefa: ");
								read1 = in.nextLine();
								sis.getProfessores().get(readprof).criarTarefa(sis.getProfessores().get(readprof).getTurma().get(readturma), read1);
								break;

							case 2: //Avaliar Aluno

								break;

							default://Logoff
								option = 0;
								break;
							}
						}
					}
					else 
						System.out.println ("Professor nao possui nenhuma turma registrada!");
					break;


				case 2://Login Aluno
					System.out.println ("Bem vindo ao menu do Aluno, selecione uma opcao: ");
					while (option != 4) {
						System.out.println ("[1]- Matricular em uma turma aberta");
						System.out.println ("[2]- Acessar uma turma matriculada");
						System.out.println ("[3]- Ver Historico");
						System.out.println ("[4]- Logoff");
						option = in.nextInt();
						switch (option) {
						case 1: //Matricular
							System.out.println ("Escolha uma turma: ");
							for (int i=0; i < sis.getTurmas().size(); i++) {
								System.out.println ("["+i+"]- "+sis.getTurmas().get(i).getDisciplina().getNome());
							}
							readturma = in.nextInt();
							sis.getAlunos().get(readaluno).matricular(sis.getTurmas().get(readturma));
							break;
						case 2: //Acessar
							if (sis.getAlunos().get(readaluno).getHistorico().getCount() > 0) {
								System.out.println ("Escolha uma turma: ");
								while (check != 1) {
									Historico historico = sis.getAlunos().get(readaluno).getHistorico();
									int i = 0;
									for (Disciplina disc: historico.getDisciplinas()) {
										System.out.println ("["+i+"]-" + disc.getNome());
										i++;
									}
									readturma = in.nextInt();
									if ((readturma >= 0)&&(readturma <= sis.getAlunos().get(readaluno).getHistorico().getCount()))
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
										if ((readint1>-1)&&(readint1<sis.getTurmas().get(readturma).getTarefas().size()))
											sis.getAlunos().get(readaluno).submeterTarefa(sis.getTurmas().get(readturma), sis.getTurmas().get(readturma).getTarefas().get(readint1));
										break;								
									default://Voltar
										option = 3;
										break;
									}
								}
							}
							else
								System.out.println ("Nao ha turmas matriculadas");
							break;

						case 3://Historico
							System.out.println("Histórico: ");
							Historico historico = sis.getAlunos().get(readaluno).getHistorico();
							for (Disciplina disc: historico.getDisciplinas()) {
								System.out.println(disc.getNome() + ": " + historico.getConceito(disc));
							}
							break;

						default://Deslogar
							option = 4;
							break;
						}
					}

				default://Login falhou
					System.out.println ("Login ou Senha incorreta");
					break;
				}

				break;


			default: //Sair do sistema
				option = 0;	
				break;	
			}
		}
		in.close();
	}
}
