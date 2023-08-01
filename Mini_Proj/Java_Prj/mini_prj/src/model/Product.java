package model;

import java.util.Date;

public class Product extends Model{
    private int prod_id;
    private String prod_name;
    private int stock;
    private Date manf_date;
    private String category;
    private double price;

    public int getProd_id() {
        return prod_id;
    }
    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }
    public String getProd_name() {
        return prod_name;
    }
    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public Date getManf_date() {
        return manf_date;
    }
    public void setManf_date(Date manf_date) {
        this.manf_date = manf_date;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Product(String prod_name, int stock, Date manf_date, String category, double price) {
        this.prod_name = prod_name;
        this.stock = stock;
        this.manf_date = manf_date;
        this.category = category;
        this.price = price;
    }
    public Product(int prod_id,String prod_name, int stock, Date manf_date, String category, double price) {
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.stock = stock;
        this.manf_date = manf_date;
        this.category = category;
        this.price = price;
    }
    public Product() {
        
    }
    public String toString(){
        return "******\nPRODUCT DETAILS :\nID: "+prod_id+"\nName: "+prod_name+"\nCategory: "+category+"\nManufactured on: "+manf_date+"\nStock: "+stock+"\nPrice: $"+price+"\n";
    }
}