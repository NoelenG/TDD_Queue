package noelen;

import java.util.Scanner;

public  class App {
    private static Scanner scanner;
    private static Client cli;
    private static ManageAttendance management;

    private App() {
    }

    public static void main(final String[] args) throws Exception {
        management = new ManageAttendance(10);
        scanner = new Scanner(System.in, "UTF-8");   
        int opcao = 0;
            do {
                System.out.println("----------------Bem vindo ao Dinheiro da Mão!!!----------------");
                System.out.println("Opções:");
                System.out.println("1- Chegada do cliente na agência");
                System.out.println("2- Verificar quem é o próximo a ser atendido");
                System.out.println("3- Atender um cliente");
                System.out.println("4- Exibir as filas");
                System.out.println("5- Finalizar o programa");
                try{
                    opcao = scanner.nextInt();
                }catch(Exception e){
                    System.out.println("Apenas números por favor.");
                }
                scanner.nextLine();
                    switch (opcao) {
                        case 1:
                            System.out.println("Chegou um cliente!!");
                            chegouCliente();
                            break;
                        case 2:
                                System.out.println("Proximo cliente:\n");
                                if(management.showNext() != null){
                                    System.out.println(management.showNext());
                                }else{
                                    System.out.println("Não há clientes.");
                                }
                            break;
                        case 3:
                                if(!management.isEmpty()){
                                    if(management.showNext() != null){
                                        System.out.println("O próximo cliente está indo até você");
                                        System.out.println(management.getNext());
                                        System.out.println("Cliente atendido. PRÓXIMO!");
                                    }else{
                                        System.out.println("Não há ninguém na fila.");
                                    }
                                }else {
                                    System.out.println("Não há ninguém na fila.");
                                }
                            break;
                        case 4:
                           if(management.showQueues() != null){
                              System.out.println(management.showQueues());
                            }else{
                                System.out.println("Não há ninguém no momento");
                            }
                            break;
                        case 5:
                            if (management.showNext() != null || management.getNext() != null) {
                                System.out.println("Hey, não dá para encerrar agora, tem cliente na fila!!!");
                            } else {
                                opcao = 6;
                            }
                            break;
                        default:
                            System.out.println("Opção inválida. Selecione outra opção.");
                            break;
                    }
            } while (opcao != 6);
          
    }
       public static void chegouCliente() throws Exception {
       String nomeCliente = "";
       int idadeCliente = 0;
        try{
            System.out.println("Qual o nome do cliente?\n");
            nomeCliente = scanner.next();
            scanner.nextLine();
            System.out.println("Qual a idade do cliente?\n");
            idadeCliente = scanner.nextInt();
        }catch(java.util.InputMismatchException e){
            System.out.println("Por favor, verifique suas entradas. Atente-se a idade ser apenas em números e nome apenas em letras.");
        }
         cli = new Client(nomeCliente, idadeCliente);
         System.out.println(management.addClient(cli));
    }
}
   