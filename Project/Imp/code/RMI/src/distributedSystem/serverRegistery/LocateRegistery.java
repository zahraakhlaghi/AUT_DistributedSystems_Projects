package distributedSystem.serverRegistery;


import java.io.*;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Scanner;

public class LocateRegistery {

    public HashMap<ServerAttr, Integer> nodeTable = new HashMap<>();

    public LocateRegistery() throws FileNotFoundException {

        File file = new File("src/distributedSystem/serverRegistery/info.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(",");
            ServerAttr attr = new ServerAttr(line[0], line[1]);
            nodeTable.put(attr, Integer.valueOf(line[2]));
        }
    }

    public ServerSocket assign(String className, String classVersion) throws IOException {

        ServerAttr serverAttr = new ServerAttr(className, classVersion);
        if (!nodeTable.containsKey(serverAttr)) {
            ServerSocket socket = new ServerSocket(0);
            this.write(className, classVersion, socket.getLocalPort());
            nodeTable.put(serverAttr, socket.getLocalPort());

            return socket;
        }

        return new ServerSocket(nodeTable.get(serverAttr));
    }

    public Integer find(String className, String classVersion) throws IOException {

        ServerAttr serverAttr = new ServerAttr(className, classVersion);
        if (nodeTable.containsKey(serverAttr))
            return nodeTable.get(serverAttr);


        return null;
    }

    public void write(String className, String classVersion, Integer port) throws IOException {
        FileWriter fw = new FileWriter("src/distributedSystem/serverRegistery/info.txt", true); //the true will append the new data
        fw.write(className + "," + classVersion + "," + port.toString() + "\n");//appends the string to the file
        fw.close();
    }
}
