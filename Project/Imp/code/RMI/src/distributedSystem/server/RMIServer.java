package distributedSystem.server;


import java.io.IOException;

public class RMIServer {

    public static void main(String[] args) throws IOException {

        RegisteryServer registery = new RegisteryServer();

        registery.bind(RMIServer.class.getSimpleName(),"1.2");


    }
}
