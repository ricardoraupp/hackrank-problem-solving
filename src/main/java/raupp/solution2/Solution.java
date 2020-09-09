package raupp.solution2;
import java.util.*;


/* model output for cut and paste
Incomes of ____ credited
Average salary of ____ is ____
Maximum salary amongst ____ is ____
Minimum salary amongst ____ is ____
*/
public class Solution {
    public static void main(String args[] ) throws Exception {
        Scanner sc = new Scanner(System.in);

        String[] count = sc.nextLine().split(" ");

        EngineerFirm e = new EngineerFirm(Integer.parseInt(count[0]));
        AccountantFirm a = new AccountantFirm(Integer.parseInt(count[1]));

        count = sc.nextLine().split(" ");

        int[] incomeEngineers = new int[count.length];
        for (int i=0; i < count.length; i++) {
            incomeEngineers[i] = Integer.parseInt(count[i]);
        }
        e.assignSalaries(incomeEngineers);

        count = sc.nextLine().split(" ");
        int[] incomeAccountants = new int[count.length];
        for (int i=0; i < count.length; i++) {
            incomeAccountants[i] = Integer.parseInt(count[i]);
        }
        a.assignSalaries(incomeAccountants);

        e.averageSalary();
        e.maxSalary();
        e.minSalary();

        a.averageSalary();
        a.maxSalary();
        a.minSalary();
    }
}