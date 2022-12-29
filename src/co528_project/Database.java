package co528_project;
import java.util.*;

/**
 *
 * @author Ryan Le
 */
public abstract class Database {

    private final String ownerName = "admin";
    private final String ownerPassword = "admin";
    
    public Database(){
        
    }
    
    public abstract ArrayList<String> getName();
    
    public abstract ArrayList<String> getPassword();
    
    public abstract ArrayList<Double> getNumericValue();
    
    public abstract void save();
    
    public abstract void read();
    
    public String getOwnerName(){
        return ownerName;
    }
    
    public String getOwnerPassowrd(){
        return ownerPassword;
    }
    
}
