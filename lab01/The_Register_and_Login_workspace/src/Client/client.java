package Client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import UserRMI.RemoteUser;

/**
 * Created by hans on 25/03/2018.
 */

public class client {

    private static RemoteUser stub = null;

    public static void main(String [] args){
        String username;
        String password;
        Scanner input = new Scanner(System.in);
        try {
            Registry reg = LocateRegistry.getRegistry(17524);
            stub = (RemoteUser) reg.lookup("UserSys");
        } catch (Exception e){
            System.err.println("Client exception thrown: " + e.toString());
            e.printStackTrace();
        }
        System.out.println("Choose to login or register: 1.login    2.register");
        int inputValue = input.nextInt();
        switch (inputValue) {
            //登录的情况
            case 1:
                System.out.println("Enter new Username and Password to login");
                System.out.printf("Your Username: \n");
                username = input.next();
                System.out.printf("Your Password: \n");
                password = input.next();
                try{
                    //调用Remote的方法来执行
                    System.out.println(stub.login(username, password));
                } catch (Exception e) {
                    System.out.println("Login error " + e.getMessage());
                    System.exit(0);
                }
                break;
            //输入新的用户名和密码以注册
            case 2:
                System.out.println("Enter new Username and Password to register");
                System.out.printf("Your Username: \n");
                username = input.next();
                System.out.printf("Your Password: \n");
                password = input.next();
                try{
                    //调用Remote的方法来执行
                    System.out.println(stub.register(username, password));
                } catch (Exception e) {
                    System.out.println("Register error " + e.getMessage());
                    System.exit(0);
                }
                break;
            default:
                System.out.println("Choice error!");
                break;
        }

    }
}
