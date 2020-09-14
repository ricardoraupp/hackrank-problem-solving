package raupp.solution3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class Result2 {

    /*
     * Complete the 'kthPerson' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY p
     *  3. INTEGER_ARRAY q
     */

    public static List<Integer> kthPerson(int k, List<Integer> p, List<Integer> q) {
        // Write your code here
        int passengers = 0;
        int kth = 0;
        int pSize = p.size();
        int qSize = q.size();
        int query = 0;
        List<Integer> kths = new ArrayList<Integer>();
        if(k == 0){
            q.stream().forEach(l -> kths.add(0));
            return kths;
        }
        for(int i =0; i < qSize; i++){
            kth = 0;
            passengers = 0;
            query = q.get(i);
            for(int j=0; j < pSize; j++){
                if(query <= p.get(j)){
                    if(passengers < k){
                        passengers++;
                        kth = j;
                    }
                }
            }
            if(passengers < k){
                kths.add(0);
            }else{
                kths.add(kth+1);
            }
        }
        return kths;
    }

}

public class Solutionb {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        int pCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> p = IntStream.range(0, pCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int qCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> q = IntStream.range(0, qCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.kthPerson(k, p, q);

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
