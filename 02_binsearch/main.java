import java.util.Scanner;

public class main {
    static Scanner s = new Scanner(System.in);

    public static int difference(int tab[], int n) {
        int a = 1;
        for (int i = 0; i < n - 1; i++) {
            if (tab[i] != tab[i + 1]) {
                a++;
            }
        }
        return a;
    }

    public static void main(String[] args) {
        int z = s.nextInt();
        for (int a = 0; a < z; a++) {
            int y = s.nextInt();
            int t[] = new int[y];
            for (int b = 0; b < y; b++) {
                t[b] = s.nextInt();
            }
            int p = s.nextInt();
            for (int x = 0; x < p; x++) {
                int q = s.nextInt();
                int w = s.nextInt();
                int o = 0;
                for (int searchKey = q; searchKey <= w; searchKey++) {
                    int l = 0;
                    int u = y - 1;
                    int c;
                    while (l <= u) {
                        c = (l + u) / 2;
                        if (t[c] == searchKey) {
                            o++;
                            int m = c - 1;
                            while (m != -1) {
                                if (t[m] == searchKey) {
                                    o++;
                                    m--;
                                } else {
                                    break;
                                }
                                ;
                            }
                            m = c + 1;
                            while (m != y) {
                                if (t[m] == searchKey) {
                                    o++;
                                    m++;
                                } else {
                                    break;
                                }
                                ;
                            }
                            break;
                        } else {
                            if (t[c] < searchKey)
                                l = c + 1;
                            else
                                u = c - 1;
                        }
                    }
                }
                System.out.println(o);
            }
            System.out.println(difference(t, y));
        }
    }
}