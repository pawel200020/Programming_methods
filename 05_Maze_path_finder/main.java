import java.util.LinkedList;
import java.util.Scanner;

class RecursivePathfinder {
    int height, width;
    int maze[][];

    RecursivePathfinder(int w, int h, int m[][]) {
        height = h;
        width = w;
        maze = m;
    }

    boolean checkOverNotMaze(int x, int y) {
        if (x >= 0 && y >= 0 && y < width && x < height) {
            return true;
        }
        return false;
    }

    int convertYtoTableFit(int y) {
        return width - y - 1;
    }

    void recursiveLoopToFindPath(LinkedList<Character> stepStack, int xCurrent, int yCurrent, int xFinish, int yFinish,
            int[][] tabhelp) {
        // wpisz dane o polnocy do tabeli pomocniczej
        if (checkOverNotMaze(xCurrent, yCurrent - 1)) {
            if (maze[yCurrent - 1][xCurrent] == 1) {
                tabhelp[yCurrent - 1][xCurrent] = 1;
            }
        }
        // wpisz dane o wschodzie do tabeli pomocniczej
        if (checkOverNotMaze(xCurrent + 1, yCurrent)) {
            if (maze[yCurrent][xCurrent + 1] == 1) {
                tabhelp[yCurrent][xCurrent + 1] = 1;
            }
        }
        // wpisz dane o poludniu do tabeli pomocniczej
        if (checkOverNotMaze(xCurrent, yCurrent + 1)) {
            if (maze[yCurrent + 1][xCurrent] == 1) {
                tabhelp[yCurrent + 1][xCurrent] = 1;
            }
        }
        // wpisz dane o zachodzie do tabeli pomocniczej
        if (checkOverNotMaze(xCurrent - 1, yCurrent)) {
            if (maze[yCurrent][xCurrent - 1] == 1) {
                tabhelp[yCurrent][xCurrent - 1] = 1;
            }
        }

        // sprawdzam gdzie moge isc jesli stos nie jest pusty
        if (!stepStack.isEmpty()) {
            switch (stepStack.getFirst()) {
            case 'N': {
                // na polnoc
                if (checkOverNotMaze(xCurrent, yCurrent - 1)) {
                    if (tabhelp[yCurrent - 1][xCurrent] == 0) {
                        stepStack.addFirst('N');
                        yCurrent--;
                        break;
                    }
                }
                // na wschod
                if (checkOverNotMaze(xCurrent + 1, yCurrent)) {
                    if (tabhelp[yCurrent][xCurrent + 1] == 0) {
                        stepStack.addFirst('E');
                        xCurrent++;
                        break;
                    }
                }
                // na zachod
                if (checkOverNotMaze(xCurrent - 1, yCurrent)) {
                    if (tabhelp[yCurrent][xCurrent - 1] == 0) {
                        stepStack.addFirst('W');
                        xCurrent--;
                        break;
                    }
                }
                stepStack.removeFirst();
                tabhelp[yCurrent][xCurrent] = 1;
                yCurrent++;
                break;
            }
            case 'W': {
                // na polnoc
                if (checkOverNotMaze(xCurrent, yCurrent - 1)) {
                    if (tabhelp[yCurrent - 1][xCurrent] == 0) {
                        stepStack.addFirst('N');
                        yCurrent--;
                        break;
                    }
                }
                // na poludnie
                if (checkOverNotMaze(xCurrent, yCurrent + 1)) {
                    if (tabhelp[yCurrent + 1][xCurrent] == 0) {
                        stepStack.addFirst('S');
                        yCurrent++;
                        break;
                    }
                }
                // na zachod
                if (checkOverNotMaze(xCurrent - 1, yCurrent)) {
                    if (tabhelp[yCurrent][xCurrent - 1] == 0) {
                        stepStack.addFirst('W');
                        xCurrent--;
                        break;
                    }
                }

                stepStack.removeFirst();
                tabhelp[yCurrent][xCurrent] = 1;
                xCurrent++;
                break;
            }
            case 'S': {
                // na poludnie
                if (checkOverNotMaze(xCurrent, yCurrent + 1)) {
                    if (tabhelp[yCurrent + 1][xCurrent] == 0) {
                        stepStack.addFirst('S');
                        yCurrent++;
                        break;
                    }
                }
                // na wschod
                if (checkOverNotMaze(xCurrent + 1, yCurrent)) {
                    if (tabhelp[yCurrent][xCurrent + 1] == 0) {
                        stepStack.addFirst('E');
                        xCurrent++;
                        break;
                    }
                }
                // na zachod
                if (checkOverNotMaze(xCurrent - 1, yCurrent)) {
                    if (tabhelp[yCurrent][xCurrent - 1] == 0) {
                        stepStack.addFirst('W');
                        xCurrent--;
                        break;
                    }
                }
                stepStack.removeFirst();
                tabhelp[yCurrent][xCurrent] = 1;
                yCurrent--;
                break;

            }
            case 'E': {
                // na polnoc
                if (checkOverNotMaze(xCurrent, yCurrent - 1)) {
                    if (tabhelp[yCurrent - 1][xCurrent] == 0) {
                        stepStack.addFirst('N');
                        yCurrent--;
                        break;
                    }
                }
                // na wschod
                if (checkOverNotMaze(xCurrent + 1, yCurrent)) {
                    if (tabhelp[yCurrent][xCurrent + 1] == 0) {
                        stepStack.addFirst('E');
                        xCurrent++;
                        break;
                    }
                }
                // na poludnie
                if (checkOverNotMaze(xCurrent, yCurrent + 1)) {
                    if (tabhelp[yCurrent + 1][xCurrent] == 0) {
                        stepStack.addFirst('S');
                        yCurrent++;
                        break;
                    }
                }
                stepStack.removeFirst();
                tabhelp[yCurrent][xCurrent] = 1;
                xCurrent--;
                break;
            }
            }
        }
        // jesli stos jest pusty to moge isc wszedzie gdzie mam 0
        else {
            boolean isDone = false;
            // na polnoc
            if (checkOverNotMaze(xCurrent, yCurrent - 1)) {
                if (tabhelp[yCurrent - 1][xCurrent] == 0) {
                    stepStack.addFirst('N');
                    yCurrent--;
                    isDone = true;
                }
            }
            // na poludnie
            if (checkOverNotMaze(xCurrent, yCurrent + 1) && isDone == false) {
                if (tabhelp[yCurrent + 1][xCurrent] == 0) {
                    stepStack.addFirst('S');
                    yCurrent++;
                    isDone = true;
                }
            }
            // na wschod
            if (checkOverNotMaze(xCurrent + 1, yCurrent) && isDone == false) {
                if (tabhelp[yCurrent][xCurrent + 1] == 0) {
                    stepStack.addFirst('E');
                    xCurrent++;
                    isDone = true;
                }
            }
            // na zachod
            if (checkOverNotMaze(xCurrent - 1, yCurrent) && isDone == false) {
                if (tabhelp[yCurrent][xCurrent - 1] == 0) {
                    stepStack.addFirst('W');
                    xCurrent--;
                }
            }
        }
        // jesli nie da sie nigdzie indziej isc
        int q = 0;
        // sprawdzam polnoc
        if (checkOverNotMaze(xCurrent, yCurrent - 1)) {
            if (tabhelp[yCurrent - 1][xCurrent] == 1) {
                q++;
            }
        } else {
            q++;
        }
        // sprawdzam poludnie
        if (checkOverNotMaze(xCurrent, yCurrent + 1)) {
            if (tabhelp[yCurrent + 1][xCurrent] == 1) {
                q++;
            }
        } else {
            q++;
        }
        // sprawdzam wschod
        if (checkOverNotMaze(xCurrent + 1, yCurrent)) {
            if (tabhelp[yCurrent][xCurrent + 1] == 1) {
                q++;
            }
        } else {
            q++;
        }
        // sprawdzam zachod
        if (checkOverNotMaze(xCurrent - 1, yCurrent)) {
            if (tabhelp[yCurrent][xCurrent - 1] == 1) {
                q++;
            }
        } else {
            q++;
        }
        if (q == 4) {
            if (!(xCurrent == xFinish && yCurrent == convertYtoTableFit(yFinish))) {
                stepStack.addFirst('X');
                return;
            }

        }
        boolean isFinish = (xCurrent == xFinish && yCurrent == convertYtoTableFit(yFinish));
        if (isFinish == true) {
            return;
        } else {
            recursiveLoopToFindPath(stepStack, xCurrent, yCurrent, xFinish, yFinish, tabhelp);
        }

    }

