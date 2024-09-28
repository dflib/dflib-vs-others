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
cd dflib/
mvnd clean package
time java -Xms16g -Xmx16g -jar target/dflib-*.jar ../measurements.txt
```

```
cd dataframe-ec/
mvnd clean package
time java -Xms16g -Xmx16g -jar target/dataframe-ec-*.jar ../measurements.txt
```

## Profiling

https://github.com/async-profiler/async-profiler