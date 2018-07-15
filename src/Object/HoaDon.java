/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

/**
 *
 * @author DUNG NV
 */
public class HoaDon {

    private String ID_Custormer;
    private String ID_Employee;
    private String ID_Product;
    private double count;
    private double price;
    private double money;
    private String Day;

    public String getID_Custormer() {
        return ID_Custormer;
    }

    public void setID_Custormer(String ID_Custormer) {
        this.ID_Custormer = ID_Custormer;
    }

    public String getID_Employee() {
        return ID_Employee;
    }

    public void setID_Employee(String ID_Employee) {
        this.ID_Employee = ID_Employee;
    }

    public String getID_Product() {
        return ID_Product;
    }

    public void setID_Product(String ID_Product) {
        this.ID_Product = ID_Product;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String Day) {
        this.Day = Day;
    }

    public HoaDon() {
    }

    public HoaDon(String ID_Custormer, String ID_Employee, String ID_Product, double count, double price, double money, String Day) {
        this.ID_Custormer = ID_Custormer;
        this.ID_Employee = ID_Employee;
        this.ID_Product = ID_Product;
        this.count = count;
        this.price = price;
        this.money = money;
        this.Day = Day;
    }
    public void inTTHD(){
        System.out.printf("|%11s|%11s|%11s|%12f|%12f|%12f|%15s|",this.ID_Custormer,this.ID_Employee,this.ID_Product,this.count,this.money,this.price,this.Day);
    }

}