    void findPath(LinkedList<Character> stepStack, int xBegin, int yBegin, int xFinish, int yFinish) {
        int tabhelp[][] = new int[width][height];
        int xCurrent = xBegin;
        int yCurrent = yBegin;
        boolean IsFinish = false;
        yCurrent = convertYtoTableFit(yCurrent);
        recursiveLoopToFindPath(stepStack, xCurrent, yCurrent, xFinish, yFinish, tabhelp);
    }
}

class IterativePathfinder {
    int height, width;
    int maze[][];

    IterativePathfinder(int w, int h, int m[][]) {
        height = h;
        width = w;
        maze = m;
    }

    boolean checkOverNotMaze(int x, int y) {
        if (x >= 0 && y >= 0 && y < width && x < height) {
            return true;
        }
        return false;
    }

    int convertYtoTableFit(int y) {
        return width - y - 1;
    }

    void findPath(LinkedList<Character> stepStack, int xBegin, int yBegin, int xFinish, int yFinish) {
        int tabhelp[][] = new int[width][height];
        int xCurrent = xBegin;
        int yCurrent = yBegin;
        int x = 0;
        boolean IsFinish = false;
        yCurrent = convertYtoTableFit(yCurrent);
        while (!IsFinish) {

            // wpisz dane o polnocy do tabeli pomocniczej
            if (checkOverNotMaze(xCurrent, yCurrent - 1)) {
                if (maze[yCurrent - 1][xCurrent] == 1) {
                    tabhelp[yCurrent - 1][xCurrent] = 1;
                }
            }
            // wpisz dane o wschodzie do tabeli pomocniczej
            if (checkOverNotMaze(xCurrent + 1, yCurrent)) {
                if (maze[yCurrent][xCurrent + 1] == 1) {
                    tabhelp[yCurrent][xCurrent + 1] = 1;
                }
            }
            // wpisz dane o poludniu do tabeli pomocniczej
            if (checkOverNotMaze(xCurrent, yCurrent + 1)) {
                // System.out.println(maze[yCurrent + 1][xCurrent]);
                if (maze[yCurrent + 1][xCurrent] == 1) {
                    tabhelp[yCurrent + 1][xCurrent] = 1;
                }
            }
            // wpisz dane o zachodzie do tabeli pomocniczej
            if (checkOverNotMaze(xCurrent - 1, yCurrent)) {
                // System.out.println(maze[yCurrent][xCurrent - 1]);
                if (maze[yCurrent][xCurrent - 1] == 1) {
                    tabhelp[yCurrent][xCurrent - 1] = 1;
                }
            }

            // sprawdzam gdzie moge isc jesli stos nie jest pusty
            if (!stepStack.isEmpty()) {
                switch (stepStack.getFirst()) {
                case 'N': {
                    // na polnoc
                    if (checkOverNotMaze(xCurrent, yCurrent - 1)) {
                        if (tabhelp[yCurrent - 1][xCurrent] == 0) {
                            stepStack.addFirst('N');
                            yCurrent--;
                            break;
                        }
                    }
                    // na wschod
                    if (checkOverNotMaze(xCurrent + 1, yCurrent)) {
                        if (tabhelp[yCurrent][xCurrent + 1] == 0) {
                            stepStack.addFirst('E');
                            xCurrent++;
                            break;
                        }
                    }
                    // na zachod
                    if (checkOverNotMaze(xCurrent - 1, yCurrent)) {
                        if (tabhelp[yCurrent][xCurrent - 1] == 0) {
                            stepStack.addFirst('W');
                            xCurrent--;
                            break;
                        }
                    }
                    stepStack.removeFirst();
                    tabhelp[yCurrent][xCurrent] = 1;
                    yCurrent++;
                    break;
                }
                case 'W': {
                    // na polnoc
                    if (checkOverNotMaze(xCurrent, yCurrent - 1)) {
                        if (tabhelp[yCurrent - 1][xCurrent] == 0) {
                            stepStack.addFirst('N');
                            yCurrent--;
                            break;
                        }
                    }
                    // na poludnie
                    if (checkOverNotMaze(xCurrent, yCurrent + 1)) {
                        if (tabhelp[yCurrent + 1][xCurrent] == 0) {
                            stepStack.addFirst('S');
                            yCurrent++;
                            break;
                        }
                    }
                    // na zachod
                    if (checkOverNotMaze(xCurrent - 1, yCurrent)) {
                        if (tabhelp[yCurrent][xCurrent - 1] == 0) {
                            stepStack.addFirst('W');
                            xCurrent--;
                            break;
                        }
                    }

                    stepStack.removeFirst();
                    tabhelp[yCurrent][xCurrent] = 1;
                    xCurrent++;
                    break;
                }
                case 'S': {
                    // na poludnie
                    if (checkOverNotMaze(xCurrent, yCurrent + 1)) {
                        if (tabhelp[yCurrent + 1][xCurrent] == 0) {
                            stepStack.addFirst('S');
                            yCurrent++;
                            break;
                        }
                    }
                    // na wschod
                    if (checkOverNotMaze(xCurrent + 1, yCurrent)) {
                        if (tabhelp[yCurrent][xCurrent + 1] == 0) {
                            stepStack.addFirst('E');
                            xCurrent++;
                            break;
                        }
                    }
                    // na zachod
                    if (checkOverNotMaze(xCurrent - 1, yCurrent)) {
                        if (tabhelp[yCurrent][xCurrent - 1] == 0) {
                            stepStack.addFirst('W');
                            xCurrent--;
                            break;
                        }
                    }
                    stepStack.removeFirst();
                    tabhelp[yCurrent][xCurrent] = 1;
                    yCurrent--;
                    break;

                }
                case 'E': {
                    // na polnoc
                    if (checkOverNotMaze(xCurrent, yCurrent - 1)) {
                        if (tabhelp[yCurrent - 1][xCurrent] == 0) {
                            stepStack.addFirst('N');
                            yCurrent--;
                            break;
                        }
                    }
                    // na wschod
                    if (checkOverNotMaze(xCurrent + 1, yCurrent)) {
                        if (tabhelp[yCurrent][xCurrent + 1] == 0) {
                            stepStack.addFirst('E');
                            xCurrent++;
                            break;
                        }
                    }
                    // na poludnie
                    if (checkOverNotMaze(xCurrent, yCurrent + 1)) {
                        if (tabhelp[yCurrent + 1][xCurrent] == 0) {
                            stepStack.addFirst('S');
                            yCurrent++;
                            break;
                        }
                    }
                    stepStack.removeFirst();
                    tabhelp[yCurrent][xCurrent] = 1;
                    xCurrent--;
                    break;
                }
                }
                // System.out.println(stepStack);
            }
            // jesli stos jest pusty to moge isc wszedzie gdzie mam 0
            else {
                boolean isDone = false;
                // na polnoc
                if (checkOverNotMaze(xCurrent, yCurrent - 1)) {
                    if (tabhelp[yCurrent - 1][xCurrent] == 0) {
                        stepStack.addFirst('N');
                        yCurrent--;
                        isDone = true;
                    }
                }
                // na poludnie
                if (checkOverNotMaze(xCurrent, yCurrent + 1) && isDone == false) {
                    if (tabhelp[yCurrent + 1][xCurrent] == 0) {
                        stepStack.addFirst('S');
                        yCurrent++;
                        isDone = true;
                    }
                }
                // na wschod
                if (checkOverNotMaze(xCurrent + 1, yCurrent) && isDone == false) {
                    if (tabhelp[yCurrent][xCurrent + 1] == 0) {
                        stepStack.addFirst('E');
                        xCurrent++;
                        isDone = true;
                    }
                }
                // na zachod
                if (checkOverNotMaze(xCurrent - 1, yCurrent) && isDone == false) {
                    if (tabhelp[yCurrent][xCurrent - 1] == 0) {
                        stepStack.addFirst('W');
                        xCurrent--;
                    }
                }
            }
            // jesli nie da sie nigdzie indziej isc
            int q = 0;
            // sprawdzam polnoc
            if (checkOverNotMaze(xCurrent, yCurrent - 1)) {
                if (tabhelp[yCurrent - 1][xCurrent] == 1) {
                    q++;
                }
            } else {
                q++;
            }
            // sprawdzam poludnie
            if (checkOverNotMaze(xCurrent, yCurrent + 1)) {
                if (tabhelp[yCurrent + 1][xCurrent] == 1) {
                    q++;
                }
            } else {
                q++;
            }
            // sprawdzam wschod
            if (checkOverNotMaze(xCurrent + 1, yCurrent)) {
                if (tabhelp[yCurrent][xCurrent + 1] == 1) {
                    q++;
                }
            } else {
                q++;
            }
            // sprawdzam zachod
            if (checkOverNotMaze(xCurrent - 1, yCurrent)) {
                if (tabhelp[yCurrent][xCurrent - 1] == 1) {
                    q++;
                }
            } else {
                q++;
            }
            if (q == 4) {
                if (!(xCurrent == xFinish && yCurrent == convertYtoTableFit(yFinish)))
                    stepStack.addFirst('X');
                break;

            }

            /*
             * for (int i = 0; i < height; i++) { for (int j = 0; j < width; j++) {
             * System.out.print(tabhelp[i][j] + " "); } System.out.println(""); }
             * System.out.println(xCurrent + " " + yCurrent); System.out.println("");
             */
            IsFinish = (xCurrent == xFinish && yCurrent == convertYtoTableFit(yFinish));
        }
    }
}

