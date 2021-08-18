# Sorting codon genom - Iterative QuickSort without a stack
## task introduction
An application which sorts DNA genoms when AAA is the smallest one and TTT is the biggest one. Sorting is proceeded in 3 steps. Firstly we sort by first letter of codon then second one and finally third one. Sorting algorithm which is used is an iterative quicksort without a stack. There are some extra assumptions:
<ol>
<li>App check if in string there are other letters than {A, T, C, G}. When there is different char we recive an error: “Wrong character in DNA sequence.”
</li>
<li> Begening of genom is singned by codon “ATG”, its end by one of three: "TAA", "TGA" lub "TAG"</li>
<li>Between start sequnece and end sequence there shouldn't be any more start or end sequnece. When there is an extra start / end sequence, there is an error: “More than one START/STOP codon.”</li>
<li>If there is not any START/STOP codon, there is an error: “Wrong DNA sequence.”</li>
<li>Between start/stop sequence quantity of amino acids should be multiplicity of 3. If not, there is an error:“Wrong DNA sequence.”.
</li>
</ol>

## input
Data is read form standard input (keyboard).

## input structure
<ol>
<li> quantity of data</li>
<li> string of genoms example (ATGAACAAATGA)</li>
</ol>

## output
Sorted genoms