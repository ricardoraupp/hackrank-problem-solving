package raupp.solution2;

public abstract class EmployeeFirm implements Company{
    int[] income;
    String type = "employees";
    int min = 1000000000;
    float avg = 0;
    int max = -1000000000;
    EmployeeFirm(int n){
        income = new int[n];
        for(int i = 0; i < n; i++){
            income[i] = 0;
        }

    }

    @Override
    public void assignSalaries(int[] salaries) {
        if(salaries.length > income.length){
            for(int i = 0; i < income.length;i++){
                income[i] = salaries[i];
            }
        }else{
            for(int i = 0; i < salaries.length;i++){
                income[i] = salaries[i];
            }
        }

    }

    @Override
    public void averageSalary() {
        for(int i = 0; i < income.length; i++){

            avg += income[i];
        }
       Math.abs(avg = avg/income.length);
    }

    @Override
    public void maxSalary() {

        for(int i = 0; i < income.length; i++){
            if(max < income[i])
                max = income[i];
        }
    }

    @Override
    public void minSalary() {

        for(int i = 0; i < income.length; i++){
            if(min > income[i])
                min = income[i];
        }
    }

}