public class main {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        //////// wczytanie danych do tablicy//////////
        int n = in.nextInt(); // szerokosc
        int m = in.nextInt(); // wysokosc
        int[][] maze = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maze[i][j] = in.nextInt();

            }
        }
        //////// wczytanie liczby zapytan//////////
        int questions = in.nextInt();
        ////// wczytanie zapytan///////////////////
        for (int i = 0; i < questions; i++) {
            char chooseAlgorithm = in.next().charAt(0);
            int xBegin = in.nextInt();
            int yBegin = in.nextInt();
            int xFinish = in.nextInt();
            int yFinish = in.nextInt();
            ////////// utworzenie obiektu klasy /////////
            LinkedList<Character> stackToSteps = new LinkedList();
            if (chooseAlgorithm == 'i') {
                IterativePathfinder find = new IterativePathfinder(m, n, maze);
                find.findPath(stackToSteps, xBegin, yBegin, xFinish, yFinish);
            }
            if (chooseAlgorithm == 'r') {
                RecursivePathfinder findr = new RecursivePathfinder(m, n, maze);
                findr.findPath(stackToSteps, xBegin, yBegin, xFinish, yFinish);
            }

            System.out.print((char) chooseAlgorithm + " ");
            for (int j = stackToSteps.size(); j > 0; j--) {
                System.out.print(stackToSteps.get(j - 1) + " ");
            }
            System.out.println("");

        }

    }
}
