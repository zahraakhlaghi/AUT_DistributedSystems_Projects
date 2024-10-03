package distributedSystem.serverRegistery;

import java.util.Objects;

public class ServerAttr {

    public String className;

    public String classVersion;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServerAttr)) return false;
        ServerAttr that = (ServerAttr) o;
        return Objects.equals(className, that.className) && Objects.equals(classVersion, that.classVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, classVersion);
    }

    public ServerAttr(String className, String classVersion) {
        this.className = className;
        this.classVersion = classVersion;
    }
}
