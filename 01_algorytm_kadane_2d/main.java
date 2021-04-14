import java.util.Scanner;

public class main {
    static int WIERSZE = 200;
    static int KOLUMNY = 200;
    static Scanner s = new Scanner(System.in);

    public static int mod(int a) {
        if (a >= 0)
            return a;
        else
            return -a;
    }

    public static void main(String[] args) {
        int tab[][] = new int[WIERSZE][KOLUMNY];
        int licznosc = s.nextInt();
        for (int Q = 0; Q < licznosc; Q++) {
            WIERSZE = s.nextInt();
            KOLUMNY = s.nextInt();
            int maxSum1 = 0;
            int finalLeft = 0;
            int finalRight = 0;
            int finalTop = 0;
            int finalBottom = 0;
            int left;
            int right;
            int temp[] = new int[WIERSZE];
            for (int w = 0; w < WIERSZE; w++) {
                for (int k = 0; k < KOLUMNY; k++) {
                    tab[w][k] = s.nextInt();
                }
            }
            for (left = 0; left < KOLUMNY; left++) {
                for (int j = 0; j < WIERSZE; j++) {
                    temp[j] = 0;
                }
                for (right = left; right < KOLUMNY; right++) {

                    for (int k = 0; k < WIERSZE; k++) {
                        temp[k] += tab[k][right];
                    }
                    ///////////////////////// ALGORYTM KADANE /////////////////////////////
                    int maxSum = 0;
                    int current = 0;
                    int b2 = 0;
                    int b1 = 0;
                    int e1 = 0;
                    int i;
                    for (i = 0; i < WIERSZE; i++) {
                        current = current + temp[i];
                        if (current <= 0) {
                            current = 0;
                            b2 = i + 1;
                        }
                        if (current == maxSum) {
                            if (i + 1 == WIERSZE) {
                                if (e1 - b1 > mod((i) - b2)) {
                                    e1 = i;
                                    b1 = b2;
                                }
                            } else if (e1 - b1 > mod((i - 1) - b2)) {
                                if (i + 1 == WIERSZE) {
                                    e1 = i - 1;
                                } else
                                    e1 = i;
                                b1 = b2;
                            }
                        }
                        if (current > maxSum) {
                            maxSum = current;
                            b1 = b2;
                            e1 = i;
                        }

                    }
                    if (maxSum > maxSum1) {
                        maxSum1 = maxSum;
                        finalTop = b1;
                        finalBottom = e1;
                        finalLeft = left;
                        finalRight = right;
                    }
                    if (maxSum == maxSum1) {
                        if (e1 - b1 + right - left < finalBottom - finalTop + finalRight - finalLeft) {
                            finalTop = b1;
                            finalBottom = e1;
                            finalLeft = left;
                            finalRight = right;
                        }
                        if (e1 - b1 + right - left == finalBottom - finalTop + finalRight - finalLeft) {
                            if (finalTop > b1) {
                                finalTop = b1;
                                finalBottom = e1;
                                finalLeft = left;
                                finalRight = right;
                            }
                            if (finalBottom > e1) {
                                finalTop = b1;
                                finalBottom = e1;
                                finalLeft = left;
                                finalRight = right;
                            }
                        }
                    }
                    //////////////////////////////////////////////////////////////
                }
            }
            int n = 0;
            int c = 0;
            if (maxSum1 == 0) {
                for (int a = 0; a < WIERSZE; a++)
                    for (int b = 0; b < KOLUMNY; b++) {
                        if (tab[a][b] < 0) {
                            n++;
                        }
                        if (tab[a][b] == 0 && c == 0) {
                            finalTop = a;
                            finalBottom = a;
                            finalLeft = b;
                            finalRight = b;
                            c++;
                        }

                    }
            }
            if (n == WIERSZE * KOLUMNY) {
                System.out.println("empty");
            } else {
                System.out.println("max_sum = " + maxSum1 + ", a" + "[" + finalTop + ".." + finalBottom + "]["
                        + finalLeft + ".." + finalRight + "]");
            }
        }
    }
}