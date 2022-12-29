/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co528_project;

import javafx.scene.control.*;

/**
 *
 * @author Harish
 */
public class TableBookInfo 
{
    private String bookName;
    private double bookPrice;
    private CheckBox select;

    public TableBookInfo(String listName, double listPrice, CheckBox select) {
        this.bookName = listName;
        this.bookPrice = listPrice;
        this.select = select;
    }

    public String getListName() {
        return bookName;
    }

    public void setListName(String listName) {
        this.bookName = listName;
    }

    public double getListPrice() {
        return bookPrice;
    }

    public void setListPrice(double listPrice) {
        this.bookPrice = listPrice;
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }
    
    

}
