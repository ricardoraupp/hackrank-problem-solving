package raupp.solution4;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.Arrays.binarySearch;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'predictAnswer' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY stockData
     *  2. INTEGER_ARRAY queries
     */
    static class kv {
        kv(int i, int v){
            index = i;
            value = v;
        }
        int index;
        int value;
    }

    public static int max(List<kv> match, int index) {
        int result = -1;
        for(int i = 0; i < match.size(); i++){
            if(index < match.get(i).index){
                break;
            }else{
                result = match.get(i).index;
            }

        }
        return result;
    }

    public static int min(List<kv> match, int index) {
        int result = -1;
        for(int i = 0; i < match.size(); i++){
            if(index > match.get(i).index){
                break;
            }else{
                result = match.get(i).index;
            }

        }
        return result;
    }
    public static List<Integer> predictAnswer(List<Integer> stockData, List<Integer> queries) {
        // Write your code here
        List<Integer> days = new ArrayList<Integer>();
        for(Integer qu : queries) {
            int stockQDay = stockData.get(qu-1);
            List<kv> match = (List<kv>) IntStream.range(0, stockData.size())
                    .parallel()
                    .mapToObj(index -> {
                        return new kv(index,stockData.get(index));
                    }).filter(stock -> stock.value < stockQDay).collect(toList());
            List<Integer> l = new ArrayList<Integer>();
            match.stream().forEach(k -> l.add(k.index));
            match.stream().forEach(k -> System.out.println(qu+" - INDEX: "+k.index+" VALUE: "+k.value));
            if(match.size() < 1){
                System.out.println(qu+" - MATCH < 1");
                days.add(-1);
            }else{
                int binary = Collections.binarySearch(l,qu);
                if(binary == -1){
                    System.out.println(qu+" - BINARY -1");
                    days.add(match.get(0).index+1);
                }else{
                    int min = min(match,qu);
                    System.out.println(qu+" - min "+min);
                    if(min != -1){
                        System.out.println(qu+" - min <> -1"+min);
                        days.add(match.get(min).index+1);
                    }else{
                        int max = max(match,qu);
                        System.out.println(qu+" - max "+max);
                        days.add(match.get(max).index+1);
                    }

                }
            }
        }
        return days;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int stockDataCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> stockData = IntStream.range(0, stockDataCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.predictAnswer(stockData, queries);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
