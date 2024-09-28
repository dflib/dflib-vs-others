package org.dflib.vsothers;

import org.apache.commons.csv.CSVFormat;
import org.dflib.DataFrame;
import org.dflib.csv.Csv;

import static org.dflib.Exp.$col;
import static org.dflib.Exp.$double;

public class CalculateAverage {

    static private final String FILE = "measurements.txt";

    public static void main(String[] args) {

        DataFrame measurements = Csv.loader()
                .header("Station", "Temperature")
                .doubleCol("Temperature")
                .format(CSVFormat.DEFAULT.builder().setDelimiter(';').build())
                .load(FILE);


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

        aggregated.forEach(row ->
                System.out.printf(
                        "%s=%2.1f/%2.1f/%2.1f\n",
                        row.get("Station"),
                        row.get("Min"),
                        row.get("Mean"),
                        row.get("Max")));
    }
}
