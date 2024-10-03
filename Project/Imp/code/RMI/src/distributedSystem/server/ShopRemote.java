package distributedSystem.server;

import java.rmi.RemoteException;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class ShopRemote implements Shop {

    Map<String, Product> productMap = new HashMap<>();

    public ShopRemote() {
        this.productMap.put("jacket", new Product("jacket", 12.89, 30));
        this.productMap.put("dress", new Product("dress", 75.9, 16));
        this.productMap.put("hat", new Product("hat", 12.9, 39));
        this.productMap.put("scarf", new Product("scarf", 24.4, 43));
    }

    @Override
    public Double sellProduct(String name, Integer amount) throws RemoteException {

        if (!productMap.containsKey(name))
            throw new InvalidParameterException();

        Product product = productMap.get(name);
        product.setAmount(product.getAmount() - amount);
        productMap.replace(name, product);

        return product.getValue() * amount;
    }

    @Override
    public Boolean check(String name) throws RemoteException {
        return productMap.containsKey(name);
    }

    @Override
    public Integer insertProduct(String name, Integer amount) throws RemoteException {
        if (!productMap.containsKey(name))
            throw new InvalidParameterException();

        Product product = productMap.get(name);
        product.setAmount(product.getAmount() + amount);
        productMap.replace(name, product);

        return product.getAmount();
    }

    @Override
    public Product alterProduct(String newName, Double newValue, Integer newAmount) throws RemoteException {

        Product product = new Product(newName, newValue, newAmount);
        productMap.put(newName, product);

        return product;
    }

    @Override
    public void removeProduct(String productName) throws RemoteException {

        if (productMap.containsKey(productName))
            productMap.remove(productName);
    }
}
