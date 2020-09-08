

import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class SimpleArraySum {
    public static String getJson(int page,int userId) {
        URL url = null;
        try {
            url = new URL("https://jsonmock.hackerrank.com/api/transactions/search?userId="+userId+"&page="+page);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if(responseCode != 200)
                throw new RuntimeException("HttpResponseCode: " +responseCode);
            else{
                BufferedReader bfReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String input;
                StringBuffer response = new StringBuffer();
                while ((input = bfReader.readLine()) != null) {
                    response.append(input);
                }
                bfReader.close();
                return response.toString();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
    public static void getData(String json,Root root) {
        json = json.replace("$","");
        json = json.replace(",\"",";");
        json = json.replace("},{",";");
        json = json.replace("}","");
        json = json.replace("{","");
        json = json.replace("[","");
        json = json.replace("]","");
        json = json.replace(",","");
        json = json.replace("\"","");
        String[] jsonArray = json.split(";");
        Datum data = new Datum();
        for(int i = 0; i < jsonArray.length; i++){
            //System.out.println(json[i]);
            if(jsonArray[i].contains("total_pages:")){
                root.total_pages= Integer.parseInt(jsonArray[i].substring(jsonArray[i].indexOf(":")+1));
            }
            if(jsonArray[i].contains("userId:")){
                data = new Datum();
                data.userId = Integer.parseInt(jsonArray[i].substring(jsonArray[i].indexOf(":")+1));
            }
            if(jsonArray[i].contains("amount:")){
                data.amount = Float.parseFloat(jsonArray[i].substring(jsonArray[i].indexOf(":")+1));
            }
            if(jsonArray[i].contains("location:id:")){
                data.location = Integer.parseInt(jsonArray[i].substring(jsonArray[i].indexOf(":")+4));
            }
            if(jsonArray[i].contains("ip:")){
                data.ip = Integer.parseInt(jsonArray[i].substring(jsonArray[i].indexOf(":")+1,jsonArray[i].indexOf(".")));
                root.data.add(data);
            }
        }
    }
    public static int getTransactions(int userId, int locationId, int netStart, int netEnd) {

        String json = getJson(1,userId);
        Root root = new Root();
        ArrayList<Datum> data = new ArrayList<Datum>();
        root.data = data;
        getData(json,root);
        for(int i = 2; i<=root.total_pages; i++){
            getData(getJson(i,userId),root);
        }
        float totalAmount = 0;
        for(Datum d : root.data){
            if(d.userId == userId && d.location == locationId && d.ip >= netStart && d.ip <= netEnd ){
                totalAmount += d.amount;
            }
        }

        return Math.round(totalAmount);
    }
    public static void fizzBuzz(int n) {
        // Write your code here
        int m3 = 0;
        int m5 = 0;
        for (int i = 1; i <= n; i++){
            m3 = i%3;
            m5 = i%5;
            if(m3 == 0 && m5 == 0){
                System.out.println("FizzBuzz");
            }else if(m3 == 0 && m5 != 0){
                System.out.println("Fizz");
            }else if(m5 == 0 && m3 != 0){
                System.out.println("Buzz");
            }else{
                System.out.println(i);
            }
        }

    }
    static String hackerrankInString(String s) {
        String needed = "hackerrank";
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == needed.charAt(count)) {
                count++;
            }
            if (count == needed.length()) {
                return "YES";
            }
            System.out.println(count);
        }
        return "NO";
    }
    static String pangrams(String s) {
        String lowerCase = s.toLowerCase();
        lowerCase = lowerCase.replace(" ", "");
        List<Character> list = new ArrayList<Character>();
        for (char c : lowerCase.toCharArray()) {
            list.add(c);
        }
        Collections.sort(list);
        List<Character> listNotDuplicated = list.stream().distinct().collect(Collectors.toList());
        if(listNotDuplicated.size() == 26){
            return "pangram";
        }else{
            return "not pangram";
        }
    }
    public static int birthdayCakeCandles(List<Integer> candles) {
        Collections.sort(candles);
        int aux = candles.get(candles.size()-1);
        int count = 0;
        for(Integer i : candles){
            if(i == aux){
                count++;
            }
        }

        return count;
    }
    static String timeConversion(String s){
        SimpleDateFormat date12Format = new SimpleDateFormat("hh:mm:ssaa");
        SimpleDateFormat date24Format = new SimpleDateFormat("HH:mm:ss");
        try {
            return date24Format.format(date12Format.parse(s));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static List<Integer> gradingStudents(List<Integer> grades) {
        List<Integer> result = new ArrayList<Integer>();
        int aux = 0;
        int nextM = 0;
        for(Integer grade: grades){
            aux = grade%5;
            nextM = grade - aux + 5;
            if((nextM - grade) < 3 && grade >= 38){
                result.add(nextM);
            }else{
                result.add(grade);
            }

        }

        return result;
    }
    public static int arraySum(int[] array){
        int sum = 0;
        for(int i =0; i < array.length; i++){
           sum = sum + array[i];
        }
         return sum;
    }
    static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {

        List<Integer> result = new ArrayList<Integer>();
        result.add(0);
        result.add(0);
        String tmp = "";
        int alice = 0;
        int bob = 0;
        for(int i = 0; i < 3; i++)
            if (a.get(i) > b.get(i)) {
                result.set(0, ++alice);
            } else if(a.get(i) < b.get(i)) {
                result.set(1, ++bob);
            }
        return result;
    }
    static long aVeryBigSum(long[] ar) {
        long sum = 0;
        for(long a : ar){
            sum += a;
        }
        return sum;

    }
    static void plusMinus(int[] arr) {
        int size = arr.length;
        float positive = 0;
        float negative = 0;
        float zeros = 0;
        for(int i =0; i < size ; i++){
            if(arr[i] > 0){
                positive +=1;
            }else if(arr[i] < 0){
                negative +=1;
            }else{
                zeros +=1;
            }

        }

        System.out.printf("%.6f",(positive/size));
        System.out.println();
        System.out.printf("%.6f",(negative/size));
        System.out.println();
        System.out.printf("%.6f",(zeros/size));
        System.out.println();
    }
    static void staircase(int n) {

        int counter=0;
        for(int i=0; i<n;i++){
            counter++;
            for(int k=n-1; k > i;k--){
                System.out.print(" ");
            }
            for(int j=0; j<=counter-1;j++){
                System.out.print("#");
            }
            System.out.println();
        }

    }
    public static int diagonalDifference(List<List<Integer>> arr) {
        // Write your code here
        int sum1 = 0;
        int sum2 = 0;
        for(int i=0;i<arr.size();i++){
            for(int j=0;j<arr.get(i).size();j++){
                    if(i==j){
                        sum1 += arr.get(i).get(j);
                    }
                    if (i == arr.size() - j - 1){
                        sum2 += arr.get(i).get(j);
                    }
            }
        }
        return Math.abs(sum1 - sum2);
    }
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        /*
        Scanner input = new Scanner(System.in);

        int arrayLength = input.nextInt();
        int[] array = new int[arrayLength];
        for(int i =0; i < arrayLength; i++){
            array[i] = input.nextInt();
        }
        System.out.println(arraySum(array));

        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<Integer> a = new ArrayList<Integer>();
        a.add(11); a.add(2); a.add(4);
        List<Integer> b = new ArrayList<Integer>();
        b.add(4); b.add(5); b.add(6);
        List<Integer> c = new ArrayList<Integer>();
        c.add(10); c.add(8); c.add(-18);
        list.add(0,a);
        list.add(1,b);
        list.add(2,c);
        System.out.println(diagonalDifference(list));
        staircase(4);

        int[] i = {-4, 3, -9, 0, 4, 1};
        plusMinus(i);


        Java Output Formatting
            Scanner sc=new Scanner(System.in);
            System.out.println("================================");
            for(int i=0;i<3;i++)
            {
                String s1=sc.next();
                int x=sc.nextInt();
                System.out.printf(s1);
                for(int j = s1.length(); j <15; j++){
                    System.out.printf(" ");
                }
                System.out.printf("%03d",x);
                System.out.println();
                //Complete this line
            }
            System.out.println("================================");

        Scanner in = new Scanner(System.in);
        int t=in.nextInt();
        for(int i=0;i<t;i++){
            int a = in.nextInt();
            int b = in.nextInt();
            int n = in.nextInt();
            for
        }
        in.close();

        Scanner in = new Scanner(System.in);
        int t=in.nextInt();
        for(int i=0;i<t;i++){
            int a = in.nextInt();
            int b = in.nextInt();
            int n = in.nextInt();
            for(int j = 0; j < n; j++ ){
               a =  a + (int)Math.pow(2,j)*b;
               System.out.print(a+" ");
            }
            System.out.println("");
        }
        in.close();
                Scanner sc=new Scanner(System.in);
        while(sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }
        int n = 0;
        String.valueOf()
        System.out.println(LocalDate.of(2020, 9, 04).getDayOfWeek().toString());
        Scanner scanner = new Scanner(System.in);
        double payment = scanner.nextDouble();
        scanner.close();



        // Write your code here.


        System.out.println("US: " + NumberFormat.getCurrencyInstance(Locale.US).format(payment));
        System.out.println("India: " + NumberFormat.getCurrencyInstance(locale).format(payment));
        System.out.println("China: " + NumberFormat.getCurrencyInstance(Locale.CHINA).format(payment));
        System.out.println("France: " + NumberFormat.getCurrencyInstance(Locale.FRANCE).format(payment));
        List<Integer> lista = new ArrayList<Integer>();
        lista.add(73);
        lista.add(67);
        lista.add(38);
        lista.add(33);
        List<Integer> lista2 = gradingStudents(lista);
        lista2.forEach(grade -> System.out.println(grade));
        String a = "hhaacckkekrraraannk";
        System.out.println(hackerrankInString(a));
        String s = "We promptly judged antique ivory buckles for the next prize";
        System.out.println(pangrams(s));
         fizzBuzz(15);
                 int i = 13;
        byte[] b = String.valueOf(i).getBytes();
        for(i = 0; i< b.length; i++){
            System.out.println(b[i]);
        }
         */
       //getTransactions(2,8,5,50);

        System.out.println(getTransactions(2,8,5,50));
    }
}
class Location{
    public int id;
    public String address;
    public String city;
    public int zipCode;
}

class Datum{
    public int id;
    public int userId;
    public String userName;
    public Object timestamp;
    public String txnType;
    public float amount;
    public int location;
    public int ip;

    @Override
    public String toString() {
        return "Datum{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", timestamp=" + timestamp +
                ", txnType='" + txnType + '\'' +
                ", amount='" + amount + '\'' +
                ", location='" + location + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}

class Root{
    public String page;
    public int per_page;
    public int total;
    public int total_pages;
    public List<Datum> data;

}
