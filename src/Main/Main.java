/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import app.KhachHangApp;
import app.NhanVienApp;
import app.QuanLyBanHangApp;
import app.SanPhamApp;
import java.util.Scanner;

/**
 *
 * @author DUNG NV
 */
public class Main {

    public void login() {
        Scanner sc = new Scanner(System.in);
        String pass;
        String id;
        NhanVienApp nvp = new NhanVienApp();
        System.out.println("ĐĂNG NHẬP VÀO HỆ THỐNG ");
        do {

            System.out.print("NHẬP ID:");
            id = sc.nextLine();
            System.out.print("NHẬP PASS:");
            pass = sc.nextLine();
        } while (!nvp.login(id, pass));
    }

    public void menu() {
        int fun;
        do {

            System.out.println("MỜI BẠN CHỌN CÁC CHỨC NĂNG TRONG HỆ THỐNG QUẢN LÝ BÁN HÀNG !!!");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|        HỆ THỐNG QUẢN LÝ BÁN HÀNG                 |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    1.  QUẢN LÝ KHÁCH HÀNG                        |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    2.  QUẢN LÝ NHÂN VIÊN                         |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    3.  QUẢN LÝ SẢN PHẨM                          |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    4.  ĐĂNG XUẤT                                 |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    5.  THOÁT                                     |");
            System.out.println("+--------------------------------------------------+");

            Scanner sc = new Scanner(System.in);
            fun = sc.nextInt();

            switch (fun) {
                case 1: {
                    KhachHangApp khapp = new KhachHangApp();

                    khapp.menu();
                    break;

                }
                case 2: {
                    NhanVienApp nvapp = new NhanVienApp();
                    nvapp.menu();
                    break;
                }
                case 3: {
                    SanPhamApp sapp = new SanPhamApp();
                    sapp.menu();
                    break;
                }
                case 4: {
                    Main m = new Main();
                    m.login();
                    m.menu();
                    break;

                }
                case 5: {
                    QuanLyBanHangApp ql = new QuanLyBanHangApp();
                    ql.menu();
                    break;
                }
                
                default: {
                    System.out.println("BẠN PHẢI NHẬP SỐ  1 2 3 HOẶC 4 5 ");
                }
            }
        } while (fun == 1 || fun == 2 || fun == 3 || fun == 4 || fun == 5);

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Main m = new Main();

        m.login();
        m.menu();
    }
}
