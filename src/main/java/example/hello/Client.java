package example.hello;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            //This client first obtains the stub for the registry by invoking the
            //static LocateRegistry.getRegistry method with the hostname specified
            // on the command line. If no hostname is specified, then null is used
            // as the hostname indicating that the local host address should be used.
            Registry registry = LocateRegistry.getRegistry(host);

            //Next, the client invokes the remote method lookup on the registry
            // stub to obtain the stub for the remote object from the server's registry.
            Hello stub = (Hello) registry.lookup("Hello");

            //The client-side runtime opens a connection to the server using the
            // host and port information in the remote object's stub and then
            // serializes the call data.
            //The server-side runtime accepts the incoming call, dispatches the
            //call to the remote object, and serializes the result (the reply string
            // "Hello, world!") to the client.
            //The client-side runtime receives, deserializes, and returns the result to the caller.
            String response = stub.sayHello();

            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}