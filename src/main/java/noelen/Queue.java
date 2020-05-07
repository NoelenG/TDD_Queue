package noelen;

public class Queue {
    private Client[] data;
    private int start, end, amount;

    public Queue(int size) {
        data = new Client[size];
        start = end = amount = 0;
    }

    public boolean isEmpty() {
        return (amount == 0);
    }

    public void enqueue(Client client) {

        if (isFull()) {
            return;
        }

        data[end] = client;

        amount++;

        end = (end + 1) % data.length;
    }

    public Client peek() {
        if (isEmpty()) {
            return null;
        }
        return data[start];
    }

    public int size() {
        return amount;
    }

    public boolean isFull() {
        return (amount == data.length);
    }

    public Client dequeue() {
        if (isEmpty()) {
            return null;
        }
        Client client = data[start];

        amount--;
        start = (start + 1) % data.length;

        return client;
    }

    public String show() {
        String out = "";
        int aux;

        if (!isEmpty()) {
            aux = start;

            do {
                out += data[aux] + "-";
                aux = (aux + 1) % data.length;
            } while (aux != end);
            out = out.substring(0, out.length() - 1);
        }

        return out;
    }

}
