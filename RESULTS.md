
## 2025-01-01

### Setup 1 - 30 mln rows 
* MacBook Pro, M4 Pro, 48GB 
* -Xmx12g -Xms12g


DF        | All     | Load  | Aggregate | Iterate
----------|---------|-------|-----------|--------
dflib-1-1 | 5.900s  | 5055  | 692       | 24
dflib-2-0 | 5.732s  | 4908  | 669       | 24 
df-ec-1-0 | 5.496s  | 4288  | 1047      | 23

### Setup 2 - 100 mln rows 
* MacBook Pro, M4 Pro, 48GB 
* -Xmx32g -Xms32g


DF        | All     | Load  | Aggregate | Iterate
----------|---------|-------|-----------|--------
dflib-1-1 | 18.990s | 16096 | 2672      | 25
dflib-2-0 | 19.006s | 16621 | 2163      | 25 
df-ec-1-0 | 17.897s | 14186 | 3475      | 24


## 2024-09-28

### Setup: 
* MacBook Pro, M1 Pro, 16GB
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
