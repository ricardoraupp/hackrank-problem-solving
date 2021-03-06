package raupp.solution3;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class Result {

    /*
     * Complete the 'kthPerson' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY p
     *  3. INTEGER_ARRAY q
     */
static class kv {
    kv(int i, int v){
        index = i;
        value = v;
    }
    int index;
    int value;
    }
    public static List<Integer> kthPerson(int k, List<Integer> p, List<Integer> q) {
        // Write your code here
        List<Integer> kths = new ArrayList<Integer>();
        for(Integer qu : q) {
            List<kv> match = (List<kv>) IntStream.range(0, p.size())
                    .parallel()
                    .mapToObj(index -> {
                        return new kv(index,p.get(index));
                    }).filter(pa -> pa.value >= qu).collect(toList());
            if(match.size() < k){
                kths.add(0);
            }else{
                kths.add(match.get(k-1).index+1);
            }
        }
        return kths;
    }

}
public class Solution {
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
