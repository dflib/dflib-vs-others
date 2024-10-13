package org.dflib.vsothers;

import org.apache.commons.csv.CSVFormat;
import org.dflib.DataFrame;
import org.dflib.csv.Csv;

import static org.dflib.Exp.*;

public class CalculateAverage {

    public static void main(String[] args) {

        long t0 = System.currentTimeMillis();
//        long m0 = measureMemoryConsumption();

        DataFrame measurements = Csv.loader()
                .header("Station", "Temperature")
                .floatCol("Temperature")
                .compactCol("Station")
                .format(CSVFormat.DEFAULT.builder().setDelimiter(';').build())
                .load(args[0]);

        long t1 = System.currentTimeMillis();
//        long m1 = measureMemoryConsumption();

        DataFrame aggregated = measurements
                .group("Station")
                .cols("Min", "Mean", "Max", "Station")
                .agg(
                        $float("Temperature").min(),
                        $float("Temperature").avg(),
                        $float("Temperature").max(),
                        $col("Station").first()
                )
                .sort($col("Station").asc());

        long t2 = System.currentTimeMillis();
//        long m2 = measureMemoryConsumption();

        aggregated.forEach(row ->
                System.out.printf(
                        "%s=%2.1f/%2.1f/%2.1f\n",
                        row.get("Station"),
                        row.get("Min"),
                        row.get("Mean"),
                        row.get("Max")));

        long t3 = System.currentTimeMillis();
        System.out.printf("load: %s, agg: %s, iterate: %s\n", t1 - t0, t2 - t1, t3 - t2);
//        System.out.printf("memory: %s, agg: %s\n", m1 - m0, m2 - m1);
    }

    private static long measureMemoryConsumption() {
        runGC();
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    private static void runGC() {
        System.gc();
        System.gc();
        System.gc();
    }
}
