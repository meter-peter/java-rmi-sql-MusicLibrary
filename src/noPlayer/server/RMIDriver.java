package noPlayer.server;

import noPlayer.api.ClientInterface;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RMIDriver {
    public RMIDriver(RMIImpl rmiImpl){
           try {
                LocateRegistry.createRegistry(5555);
                ClientInterface stub = (ClientInterface) UnicastRemoteObject.exportObject(rmiImpl, 5555);


                Naming.rebind("rmi://localhost:5555/laMusica"
                        ,stub);
                System.err.println("Server ready");
            } catch (Exception e) {
                System.err.println("Server exception: " + e.toString());
                e.printStackTrace();
            }
        }

    }

