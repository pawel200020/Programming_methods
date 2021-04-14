import java.util.Scanner;

class Node {
    int priority;
    public int howMany;
    char sign;
}

class NodePriority {
    public int priority;

    public char sign;
}

class NodeReturn {
    Node node;
    boolean isFound;
}

class NewQueuePriority {
    static Node[] HeapArray;
    static NodePriority[] PrioritiesArray;
    static int depth;
    static int elems;

    NewQueuePriority(NodePriority[] array1) {
        PrioritiesArray = array1;
        elems = 0;
        depth = 0;
        HeapArray = new Node[1];

    }

    private int exponent2(int pot) {
        int number = 1;
        for (int i = 0; i < pot; i++) {
            number = number * 2;
        }
        return number;
    }

    private void reallocHeap() {
        depth++;
        int newSize = HeapArray.length + exponent2(depth);
        Node[] HeapArray2 = new Node[newSize];
        for (int i = 0; i < HeapArray.length; i++) {
            HeapArray2[i] = HeapArray[i];
        }
        HeapArray = HeapArray2;
    }

    private int getPriority(char sign) {
        for (int i = 0; i < PrioritiesArray.length; i++) {
            if (sign == PrioritiesArray[i].sign) {
                return PrioritiesArray[i].priority;
            }
        }
        return 1;
    }

    private int heapSearch(char key) {
        for (int i = 0; i < elems; i++) {
            if (HeapArray[i].sign == key) {
                return i;
            }
        }
        return -1;
    }

    private char toLowerCase(char a) {
        char finish;
        if ((int) a >= 97 && (int) a <= 122) {
            finish = (char) ((int) a);
        } else {
            finish = (char) ((int) a + 32);
        }
        return finish;
    }

    private boolean upHeapCondition(Node upheaper, Node tmp) {
        if (upheaper.priority < tmp.priority) {
            return true;
        } else if (upheaper.priority > tmp.priority) {
            return false;
        } else {
            if ((int) upheaper.sign == (int) tmp.sign - 32) {
                return false;
            }
            if ((int) upheaper.sign == (int) tmp.sign + 32) {
                return true;
            } else {
                if ((int) toLowerCase(upheaper.sign) > (int) toLowerCase(tmp.sign)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    private void upHeap(int k) {
        int i = (k - 1) / 2;
        Node tmp = HeapArray[k];
        while (k > 0 && upHeapCondition(HeapArray[i], tmp)) {
            HeapArray[k] = HeapArray[i];
            k = i;
            i = (i - 1) / 2;
        }
        HeapArray[k] = tmp;
    }

    private NodeReturn downHeap() {
        NodeReturn nodetoret = new NodeReturn();
        if (elems == 0) {
            nodetoret.isFound = false;
            return nodetoret;
        }
        Node tmp = HeapArray[0];
        int curr = 0;
        while (true) {
            int i = curr;
            // System.out.print("[" + HeapArray[i].sign + " " + HeapArray[i].priority + " "
            // + HeapArray[i].howMany + "]" + "\n");
            int nastepnik = 2 * curr + 1;
            if (nastepnik >= elems) {
                if (elems - 1 != curr) {
                    HeapArray[curr] = HeapArray[elems - 1];
                    upHeap(curr);

                }
                elems--;
                nodetoret.node = tmp;
                nodetoret.isFound = true;

                return nodetoret;
            } else if (nastepnik + 1 >= elems) {
                HeapArray[curr] = HeapArray[nastepnik];
                elems--;
                nodetoret.node = tmp;
                nodetoret.isFound = true;
                return nodetoret;
            } else {
                if (!upHeapCondition(HeapArray[nastepnik], HeapArray[nastepnik + 1])) {
                    HeapArray[curr] = HeapArray[nastepnik];
                    i = nastepnik;
                    HeapArray[nastepnik] = HeapArray[nastepnik + 1];
                    curr = nastepnik;
                } else {
                    HeapArray[curr] = HeapArray[nastepnik + 1];
                    i = nastepnik + 1;
                    curr = nastepnik + 1;
                }
            }

        }
    }

    public void add(char key) {
        int position = heapSearch(key);
        if (position != -1) {
            HeapArray[position].howMany++;
            HeapArray[position].priority = HeapArray[position].howMany * getPriority(key);
            upHeap(position);
        } else {
            if (elems >= HeapArray.length) {
                reallocHeap();
                HeapArray[elems] = new Node();
                HeapArray[elems].sign = key;
                HeapArray[elems].howMany = 1;
                HeapArray[elems].priority = getPriority(key);
                upHeap(elems);
                elems++;

            } else {
                HeapArray[elems] = new Node();
                HeapArray[elems].sign = key;
                HeapArray[elems].howMany = 1;
                HeapArray[elems].priority = getPriority(key);
                upHeap(elems);
                elems++;

            }
        }
    }

    public void get() {

        NodeReturn nrt = new NodeReturn();
        nrt = downHeap();
        if (nrt.isFound == true) {
            displayNode(nrt.node);
        } else {
            System.out.println("- (-)");
        }
    }

    public void get2() {

        NodeReturn nrt = new NodeReturn();
        nrt = downHeap();
        if (nrt.isFound == true) {
            displayNode2(nrt.node);
        } else {
            System.out.println("- (-)");
        }
    }

    public void display() {
        for (int i = 0; i < elems; i++) {
            System.out.print(
                    "[" + HeapArray[i].sign + " " + HeapArray[i].priority + " " + HeapArray[i].howMany + "]" + " ");
        }
        System.out.println();
    }

    private void displayNode(Node node) {
        System.out.println(node.sign + " (" + node.priority + ")");
    }

    private void displayNode2(Node node) {
        System.out.print(node.sign + " (" + node.priority + ") ");
    }

    public void displayInEndOfTimes() {
        boolean isDone = false;
        while (elems != 0) {
            get2();
            isDone = true;
        }
        if (elems == 0 && isDone == false) {
            System.out.println("- (-)");
        }
    }
}

public class main {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        int howManyPriorities = in.nextInt();
        NodePriority[] priorityArray = new NodePriority[howManyPriorities];

        for (int i = 0; i < howManyPriorities; i++) {
            priorityArray[i] = new NodePriority();
            priorityArray[i].sign = in.next().charAt(0);
            priorityArray[i].priority = in.nextInt();
        }

        NewQueuePriority queue = new NewQueuePriority(priorityArray);
        int questions = in.nextInt();

        for (int i = 0; i < questions; i++) {
            String command = in.next();
            if (command.charAt(0) == 'a' && command.charAt(1) == 'd' && command.charAt(2) == 'd') {
                char adder = in.next().charAt(0);
                queue.add(adder);
            } else if (command.charAt(0) == 'g' && command.charAt(1) == 'e' && command.charAt(2) == 't') {
                queue.get();
            }

        }
        queue.displayInEndOfTimes();

    }
}
