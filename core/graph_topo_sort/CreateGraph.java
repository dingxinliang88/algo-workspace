package core.graph_topo_sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateGraph {

    /**
     * 点的最大数量
     */
    public static final int MAXN = 11;

    /**
     * 边的最大数量，无向图需要 * 2
     */
    public static final int MAXM = 21;

    /**
     * 邻接矩阵建图
     */
    public static int[][] graph1 = new int[MAXN][MAXN];

    /**
     * 邻接表建图
     */
    // public static List<List<Integer>> graph2 = new ArrayList<>();
    public static List<List<int[]>> graph2 = new ArrayList<>();

    /**
     * 链式前向星建图
     */
    public static int[] head = new int[MAXN];
    public static int[] next = new int[MAXM];
    public static int[] to = new int[MAXM];
    public static int[] weight = new int[MAXM]; // 边权
    public static int cnt; // 边号

    public static void build(int n) {
        // 清空邻接矩阵
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                graph1[i][j] = 0;
            }
        }
        // 清空邻接表和准备工作
        graph2.clear();
        for (int i = 0; i <= n; i++) {
            graph2.add(new ArrayList<>());
        }
        // 链式前向星清空
        cnt = 1;
        Arrays.fill(head, 1, n + 1, 0);
    }

    /**
     * 链式前向星加边
     * u -> v, 边权是 w
     */
    public static void addEdge(int u, int v, int w) {
        next[cnt] = head[u];
        to[cnt] = v;
        weight[cnt] = w;
        head[u] = cnt++;
    }

    /**
     * 有向图
     */
    public static void directGraph(int[][] edges) {
        // 邻接矩阵建图
        for (int[] edge : edges) {
            graph1[edge[0]][edge[1]] = edge[2];
        }
        // 邻接表建图
        for (int[] edge : edges) {
            // graph2.get(edge[0]).add(edge[1]);
            graph2.get(edge[0]).add(new int[] { edge[1], edge[2] });
        }
        // 链式前向星建图
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1], edge[2]);
        }
    }

    /**
     * 无向图
     */
    public static void undirectGraph(int[][] edges) {
        // 邻接矩阵建图
        for (int[] edge : edges) {
            graph1[edge[0]][edge[1]] = edge[2];
            graph1[edge[1]][edge[0]] = edge[2];
        }
        // 邻接表建图
        for (int[] edge : edges) {
            // graph2.get(edge[0]).add(edge[1]);
            // graph2.get(edge[1]).add(edge[0]);
            graph2.get(edge[0]).add(new int[] { edge[1], edge[2] });
            graph2.get(edge[1]).add(new int[] { edge[0], edge[2] });
        }
        // 链式前向星建图
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1], edge[2]);
            addEdge(edge[1], edge[0], edge[2]);
        }
    }

    public static void traversal(int n) {
        System.out.println("邻接矩阵遍历结果：");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(graph1[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("邻接表遍历结果：");
        for (int i = 1; i <= n; i++) {
            System.out.print("节点" + i + "(邻居, 边权)：");
            for (int[] edge : graph2.get(i)) {
                System.out.print("(" + edge[0] + ", " + edge[1] + ") ");
            }
            System.out.println();
        }
        System.out.println("链式前向星遍历结果：");
        for (int i = 1; i <= n; i++) {
            System.out.print("节点" + i + "(邻居, 边权)：");
            for (int ei = head[i]; ei > 0; ei = next[ei]) {
                System.out.print("(" + to[ei] + ", " + weight[ei] + ") ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("=======================有向图=======================");
        int n1 = 4;
        int[][] edges1 = { { 1, 3, 6 }, { 4, 3, 4 }, { 2, 4, 2 }, { 1, 2, 7 }, { 2, 3, 5 }, { 3, 1, 1 } };
        build(n1);
        directGraph(edges1);
        traversal(n1);
        System.out.println("=======================无向图=======================");
        int n2 = 5;
        int[][] edges2 = { { 3, 5, 4 }, { 4, 1, 1 }, { 3, 4, 2 }, { 5, 2, 4 }, { 2, 3, 7 }, { 1, 5, 5 }, { 4, 2, 6 } };
        build(n2);
        undirectGraph(edges2);
        traversal(n2);
    }
}
