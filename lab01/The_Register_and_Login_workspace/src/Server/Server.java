package Server;

import UserRMI.RemoteUser;
import UserRMI.RemoteUserImpl;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by hans on 25/03/2018.
 */

public class Server {

    public Server() {}

    public static void main(String [] args){
        int port = 17524;
        try {
            RemoteUserImpl remoteUserobj = new RemoteUserImpl();
            RemoteUser stub = (RemoteUser) UnicastRemoteObject.exportObject(remoteUserobj, port);  // stub is the remote obj

            LocateRegistry.createRegistry(port);// 使用默认的Register的端口
            Registry registry = LocateRegistry.getRegistry(port);
            registry.rebind("UserSys", stub);  // bind() function need a name and an remoted obj. Here, it is the reference of the obj. Name is the unique server name
            System.out.println("UserSys Server.Server is ready to listen... ");

        } catch (Exception e) {
            System.err.println("Server.Server exception thrown: " + e.toString());
            e.printStackTrace();
        }
    }
}
