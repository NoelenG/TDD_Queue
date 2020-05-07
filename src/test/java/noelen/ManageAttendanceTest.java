package noelen;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * ManageAttendanceTest
 */

public class ManageAttendanceTest {

    private static final int _managerQueueSize = 3;
    private ManageAttendance manager;
    private Client cli1Normal;
    private Client cli2Idoso;
    private Client cli3Normal;
    private Client cli4Idoso;
    private Client cli5Idoso;
    private final int idadeCliente1 = 25;
    private final int idadeCliente2 = 65;
    private final int idadeCliente3 = 25;
    private final int idadeCliente4 = 65;
    private final int idadeCliente5 = 65;
    private final String nomeCliente1 = "Cliente1";
    private final String nomeCliente2 = "Cliente2";
    private final String nomeCliente3 = "Cliente3";
    private final String nomeCliente4 = "Cliente4";
    private final String nomeCliente5 = "Cliente5";

    @Before
    public void setup() {
        manager = new ManageAttendance(_managerQueueSize);
        cli1Normal = new Client(nomeCliente1, idadeCliente1);
        cli2Idoso = new Client(nomeCliente2, idadeCliente2);
        cli3Normal = new Client(nomeCliente3, idadeCliente3);
        cli4Idoso = new Client(nomeCliente4, idadeCliente4);
        cli5Idoso = new Client(nomeCliente5, idadeCliente5);
    }

    @Test
    public void testEmptyQueueOnManager() {
        boolean vazia = manager.isEmpty();

        assertTrue("Não há clientes para atender no início", vazia);
    }

    @Test
    public void testEnqueueClient() {
        manager.addClient(cli1Normal);
        boolean vazia = manager.isEmpty();

        assertFalse("Fila chegada de cliente não deve estar vazia", vazia);
    }

    @Test
    public void testDequeueClient() {
        manager.addClient(cli1Normal);

        Client cli = manager.showNext();
        boolean vazia = manager.isEmpty();

        assertFalse("methodo show não deve remover da fila", vazia);
        assertEquals("Cliente que chegou deve ser o cliente 1", cli, cli1Normal);
    }

    @Test
    public void testNextClient() {
        manager.addClient(cli1Normal);

        Client cli = manager.getNext();

        assertEquals("Cliente que chegou deve ser o cliente 1", cli, cli1Normal);
    }

    @Test
    public void testNextElderlyClient() {
        manager.addClient(cli1Normal);
        manager.addClient(cli2Idoso);
        manager.addClient(cli3Normal);
        manager.addClient(cli4Idoso);
        manager.addClient(cli5Idoso);

        Client cli = manager.getNext();
        Client cli2 = manager.getNext();
        Client cli3 = manager.getNext();

        assertEquals("Próximo cliente a ser atendido deve ser idoso", cli2Idoso,cli);
        assertEquals("Próximo cliente a ser atendido deve ser idoso",  cli4Idoso ,cli2);
        assertEquals("Próximo cliente a ser atendido deve ser normal", cli1Normal ,cli3);
       // assertEquals("Próximo cliente a ser atendido deve ser idoso", cli4, cli5Idoso);
       
    }

    @Test
    public void testNumClients() {
        manager.addClient(cli1Normal);
        manager.addClient(cli2Idoso);
        manager.addClient(cli3Normal);

        int numClientes = manager.numClients();
        int numElderlyClientes = manager.numElderlyClients();

        assertEquals("Número de clientes aguardando atendimento", 3, numClientes);
        assertEquals("Número de clientes Idosos aguardando atendimento", 1, numElderlyClientes);
    }

    @Test
    public void testShowEmptyElderlyClients() {
        manager.addClient(cli1Normal);
        manager.addClient(cli3Normal);

        String saidaEsperada = "idoso:vazia;normal:Cliente1:25-Cliente3:25";
        String fila = manager.showQueues();

        assertEquals("Filas de clientes sem idoso aguardando", saidaEsperada, fila);
    }

    @Test
    public void testShowClients() {
        manager.addClient(cli1Normal);
        manager.addClient(cli2Idoso);
        manager.addClient(cli3Normal);

        String saidaEsperada = "idoso:Cliente2:65;normal:Cliente1:25-Cliente3:25";
        String fila = manager.showQueues();

        assertEquals("Filas de clientes aguardando", saidaEsperada, fila);
    }


}
