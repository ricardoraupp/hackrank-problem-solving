package raupp.solution2;

public class EngineerFirm extends EmployeeFirm{
    String type = "engineers";

    EngineerFirm(int i) {
        super(i);
    }
    public void assignSalaries(int[] salaries) {
        super.assignSalaries(salaries);
        System.out.println("Incomes of "+this.type+" credited");
    }
    public void averageSalary() {
        super.averageSalary();
        System.out.printf("Average salary of "+this.type+" is %.2f",this.avg);
        System.out.println();
    }
    public void maxSalary() {
        super.maxSalary();
        System.out.println("Maximum salary amongst "+this.type+" is "+this.max);
    }
    public void minSalary() {
        super.minSalary();
        System.out.println("Minimum salary amongst "+this.type+" is "+this.min);
    }
}
