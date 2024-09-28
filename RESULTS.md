## 2024-09-28

Setup: 
* MacBook Pro
* -Xmx8g -Xms8g
* 30 mln rows
* experimental feature: compact String column

Time:

* dflib: 			real	0m11.493s load: 9515, agg: 1802, iterate: 35
* dflib-exp:      real	0m10.570s load: 9095, agg: 1295, iterate: 34
* df-ec:			real	0m10.633s load: 8257, agg: 2183, iterate: 33