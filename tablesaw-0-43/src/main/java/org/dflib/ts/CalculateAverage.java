package org.dflib.ts;

import tech.tablesaw.api.ColumnType;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;

import static tech.tablesaw.aggregate.AggregateFunctions.max;
import static tech.tablesaw.aggregate.AggregateFunctions.mean;
import static tech.tablesaw.aggregate.AggregateFunctions.min;
import static tech.tablesaw.api.ColumnType.FLOAT;
import static tech.tablesaw.api.ColumnType.STRING;

public class CalculateAverage {

    public static void main(String[] args) throws Exception {

        long t0 = System.nanoTime();
//        long m0 = measureMemoryConsumption();

        URL measurementFile = new File(args[0]).toURI().toURL();

        CsvReadOptions options = CsvReadOptions
                .builder(measurementFile)
                .columnTypes(new ColumnType[]{STRING, FLOAT})
                .separator(';')
                .header(false)
                .build();

        Table measurements = Table.read().usingOptions(options);
        measurements.column(0).setName("Station");
        measurements.column(1).setName("Temperature");

        long t1 = System.nanoTime();
//        long m1 = measureMemoryConsumption();

        Table aggregated = measurements
                .summarize("Temperature", min, mean, max).by("Station")
                .sortOn("Station");

        long t2 = System.nanoTime();
//        long m2 = measureMemoryConsumption();

        aggregated.forEach(row ->
                System.out.printf(
                        "%s=%2.1f/%2.1f/%2.1f\n",
                        row.getString("Station"),
                        row.getDouble("Min [Temperature]"),
                        row.getDouble("Mean [Temperature]"),
                        row.getDouble("Max [Temperature]"))
        );

        long t3 = System.nanoTime();
        System.err.printf("load: %s, agg: %s, iterate: %s\n",
                (t1 - t0) / 1_000_000,
                (t2 - t1) / 1_000_000,
                (t3 - t2) / 1_000_000);
//        System.err.printf("memory: %s, agg: %s\n", m1 - m0, m2 - m1);
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
