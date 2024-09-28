package org.dflib.dataframeec;

import io.github.vmzakharov.ecdataframe.dataframe.DataFrame;
import io.github.vmzakharov.ecdataframe.dataset.CsvDataSet;
import io.github.vmzakharov.ecdataframe.dataset.CsvSchema;
import org.eclipse.collections.api.factory.Lists;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;

import static io.github.vmzakharov.ecdataframe.dataframe.AggregateFunction.*;
import static io.github.vmzakharov.ecdataframe.dsl.value.ValueType.FLOAT;
import static io.github.vmzakharov.ecdataframe.dsl.value.ValueType.STRING;

public class CalculateAverage {

    public static void main(String[] args) {

        long t0 = System.currentTimeMillis();
//        long m0 = measureMemoryConsumption();

        URI measurementFile = new File(args[0]).toURI();

        CsvSchema msSchema = new CsvSchema()
                .addColumn("Station", STRING)
                .addColumn("Temperature", FLOAT)
                .separator(';')
                .hasHeaderLine(false);

        CsvDataSet msDataSet = new CsvDataSet(Path.of(measurementFile), "measurements", msSchema);

        DataFrame measurements = msDataSet.loadAsDataFrame();

        long t1 = System.currentTimeMillis();
//        long m1 = measureMemoryConsumption();

        DataFrame aggregated = measurements
                .aggregateBy(
                        Lists.immutable.of(min("Temperature", "Min"), avg2d("Temperature", "Mean"), max("Temperature", "Max")),
                        Lists.immutable.of("Station"))
                .sortBy(Lists.immutable.of("Station"));

        long t2 = System.currentTimeMillis();
//        long m2 = measureMemoryConsumption();

        aggregated.forEach(row ->
                System.out.printf(
                        "%s=%2.1f/%2.1f/%2.1f\n",
                        row.getString("Station"),
                        row.getFloat("Min"),
                        row.getDouble("Mean"),
                        row.getFloat("Max")));

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
