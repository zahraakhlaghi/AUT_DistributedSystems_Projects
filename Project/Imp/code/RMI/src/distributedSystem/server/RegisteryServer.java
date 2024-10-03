package distributedSystem.server;

import distributedSystem.Registery;
import distributedSystem.Remote;
import distributedSystem.serverRegistery.LocateRegistery;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class RegisteryServer implements Registery {

    protected HashMap<String, Remote> nodeTable = new HashMap<>();

    public RegisteryServer() {
    }

    @Override
    public void bind(String className, String classVersion) throws IOException {

        LocateRegistery registery = new LocateRegistery();
        ServerSocket listener = registery.assign(className, classVersion);
        Shop shop = new ShopRemote();
        nodeTable.put(className, shop);
        System.out.println("server ready");
        while (true) {
            Socket socket = listener.accept();
            System.out.println("Client connected : " + socket);

            HandlerServer handlerServer = new HandlerServer(socket, nodeTable);
            handlerServer.run();
        }
    }

    @Override
    public Remote lookup(String className, String classVersion) throws IOException {
        return null;
    }
}
