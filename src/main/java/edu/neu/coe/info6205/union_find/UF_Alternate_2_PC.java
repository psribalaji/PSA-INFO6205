package edu.neu.coe.info6205.union_find;

import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;

import java.util.*;

public class UF_Alternate_2_PC {


    public static void main(String args[]){
        Benchmark<Integer> benchmark_WQU_RUN = new Benchmark_Timer<>("WeightedUnionFindRun 100000", func -> {
            WQU_Size.generatePairs(100000);
        });
        System.out.printf("Weighted QuickUnion by storing the size. To connect 100000 sites it takes %.2f milliseconds\n",
                benchmark_WQU_RUN.run(0,200));

        Benchmark<Integer> benchmark_WQU_RUN1 = new Benchmark_Timer<>("WeightedUnionFindRun 100000", func -> {
            WQUPC.generatePairs(100000);
        });
        System.out.printf("Weighted quick union with double path compression. TO connect 100000 sites it takes %.2f milliseconds\n",
                benchmark_WQU_RUN1.run(0,200));

    }
}
