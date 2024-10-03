package distributedSystem.client;

import distributedSystem.Remote;
import java.rmi.RemoteException;

public interface Shop extends Remote {

    public Double sellProduct(String name,Integer amount) throws RemoteException;

    public Boolean check(String name) throws RemoteException;

    public Integer insertProduct(String name,Integer amount) throws RemoteException;

    public Object alterProduct(String newName, Double newValue, Integer newAmount) throws RemoteException;

    public void removeProduct(String productName) throws RemoteException;
}
 