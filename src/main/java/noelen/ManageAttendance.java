package noelen;

/**
 * ManageAttendance
 */
public class ManageAttendance {
    Queue fila;
    Queue filaIdoso;
    Client cli;
    int vez = 0;
    
    public ManageAttendance(int size) {
        fila = new Queue(size);
        filaIdoso = new Queue(size);
       
    }

    public boolean isEmpty() {
       if(fila.isEmpty() && filaIdoso.isEmpty()){
        return true;
       }else{
        return false;
       }
    }

    public String addClient(Client client) {
        if(client.getElderly(client.getAge()) == true){
            filaIdoso.enqueue(client);
            return "chegou um idoso";
        }else{
            fila.enqueue(client);
            return "chegou um jovem";
        }
    }

    public Client showNext() {
        Client aux = null;
        if(!filaIdoso.isEmpty()  && !fila.isEmpty()){
            if(vez < 2){
                aux = filaIdoso.peek();
            }else{
                aux = fila.peek();
            }
        }else if(filaIdoso.isEmpty() && !fila.isEmpty()){
            aux = fila.peek();
        }else if(!filaIdoso.isEmpty() && fila.isEmpty()){
            aux = filaIdoso.peek();
        }else{
            aux = null;
        }
        return aux;
    }

    public Client getNext() {
        if(!filaIdoso.isEmpty()  && !fila.isEmpty()){
            if(vez < 2){
                vez++;
                return filaIdoso.dequeue();
            }else{
                vez = 0;
                return fila.dequeue();
            }
        }else if(filaIdoso.isEmpty() && !fila.isEmpty()){
            return fila.dequeue();
        }else if(!filaIdoso.isEmpty() && fila.isEmpty()){
            return filaIdoso.dequeue();
        }else{
            return null;
        }
    }
       

    public String showQueues() {
        if(filaIdoso.isEmpty() == false && fila.isEmpty() == false){
            return "idoso:"+filaIdoso.show()+";normal:"+fila.show();
        }else if(filaIdoso.isEmpty() == true && fila.isEmpty() == false){
            return "idoso:vazia"+";normal:"+fila.show();
        }else if(filaIdoso.isEmpty() == false && fila.isEmpty() == true){
            return "idoso:"+filaIdoso.show()+";normal:vazia";
        }else{
            return null;
        }     
    }

    public int numClients() {
        return fila.size()+filaIdoso.size();
    }

    public int numElderlyClients() {
        return filaIdoso.size();
    }
}
