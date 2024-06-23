package boj.gold.b14867;

import java.util.*;

public class Main {

    public static class Bottle {
        int a;
        int b;
        int count;

        public Bottle(int a, int b, int count) {
            this.a = a;
            this.b = b;
            this.count = count;
        }
    }

    static int A;
    static int B;
    static int targetA;
    static int targetB;
    static Set<String> visited;

    public static void main(String[] args) {
        init();
        System.out.println(BFS());
    }

    private static int BFS() {
        Queue<Bottle> queue = new LinkedList<>();
        queue.add(new Bottle(0, 0, 0));
        visited.add("0,0");

        while (!queue.isEmpty()) {
            Bottle bottle = queue.poll();

            if (bottle.a == targetA && bottle.b == targetB) {
                return bottle.count;
            }

            for (int i = 0; i < 6; i++) {
                int a = bottle.a;
                int b = bottle.b;

                switch (i) {
                    case 0: // Fill A
                        a = A;
                        break;
                    case 1: // Fill B
                        b = B;
                        break;
                    case 2: // Empty A
                        a = 0;
                        break;
                    case 3: // Empty B
                        b = 0;
                        break;
                    case 4: // Move A to B
                        if (a + b <= B) {
                            b += a;
                            a = 0;
                        } else {
                            a -= (B - b);
                            b = B;
                        }
                        break;
                    case 5: // Move B to A
                        if (a + b <= A) {
                            a += b;
                            b = 0;
                        } else {
                            b -= (A - a);
                            a = A;
                        }
                        break;
                }

                String state = a + "," + b;
                if (!visited.contains(state)) {
                    queue.add(new Bottle(a, b, bottle.count + 1));
                    visited.add(state);
                }
            }
        }
        return -1;
    }

    private static void init() {
        Scanner scanner = new Scanner(System.in);
        A = scanner.nextInt();
        B = scanner.nextInt();
        targetA = scanner.nextInt();
        targetB = scanner.nextInt();
        visited = new HashSet<>();
    }
}

