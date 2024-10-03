package distributedSystem.client;

import java.io.Serializable;

public class RMIRequestClient implements Serializable {

    public String InterfaceName;
    public String methodName;
    public Object[] inputs;


    public RMIRequestClient(String interfaceName, String methodName, Object[] inputs) {
        InterfaceName = interfaceName;
        this.methodName = methodName;
        this.inputs = inputs;
    }

    public RMIRequestClient() {
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getInterfaceName() {
        return InterfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        InterfaceName = interfaceName;
    }

    public Object[] getInputs() {
        return inputs;
    }

    public void setInputs(Object[] inputs) {
        this.inputs = inputs;
    }

}
