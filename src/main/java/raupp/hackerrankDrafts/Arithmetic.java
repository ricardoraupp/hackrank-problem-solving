package raupp.hackerrankDrafts;

interface AdvancedArithmetic {

    int divisor_sum(int n);

}

class MyCalculator implements AdvancedArithmetic {

    public static int getMaxValue(int[] numbers){
        int maxValue = numbers[0];
        for(int i=1;i < numbers.length;i++){
            if(numbers[i] > maxValue){
                maxValue = numbers[i];
            }
        }
        return maxValue;
    }

    public static int getMinValue(int[] numbers){
        int minValue = numbers[0];
        for(int i=1;i<numbers.length;i++){
            if(numbers[i] < minValue){
                minValue = numbers[i];
            }
        }
        return minValue;
    }
    @Override
    public int divisor_sum(int n) {
        int result = 0;
        for(int i = n; i > 0; i--){
            if(n%i==0){
                result += i;
            }
        }
        return result;
    }
}
