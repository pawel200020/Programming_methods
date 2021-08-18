# Path Finder - Maze
## task introduction
An applicaction which find a path between two points in maze. App can do this Recursively or iteratively.
## input
Data is read form standard input (keyboard).

## input structure
<ol>
<li> m - width of maze</li>
<li> n - height of maze</li>
<li> m rows of n data 0 or 1 when 0 menans you can go through this place and 1 you can't</li>
<li> t - number of paths to find</li>
<li> t - rows of data:
<ul>
<li>char R or I when R means use recursive algorithm and I use iterative algorithm</li>
<li> two numbers which means coordinates of a start point X and Y</li>
<li> two numbers which means coordinates of an end point X and Y</li>
</ul>
</li>
</ol>

## output
String of letters {N - north, S - south, E - east, W - west} steps which you need to do to go to the end.