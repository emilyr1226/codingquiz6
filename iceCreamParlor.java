import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import javafx.util.Pair;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'icecreamParlor' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER m
     *  2. INTEGER_ARRAY arr
     */
    public static Pair<Boolean, List<Integer>> helperFunction (List<Integer> arr, int index, int m, int numIceCream, List<Integer> indices) {
    Pair<Boolean, List<Integer>> correct = new Pair <> (true, indices);
    if (index == 0 && m == 0 && numIceCream == 3) {
        return correct;
    }
    if (index == 0 && m == 1 && numIceCream == 3) {
        return correct;
    }
    Pair<Boolean, List<Integer>> none = new Pair <> (false, indices);
    if (m < 0 || index == -1) {
        return none;    
    }  
    
    Pair<Boolean, List<Integer>> pathway = new Pair <> (true, indices);
    pathway = helperFunction(arr, index - 1, m, numIceCream, indices);
    if (pathway.getKey()) { 
        return pathway;
    }
    pathway = helperFunction(arr, index - 1, m - arr.get(index), numIceCream + 1, indices); 
    if (pathway.getKey()) {
        indices.add(index+1); 
        return pathway;
    }
    return none;
    
    }
    public static List<Integer> icecreamParlor(int m, List<Integer> arr) {
    // Write your code here
    List<Integer> indices = new ArrayList<>();
    Pair<Boolean, List<Integer>> result = helperFunction(arr, arr.size()-1, m, 0, indices);
    return result.getValue();
    }
}



public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int m = Integer.parseInt(bufferedReader.readLine().trim());

                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                List<Integer> result = Result.icecreamParlor(m, arr);

                bufferedWriter.write(
                    result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                    + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}