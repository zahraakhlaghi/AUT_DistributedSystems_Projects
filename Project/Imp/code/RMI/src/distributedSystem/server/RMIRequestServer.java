package distributedSystem.server;

import java.io.Serializable;

public class RMIRequestServer implements Serializable {

    public String InterfaceName;
    public String methodName;
    public Object[] inputs;


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


    public RMIRequestServer(String interfaceName, String methodName, Object[] inputs) {
        InterfaceName = interfaceName;
        this.methodName = methodName;
        this.inputs = inputs;
    }

    public RMIRequestServer() {
    }
}
