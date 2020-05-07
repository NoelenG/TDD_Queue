package noelen;
/**
 * Client
 */
public class Client {
    //oi teste commit
    private String name;
    private int age;

    public Client(String name, int age) {
        this.name = name;
        setAge(age);
    }

    public void setAge(int age){
        if(age <= 0){
            this.age = 18;
        }
        this.age = age;
    }
    
    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getElderly(int age){
        if(age >= 60){
            return true;
        }
        return false;
    }
    
    public String toString() {
       return name + ":" + age;
      // return name;
    }
}
