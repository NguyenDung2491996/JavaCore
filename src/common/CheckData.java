/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author DUNG NV
 */
public class CheckData {

//    public boolean kiemTraSoDienThoai(String sdt) {
//        
//        if (sdt.length() > 11) {
//            System.out.println("So dien thoai khong co nhieu hon 11 chu so");
//            return true;
//
//        } else if (sdt.length() < 10) {
//            System.out.println("So dien thoai khong co it hon 10 chu so");
//            return true;
//        } else if (sdt.startsWith(0 + "") == false) {
//            System.out.println("So dien thoai phai bat dau bang 0");
//            return true;
//        }
//        return false;
//
//    }
    public boolean checkNumberPhone(String number) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(number);
        if (!matcher.matches()) {
            System.err.println("CHUỖI VỪA NHẬP KHÔNG PHẢI LÀ SỐ!");
            return true;
        }

        if (number.length() == 10 || number.length() == 11) {
            if (number.length() == 10) {
                if (number.substring(0, 2).equals("09")) {
                    System.err.println("SỐ ĐIỆN THOẠI HỢP LỆ !!! ");
                    return false;

                } else {
                    System.err.println("SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ !!!");
                    return true;
                }
            } else if (number.substring(0, 2).equals("01")) {

                return false;
            } else {
                System.err.println("SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ !!!");
                return true;
            }
        } else {
            System.err.println("ĐỌ DÀI CHUỖI KHÔNG HỢP LỆ !!!!");
            return true;
        }

    }
    public boolean checkNumberPhoneNumber(String number) {
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matcher = pattern.matcher(number);
        if (!matcher.matches()) {
            System.err.println("CHUỖI VỪA NHẬP KHÔNG PHẢI LÀ SỐ!");
            return true;
        }
        if (number.equalsIgnoreCase("")) {
            return false;
        }

        if (number.length() == 10 || number.length() == 11) {
            if (number.length() == 10) {
                if (number.substring(0, 2).equals("09")) {
                    System.err.println("SỐ ĐIỆN THOẠI HỢP LỆ !!! ");
                    return false;

                } else {
                    System.err.println("SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ !!!");
                    return true;
                }
            } else if (number.substring(0, 2).equals("01")) {

                return false;
            } else {
                System.err.println("SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ !!!");
                return true;
            }
        } else {
            System.err.println("ĐỌ DÀI CHUỖI KHÔNG HỢP LỆ !!!!");
            return true;
        }

    }

    public boolean kiemTraTen(String ten) {
        for (int i = 0; i < 10; i++) {
            if (ten.contains(i + "") == true || ten.equalsIgnoreCase("")) {
                return true;
            }
        }
        return false;
    }
     public boolean kiemTraName(String name) {
        for (int i = 0; i < 10; i++) {
            if (name.contains(i + "") == true ) {
                return true;
            }
        }
        return false;
    }

    public boolean kiemTraEmail(String email) {
        String dinhDangEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        boolean ktEmail = email.matches(dinhDangEmail);
        
        if (ktEmail == false) {
            System.err.println("Email sai, nhập lại theo dạng abc@domain.com");
            return true;
        }
        return false;
    
    }
     public boolean checkEmail(String email) {
        String dinhDangEmail = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        boolean ktEmail = email.matches(dinhDangEmail);
        if (email.equalsIgnoreCase("")) {
             return false;
         }
        if (ktEmail == false) {
            System.err.println("Email sai, nhập lại theo dạng abc@domain.com");
            return true;
        }
        return false;
    }

    public boolean checkCount(String Count) {
      
        boolean check = false;
        try {
            int c = Integer.parseInt(Count);
            if (c < 0) {
                System.out.println("SỐ LƯỢNG PHẢI LÀ SỐ LỚN HƠN 0");
                return check = true;
            }
        } catch (NumberFormatException e) {
            System.out.println("KHÔNG ĐƯỢC CHỨA CHỮ");
            return check = true;
        }
        return check;
    }

    public boolean checkPrice(String price) {
        
        try {
            int p = Integer.parseInt(price);
            if (p < 0) {
                System.out.println("GIÁ PHẢI LÀ SỐ LỚN HƠN 0");
                return true;
            }

        } catch (NumberFormatException e) {
            System.out.println("KHÔNG ĐƯỢC CHỨA CHỮ");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;

    }

    public String chuanHoaString(String str) {
        str = str.trim();

        String temp[] = str.split(" ");
        str = "";
        for (int i = 0; i < temp.length; i++) {
            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);

            if (i < temp.length - 1) {
                str += " ";
            }
        }

        return str;
    }

    public String md5(String str) {
        String result = "";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest.digest());
            result = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

}
