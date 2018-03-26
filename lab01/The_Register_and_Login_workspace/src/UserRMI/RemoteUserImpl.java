package UserRMI;

import UserRMI.RemoteUser;

import java.rmi.RemoteException;
//import java.rmi.server.UnicastRemoteObject;
import Dao.DBmanager;

/**
 * Created by hans on 2018/3/20.
 */
public class RemoteUserImpl implements RemoteUser {

    public RemoteUserImpl() throws RemoteException{}

    DBmanager dBmanager = new DBmanager();

    @Override
    public String login(String username, String password) throws RemoteException {
        int result = 0;
        String returnInfo = "";
        try {
            result = dBmanager.login(username, password);
            if (result == 1) {
                returnInfo = "You login successfully\nWelcome to e-shop";
            } else {
                returnInfo = "There is not such User!";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnInfo;
    }

    //
    @Override
    public String register(String username, String password) throws RemoteException {
        int result = 0;
        String returnInfo = "";
        try {
            result = dBmanager.createNewUser(username,password);
            if (result == 1){
                returnInfo = "New user created successfully!";
            } else {
                returnInfo = "Fail to create new user.";
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return returnInfo;
    }
}
