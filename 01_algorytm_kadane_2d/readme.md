# Kadane_algorithm
## task introduction
For non empty 2D array of integer number a[0][0], … ,a[n-1][m-1] for 0 ≤ i ≤ j < n, 0 ≤ k ≤ l < m. We define it max subarray as a coherent fragment a[i..j][k..l] with the maximum non-negative sum of elements, calculated according to formula  s(i,j,k,l) = SUM{ a[x][y] } for  i ≤ x ≤ j and k ≤ y ≤ l. 

The task is to count this sum of an array which will be given in input

## input
Data is read form standard input (keyboard).

## input structure
<ol>
<li> number of sets data</li>
<li> row quantity </li>
<li> column quantity </li>
<li> data to array form first row to last row (negative numbers can be included) space separated</li>
</ol>

## output
indexes of the bigges sum subarray and it sum