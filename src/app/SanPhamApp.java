/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import Object.SanPham;
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
public class SanPhamApp {

    private ArrayList<SanPham> listSP = new ArrayList<>();
    CheckData check = new CheckData();

    public void savefile() {

        try {
            Writer w = new FileWriter("sanpham.txt");
            BufferedWriter bw = new BufferedWriter(w);
            String data = "";
            for (int i = 0; i < listSP.size(); i++) {
                String row = "";
                row += listSP.get(i).getID() + "\t";
                row += listSP.get(i).getName() + "\t";
                row += listSP.get(i).getCount() + "\t";
                row += listSP.get(i).getPrice() + "\n";

                data += row;
            }
            bw.write(data);
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(SanPhamApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadfile() {
        try {

            FileReader fr = new FileReader("sanpham.txt");
            BufferedReader br = new BufferedReader(fr);
            listSP = new ArrayList<>();
            String s = null;
            while ((s = br.readLine()) != null) {
                SanPham sp = new SanPham();
                String arr[] = s.split("\t");
                sp.setID(arr[0]);
                sp.setName(arr[1]);
                sp.setCount((arr[2]));
                sp.setPrice(arr[3]);

                listSP.add(sp);
            }
        } catch (IOException ex) {
            Logger.getLogger(SanPhamApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void showAll() {
        loadfile();
        int dem = 0;
        if (listSP.isEmpty() == true) {
            System.err.println("DANH SÁCH RỖNG !!!");
            dem++;
        }
        if (dem == 0) {
            System.out.println("+---------------------------------------------------------------+");
            System.out.println("|                     THÔNG TIN SẢN PHẨM                        |");
            System.out.println("+------------+------------------+-------------+-----------------|");
            System.out.println("|   ID       |    NAME          |   COUNT     |    PRICE        |");
            for (int i = 0; i < listSP.size(); i++) {
                listSP.get(i).inTTSP();
            }
            System.out.println("|---------------------------------------------------------------|");

        }
        savefile();

    }
    int index = 0;
    SanPham sp = new SanPham();

    public boolean search() {

        Scanner sc = new Scanner(System.in);
        System.out.print("NHẬP VÀO MÃ SẢN PHẨM CODE :");
        String code = sc.nextLine();

        for (int i = 0; i < listSP.size(); i++) {
            sp = listSP.get(i);
            if (code.equalsIgnoreCase(sp.getID())) {
                index = i;
                return true;

            }
        }
        return false;
    }

    public void delete() {
        loadfile();

        if (search() == true) {
            listSP.remove(sp);
            savefile();
            showAll();

            System.err.println("XÓA THÔNG TIN SẢN PHẨM THÀNH CÔNG !!!");
        } else {
            System.err.println("XÓA THÔNG TIN KHÔNG THÀNH CÔNG !!!");
            savefile();
            showAll();

        }

    }

    public boolean checkExist(String id) {
        boolean flag = false;
        for (int i = 0; i < listSP.size(); i++) {
            sp = listSP.get(i);
            if (id.equalsIgnoreCase(sp.getID())) {
                flag = true;
                index = i;
                break;
            }
        }
        return flag;
    }

    public void addProduct() {
        loadfile();
        SanPham sp = new SanPham();
        sp.Add();
        if (!checkExist(sp.getID())) {
            listSP.add(sp);
            savefile();
            showAll();
        } else {
            System.err.println("MÃ SẢN PHẨM ĐÃ TỒN TẠI THÊM SẢN PHẨM KHÔNG THÀNH CÔNG !!!");
        }
    }

    public void edit() {
        loadfile();

        Scanner sc = new Scanner(System.in);
        if (search() == true) {
            System.out.println("CẬP NHẬT THÔNG TIN SẢN PHẨM !!! ");
            System.out.println("NAME CŨ:" + sp.getName());

            System.out.print("NHẬP NAME MỚI:");
            sp.setName(sc.nextLine());

            System.out.println("SỐ LƯỢNG SẢN PHẨM CŨ:" + sp.getCount());
            do {
                System.out.print("NHẬP SỐ LƯỢNG SẢN PHẨM MỚI :");
                sp.setCount(sc.nextLine());
            } while (check.checkCount(sp.getCount()));
            System.out.println("ĐƠN GIÁ CŨ:" + sp.getPrice());
            do {
                System.out.print("NHẬP ĐƠN GIÁ MỚI:");
                sp.setPrice(sc.nextLine());
            } while (check.checkPrice(sp.getPrice()));

            listSP.set(index, sp);
            savefile();
            showAll();

        } else {
            System.err.println("KHÔNG TÌM THẤY SẢN PHẨM !!! ");
        }
    }

    public SanPham search(String Id) {
        SanPham sp = new SanPham();
        for (int i = 0; i < listSP.size(); i++) {
            if (Id.equalsIgnoreCase(listSP.get(i).getID())) {
                sp = listSP.get(i);
                return sp;
            }
        }
        return null;
    }

    public boolean checkCount(double count) {
        SanPham sp = new SanPham();
        for (int i = 0; i < listSP.size(); i++) {
            if (Double.parseDouble(listSP.get(i).getCount()) > count && count > 0) {
                sp = listSP.get(i);
            }
            return true;
        }
        return false;
    }

    public void updateSanPham(String Id, double soLuong) {
        for (int i = 0; i < listSP.size(); i++) {
            if (Id.equalsIgnoreCase(listSP.get(i).getID())) {
                SanPham sp = listSP.get(i);
                double soluongCon = Double.parseDouble(sp.getCount()) - soLuong;
                sp.setCount(String.valueOf(soluongCon));
                listSP.set(i, sp);
            }
        }
    }

    public void findID() {
        loadfile();
        int find = 0;
        String maSP = null;
        System.err.println("KẾT QUẢ TÌM KIẾM SẢN PHẨM THEO ID. CHỈ IN RA BẢNG KHÔNG TỨC LÀ KHÔNG TÌM THẤY ");
        Scanner sc = new Scanner(System.in);
        System.out.print("NHẬP VÀO MÃ SẢN PHẨM :");
        maSP = sc.nextLine();

        SanPham sp = new SanPham();

        System.out.println("+------------+------------------+-------------+-----------------|");
        System.out.println("|   ID       |    NAME          |   COUNT     |    PRICE        |");
        for (int i = 0; i < listSP.size(); i++) {
            sp = listSP.get(i);

            if (maSP.equalsIgnoreCase(sp.getID())) {

                sp.inTTSP();

                find++;
            }

        }
        System.out.println("|---------------------------------------------------------------|");

    }

    public void menu() {
        int use;

        do {

            System.out.println("+--------------------------------------------------+");
            System.out.println("|        QUẢN LÝ THÔNG TIN SẢN PHẨM                |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    1.  XEM DANH SÁCH TOÀN BỘ SẢN PHẨM            |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    2.  TÌM KIẾM THEO MÃ SẢN PHẨM                 |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    3.  THÊM MỚI MỘT SẢN PHẨM                     |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    4.  SỬA THÔNG TIN SẢN PHẨM                    |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    5.  XÓA THÔNG TIN SẢN PHẨM                    |");
            System.out.println("+--------------------------------------------------+");
            System.out.println("|    6.  THOÁT                                     |");
            System.out.println("+--------------------------------------------------+");
            System.err.print("MỜI BẠN CHỌN CÁC CHỨC NĂNG TRONG HỆ THỐNG QUẢN LÝ SẢN PHẨM !!!");
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

                    addProduct();
                    break;
                }
                case 4: {
                    edit();
                    break;
                }
                case 5: {
                    delete();
                    break;
                }

                default: {
                    savefile();
                }
            }
        } while (use == 1 || use == 2 || use == 3 || use == 4 || use == 5);

    }
}
