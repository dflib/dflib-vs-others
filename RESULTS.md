
## 2025-01-01

### Setup 1 - 30 mln rows 
* MacBook Pro, M4 Pro, 48GB 
* -Xmx12g -Xms12g


Time             | All     | Load  | Agg_Sort  | Iterate
-----------------|---------|-------|-----------|--------
dflib-1-1        | 5.900s  | 5055  | 692       | 24
dflib-2-0        | 5.732s  | 4908  | 669       | 24 
df-ec-1-0        | 5.496s  | 4288  | 1047      | 23
tablesaw-0-43 [1]| 10.015s | 3278  | 6485      | 19

### Setup 2 - 100 mln rows 
* MacBook Pro, M4 Pro, 48GB 
* -Xmx36g -Xms36g


Time             | All     | Load  | Agg_Sort  | Iterate
-----------------|---------|-------|-----------|--------
dflib-1-1        | 17.978s | 15593 | 2164      | 24
dflib-2-0        | 18.257s | 15904 | 2131      | 25 
df-ec-1-0        | 18.413s | 14689 | 3489      | 23
tablesaw-0-43 [1]| 31.584s | 10572 | 20539     | 19


Memory[2]     | +Load           | +Agg_Sort
--------------|-----------------|---------
dflib-1-1     | 1,191,421,936   | 200,128
dflib-2-0     | 1,191,423,296   | 184,952
df-ec-1-0     | 1,606,958,320   | 186,864
tablesaw-0-43 |   656,122,280   | 248,152


[1] Tablesaw does rounding differently (?), so DFLib and DataframeEC averages do not match Tablesaw exactly
[2] With heaps >32G, Java can no longer use 32-bit pointers. In other words, large heaps require more memory per object.
