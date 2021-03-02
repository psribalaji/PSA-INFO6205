package edu.neu.coe.info6205.union_find;

import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;

import java.util.*;

/**
 * Height-weighted Quick Union
 */
public class UF_Alternate_1 implements UF {
    /**
     * Ensure that site p is connected to site q,
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     */
    public void connect(int p, int q) {
        if (!isConnected(p, q)) union(p, q);
    }

    /**
     * Initializes an empty union–find data structure with {@code n} sites
     * {@code 0} through {@code n-1}. Each site is initially in its own
     * component.
     *
     * @param n               the number of sites
   //  * @param pathCompression whether to use path compression
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public UF_Alternate_1(int n) {
        count = n;
        parent = new int[n];
        depth = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            depth[i] = 1;
        }
    }



    /**
     * Returns the number of components.
     *
     * @return the number of components (between {@code 1} and {@code n})
     */
    public int components() {
        return count;
    }

    /**
     * Returns the component identifier for the component containing site {@code p}.
     *
     * @param p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {

        validate(p);
        int root = p;
        // TO BE IMPLEMENTED
        while (root != this.parent[root])
            root = parent[root];

        return root;
    }

    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     * {@code false} otherwise
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site {@code p} with the
     * the component containing site {@code q}.
     *
     * @param p the integer representing one site
     * @param q the integer representing the other site
     * @throws IllegalArgumentException unless
     *                                  both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        // CONSIDER can we avoid doing find again?
        mergeComponents(find(p), find(q));
        count--;
    }

    @Override
    public int size() {
        return parent.length;
    }


    @Override
    public String toString() {
        return "UF_HWQUPC:" + "\n  count: " + count +
                "\n  parents: " + Arrays.toString(parent) +
                "\n  heights: " + Arrays.toString(depth);
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    private final int[] parent;   // parent[i] = parent of i
    private final int[] depth;   // depth[i] = depth of subtree rooted at i
    private int count;  // number of components

    private void mergeComponents(int i, int j) {
        int rootP = find(i);
        int rootQ = find(j);
        if (rootP == rootQ) return;

        if (depth[rootP] < depth[rootQ]) {
            parent[rootP] = rootQ;
        }
        else if(this.depth[rootP] == depth[rootQ]){
            parent[rootQ] = rootP;
            depth[rootP]++;
        }
        else {
            parent[rootQ] = rootP;
        }
    }


    public static int generatePairs(int N){
        UF_Alternate_1 uf_alternate_1 = new UF_Alternate_1(N);
        Random rand = new Random();
        int m = 0;
        while(uf_alternate_1.components() != 1) {
            int r1 = rand.nextInt(N);
            int r2 = rand.nextInt(N);
            uf_alternate_1.connect(r1,r2);
            m++;
        }
        return m;
    }

    public static void main(String args[]){


        Benchmark<Integer> benchmark_WQU_RUN = new Benchmark_Timer<>("WeightedUnionFindRun 10000", func -> {
            WQU_Size.generatePairs(10000);
        });
        System.out.format("Weighted QuickUnion by storing the size. To connect 10000 sites takes %.2f milliseconds\n",
                benchmark_WQU_RUN.run(0,100));

        Benchmark<Integer> benchmark_WQU_RUN1 = new Benchmark_Timer<>("WeightedUnionFindRun 10000", func -> {
            UF_Alternate_1.generatePairs(10000);
        });
        System.out.format("Alternate method weighted QuickUnion by storing the depth of the tree. TO connect 10000 sites takes %.2f milliseconds\n",
                benchmark_WQU_RUN1.run(0,100));


    }

}
