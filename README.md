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
cd dflib-1-1/
mvnd clean package
time java -Xms8g -Xmx8g -jar target/dflib-1-1-*.jar ../measurements.txt
```

```
cd dflib-2-0/
mvnd clean package
time java -Xms8g -Xmx8g -jar target/dflib-2-0-*.jar ../measurements.txt
```

```
cd dataframe-ec-1-0/
mvnd clean package
time java -Xms8g -Xmx8g -jar target/dataframe-ec-1-0-*.jar ../measurements.txt
```

## Profiling

https://github.com/async-profiler/async-profiler

