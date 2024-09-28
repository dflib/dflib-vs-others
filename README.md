# ..

## Generate measurements file

```
git clone git@github.com:gunnarmorling/1brc.git
cd 1brc
./mvnw clean verify
./create_measurements.sh 100000000

mv measurements.txt /root/of/this/repo
```
