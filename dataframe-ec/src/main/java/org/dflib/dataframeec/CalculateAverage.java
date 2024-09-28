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

        URI measurementFile = new File(args[0]).toURI();

        CsvSchema msSchema = new CsvSchema()
                .addColumn("Station", STRING)
                .addColumn("Temperature", FLOAT)
                .separator(';')
                .hasHeaderLine(false);

        CsvDataSet msDataSet = new CsvDataSet(Path.of(measurementFile), "measurements", msSchema);

        DataFrame measurements = msDataSet.loadAsDataFrame();

        long t1 = System.currentTimeMillis();

        DataFrame aggregated = measurements
                .aggregateBy(
                        Lists.immutable.of(min("Temperature", "Min"), avg2d("Temperature", "Mean"), max("Temperature", "Max")),
                        Lists.immutable.of("Station"))
                .sortBy(Lists.immutable.of("Station"));

        long t2 = System.currentTimeMillis();

        aggregated.forEach(row ->
                System.out.printf(
                        "%s=%2.1f/%2.1f/%2.1f\n",
                        row.getString("Station"),
                        row.getFloat("Min"),
                        row.getDouble("Mean"),
                        row.getFloat("Max")));

        System.out.printf("load: %s, agg: %s", t1 - t0, t2 - t1);
    }
}
