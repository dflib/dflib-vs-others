## 2024-09-28

### Setup: 
* MacBook Pro
* -Xmx8g -Xms8g
* 30 mln rows
* experimental feature: compact String column

### Time:

DF| All     | Load | Aggregate | Iterate
--|---------|------|-----------|--
dflib | 11.493s | 9515 | 1802      | 35
dflib-exp | 10.570s | 9095 | **1295**  | 34 
df-ec | 10.633s | 8257 | 2183      | 33

### Memory

DF| +Load           | +Aggregate
--|-----------------|--
dflib | 362,983,672     | 144,840
dflib-exp | **241,348,568** | 136,968
df-ec | 315,132,200     | 120,272

### Notes:
* compact String column is needed for both memory and performance
* To reduce the memory footprint further, need a "float" primitive type support