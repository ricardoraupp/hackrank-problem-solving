import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SimpleArraySum {

    // Complete the hackerrankInString function below.
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
    // Complete the pangrams function below.
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

    // Complete the aVeryBigSum function below.
    static long aVeryBigSum(long[] ar) {
        long sum = 0;
        for(long a : ar){
            sum += a;
        }
        return sum;

    }
    // Complete the plusMinus function below.
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
         */

        String s = "We promptly judged antique ivory buckles for the next prize";
        System.out.println(pangrams(s));

    }
}
