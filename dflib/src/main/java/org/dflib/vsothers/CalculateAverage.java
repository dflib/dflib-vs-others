package org.dflib.vsothers;

import org.apache.commons.csv.CSVFormat;
import org.dflib.DataFrame;
import org.dflib.csv.Csv;

import static org.dflib.Exp.$col;
import static org.dflib.Exp.$double;

public class CalculateAverage {

    public static void main(String[] args) {

        long t0 = System.currentTimeMillis();

        DataFrame measurements = Csv.loader()
                .header("Station", "Temperature")
                .doubleCol("Temperature")
                .format(CSVFormat.DEFAULT.builder().setDelimiter(';').build())
                .load(args[0]);

        long t1 = System.currentTimeMillis();

        DataFrame aggregated = measurements
                .group("Station")
                .cols("Min", "Mean", "Max", "Station")
                .agg(
                        $double("Temperature").min(),
                        $double("Temperature").avg(),
                        $double("Temperature").max(),
                        $col("Station").first()
                )
                .sort($col("Station").asc());

        long t2 = System.currentTimeMillis();

        aggregated.forEach(row ->
                System.out.printf(
                        "%s=%2.1f/%2.1f/%2.1f\n",
                        row.get("Station"),
                        row.get("Min"),
                        row.get("Mean"),
                        row.get("Max")));

        System.out.printf("load: %s, agg: %s", t1 - t0, t2 - t1);
    }
}
