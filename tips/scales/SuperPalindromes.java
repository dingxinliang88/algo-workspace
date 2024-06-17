package tips.scales;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/super-palindromes/
 */
public class SuperPalindromes {
    /**
     * seed --[expand]--> sqrt(x) --> x
     */
    public int superpalindromesInRange(String left, String right) {
        long l = Long.parseLong(left);
        long r = Long.parseLong(right);

        long limit = (long) Math.sqrt((double) r);
        long seed = 1;
        long num = 0; // sqrt(x)
        int ans = 0;
        do {
            // 123 -> 123321
            num = enlarge(seed, false);
            if (check(num * num, l, r)) {
                ans++;
            }
            // 123 -> 12321
            num = enlarge(seed, true);
            if (check(num * num, l, r)) {
                ans++;
            }
            seed++;
        } while (num < limit); // 每次比较 奇数扩展 的数是否越界
        return ans;
    }

    public static long enlarge(long seed, boolean odd) {
        long ans = seed;
        if (odd) {
            seed /= 10;
        }
        while (seed != 0) {
            ans = ans * 10 + seed % 10;
            seed /= 10;
        }
        return ans;
    }

    public static boolean check(long ans, long l, long r) {
        return ans >= l && ans <= r && isPalindrome(ans);
    }

    public static boolean isPalindrome(long num) {
        long offset = 1;
        // 防止溢出
        while (num / offset >= 10) {
            offset *= 10;
        }

        while (num != 0) {
            if (num / offset != num % 10) {
                return false;
            }
            num = (num % offset) / 10;
            offset /= 100;
        }
        return true;
    }

    // region 打表
    public static int superpalindromesInRange2(String left, String right) {
        long l = Long.parseLong(left);
        long r = Long.parseLong(right);
        int i = 0;
        for (; i < record.length; i++) {
            if (record[i] >= l) {
                break;
            }
        }
        int j = record.length - 1;
        for (; j >= 0; j--) {
            if (record[j] <= r) {
                break;
            }
        }
        return j - i + 1;
    }

    public static long[] record = new long[] {
            1L,
            4L,
            9L,
            121L,
            484L,
            10201L,
            12321L,
            14641L,
            40804L,
            44944L,
            1002001L,
            1234321L,
            4008004L,
            100020001L,
            102030201L,
            104060401L,
            121242121L,
            123454321L,
            125686521L,
            400080004L,
            404090404L,
            10000200001L,
            10221412201L,
            12102420121L,
            12345654321L,
            40000800004L,
            1000002000001L,
            1002003002001L,
            1004006004001L,
            1020304030201L,
            1022325232201L,
            1024348434201L,
            1210024200121L,
            1212225222121L,
            1214428244121L,
            1232346432321L,
            1234567654321L,
            4000008000004L,
            4004009004004L,
            100000020000001L,
            100220141022001L,
            102012040210201L,
            102234363432201L,
            121000242000121L,
            121242363242121L,
            123212464212321L,
            123456787654321L,
            400000080000004L,
            10000000200000001L,
            10002000300020001L,
            10004000600040001L,
            10020210401202001L,
            10022212521222001L,
            10024214841242001L,
            10201020402010201L,
            10203040504030201L,
            10205060806050201L,
            10221432623412201L,
            10223454745432201L,
            12100002420000121L,
            12102202520220121L,
            12104402820440121L,
            12122232623222121L,
            12124434743442121L,
            12321024642012321L,
            12323244744232321L,
            12343456865434321L,
            12345678987654321L,
            40000000800000004L,
            40004000900040004L,
            1000000002000000001L,
            1000220014100220001L,
            1002003004003002001L,
            1002223236323222001L,
            1020100204020010201L,
            1020322416142230201L,
            1022123226223212201L,
            1022345658565432201L,
            1210000024200000121L,
            1210242036302420121L,
            1212203226223022121L,
            1212445458545442121L,
            1232100246420012321L,
            1232344458544432321L,
            1234323468643234321L,
            4000000008000000004L
    };

    public static List<Long> collect() {
        long l = 1;
        long r = Long.MAX_VALUE;
        long limit = (long) Math.sqrt((double) r);
        long seed = 1;
        long enlarge = 0;
        ArrayList<Long> ans = new ArrayList<>();
        do {
            enlarge = enlarge(seed, false);
            if (check(enlarge * enlarge, l, r)) {
                ans.add(enlarge * enlarge);
            }
            enlarge = enlarge(seed, true);
            if (check(enlarge * enlarge, l, r)) {
                ans.add(enlarge * enlarge);
            }
            seed++;
        } while (enlarge < limit);
        ans.sort((a, b) -> a.compareTo(b));
        return ans;
    }

    public static void main(String[] args) {
        List<Long> ans = collect();
        for (long p : ans) {
            System.out.println(p + "L,");
        }
        System.out.println("size : " + ans.size());
    }
    // endregion
}
