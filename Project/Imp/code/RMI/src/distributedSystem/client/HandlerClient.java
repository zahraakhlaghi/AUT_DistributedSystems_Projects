package distributedSystem.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;

public class HandlerClient implements InvocationHandler {

    String InterfaceName = null;
    Integer port;

    public HandlerClient(String interfaceName, Integer port) {

        this.InterfaceName = interfaceName;
        this.port = port;

    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

        RMIRequestClient rmiMessage = new RMIRequestClient(InterfaceName, method.getName(), objects);

        InetAddress host = InetAddress.getLocalHost();
        Socket socket = new Socket(host.getHostName(), this.port);

        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(rmiMessage);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Object obj = ois.readObject();
        RMIRequestClient message = new RMIRequestClient();

        for (Field field : obj.getClass().getFields()) {
            message.getClass().getField(field.getName()).set(message, field.get(obj));
        }

        Object returnValue = message.inputs[0];

        ois.close();
        oos.close();
        Thread.sleep(1000);
        return returnValue;
    }
}
