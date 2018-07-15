/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import Object.NhanVien;
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
public class NhanVienApp {

    private ArrayList<NhanVien> listNV = new ArrayList<>();
    CheckData check = new CheckData();

    public void savefile() {

        try {
            Writer w = new FileWriter("nhanvien.txt");
            BufferedWriter bw = new BufferedWriter(w);
            String data = "";
            for (int i = 0; i < listNV.size(); i++) {
                String row = "";
                row += listNV.get(i).getId() + "\t";
                row += listNV.get(i).getName() + "\t";
                row += listNV.get(i).getPhone() + "\t";
                row += listNV.get(i).getEmail() + "\t";
                row += listNV.get(i).getPassword() + "\t";
                row += listNV.get(i).getAddress() + "\n";
                data += row;
            }
            bw.write(data);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(NhanVienApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadfile() {
        try {

            FileReader fr = new FileReader("nhanvien.txt");
            BufferedReader br = new BufferedReader(fr);
            listNV = new ArrayList<>();
            String s = null;
            while ((s = br.readLine()) != null) {
                NhanVien nv = new NhanVien();
                String arr[] = s.split("\t");
                nv.setId(arr[0]);
                nv.setName(arr[1]);
                nv.setPhone(arr[2]);
                nv.setEmail(arr[3]);
                nv.setPassword(arr[4]);
                nv.setAddress(arr[5]);
                listNV.add(nv);
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(NhanVienApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showAll() {
        loadfile();
        int dem = 0;
        if (listNV.isEmpty() == true) {
            System.err.println("DANH SÁCH RỖNG.");
            dem++;
        }
        if (dem == 0) {
            System.out.println("+--------------------------------------------------------------------------------------+");
            System.out.println("|                           THÔNG TIN NHÂN VIÊN                                        |");
            System.out.println("+------------+---------------+-------------+---------------------------+---------------|");
            System.out.println("|   ID       |    NAME       |   PHONE     |       EMAIL               |     ADRESSS   |");
            for (int i = 0; i < listNV.size(); i++) {
                listNV.get(i).inTTNV();
            }
            System.out.println("|--------------------------------------------------------------------------------------|");

        }
        savefile();

    }
    int index = 0;
    NhanVien nv = new NhanVien();

    public boolean search() {

        Scanner sc = new Scanner(System.in);
        System.out.print("NHẬP VÀO MÃ NHÂN VIÊN CODE :");
        String code = sc.nextLine();

        for (int i = 0; i < listNV.size(); i++) {
            nv = listNV.get(i);
            if (code.equalsIgnoreCase(nv.getId())) {
                index = i;
                return true;

            }
        }
        return false;
    }

    public boolean searchID(String maNV) {
        for (int i = 0; i < listNV.size(); i++) {
            if (maNV.equalsIgnoreCase(maNV)) {
                return true;
            }
        }
        return false;
    }

    public void delete() {
        loadfile();

        if (search() == true) {
            listNV.remove(nv);
            savefile();
            showAll();

            System.err.println("XÓA NHÂN VIÊN THÀNH CÔNG!!!");
        } else {
            System.err.println("XÓA THẤT BẠI");
            savefile();
            showAll();

        }

    }

    public boolean checkExist(String id) {
        boolean flag = false;
        for (int i = 0; i < listNV.size(); i++) {
            nv = listNV.get(i);
            if (id.equalsIgnoreCase(nv.getId())) {
                flag = true;
                index = i;
                break;
            }
        }
        return flag;
    }

    public void addEmplyee() {
        loadfile();
        NhanVien nv = new NhanVien();
        nv.Add();
        if (!checkExist(nv.getId())) {
            listNV.add(nv);
            savefile();
            showAll();
        } else {
            System.out.println("MÃ NHÂN VIÊN ĐÃ TỒN TẠI!!!");
        }
    }

    public void edit() {
        loadfile();

        Scanner sc = new Scanner(System.in);
        if (search() == true) {
            System.out.println("CẬP NHẬT THÔNG TIN NHÂN VIÊN ");
            System.out.println("NAME CŨ:" + nv.getName());
            String tenCu = nv.getName();
            do {
                System.out.print("NHẬP NAME MỚI:");
                nv.setName(sc.nextLine());
                if ("".equalsIgnoreCase(nv.getName())) {
                    nv.setName(tenCu);
                }
            } while (check.kiemTraName(nv.getName()));
            System.out.println("PHONE CŨ:" + nv.getPhone());
            String sodt = nv.getPhone();
            do {
                System.out.print("NHẬP PHONE MỚI:");
                nv.setPhone(sc.nextLine());
                if ("".equalsIgnoreCase(nv.getPhone())) {
                    nv.setPhone(sodt);
                }
            } while (check.checkNumberPhoneNumber(nv.getPhone()));
            System.out.println("MAIL CŨ:" + nv.getEmail());
            String mail = nv.getEmail();
            do {
                System.out.print("NHẬP MAIL MỚI:");
                nv.setEmail(sc.nextLine());
                if ("".equalsIgnoreCase(nv.getEmail())) {
                    nv.setEmail(mail);
                }
            } while (check.checkEmail(nv.getEmail()));

            System.out.println("PASS CŨ:" + nv.getPassword());
            String pass = nv.getPassword();
            
            System.out.print("NHẬP PASS MỚI:");
            nv.setPassword((check.md5(sc.nextLine())));
            
            
            
            
            
            if (check.md5("").equalsIgnoreCase(nv.getPassword())) {
                nv.setPassword(pass);
            }
            
            
            System.out.println("ADDRESS CŨ:" + nv.getAddress());
            String diachi = nv.getAddress();
            System.out.print("NHẬP ADDRESS MỚI :");
            
            nv.setAddress(sc.nextLine());
            if ("".equalsIgnoreCase(nv.getAddress())) {
                nv.setAddress(diachi);
            }
            listNV.set(index, nv);
            savefile();
            showAll();

        } else {
            System.out.println("KHÔNG TÌM THẤY NHÂN VIÊN !!!  ");
        }
    }

    public void findID() {
        loadfile();
        int find = 0;
        String maNV = null;
        System.err.println("KẾT QUẢ TÌM KIẾM NHÂN VIÊN THEO ID ." + "(CHỈ IN RA BẢNG KHÔNG TỨC LÀ KHÔNG TÌM THẤY )");
        Scanner sc = new Scanner(System.in);
        System.out.print("NHẬP VÀO MÃ NHÂN VIÊN :");
        maNV = sc.nextLine();

        NhanVien nv = new NhanVien();

        System.out.println("+------------+---------------+-------------+---------------------------+---------------|");
        System.out.println("|   ID       |    NAME       |   PHONE     |       EMAIL               |     ADRESSS   |");
        for (int i = 0; i < listNV.size(); i++) {
            nv = listNV.get(i);

            if (maNV.equalsIgnoreCase(nv.getId())) {

                nv.inTTNV();

                find++;
            }

        }
        System.out.println("|--------------------------------------------------------------------------------------|");

    }

    public void findEmail() {
        loadfile();
        int fin = 0;
        String mail;
        Scanner sc = new Scanner(System.in);
        System.err.println("KẾT QUẢ TÌM KIẾM NHÂN VIÊN THEO MAIL .CHỈ IN RA BẢNG KHÔNG TỨC LÀ KHÔNG TÌM THẤY ) ");
        do {
            System.out.print("NHẬP EMAIL:");
            mail = sc.nextLine();
        } while (check.kiemTraEmail(mail) == true);

        System.out.println("+------------+---------------+-------------+---------------------------+---------------+");
        System.out.println("|   ID       |    NAME       |   PHONE     |       EMAIL               |    ADDRESS    |");
        NhanVien nv = new NhanVien();
        for (int i = 0; i < listNV.size(); i++) {
            nv = listNV.get(i);
            if (mail.equalsIgnoreCase(nv.getEmail())) {
                nv.inTTNV();
                fin++;
            }
        }

        System.out.println("+--------------------------------------------------------------------------------------|");
    }

    public void findPhone() {
        loadfile();
        int fix = 0;
        String phone;
        Scanner sc = new Scanner(System.in);
        System.err.println("KẾT QUẢ TÌM KIẾM NHÂN VIÊN THEO PHONE.CHỈ IN RA BẢNG KHÔNG TỨC LÀ KHÔNG TÌM THẤY ");
        do {
            System.out.print("NHẬP PHONE:");
            phone = sc.nextLine();
        } while (check.checkNumberPhone(phone));

        System.out.println("+------------+---------------+-------------+---------------------------+---------------|");
        System.out.println("|   ID       |    NAME       |   PHONE     |       EMAIL               |    ADDRESS    |");
        NhanVien nv = new NhanVien();
        for (int i = 0; i < listNV.size(); i++) {
            nv = listNV.get(i);
            if (phone.equalsIgnoreCase(nv.getPhone())) {
                nv.inTTNV();
            }
        }
        System.out.println("|--------------------------------------------------------------------------------------|");

    }

    public boolean login(String id, String pass) {
        loadfile();
        int k = 0;

        do {

            NhanVien nv = new NhanVien();
            for (int i = 0; i < listNV.size(); i++) {
                nv = listNV.get(i);
                //System.out.println(check.md5(pass));
                if (id.equalsIgnoreCase(nv.getId()) && (check.md5(pass)).equalsIgnoreCase((nv.getPassword()))) {
                    System.err.print("ĐĂNG NHẬP THÀNH CÔNG !!! \n");
                    k++;
                    return true;
                }
            }
            if (k == 0) {
                System.err.println("THÔNG TIN NHẬP VÀO KHÔNG HỢP LỆ NHẬP LẠI!!!");
                return false;
            }

        } while (id.equalsIgnoreCase(nv.getId()) && check.md5(pass).equalsIgnoreCase(nv.getPassword()) == false);
        return true;
    }

    public void menu() {
        int use;

        do {
            System.out.println("+--------------------------------------------------+");
            System.out.println("|        QUẢN LÝ THÔNG TIN NHÂN VIÊN               |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    1.  XEM DANH SÁCH TOÀN BỘ NHÂN VIÊN           |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    2.  TÌM KIẾM THEO MÃ NHÂN VIÊN                |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    3.  TÌM KIẾM THEO MAIL                        |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    4.  TÌM KIẾM THEO PHONE                       |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    5.  THÊM MỚI NHÂN VIÊN                        |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    6.  SỬA THÔNG TIN NHÂN VIÊN                   |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    7.  XÓA THÔNG TIN NHÂN VIÊN                   |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    8.  THOÁT                                     |");
            System.out.println("+--------------------------------------------------+");
            System.err.print("MỜI BẠN CHỌN CÁC CHỨC NĂNG TRONG QUẢN LÝ NHÂN VIÊN!!! ");
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

                    addEmplyee();
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

                default: {
                    savefile();
                }
            }
        } while (use == 1 || use == 2 || use == 3 || use == 4 || use == 5 || use == 6 || use == 7);

    }
}
