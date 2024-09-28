# ..

## Generate measurements file

```
git clone git@github.com:gunnarmorling/1brc.git
cd 1brc
./mvnw clean verify
./create_measurements.sh 100000000

mv measurements.txt /root/of/this/repo
```

## Build and Run Tests

```
cd dflib-vs-others/
mvnd clean package
time java -Xms8g -Xmx8g -jar target/dflib-vs-others-*.jar ../measurements.txt
```

```
cd dflib-vs-others-experimental/
mvnd clean package
time java -Xms8g -Xmx8g -jar target/dflib-vs-others-experimental-*.jar ../measurements.txt
```

```
cd dataframe-ec/
mvnd clean package
time java -Xms8g -Xmx8g -jar target/dataframe-ec-*.jar ../measurements.txt
```

## Profiling

https://github.com/async-profiler/async-profiler

