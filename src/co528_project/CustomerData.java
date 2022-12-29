package co528_project;

import java.util.*;
import java.io.*;


/**
 *
 * @author Ryan Le
 */
public class CustomerData extends Database{
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<Double> points = new ArrayList<Double>();
    ArrayList<String> password = new ArrayList<String>();
    
    
    private static CustomerData instance; 
    
    private CustomerData(){
        read();
    }
    
    public static CustomerData getInstance(){
        if (instance == null){
            instance = new CustomerData();
        }
        return instance;
    }
    
    @Override
    public void read() {         
        try {  
            FileReader fr = new FileReader("customers.txt");
            BufferedReader br = new BufferedReader(fr);
            
            String str;
            double num;
            while((str = br.readLine()) != null){
                if (str.equals("Name:")){
                    str = br.readLine();
                    name.add(str);
                }
                else if (str.equals("Password:")){
                    str = br.readLine();
                    password.add(str);
                }
                else if (str.equals("Points:")){
                    str = br.readLine();
                    num = Double.parseDouble(str);
                    points.add(num);
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
            FileWriter fw = new FileWriter("customers.txt",false);  
            int length = name.size();
            for (int count = 0; count < length; count++){
                fw.write("Name:\n" + name.get(count) + "\nPassword:\n" + password.get(count) + "\nPoints:\n" + points.get(count) + "\n");            
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
        return points;
    }
    
    @Override 
    public ArrayList<String> getPassword(){
        return password;
    }
}
