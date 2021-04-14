import java.util.Scanner;

public class main {

    static void swap(int array[], int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    static void QuickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotValue = array[right];
        int border = left - 1;
        int i = left;
        while (i < right) {
            if (array[i] < pivotValue) {
                border++;
                if (border != i) {
                    int tmp = array[i];
                    array[i] = array[border];
                    array[border] = tmp;
                }
            }
            i++;
        }
        border++;
        if (border != right) {
            int tmp = array[right];
            array[right] = array[border];
            array[border] = tmp;
        }
        QuickSort(array, left, border - 1);
        QuickSort(array, border + 1, right);
    }

    static int sortfives(int[] array, int left, int right) {
        int j = 0;
        for (int i = left; i <= right; i += 5) {
            if (i + 4 >= right) {
                j++;
                QuickSort(array, i, right);
            } else {
                QuickSort(array, i, i + 4);
                j++;
            }
        }
        return j;
    }

    static int MoveMediansEnd(int array[], int left, int right, int length) {
        int i = left + 2;
        int j = 0;
        while (i < right) {
            array[length + j] = array[i];
            j++;
            i += 5;
        }
        if ((right + 1) % 5 != 0) {
            i -= 3;
            if (i + 1 == right) {
                array[length + j] = array[i + 1];
            } else if (i + 2 == right) {
                array[length + j] = array[i + 1];
            } else if (i + 3 == right) {
                array[length + j] = array[i + 2];
            } else if (i + 4 == right) {
                array[length + j] = array[i + 2];
            }
        }
        return j++;
    }

    static void MoveMediansStart(int array[], int left, int right) {
        int i = left + 2;
        int j = left;
        while (i < right) {
            array[j] = array[i];
            i += 5;
            j++;
        }
        if ((right + 1) % 5 != 0) {
            i -= 3;
            if (i + 1 == right) {
                array[j] = array[i + 1];
            } else if (i + 2 == right) {
                array[j] = array[i + 1];
            } else if (i + 3 == right) {
                array[j] = array[i + 2];
            } else if (i + 4 == right) {
                array[j] = array[i + 2];
            }
        }

    }

    static int SetDivision(int array[], int left, int right) {
        if (right - left <= 5) {
            QuickSort(array, left, right);
            return array[(right - left) / 2 + left];
        } else {
            int z = sortfives(array, left, right);
            MoveMediansStart(array, left, right);
            return SetDivision(array, left, z + left - 1);

        }
    }

    static int partition(int array[], int left, int right, int x) {
        int i = left;

        for (int j = left; j <= right; j++) {
            if (array[j] < x) {

                swap(array, i, j);
                i = i + 1;
            }

        }
        int tmp = i;
        for (int z = i; z <= right; z++) {
            if (array[z] == x) {
                swap(array, tmp, z);
                tmp++;
            }
        }
        return i + 1;
    }

    static int howManyElems(int[] array, int elem, int start, int length) {
        int out = 0;
        int tmp = start;
        while (array[tmp] == elem) {
            out++;
            tmp++;
            if (tmp >= length)
                break;
        }
        return out;
    }

    static int MagicFiveAlgorithm(int[] array, int k, int length, int left, int right) {
        if (right - left <= 5) {
            QuickSort(array, left, right);
            return array[k + left];
        }
        sortfives(array, left, right);
        int c = MoveMediansEnd(array, left, right, length);
        int m = SetDivision(array, length, length + c - 1);
        int dividor = partition(array, left, right, m);
        int dimension = dividor - left;
        if (k < dimension) {
            return MagicFiveAlgorithm(array, k, length, left, dividor - 1);
        } else {
            int q = howManyElems(array, m, dividor, length);
            if (k <= dimension + q - 1) {
                return m;
            } else {
                return MagicFiveAlgorithm(array, k - dimension - q, length, dividor + q, right);
            }
        }

    }

    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int howMany = in.nextInt();
        for (int how = 0; how < howMany; how++) {
            int tableLength = in.nextInt();
            int medianePlace = (tableLength + 4) / 5;
            int[] array = new int[tableLength + medianePlace];
            for (int i = 0; i < tableLength; i++) {
                array[i] = in.nextInt();
            }
            int questionLength = in.nextInt();
            for (int i = 0; i < questionLength; i++) {
                int question = in.nextInt();
                if (question > tableLength) {
                    System.out.println(question + " " + "brak");
                } else if (question <= 0) {
                    System.out.println(question + " " + "brak");
                } else {
                    int wynik;
                    wynik = MagicFiveAlgorithm(array, question - 1, tableLength, 0, tableLength - 1);
                    System.out.println(question + " " + (wynik));
                }
            }
        }
    }
}
