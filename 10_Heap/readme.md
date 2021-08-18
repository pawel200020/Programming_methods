# Heap - priority queue
## task introduction
An application implements priority queue with usage max - heap. Numbers are elements of queue which can be present many times. Queue has two capacities, N - max quantity of different elements, P - max quantity of all elements i queue. Priority is set by formula:
<ol>
<li> if the number of repetitions of element x in the queue is greater than the number of repetitions of element y, then x has higher priority</li>
<li> if the numbers x and y have the same number of occurrences in a queue, the higher the number takes precedence.</li>
</ol>

## input
Data is read form standard input (keyboard).

## input structure
<ol>
<li> data quantity</li>
<li> N, P</li>
<li> i k x_1 x_2 ... x_k, where letter i means insert and means quantity of items which will be inserted, x_1 ... x_k are data to insert</li>
<li> g k where g - means get max k - elements from array with the highest priority</li>
<li> after removing elements from the queue, the program should list the element with the highest priority and the actual number of repetitions removed</li>
<li>when x_i, occurring n times, is an element with the current highest priority and k > n, only n elements are removed</li>
<li>e.g. suppose the number 4 occurs 10 times and has the highest priority in the queue, then command g 3 would reduce the number 4 to 10 from 10 to 7 (the program prints 4 3). Command g 13 would remove all occurrences of the number 4 (the program will print 4 10), but without disturbing the others
numbers</li>
<li>In case of empty queue application should write 0 0 </li>
<li>s - means sort operation and end of application. Program writes elements of queue ascending by priority in O(1)</li>
</ol>

## output
sorted priority queue