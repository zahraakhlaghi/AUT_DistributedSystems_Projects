package distributedSystem.client;


public class RMIClientOne {


    public static void main(String[] args) {
        try {

            RegisteryClient registery = new RegisteryClient();
            Shop stub = (Shop) registery.lookup("RMIServer","1.2");

            System.out.println(stub.check("scarf"));
            stub.removeProduct("scarf");
            System.out.println(stub.check("scarf"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
