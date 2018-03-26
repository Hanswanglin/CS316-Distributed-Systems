package UserRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by hans on 2018/3/20.
 */

public interface RemoteUser extends Remote {
    public String login(String username, String password) throws RemoteException;
    public String register(String username, String password) throws RemoteException;
}
