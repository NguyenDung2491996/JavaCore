/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import Object.KhachHang;
import common.CheckData;
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
public class KhachHangApp {

    private ArrayList<KhachHang> listKH = new ArrayList<>();
    CheckData check = new CheckData();

    public void savefile() {
        try {
            Writer w = new FileWriter("khachhang.txt");
            BufferedWriter bw = new BufferedWriter(w);
            String data = "";
            for (int i = 0; i < listKH.size(); i++) {
                String row = "";
                row += listKH.get(i).getId() + "\t";
                row += listKH.get(i).getName() + "\t";
                row += listKH.get(i).getPhone() + "\t";
                row += listKH.get(i).getEmail() + "\t";
                row += listKH.get(i).getaddress() + "\n";
                data += row;
            }
            bw.write(data);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(KhachHangApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadfile() {
        try {

            FileReader fr = new FileReader("khachhang.txt");
            BufferedReader br = new BufferedReader(fr);
            listKH = new ArrayList<>();
            String s = null;
            while ((s = br.readLine()) != null) {
                KhachHang kh = new KhachHang();
                String arr[] = s.split("\t");
                kh.setId(arr[0]);
                kh.setName(arr[1]);
                kh.setPhone(arr[2]);
                kh.setEmail(arr[3]);
                kh.setAddress(arr[4]);
                listKH.add(kh);
            }
        } catch (IOException ex) {
            Logger.getLogger(KhachHangApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showAll() {
        loadfile();
        int dem = 0;
        if (listKH.isEmpty() == true) {
            System.err.println("DANH SÁCH RỖNG !!!");
            dem++;
        }
        if (dem == 0) {
            System.out.println("+------------------------------------------------------------------------------------+");
            System.out.println("|                     THÔNG TIN KHÁCH HÀNG                                           |");
            System.out.println("+------------+---------------+-------------+---------------------------+-------------|");
            System.out.println("|   ID       |    NAME       |   PHONE     |       EMAIL               |   ADDRESS   |");
            for (int i = 0; i < listKH.size(); i++) {
                listKH.get(i).inTTKH();
            }
            System.out.println("|------------------------------------------------------------------------------------|");

        }
        savefile();

    }
    int index = 0;
    KhachHang kh = new KhachHang();

    public boolean search() {

        Scanner sc = new Scanner(System.in);
        System.out.println("NHẬP VÀO MÃ KHÁCH HÀNG CODE:");
        String code = sc.nextLine();

        for (int i = 0; i < listKH.size(); i++) {
            kh = listKH.get(i);
            if (code.equalsIgnoreCase(kh.getId())) {
                index = i;
                return true;

            }
        }
        return false;
    }
    public boolean timkiem(String maKH){
        loadfile();
        for (int i = 0; i < listKH.size(); i++) {
            kh= listKH.get(i);
            if (maKH.equalsIgnoreCase(kh.getId())) {
               loadfile();
               kh.inTTKH();
               return true;
            }
        }
        return false;
    }

    public void delete() {
        loadfile();

        if (search() == true) {
            listKH.remove(kh);
            savefile();
            showAll();

            System.err.println("XÓA THÔNG TIN KHÁCH HÀNG THÀNH CÔNG !!!");
        } else {
            System.err.println("XÓA THẤT BẠI !!!");
            savefile();
            showAll();

        }

    }

    public boolean checkExist(String id) {
        boolean flag = false;
        for (int i = 0; i < listKH.size(); i++) {
            kh = listKH.get(i);
            if (id.equalsIgnoreCase(kh.getId())) {
                flag = true;
                index = i;
                break;
            }
        }
        return flag;
    }

    public void addCustomer() {
        loadfile();
        KhachHang kh = new KhachHang();
        kh.Add();
        if (!checkExist(kh.getId())) {
            listKH.add(kh);
            savefile();
            showAll();
        } else {
            System.out.println("MÃ KHÁCH HÀNG ĐÃ TỒN TẠI !!!");
            showAll();
        }
    }

    public void edit() {
        loadfile();

        Scanner sc = new Scanner(System.in);
        if (search() == true) {
            System.out.println("CẬP NHẬT THÔNG TIN KHÁCH HÀNG !!!");
            System.out.println("NAME CŨ:" + kh.getName());
            String nameCu = kh.getName();
            do {
                System.out.print("NHẬP NAME MỚI:");
                kh.setName(sc.nextLine());
                if ("".equalsIgnoreCase(kh.getName())) {
                    kh.setName(nameCu);
                }

            } while (check.kiemTraName(kh.getName()));

            System.out.println("PHONE CŨ:" + kh.getPhone());
            String phoneCu = kh.getPhone();
            do {
                System.out.print("NHẬP PHONE MỚI:");
                kh.setPhone(sc.nextLine());
                if ("".equalsIgnoreCase(kh.getPhone())) {
                    kh.setPhone(phoneCu);
                }
            } while (check.checkNumberPhoneNumber(kh.getPhone()));
            System.out.println("MAIL CŨ:" + kh.getEmail());
            String mailcu = kh.getEmail();
            do {
                System.out.print("NHẬP MAIL MỚI:");
                kh.setEmail(sc.nextLine());
                if ("".equalsIgnoreCase(kh.getEmail())) {
                    kh.setEmail(mailcu);
                }

            } while (check.checkEmail(kh.getEmail()));

            System.out.println("ADDRESS CŨ:" + kh.getaddress());
            String addresscu = kh.getaddress();
            System.out.print("NHẬP ADDRESS MỚI :");
            kh.setAddress(sc.nextLine());
            if ("".equalsIgnoreCase(kh.getaddress())) {
                kh.setAddress(addresscu);
            }
            listKH.set(index, kh);
            savefile();
            showAll();

        } else {
            System.out.println("KHÔNG TÌM THẤY KHÁCH HÀNG !!!  ");
        }
    }

    public void find() {
        loadfile();
        int k = 0;
        String code;
        Scanner sc = new Scanner(System.in);
        System.out.println("NHẬP VÀO MÃ BẤT KÌ:");
        code = sc.nextLine();
        KhachHang kh = new KhachHang();
        System.out.println("+------------+---------------+-------------+---------------------------+-------------|");
        System.out.println("|   ID       |    NAME       |   PHONE     |       EMAIL               |   ADDRESS   |");
        for (int i = 0; i < listKH.size(); i++) {
            if (listKH.get(i).getId().contains(code) || listKH.get(i).getPhone().contains(code) || listKH.get(i).getEmail().contains(code)) {
                kh = listKH.get(i);
                kh.inTTKH();
                k++;
            }
        }
        System.out.println("|-------------------------------------------------------------------------------------|");
        if (k == 0) {
            System.out.println("KHÔNG TÌM THẤY GÌ");
        }
    }

    public void findID() {
        loadfile();
        int find = 0;
        String maKH = null;
        System.err.println("KẾT QUẢ TÌM KIẾM KHÁCH HÀNG THEO ID .CHỈ IN RA BẢNG KHÔNG TỨC LÀ KHÔNG TÌM THẤY  )");
        Scanner sc = new Scanner(System.in);
        System.out.print("NHẬP VÀO MÃ KHÁCH HÀNG:");
        maKH = sc.nextLine();

        KhachHang kh = new KhachHang();

        System.out.println("+------------+---------------+-------------+---------------------------+-------------|");
        System.out.println("|   ID       |    NAME       |   PHONE     |       EMAIL               |   ADDRESS   |");
        for (int i = 0; i < listKH.size(); i++) {
            kh = listKH.get(i);

            if (maKH.equalsIgnoreCase(kh.getId())) {

                kh.inTTKH();

                find++;
            }

        }
        System.out.println("|------------------------------------------------------------------------------------|");

    }

    public void findEmail() {
        loadfile();
        int fin = 0;
        String mail;
        Scanner sc = new Scanner(System.in);
        System.err.println(" KẾT QUẢ TÌM KIẾM KHÁCH HÀNG THEO MAIL .CHỈ IN RA BẢNG KHÔNG TỨC LÀ KHÔNG TÌM THẤY ");
        do {
            System.out.print("NHẬP EMAIL:");
            mail = sc.nextLine();
        } while (check.kiemTraEmail(mail) == true);

        System.out.println("+------------+---------------+-------------+---------------------------+-------------|");
        System.out.println("|   ID       |    NAME       |   PHONE     |       EMAIL               |   ADDRESS   |");
        KhachHang kh = new KhachHang();
        for (int i = 0; i < listKH.size(); i++) {
            kh = listKH.get(i);
            if (mail.equalsIgnoreCase(kh.getEmail())) {
                kh.inTTKH();
                fin++;
            }
        }

        System.out.println("+------------------------------------------------------------------------------------+");

    }

    public KhachHang search(String id) {
        KhachHang kh = new KhachHang();
        for (int i = 0; i < listKH.size(); i++) {
            if (id.equalsIgnoreCase(listKH.get(i).getId())) {
                kh = listKH.get(i);
                return kh;
            }

        }
        return null;
    }

    public void findPhone() {
        loadfile();
        int fix = 0;
        String phone;
        Scanner sc = new Scanner(System.in);
        System.err.println("KẾT QUẢ TÌM KIẾM KHÁCH HÀNG THEO PHONE .CHỈ IN RA BẢNG KHÔNG TỨC LÀ KHÔNG TÌM THẤY ");
        do {
            System.out.print("NHẬP PHONE:");
            phone = sc.nextLine();
        } while (check.checkNumberPhone(phone));

        System.out.println("+------------+---------------+-------------+---------------------------+-------------|");
        System.out.println("|   ID       |    NAME       |   PHONE     |       EMAIL               |  ADDRESS   |");
        KhachHang kh = new KhachHang();
        for (int i = 0; i < listKH.size(); i++) {
            kh = listKH.get(i);
            if (phone.equalsIgnoreCase(kh.getPhone())) {
                kh.inTTKH();
            }
        }
        System.out.println("|------------------------------------------------------------------------------------|");

    }

    public void menu() {
        int use;

        do {

            System.out.println("+--------------------------------------------------+");
            System.out.println("|        QUẢN LÝ THÔNG TIN KHÁCH HÀNG              |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    1.  XEM DANH SÁCH TOÀN BỘ KHÁCH HÀNG          |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    2.  TÌM KIẾM THEO MÃ KHÁCH HÀNG               |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    3.  TÌM KIẾM THEO MAIL                        |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    4.  TÌM KIẾM THEO PHONE                       |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    5.  THÊM MỚI MỘT KHÁCH HÀNG                   |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    6.  SỬA THÔNG TIN KHÁCH HÀNG                  |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    7.  XÓA THÔNG TIN KHÁCH HÀNG                  |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    8.  TÌM KIẾM                                  |");
            System.out.println("+--------------------------------------------------|");
            System.out.println("|    9.  THOÁT                                     |");
            System.out.println("+--------------------------------------------------+");

            System.err.print("MỜI BẠN CHỌN CÁC CHỨC NĂNG TRONG QUẢN LÝ KHÁCH HÀNG !!! ");
            Scanner sc = new Scanner(System.in);
            use = sc.nextInt();
            switch (use) {
                case 1: {

                    showAll();
                    break;
                }
                case 2: {
                    findID();
                    break;
                }
                case 3: {
                    findEmail();
                    break;
                }
                case 4: {
                    findPhone();
                    break;
                }
                case 5: {

                    addCustomer();
                    break;
                }
                case 6: {
                    edit();
                    break;
                }
                case 7: {
                    delete();
                    break;
                }
                case 8: {
                    find();
                    break;
                }

                default: {
                    savefile();
                }
            }
        } while (use == 1 || use == 2 || use == 3 || use == 4 || use == 5 || use == 6 || use == 7 || use == 8);

    }
}
