/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import Object.HoaDon;
import Object.KhachHang;
import Object.SanPham;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DUNG NV
 */
public class QuanLyBanHangApp {

    private ArrayList<HoaDon> listhd = new ArrayList<>();
    KhachHang kh = new KhachHang();
    SanPham sp = new SanPham();
    KhachHangApp khapp = new KhachHangApp();
    NhanVienApp nvapp = new NhanVienApp();
    SanPhamApp sapp = new SanPhamApp();

    public void BanHang() {
        
        Scanner sc = new Scanner(System.in);

        System.out.println("NHẬP VÀO MÃ KHÁCH HÀNG:");
        String maKH = sc.nextLine();
        
        if (khapp.timkiem(maKH)==false) {
            
            System.out.println("KHÔNG TÌM THẤY KHÁCH HÀNG");
            khapp.loadfile();
            khapp.addCustomer();
           
            kh.inTTKH();

        } else {
            khapp.loadfile();
            kh.inTTKH();

        }

        String maNV;
        do {
            System.out.println("NHẬP VÀO MÃ NHÂN VIÊN:");
            maNV = sc.nextLine();
        } while (!nvapp.searchID(maNV));

        String maSP;

        do {
            System.out.println("NHẬP VÀO MÃ SẢN PHẨM:");
            maSP = sc.nextLine();

            sp = sapp.search(maSP);
            if (!sp.getID().equalsIgnoreCase(maSP)) {
                System.out.println("KHÔNG TÌM THẤY SẢN PHẨM");
            }
        } while (!sp.getID().equalsIgnoreCase(maSP));
        sapp.loadfile();
        sp.inTTSP();
        double soLuongMua;

        do {
            System.out.println("NHẬP VÀO SỐ LƯỢNG MUA:");
            soLuongMua = sc.nextDouble();

        } while (!sapp.checkCount(soLuongMua));

        System.out.println("NHẬP NGÀY BÁN :");
        String day = sc.nextLine();

        double donGia = Double.parseDouble(sp.getPrice());
        double thanhTien = soLuongMua * Double.parseDouble(sp.getPrice());
        HoaDon hd = new HoaDon(kh.getId(), maNV, sp.getID(), soLuongMua, donGia, thanhTien, day);
        listhd.add(hd);
        sapp.loadfile();
        sapp.updateSanPham(maSP, soLuongMua);
        sapp.savefile();
    }

    public void savefile() {
        try {
            Writer w = new FileWriter("hoadon.txt");
            BufferedWriter bw = new BufferedWriter(w);
            String data = "";
            for (int i = 0; i < listhd.size(); i++) {
                String row = "";
                row += listhd.get(i).getID_Custormer() + "\t";
                row += listhd.get(i).getID_Employee() + "\t";
                row += listhd.get(i).getID_Product() + "\t";
                row += listhd.get(i).getCount() + "\t";
                row += listhd.get(i).getMoney() + "\t";
                row += listhd.get(i).getPrice() + "\t";
                row += listhd.get(i).getDay() + "\n";

                data += row;
            }
            bw.write(data);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(QuanLyBanHangApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadfile() {
        try {

            FileReader fr = new FileReader("hoadon.txt");
            BufferedReader br = new BufferedReader(fr);
            listhd = new ArrayList<>();
            String s = null;
            while ((s = br.readLine()) != null) {
                HoaDon hd = new HoaDon();
                String arr[] = s.split("\t");
                hd.setID_Custormer(arr[0]);
                hd.setID_Employee(arr[1]);
                hd.setID_Product(arr[2]);
                hd.setCount(Double.parseDouble(arr[3]));
                hd.setMoney(Double.parseDouble(arr[4]));
                hd.setPrice(Double.parseDouble(arr[5]));
                hd.setDay(arr[6]);
                listhd.add(hd);
            }
        } catch (IOException ex) {
            Logger.getLogger(QuanLyBanHangApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showAll() {
        loadfile();
        int dem = 0;
        if (listhd.isEmpty() == true) {
            System.err.println("DANH SÁCH RỖNG !!!");
            dem++;
        }
        if (dem == 0) {
            System.out.println("+------------------------------------------------------------------------------------------+");
            System.out.println("|                     THÔNG TIN HÓA ĐƠN                                                    |");
            System.out.println("+-----------+-----------+-----------+------------+------------+------------+---------------|");
            System.out.println("|  MÃ KH    |  MÃ NV    |   MÃ SP   |  SỐ LƯỢNG  |  ĐƠN GIÁ   |   TIỀN     |   NGÀY BÁN    |");
            for (int i = 0; i < listhd.size(); i++) {
                listhd.get(i).inTTHD();
            }
            System.out.println("|------------------------------------------------------------------------------------------|");

        }
        savefile();

    }

    public void menu() {
        int use;

        do {

            System.out.println("+--------------------------------------------------+");
            System.out.println("|        QUẢN LÝ THÔNG TIN BÁN HÀNG                |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    1.  BÁN HÀNG                                  |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    2.  THANH TOÁN                                |");
            System.out.println("+--------------------------------------------------+");
            System.err.print("MỜI BẠN CHỌN CÁC CHỨC NĂNG TRONG HỆ THỐNG QUẢN LÝ BÁN HÀNG !!!");
            Scanner sc = new Scanner(System.in);
            use = sc.nextInt();
            switch (use) {
                case 1: {
                    savefile();
                    loadfile();
                    BanHang();
                    showAll();
                    break;
                }
                case 2: {
                    System.exit(0);
                    break;
                }

                default: {
                    savefile();
                }
            }
        } while (use == 1 || use == 2);
    }

}
