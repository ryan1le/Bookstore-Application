package co528_project;
import java.io.*;
import java.util.*;

/**
 *
 * @author Ryan Le
 */
public class BooksData extends Database{
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<Double> price = new ArrayList<Double>();
    
    private static BooksData instance; 
    
    private BooksData(){
        read();
    }
    
    public static BooksData getInstance(){
        if (instance == null){
            instance = new BooksData();
        }
        return instance;
    }
    
    @Override
    public void read() {         
        try {  
            FileReader fr = new FileReader("books.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String str;
            double num;
            while((str = br.readLine()) != null){
                if (str.equals("Name:")){
                    str = br.readLine();
                    name.add(str);
                }
                else if (str.equals("Price:")){
                    str = br.readLine();
                    num = Double.parseDouble(str);
                    price.add(num);
                }
            }
            br.close();
        } catch (IOException e) {             
            System.out.println("An error occurred.");             
            e.printStackTrace();         
        }     
    }
    
    @Override
    public void save() {         
        try {   
            FileWriter fw = new FileWriter("books.txt",false);  
            int length = name.size();
            for (int count = 0; count < length; count++){
                fw.write("Name:\n" + name.get(count) + "\nPrice:\n" + price.get(count) + "\n");            
            }
                
            fw.close();
        } catch (IOException e) {             
            System.out.println("An error occurred.");             
            e.printStackTrace();         
        }     
    }
    
    @Override
    public ArrayList<String> getName(){
        return name;
    }
    
    @Override
    public ArrayList<Double> getNumericValue(){
        return price;
    }
    
    @Override 
    public ArrayList<String> getPassword(){
        return null;
    }
   
}
