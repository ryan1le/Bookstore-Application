/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co528_project;

/**
 *
 * @author Ryan Le
 */
public class User {
    
    public static String username;
    public static double points;
    
    public User(){
        
    }
    
    public static int verify(String username, String password){
        Database data = CustomerData.getInstance();
        if (username.equals(data.getOwnerName()) && password.equals(data.getOwnerPassowrd())){
            return -1;
        }
        else if (data.getName().contains(username) && data.getPassword().contains(password)){
            User.username = username;
            int index = data.getName().indexOf(username);
            points = data.getNumericValue().get(index);
            return 1;
        }
        else {
            return 0;
        }
    }
}
