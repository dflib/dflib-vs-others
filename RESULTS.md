
## 2025-01-01

### Setup 1 - 30 mln rows 
* MacBook Pro, M4 Pro, 48GB 
* -Xmx12g -Xms12g


Time      | All     | Load  | Agg_Sort  | Iterate
----------|---------|-------|-----------|--------
dflib-1-1 | 5.900s  | 5055  | 692       | 24
dflib-2-0 | 5.732s  | 4908  | 669       | 24 
df-ec-1-0 | 5.496s  | 4288  | 1047      | 23

### Setup 2 - 100 mln rows 
* MacBook Pro, M4 Pro, 48GB 
* -Xmx36g -Xms36g


Time      | All     | Load  | Agg_Sort  | Iterate
----------|---------|-------|-----------|--------
dflib-1-1 | 17.978s | 15593 | 2164      | 24
dflib-2-0 | 18.257s | 15904 | 2131      | 25 
df-ec-1-0 | 18.413s | 14689 | 3489      | 23


Memory*   | +Load           | +Agg_Sort
----------|-----------------|---------
dflib-1-1 | 1,191,421,936   | 200,128
dflib-2-0 | 1,191,423,296   | 184,952
df-ec-1-0 | 1,606,958,320   | 186,864

* With heaps >32G, Java can no longer use 32-bit pointers. In other words, large heaps require more memory per object.


## 2024-09-28

### Setup: 
* MacBook Pro, M1 Pro, 16GB
* -Xmx8g -Xms8g
* 30 mln rows
* experimental feature: compact String column

### Time:

DF| All     | Load | Agg_Sort  | Iterate
--|---------|------|-----------|--
dflib | 11.493s | 9515 | 1802      | 35
dflib-exp | 10.570s | 9095 | **1295**  | 34 
df-ec | 10.633s | 8257 | 2183      | 33

### Memory

DF| +Load           | +Agg_Sort
--|-----------------|--
dflib | 362,983,672     | 144,840
dflib-exp | **241,348,568** | 136,968
df-ec | 315,132,200     | 120,272
