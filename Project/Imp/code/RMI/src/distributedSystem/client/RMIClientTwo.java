package distributedSystem.client;


public class RMIClientTwo {


    public static void main(String[] args) {
        try {

            RegisteryClient registery = new RegisteryClient();
            Shop stub = (Shop) registery.lookup("RMIServer","1.2");

            System.out.println(stub.alterProduct("pants", 120.0, 34));
            System.out.println(stub.insertProduct("pants", 12));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
