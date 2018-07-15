/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import common.CheckData;
import java.util.Scanner;

/**
 *
 * @author DUNG NV
 */
public class SanPham {

    private String ID;
    private String Name;
    private String Count;
    private String price;
    CheckData check = new CheckData();

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCount() {
        return Count;
    }

    public void setCount( String Count) {
        this.Count = Count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public SanPham() {
    }

    public SanPham(String ID, String Name, String Count, String price) {
        this.ID = ID;
        this.Name = Name;
        this.Count = Count;
        this.price = price;
    }

    public void Add() {
        Scanner sc = new Scanner(System.in);
        System.out.println("NHẬP VÀO THÔNG TIN SẢN PHẨM :");
        System.out.print("NHẠP MÃ SẢN PHẨM : ");
        setID(sc.nextLine());

        System.out.print("NHẬP TÊN SẢN PHẨM : ");
        setName(sc.nextLine());

        do {
            System.out.print("NHẬP SỐ LƯỢNG : ");
            setCount(sc.nextLine());
        } while (check.checkCount(Count));

        do {
            System.out.print("NHẬP ĐƠN GIÁ : ");
            setPrice(sc.nextLine());
        } while (check.checkPrice(price));

    }
    public void inTTSP() {
        System.out.printf("|%12s|%18s|%13s|%17s|\n", this.ID, check.chuanHoaString(this.Name), this.Count, this.price);
    }

    

    

}
