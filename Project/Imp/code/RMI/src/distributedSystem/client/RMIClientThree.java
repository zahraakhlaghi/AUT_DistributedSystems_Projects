package distributedSystem.client;

public class RMIClientThree {


    public static void main(String[] args) {
        try {

            RegisteryClient registery = new RegisteryClient();
            Shop stub = (Shop) registery.lookup("RMIServer","1.2");

            System.out.println(stub.check("pants"));

            System.out.println(stub.sellProduct("pants", 3));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
