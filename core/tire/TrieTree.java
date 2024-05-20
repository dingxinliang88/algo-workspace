package core.tire;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * https://www.nowcoder.com/practice/7f8a8553ddbf4eaab749ec988726702b
 */
public class TrieTree {

    public static final int MAXN = 150_001;

    public static int[][] tree = new int[MAXN][26];
    public static int[] end = new int[MAXN];
    public static int[] pass = new int[MAXN];
    public static int cnt; // 当前使用到的数据空间上限，从1开始，类似于节点编号

    public static void build() {
        cnt = 1;
    }

    public static void clear() {
        for (int i = 1; i <= cnt; i++) {
            Arrays.fill(tree[i], 0);
            end[i] = 0;
            pass[i] = 0;
        }
    }

    public static void insert(String word) {
        int cur = 1;
        pass[cur]++;
        for (int i = 0, path; i < word.length(); i++) {
            path = word.charAt(i) - 'a';
            if (tree[cur][path] == 0) {
                tree[cur][path] = ++cnt;
            }
            cur = tree[cur][path];
            pass[cur]++;
        }
        end[cur]++;
    }

    public static int search(String word) {
        int cur = 1;
        for (int i = 0, path; i < word.length(); i++) {
            path = word.charAt(i) - 'a';
            if (tree[cur][path] == 0) {
                return 0;
            }
            cur = tree[cur][path];
        }
        return end[cur];
    }

    public static int prefixNumber(String pre) {
        int cur = 1;
        for (int i = 0, path; i < pre.length(); i++) {
            path = pre.charAt(i) - 'a';
            if (tree[cur][path] == 0) {
                return 0;
            }
            cur = tree[cur][path];
        }
        return pass[cur];
    }

    public static void delete(String word) {
        if (search(word) <= 0) {
            return;
        }
        int cur = 1;
        for (int i = 0, path; i < word.length(); i++) {
            path = word.charAt(i) - 'a';
            if (--pass[tree[cur][path]] == 0) {
                tree[cur][path] = 0;
                return;
            }
            cur = tree[cur][path];
        }
        end[cur]--;
    }

    public static int m, op;
    public static String[] splits;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("in.txt")));
        // BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        String line = null;
        while ((line = in.readLine()) != null) {
            build();
            m = Integer.valueOf(line);
            for (int i = 0; i < m; i++) {
                splits = in.readLine().split(" ");
                op = Integer.valueOf(splits[0]);
                if (op == 1) {
                    insert(splits[1]);
                } else if (op == 2) {
                    delete(splits[1]);
                } else if (op == 3) {
                    out.println(search(splits[1]) > 0 ? "YES" : "NO");
                } else if (op == 4) {
                    out.println(prefixNumber(splits[1]));
                }
            }
            clear();
        }

        out.flush();
        out.close();
        in.close();
    }

}
