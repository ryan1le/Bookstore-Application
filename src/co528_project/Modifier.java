package co528_project;

/**
 *
 * @author Ryan Le
 */
public class Modifier {
    
    public void add(Database n, String name, double numericValue){//BooksData
        n.getName().add(name);
        n.getNumericValue().add(numericValue);
    }
    
    public void add(Database n, String name, String pass, double numericValue){//CustomerData
        n.getName().add(name);
        n.getNumericValue().add(numericValue);
        n.getPassword().add(pass);
    }
    
    public boolean delete(Database n, String name){//BooksData
        int index = n.getName().indexOf(name);
        if (index != -1){
            if (n instanceof BooksData){
                n.getName().remove(index);
                n.getNumericValue().remove(index);
            }
            else if (n instanceof CustomerData){
                n.getName().remove(index);
                n.getNumericValue().remove(index);
                n.getPassword().remove(index);
            }
            return (true);
        }
        else{
            return (false);
        }
        
    }
}
