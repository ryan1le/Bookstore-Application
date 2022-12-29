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
public class Purchase {
    
    Database data;
    
    public double calculate(String name){
        data = BooksData.getInstance();
        int index = data.getName().indexOf(name);
        return (data.getNumericValue().get(index));
    }
    
    public void pointsUpdate(double totalPrice, String name, int method){
        if (method == 0){
            data = CustomerData.getInstance();
            int index = data.getName().indexOf(name);
            double total = data.getNumericValue().get(index) + totalPrice*10.0;
            data.getNumericValue().set(index, total); 
        }
        else if (method == 1){
            data = CustomerData.getInstance();
            int index = data.getName().indexOf(name);
            data.getNumericValue().set(index, totalPrice*10.0);
        }
        else if (method == -1){
            data = CustomerData.getInstance();
            int index = data.getName().indexOf(name);
            data.getNumericValue().set(index, totalPrice);
        }
        
    }
    
    public double calculatewithPoints(double points, double totalPrice, String name){
        double sum = points/100.0;
        
        if (sum >= totalPrice){
            sum -=totalPrice;
            sum *= 100.0;
            pointsUpdate(sum, name, -1);
            return 0.0;
        }
        else{
            totalPrice -= sum;
            pointsUpdate(totalPrice, name, 1);
            return totalPrice;
        }
    }
    
}
