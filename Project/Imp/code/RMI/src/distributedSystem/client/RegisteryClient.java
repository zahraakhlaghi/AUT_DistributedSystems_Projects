package distributedSystem.client;

import distributedSystem.Registery;
import distributedSystem.Remote;
import distributedSystem.serverRegistery.LocateRegistery;

import java.io.IOException;
import java.lang.reflect.Proxy;

public class RegisteryClient implements Registery {


    @Override
    public void bind(String className, String classVersion) throws IOException {

    }

    @Override
    public Remote lookup(String className, String classVersion) throws IOException {

        LocateRegistery registery = new LocateRegistery();
        Integer port = registery.find(className, classVersion);

        HandlerClient handler = new HandlerClient(className, port);

        return (Remote) Proxy.newProxyInstance(Shop.class.getClassLoader(),
                new Class[]{Shop.class},
                handler);

    }
}
