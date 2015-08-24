package example.hello;

import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RmiHelloWorldTest {

    //Default value
    private final int RMI_PORT = 1099;

    @Before
    public void setup() {
        try {
            LocateRegistry.createRegistry(RMI_PORT);
            System.out.println("RMI Server ready");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void runRmiTest() {
        Server.main(null);
        String args[] = {"localhost"};
        Client.main(args);
    }

}
