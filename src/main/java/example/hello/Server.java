package example.hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements Hello {

    public Server() {}

    public String sayHello() {
        return "Hello, world!";
    }

    public static void main(String args[]) {

        try {
            Server obj = new Server();

            //The static method UnicastRemoteObject.exportObject exports the supplied remote
            // object to receive incoming remote method invocations on an anonymous TCP port
            // and returns the stub for the remote object to pass to clients.
            // As a result of the exportObject call, the runtime may begin to listen on a
            // new server socket or may use a shared server socket to accept incoming remote
            // calls for the remote object. The returned stub implements the same set of
            // remote interfaces as the remote object's class and contains the host name and
            // port over which the remote object can be contacted.
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();

            //For a caller (client, peer, or applet) to be able to invoke a method on a
            // remote object, that caller must first obtain a stub for the remote object.
            // For bootstrapping, Java RMI provides a registry API for applications to bind a
            // name to a remote object's stub and for clients to look up remote objects by name
            // in order to obtain their stubs.
            //The following code in the server obtains a stub for a registry on the local host
            // and default registry port and then uses the registry stub to bind the name
            // "Hello" to the remote object's stub in that registry:
            registry.rebind("Hello", stub);

            //The static method LocateRegistry.getRegistry that takes no arguments returns
            // a stub that implements the remote interface java.rmi.registry.Registry and
            // sends invocations to the registry on server's local host on the default registry
            // port of 1099. The bind method is then invoked on the registry stub in order to
            // bind the remote object's stub to the name "Hello" in the registry.

            System.err.println("Server ready");

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

