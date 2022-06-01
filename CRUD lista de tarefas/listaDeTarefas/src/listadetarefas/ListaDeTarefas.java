package listadetarefas;

import java.util.ArrayList;
import java.util.Scanner;

public class ListaDeTarefas {
    
    public static Usuario usuarioLogado = null;
    public static Scanner ler = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        
        ArrayList<Usuario> usuarios = new ArrayList();
                
        boolean run = true;
        while (run) {
            
            //Menu
            System.out.println("=== PAGINA DE LOGIN ===");
            System.out.println("[1] Fazer cadastro");
            System.out.println("[2] Fazer login");
            System.out.println("[3] Sair");
            System.out.print("Digite a opção: ");
            int opcao = ler.nextInt();
            
            switch (opcao) {
                case 1:
                    Usuario u = new Usuario();
                    System.out.println("=== CADASTRO ===");
                    System.out.print("Digite seu nome: ");
                    u.setNome(ler.next());
                    System.out.print("Digite sua senha: ");
                    u.setSenha(ler.next());
                    
                    ArrayList<Tarefa> tarefas = new ArrayList();
                    u.setTarefas(tarefas);
                    
                    usuarios.add(u);
                    System.out.println("Usuário cadastrado com sucesso");
                    System.out.println("");
                    break;
                case 2:
                    System.out.println("=== LOGIN ===");
                    System.out.print("Usuario: ");
                    String nome = ler.next();
                    System.out.print("Senha: ");
                    String senha = ler.next();
                    
                    boolean estadoLogin = false;
                    for (Usuario u3 : usuarios) {//for para consultar todos usuarios

                        String uNome = u3.getNome();//variavel temporaria para receber nome
                        String uSenha = u3.getSenha();//variavel temporaria para receber senha

                        boolean confereNome = nome.equals(uNome); // variavel de controle para verificar se estão identicas
                        boolean confereSenha = senha.equals(uSenha);

                        if (confereNome && confereSenha) {
                            estadoLogin = true;
                            usuarioLogado = u3;
                            break;
                        }
                    }
                    if (!estadoLogin) {
                        System.out.println("Nome ou Senha incorretos.");
                        System.out.println("");
                    }else {
                        System.out.println("Login efetuado com sucesso!");
                        System.out.println("");
                        homePage();
                    }
                break;
                case 3:
                    run = false;
                    System.out.println("Sistema finalizado.");
                    break;
                default:
            }//fim switch
        }//fim while
    }
    
    public static void homePage() {
        
        boolean runHP = true;
        
        while(runHP) {
            System.out.println("=== HOMEPAGE ===");
            System.out.println("[1] Mostrar tarefas");
            System.out.println("[2] Mostrar tarefas finalizadas");
            System.out.println("[3] Mostrar tarefas não finalizadas");
            System.out.println("[4] Adicionar tarefa");
            System.out.println("[5] Finalizar tarefa");
            System.out.println("[6] Remover tarefa");
            System.out.println("[7] Logout");
            System.out.print("Digite a opção escolhida: ");
            System.out.println("");
            int opcao = ler.nextInt();
            
            switch(opcao) {
                case 1:
                    System.out.println("=== TAREFAS ===");
                    ArrayList<Tarefa> listaTarefas = usuarioLogado.getTarefas();
                    
                    if (listaTarefas.isEmpty()) {
                        System.out.println("Lista de tarefas vazia.");
                        System.out.println("");
                    }
                    
                    for (int i=0; i <listaTarefas.size();i++) {
                        
                        Tarefa t1 = listaTarefas.get(i);
                        System.out.println("Tarefa: "+i);
                        System.out.println("\tTítulo: "+t1.getTitulo());
                        System.out.println("\tFinalizada: "+t1.isFinalizada());//VARIAVEL BOOLEANA O CORRETO É UTILIZAR 'IS' AO INVES DE 'GET'
                    }   
                    break;
                case 2:
                    System.out.println("=== TAREFAS FINALIZADAS ===");
                    ArrayList<Tarefa> listaTarefas1 = usuarioLogado.getTarefas();
                    ArrayList<Tarefa> listaFinalizadas = new ArrayList();
                    
                    for (Tarefa t2 : listaTarefas1) {
                        if (t2.isFinalizada()) {
                            listaFinalizadas.add(t2);
                        }
                    }
                    
                    if(listaFinalizadas.isEmpty()) {
                        System.out.println("Não há tarefas finalizadas.");
                        System.out.println("");
                    }
                    
                    for (int i=0; i < listaFinalizadas.size(); i++) {
                        Tarefa t3 = listaFinalizadas.get(i);
                        System.out.println("Tarefa: "+i);
                        System.out.println("\tTitulo: "+t3.getTitulo());
                        System.out.println("\tFinalizada: "+t3.isFinalizada());
                    }
                    
                    break;
                case 3:
                    System.out.println("=== TAREFAS NÃO FINALIZADAS ===");
                    ArrayList<Tarefa> listaTarefas2 = usuarioLogado.getTarefas();
                    ArrayList<Tarefa> listaNaoFinalizadas = new ArrayList();
                    
                    for(Tarefa t4 : listaTarefas2) {
                        if (!t4.isFinalizada()) {
                            listaNaoFinalizadas.add(t4);
                        }
                    }
                    
                    if(listaNaoFinalizadas.isEmpty()) {
                        System.out.println("Não há tarefas em aberto.");
                    }
                    
                    for (int i=0; i< listaNaoFinalizadas.size(); i++) {
                        Tarefa t5 = listaNaoFinalizadas.get(i);
                        
                        System.out.println("Tarefa: "+i);
                        System.out.println("\tTitulo: "+t5.getTitulo());
                        System.out.println("\tFinalizada: "+t5.isFinalizada());
                    }
                    break;
                case 4:
                    System.out.println("=== NOVA TAREFA ===");
                    System.out.print("Digite o título: ");
                    String titulo = ler.next();
                    
                    Tarefa t6 = new Tarefa();
                    t6.setTitulo(titulo);
                    t6.setFinalizada(false);
                    
                    usuarioLogado.getTarefas().add(t6);
                    System.out.println("Tarefa adicionada com sucesso!");
                    break;
                case 5:
                    System.out.println("=== CONCLUIR TAREFA ===");
                    ArrayList<Tarefa> listaTarefas3 = usuarioLogado.getTarefas();
                    ArrayList<Tarefa> naoFinalizadas = new ArrayList();
                    
                    for(Tarefa t7 : listaTarefas3) {
                        if (!t7.isFinalizada()) {
                            naoFinalizadas.add(t7);
                        }
                    }
                    
                    if (naoFinalizadas.isEmpty()) {
                        System.out.println("Não há tarefas para concluir.");
                    }else {
                     for(int i=0; i<naoFinalizadas.size(); i++) { //for para listar todos os titulos
                        Tarefa t7 = naoFinalizadas.get(i);
                        
                        System.out.println("["+i+"] "+t7.getTitulo());
                    }

                    System.out.print("Digite o ID da tarefa que deseja concluir: ");
                    int ID = ler.nextInt();
                    ler.nextLine();//PASSAR O SCANNER NEXTLINE LIMPA O BUFFER

                    Tarefa tarefaFinalizada = naoFinalizadas.get(ID);
                    tarefaFinalizada.setFinalizada(true);
                    // usuarioLogado.getTarefas().get(ID).setFinalizada(true); <<< ESTA SERIA OUTRA MANEIRA DE SETAR A TAREFA SELECIONADA COMO TRUE11
                    System.out.println("--- ["+ID+"]"+" Tarefa concluída com sucesso! ---");   
                    }
                    break;
                case 6:
                    System.out.println("=== REMOVER TAREFA ===");
                    ArrayList<Tarefa> listaTarefas4 = usuarioLogado.getTarefas();
                    
                    for (int i=0; i<listaTarefas4.size();i++) {
                        Tarefa t8 = listaTarefas4.get(i);
                        System.out.println("["+i+"] "+t8.getTitulo());
                    }
                    System.out.print("Digite o ID da tarefa que deseja remover: ");
                    int ID = ler.nextInt();
                    ler.nextLine();
                    
                    listaTarefas4.remove(ID);
                    System.out.println("Tarefa removida com sucesso!");
                    
                    break;
                case 7:
                    runHP = false;
                    usuarioLogado = null;
                    System.out.println("Saindo da HomePage...");
                    break;
            }
        }//fim while hp
    }//fim homePage
}
