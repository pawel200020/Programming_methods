import java.util.Scanner;

public class main {
    static Scanner in = new Scanner(System.in);

    static int mod(int a) {
        if (a < 0) {
            return -a;
        }
        return a;
    }

    static boolean goodChars(String check) {
        for (int i = 0; i < check.length(); i++) {
            if (check.charAt(i) == 'A' || check.charAt(i) == 'T' || check.charAt(i) == 'C' || check.charAt(i) == 'G') {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    static int isStartSequence(String check) {
        for (int i = 0; i < check.length() - 2; i++) {
            if (check.charAt(i) == 'A' && check.charAt(i + 1) == 'T' && check.charAt(i + 2) == 'G') {
                return i;
            }
        }
        return -1;
    }

    static int findEndSequence(int begin, String sequence) {
        int current = -1;
        for (int i = begin; i < sequence.length(); i += 3) {
            if (i + 3 > sequence.length()) {
                return current;
            }
            if (sequence.charAt(i) == 'T' && sequence.charAt(i + 1) == 'A' && sequence.charAt(i + 2) == 'A') {
                current = i;
            } else if (sequence.charAt(i) == 'T' && sequence.charAt(i + 1) == 'G' && sequence.charAt(i + 2) == 'A') {
                current = i;
            } else if (sequence.charAt(i) == 'T' && sequence.charAt(i + 1) == 'A' && sequence.charAt(i + 2) == 'G') {
                current = i;
            }
        }
        return current;
    }

    static boolean isMoreStartCodones(int begin, int end, String sequence) {
        for (int i = begin + 3; i < end; i += 3) {
            if (sequence.charAt(i) == 'A' && sequence.charAt(i + 1) == 'T' && sequence.charAt(i + 2) == 'G') {
                return true;
            }
        }
        return false;
    }

    static boolean isMoreEndCodones(String sequence, int begin) {
        int j = 0;
        for (int i = begin; i < sequence.length(); i += 3) {
            if (sequence.charAt(i) == 'T' && sequence.charAt(i + 1) == 'A' && sequence.charAt(i + 2) == 'A') {
                j++;
            } else if (sequence.charAt(i) == 'T' && sequence.charAt(i + 1) == 'G' && sequence.charAt(i + 2) == 'A') {
                j++;
            } else if (sequence.charAt(i) == 'T' && sequence.charAt(i + 1) == 'A' && sequence.charAt(i + 2) == 'G') {
                j++;
            }
            if (j > 1) {
                return true;
            }
        }
        return false;
    }

    static boolean passAllTests(String sequence, int startCodon, int endCodon) {
        if (goodChars(sequence) == false) {
            System.out.println("Wrong character in DNA sequence.");
            return true;
        }
        if (startCodon == -1) {
            System.out.println("Wrong DNA sequence.");
            return true;
        }
        endCodon = findEndSequence(startCodon, sequence);
        if (endCodon == -1) {
            System.out.println("Wrong DNA sequence.");
            return true;
        }
        if (startCodon + 3 == endCodon) {
            System.out.println("Wrong DNA sequence.");
            return true;
        }
        if (isMoreStartCodones(startCodon, endCodon, sequence)) {
            System.out.println("More than one START/STOP codon.");
            return true;
        }
        if (isMoreEndCodones(sequence, startCodon)) {
            System.out.println("More than one START/STOP codon.");
            return true;
        }
        return false;

    }

    static int[][] moveCodonsToTable(String sequence, int begin, int end, int[][] tableWithData) {
        int tabSize = (end - begin - 3) / 3;
        int[][] table = new int[tabSize][3];
        int q = isStartSequence(sequence) + 3;
        for (int i = 0; i < tabSize; i++) {
            if (sequence.charAt(q) == 'A') {
                tableWithData[0][0]++;
                if (sequence.charAt(q + 1) == 'A') {
                    tableWithData[1][0]++;
                }
                if (sequence.charAt(q + 1) == 'C') {
                    tableWithData[2][0]++;
                }
                if (sequence.charAt(q + 1) == 'G') {
                    tableWithData[3][0]++;
                }
                if (sequence.charAt(q + 1) == 'T') {
                    tableWithData[4][0]++;
                }
            }
            if (sequence.charAt(q) == 'C') {
                tableWithData[0][1]++;
                if (sequence.charAt(q + 1) == 'A') {
                    tableWithData[1][1]++;
                }
                if (sequence.charAt(q + 1) == 'C') {
                    tableWithData[2][1]++;
                }
                if (sequence.charAt(q + 1) == 'G') {
                    tableWithData[3][1]++;
                }
                if (sequence.charAt(q + 1) == 'T') {
                    tableWithData[4][1]++;
                }
            }
            if (sequence.charAt(q) == 'G') {
                tableWithData[0][2]++;
                if (sequence.charAt(q + 1) == 'A') {
                    tableWithData[1][2]++;
                }
                if (sequence.charAt(q + 1) == 'C') {
                    tableWithData[2][2]++;
                }
                if (sequence.charAt(q + 1) == 'G') {
                    tableWithData[3][2]++;
                }
                if (sequence.charAt(q + 1) == 'T') {
                    tableWithData[4][2]++;
                }
            }
            if (sequence.charAt(q) == 'T') {
                tableWithData[0][3]++;
                if (sequence.charAt(q + 1) == 'A') {
                    tableWithData[1][3]++;
                }
                if (sequence.charAt(q + 1) == 'C') {
                    tableWithData[2][3]++;
                }
                if (sequence.charAt(q + 1) == 'G') {
                    tableWithData[3][3]++;
                }
                if (sequence.charAt(q + 1) == 'T') {
                    tableWithData[4][3]++;
                }
            }
            for (int z = 0; z < 3; z++) {
                table[i][z] = sequence.charAt(q);
                q++;
            }
        }
        return table;
    }

    static void swap2(int[][] tab, int i, int j) {
        int[] tmp = tab[i];
        tab[i] = tab[j];
        tab[j] = tmp;
    }

    static int pivot(int[][] array, int currfinding, int left, int right) {
        long x = array[left][currfinding];
        int i = left;

        for (int j = left + 1; j <= right; j++) {
            if (array[j][currfinding] <= x) {
                i = i + 1;
                if (i != j)
                    swap2(array, i, j);
            }
        }

        if (i != left)
            swap2(array, i, left);
        return i;
    }

    static void QuickSort(int[][] array, int currfinding, int left, int right, int zakres) {
        int i = 0;
        while (left < right || i > 0) {
            if (left < right) {
                int q = pivot(array, currfinding, left, right);
                array[right][currfinding] = -array[right][currfinding];
                right = q - 1;
                i++;
            } else {
                right = left = right + 2;

                while (right < zakres)
                    if (array[right][currfinding] < 0) {
                        array[right][currfinding] = -array[right][currfinding];
                        break;
                    } else
                        right++;
                i--;
            }
        }
    }

    public static void main(String[] args) {
        int how = in.nextInt();
        for (int cases = 0; cases < how; cases++) {
            String sequence = in.next();
            sequence = sequence.toUpperCase();
            int startCodon = isStartSequence(sequence);
            int endCodon = 0;
            if (!passAllTests(sequence, startCodon, endCodon)) {
                endCodon = findEndSequence(startCodon, sequence);
                int[][] table;
                int[][] tableWithData = new int[5][4];
                table = moveCodonsToTable(sequence, startCodon, endCodon, tableWithData);
                int right = table.length - 1;
                // sortowanie po pierszych literach
                QuickSort(table, 0, 0, right, table.length);

                // sortowanie po drugich literach
                int start = 0;
                int end = 0;
                for (int i = 0; i < 4; i++) {
                    if (tableWithData[0][i] != 0) {
                        end += tableWithData[0][i] - 1;
                        QuickSort(table, 1, start, end, end + 1);
                        start = end + 1;
                        end = start;
                    }
                }
                start = 0;
                end = 0;
                // sortowanie po trzecich literach
                for (int k = 0; k < 4; k++) {
                    for (int w = 1; w < 5; w++) {

                        if (tableWithData[w][k] != 0) {
                            end += tableWithData[w][k] - 1;
                            QuickSort(table, 2, start, end, end + 1);
                            start = end + 1;
                            end = start;
                        }

                    }
                }
                for (int i = 0; i < table.length; i++) {
                    for (int j = 0; j < 3; j++) {
                        System.out.print((char) mod(table[i][j]));
                    }
                }
                System.out.println("");
            }
        }
    }
}