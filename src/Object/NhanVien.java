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
public class NhanVien {

    private String id;
    private String name;
    private String phone;
    private String email;
    private String password;
    private String address;

    CheckData check = new CheckData();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public NhanVien() {
    }

    public NhanVien(String id, String name, String phone, String email, String password, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public void Add() {

        Scanner sc = new Scanner(System.in);
        System.out.println("NHẬP VÀO THÔNG TIN NHÂN VIÊN :");
        System.out.print("NHẬP MÃ NHÂN VIÊN :");
        setId(sc.nextLine());
        do {
            System.out.print("NHẬP TÊN  :");
            setName(sc.nextLine());
            if (check.kiemTraTen(this.name)) {
                System.err.println("TÊN KHÔNG ĐƯỢC CHỨA SỐ !!! ");
            }
        } while (check.kiemTraTen(this.name));

        do {
            System.out.print("NHẬP SỐ ĐIỆN THOẠI :");
            setPhone(sc.nextLine());
        } while (check.checkNumberPhone(this.phone));

        do {
            System.out.print("NHẬP EMAIL:");
            setEmail(sc.nextLine());
        } while (check.kiemTraEmail(this.email));

        System.out.print("NHẬP PASSWORD :");
        setPassword(check.md5(sc.nextLine()));
        //this.password = check.md5(this.password);

        System.out.print("NHẬP ADDRESS:");
        setAddress(sc.nextLine());
    }

    public void inTTNV() {

        System.out.printf("|%12s|%15s|%13s|%27s|%15s|\n", this.id, check.chuanHoaString(this.name), this.phone, this.email, check.chuanHoaString(this.address));
    }
}
