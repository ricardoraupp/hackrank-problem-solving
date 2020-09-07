import java.io.*;
import java.util.*;
import java.security.MessageDigest;

public class MD5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        sc.close();
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] digest = md.digest();
            for (byte b : digest){
                System.out.printf("%02x", b);
            }
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}