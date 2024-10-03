package distributedSystem.server;

import distributedSystem.Remote;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;

public class HandlerServer extends RegisteryServer implements Runnable {

    Socket socket;

    public HandlerServer(Socket socket, HashMap map) {
        this.nodeTable = map;
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            RMIRequestServer message = new RMIRequestServer();
            Object obj = ois.readObject();

            for (Field field : obj.getClass().getFields()) {
                message.getClass().getField(field.getName()).set(message, field.get(obj));
            }

            if (message != null) {
                Remote server = nodeTable.get(message.InterfaceName);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                Object[] inputs = message.inputs;
                Class[] classes = new Class[inputs.length];

                for (int i = 0; i < inputs.length; i++) {
                    classes[i] = inputs[i].getClass();
                }

                Method method = server.getClass().getMethod(message.methodName, classes);
                Object[] output = new Object[1];
                output[0] = method.invoke(server, inputs);
                RMIRequestServer request = new RMIRequestServer(message.InterfaceName, message.methodName, output);
                oos.writeObject(request);
                Thread.sleep(4000);
                ois.close();
                oos.close();
                socket.close();
            }

        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error closing server", e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
