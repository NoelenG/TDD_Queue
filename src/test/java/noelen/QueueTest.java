package noelen;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

/**
 * QueueTest
 */
public class QueueTest {

    private static final int _queueSize = 3;
    private Queue queue;
    private Client cliente1;
    private Client cliente2;
    private Client cliente3;
    private final int idadeCliente1 = 25;
    private final int idadeCliente2 = 65;
    private final int idadeCliente3 = 25;
    private final String nomeCliente1 = "Cliente1";
    private final String nomeCliente2 = "Cliente2";
    private final String nomeCliente3 = "Cliente3";

    @Before
    public void setup() {
        queue = new Queue(_queueSize);
        cliente1 = new Client(nomeCliente1, idadeCliente1);
        cliente2 = new Client(nomeCliente2, idadeCliente2);
        cliente3 = new Client(nomeCliente3, idadeCliente3);
    }

    @Test
    public void testEmptyStack() {
        boolean vazia = queue.isEmpty();

        assertTrue("Nova fila deve iniciar vazia", vazia);
    }

    @Test
    public void testEnqueue() {
        queue.enqueue(cliente1);
        boolean vazia = queue.isEmpty();

        assertFalse("Fila após inserção não deve estar vazia", vazia);
    }

    @Test
    public void testPeek() {
        queue.enqueue(cliente1);
        queue.enqueue(cliente1);

        Client cliente = queue.peek();

        assertEquals("O valor no inicio da fila após 2 iserções deve ser o cliente 1", cliente1, cliente);
    }

    @Test
    public void testQueueSize() {
        queue.enqueue(cliente1);
        queue.enqueue(cliente2);

        int resp = queue.size();

        assertEquals("Após 2 iserções o tamanho da fila deve ser o cliente 2", 2, resp);
    }

    @Test
    public void testIsFull() {
        queue.enqueue(cliente1);
        queue.enqueue(cliente2);
        queue.enqueue(cliente3);

        boolean full = queue.isFull();

        assertTrue("A fila com tamanho 3 deve estar cheia após 3 inserções", full);
    }

    @Test
    public void testDequeue() {
        queue.enqueue(cliente1);
        queue.enqueue(cliente2);

        Client cliente1 = queue.dequeue();
        Client cliente2 = queue.dequeue();

        assertEquals("Remover o primeiro elemento deve ser o cliente 1", cliente1, cliente1);
        assertEquals("Remover o segundo elemento deve ser cliente 2", cliente2, cliente2);

        boolean vazia = queue.isEmpty();
        assertTrue("Fila deve fica vazia após todas as remoções", vazia);
    }

    @Test
    public void testIsertAfterIsFull() {
        queue.enqueue(cliente1);
        queue.enqueue(cliente2);
        queue.enqueue(cliente3);
        queue.enqueue(new Client("Cliente4", 30));

        boolean full = queue.isFull();
        assertTrue("A fila com tamanho 3 deve estar cheia após 3 inserções", full);

        int resp = queue.size();
        assertEquals("Não deve permitir inserir além da capacidade", 3, resp);
    }

    @Test
    public void testRemoveBeforeInsert() {
        queue.dequeue();

        boolean empty = queue.isEmpty();
        assertTrue("A fila permanece vazia após dequeue sem elementos", empty);

        int size = queue.size();
        assertEquals("A quantidade de elementos não deve ser alterada se está vazia", 0, size);
    }

    @Test
    public void testEnqueueDequeueManyTimes() {
        queue.enqueue(cliente1);
        queue.enqueue(cliente2);
        queue.enqueue(cliente3);

        queue.dequeue();
        queue.dequeue();

        Client cliente = new Client("Cliente4", 40);
        queue.enqueue(cliente);

        queue.dequeue();

        Client firstCli = queue.peek();
        int size = queue.size();

        assertEquals("O primeiro elemento deve ser 4", cliente, firstCli);
        assertEquals("O tamanho da fila deve ser 1", 1, size);

    }

    @Test
    public void testShow() {
        queue.enqueue(cliente1);
        queue.enqueue(cliente2);

        String esperada = nomeCliente1 + ":" + idadeCliente1 + "-" + nomeCliente2 + ":" + idadeCliente2;
        String out = queue.show();

        assertEquals("Deve exibir cliente1:-2-3", esperada, out);
    }

}
