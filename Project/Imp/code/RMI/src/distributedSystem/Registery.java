package distributedSystem;

import java.io.IOException;

public interface Registery {
    public void bind(String className, String classVersion) throws IOException;

    public Remote lookup(String className, String classVersion) throws IOException;

}
