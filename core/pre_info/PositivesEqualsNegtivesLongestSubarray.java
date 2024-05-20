package core.pre_info;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.HashMap;

/**
 * https://www.nowcoder.com/practice/545544c060804eceaed0bb84fcd992fb
 */
public class PositivesEqualsNegtivesLongestSubarray {

    public static final int MAXN = 100_001;

    public static int[] arr = new int[MAXN];

    public static int n;

    public static HashMap<Integer, Integer> map = new HashMap<>();

    public static int compute() {
        map.clear();
        map.put(0, -1);
        int ans = 0;
        for (int i = 0, sum = 0; i < n; i++) {
            sum += arr[i]; // 要求 aim = 0 的情况下就是子数组中正负个数一样的情况
            if (map.containsKey(sum)) {
                ans = Math.max(ans, i - map.get(sum)); // sum-0
            } else {
                map.put(sum, i);
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new FileReader("in.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) in.nval;
            for (int i = 0, num; i < n; i++) {
                in.nextToken();
                num = (int) in.nval;
                arr[i] = num != 0 ? (num > 0 ? 1 : -1) : 0;
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }
}
