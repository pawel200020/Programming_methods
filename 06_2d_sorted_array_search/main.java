import java.util.Scanner;

class RecSerach {
    int wiersze;
    int kolumny;
    int table[][];

    RecSerach(int w, int k, int tab[][]) {
        wiersze = w;
        kolumny = k;
        table = tab;
    }

    int[] rec_binsearch(int x, int w, int L, int R) {
        int m;
        int wynik[] = new int[2];
        if (L > R) {
            wynik[0] = 0;
            wynik[1] = 0;
            return wynik;
        }
        m = (L + R) / 2;
        if (x == table[w][m]) {
            wynik[0] = m;
            wynik[1] = 1;
            return wynik;
        }
        if (x > table[w][m]) {
            return rec_binsearch(x, w, m + 1, R);
        } else {
            return rec_binsearch(x, w, L, m - 1);
        }

    }

    int recPrevious(int current, int w, int number) {
        if (current > 0) {
            if (table[w][current - 1] != number) {
                return current;
            } else {
                current--;
                return recPrevious(current, w, number);
            }
        } else {
            return current;
        }
    }

    int recNext(int current, int w, int number) {
        if (current < kolumny - 1) {
            if (table[w][current + 1] != number) {
                return current;
            } else {
                current++;
                return recNext(current, w, number);
            }
        } else {
            return current;
        }
    }

    void recursiveLoop(int w, int number) {
        int wynik[] = new int[2];
        wynik = rec_binsearch(number, w, 0, kolumny - 1);
        if (wynik[1] == 1) {
            int a = recPrevious(wynik[0], w, number);
            System.out.println("RekPier: " + number + " " + " w (" + w + "," + a + ")");
            return;
        }
        w++;
        if (w < wiersze) {
            recursiveLoop(w, number);
        } else {
            System.out.println("RekPier: nie ma " + number);
        }

    }

    void recursiveLoopLast(int w, int number, int currentWiersz, int currentKolumna, boolean isFound) {
        int wynik[] = new int[2];

        wynik = rec_binsearch(number, w, 0, kolumny - 1);
        if (wynik[1] == 1) {
            isFound = true;
            currentKolumna = recNext(wynik[0], w, number);
            currentWiersz = w;
        }
        w++;
        if (w < wiersze) {
            recursiveLoopLast(w, number, currentWiersz, currentKolumna, isFound);
        } else {
            if (isFound == false) {
                System.out.println("RekOst: nie ma " + number);
            } else {
                System.out.println("RekOst: " + number + " w " + "(" + currentWiersz + "," + currentKolumna + ")");
            }

        }

    }

    public void findFirst(int number) {
        recursiveLoop(0, number);
        return;
    }

    public void findLast(int number) {
        recursiveLoopLast(0, number, 0, 0, false);
    }

}

class IterSearch {
    int wiersze;
    int kolumny;
    int table[][];

    IterSearch(int w, int k, int tab[][]) {
        wiersze = w;
        kolumny = k;
        table = tab;
    }

    public void findFirst(int number) {
        for (int w = 0; w < wiersze; w++) {
            int low = 0;
            int upp = kolumny - 1;
            int curr;

            while (low <= upp) {
                curr = (low + upp) / 2;
                if (table[w][curr] == number) {
                    while (curr > 0) {
                        if (table[w][curr - 1] != number) {
                            break;
                        } else {
                            curr--;
                        }

                    }
                    System.out.println("IterPier: " + number + " w " + "(" + w + "," + curr + ")");
                    return;
                } else {
                    if (table[w][curr] < number)
                        low = curr + 1;
                    else
                        upp = curr - 1;
                }
            }

        }
        System.out.println("IterPier: nie ma " + number);
    }

    public void findLast(int number) {
        int currLastW = 0;
        int currLastK = 0;
        boolean isFound = false;
        for (int w = 0; w < wiersze; w++) {
            int low = 0;
            int upp = kolumny - 1;
            int curr;

            while (low <= upp) {
                curr = (low + upp) / 2;
                if (table[w][curr] == number) {
                    isFound = true;
                    while (curr < kolumny - 1) {
                        if (table[w][curr + 1] != number) {
                            break;
                        } else {
                            curr++;
                        }
                    }
                    currLastW = w;
                    currLastK = curr;
                    break;
                } else {
                    if (table[w][curr] < number)
                        low = curr + 1;
                    else
                        upp = curr - 1;
                }
            }

        }
        if (isFound == true) {
            System.out.println("IterOst: " + number + " w " + "(" + currLastW + "," + currLastK + ")");
        } else {
            System.out.println("IterOst: nie ma " + number);
        }
        // System.out.println("IterPier: nie ma "+number);
    }
}

public class main {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int dataNumber = in.nextInt();
        for (int data = 0; data < dataNumber; data++) {
            int wiersze = in.nextInt();
            int kolumny = in.nextInt();
            int[][] table = new int[wiersze][kolumny];
            for (int w = 0; w < wiersze; w++) {
                for (int k = 0; k < kolumny; k++) {
                    table[w][k] = in.nextInt();
                }
            }
            int numberToFind = in.nextInt();
            RecSerach rec = new RecSerach(wiersze, kolumny, table);
            rec.findFirst(numberToFind);
            rec.findLast(numberToFind);
            IterSearch find = new IterSearch(wiersze, kolumny, table);
            find.findFirst(numberToFind);
            find.findLast(numberToFind);
            System.out.println("---");

        }
    }
}
