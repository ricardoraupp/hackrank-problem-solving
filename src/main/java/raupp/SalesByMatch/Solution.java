package raupp.SalesByMatch;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Solution {

    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {
        int result = 0;
        int count = 0;
        List<Integer> socks = new ArrayList<Integer>();
        for(int sock : ar){
            socks.add(sock);
        }
        List<Integer> sockTypes = socks.stream().distinct().collect(Collectors.toList());
        for(Integer sockType : sockTypes){
            for(Integer sock: ar){
                if(sockType == sock)
                    count++;
            }
            if(count%2 == 0){
                result = result + count/2;
            }else{
                result = result + (count-(count%2))/2;
            }
            count = 0;
        }
    return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("d://hackerrank.txt"));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");


        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant(n, ar);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
